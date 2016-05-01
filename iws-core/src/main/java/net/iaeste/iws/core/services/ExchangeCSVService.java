/*
 * Licensed to IAESTE A.s.b.l. (IAESTE) under one or more contributor
 * license agreements.  See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership. The Authors
 * (See the AUTHORS file distributed with this work) licenses this file to
 * You under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a
 * copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.iaeste.iws.core.services;

import static net.iaeste.iws.api.util.LogUtil.formatLogMessage;

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.constants.IWSErrors;
import net.iaeste.iws.api.dtos.Address;
import net.iaeste.iws.api.dtos.Country;
import net.iaeste.iws.api.dtos.exchange.CSVProcessingErrors;
import net.iaeste.iws.api.dtos.exchange.Employer;
import net.iaeste.iws.api.dtos.exchange.Offer;
import net.iaeste.iws.api.enums.exchange.OfferFields;
import net.iaeste.iws.api.enums.exchange.OfferState;
import net.iaeste.iws.api.exceptions.IWSException;
import net.iaeste.iws.api.requests.exchange.OfferCSVDownloadRequest;
import net.iaeste.iws.api.requests.exchange.OfferCSVUploadRequest;
import net.iaeste.iws.api.responses.exchange.OfferCSVDownloadResponse;
import net.iaeste.iws.api.responses.exchange.OfferCSVUploadResponse;
import net.iaeste.iws.api.util.Verifications;
import net.iaeste.iws.api.util.Page;
import net.iaeste.iws.common.configuration.Settings;
import net.iaeste.iws.core.exceptions.PermissionException;
import net.iaeste.iws.core.transformers.CommonTransformer;
import net.iaeste.iws.core.transformers.ExchangeTransformer;
import net.iaeste.iws.core.transformers.ViewTransformer;
import net.iaeste.iws.persistence.AccessDao;
import net.iaeste.iws.persistence.Authentication;
import net.iaeste.iws.persistence.ExchangeDao;
import net.iaeste.iws.persistence.ViewsDao;
import net.iaeste.iws.persistence.entities.GroupEntity;
import net.iaeste.iws.persistence.entities.exchange.EmployerEntity;
import net.iaeste.iws.persistence.entities.exchange.OfferEntity;
import net.iaeste.iws.persistence.views.AbstractView;
import net.iaeste.iws.persistence.views.OfferView;
import net.iaeste.iws.persistence.views.SharedOfferView;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.1
 */
public final class ExchangeCSVService extends CommonService<ExchangeDao> {

    private static final Logger LOG = LoggerFactory.getLogger(ExchangeCSVService.class);

    private static final OfferCSVUploadRequest.FieldDelimiter DELIMITER = OfferCSVUploadRequest.FieldDelimiter.COMMA;

    private final AccessDao accessDao;
    private final ViewsDao viewsDao;

    public ExchangeCSVService(final Settings settings, final ExchangeDao dao, final AccessDao accessDao, final ViewsDao viewsDao) {
        super(settings, dao);

        this.accessDao = accessDao;
        this.viewsDao = viewsDao;
    }

    public OfferCSVUploadResponse uploadOffers(final Authentication authentication, final OfferCSVUploadRequest request) {
        final OfferCSVUploadResponse response = new OfferCSVUploadResponse();
        final Map<String, OfferCSVUploadResponse.ProcessingResult> processingResult = new HashMap<>();
        final Map<String, CSVProcessingErrors> errors = new HashMap<>();

        final OfferCSVUploadRequest.FieldDelimiter delimiter = (request.getDelimiter() != null) ? request.getDelimiter() : DELIMITER;

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(request.getData());
             Reader reader = new InputStreamReader(byteArrayInputStream, IWSConstants.DEFAULT_ENCODING);
             CSVParser parser = getDefaultCsvParser(reader, delimiter.getDescription())) {
            final Map<String, Integer> headersMap = parser.getHeaderMap();
            final Set<String> headers = headersMap.keySet();
            final Set<String> expectedHeaders = new HashSet<>(createFirstRow(OfferFields.Type.UPLOAD));
            if (headers.containsAll(expectedHeaders)) {
                for (final CSVRecord record : parser.getRecords()) {
                    process(processingResult, errors, authentication, record);
                }
            } else {
                throw new IWSException(IWSErrors.PROCESSING_FAILURE, "Invalid CSV header");
            }
        } catch (IllegalArgumentException e) {
            throw new IWSException(IWSErrors.PROCESSING_FAILURE, "The header is invalid: " + e.getMessage() + '.', e);
        } catch (IOException e) {
            throw new IWSException(IWSErrors.PROCESSING_FAILURE, "CSV upload processing failed", e);
        }

        response.setProcessingResult(processingResult);
        response.setErrors(errors);
        return response;
    }

    public OfferCSVDownloadResponse downloadOffers(final Authentication authentication, final OfferCSVDownloadRequest request) {
        final OfferCSVDownloadResponse response;
        switch (request.getFetchType()) {
            case DOMESTIC:
                response = new OfferCSVDownloadResponse(findDomesticOffers(authentication, request));
                break;
            case SHARED:
                response = new OfferCSVDownloadResponse(findSharedOffers(authentication, request));
                break;
            default:
                throw new PermissionException("The search type is not permitted.");
        }

        return response;
    }

    private byte[] findDomesticOffers(final Authentication authentication, final OfferCSVDownloadRequest request) {
        final List<String> offerIds = request.getIdentifiers();
        final Page page = request.getPage();
        final Integer exchangeYear = request.getExchangeYear();

        final List<OfferView> found;
        if (offerIds.isEmpty()) {
            //paging could make a problem here if it returns only some offers
            final Set<OfferState> states = EnumSet.allOf(OfferState.class);
            states.remove(OfferState.DELETED);
            found = viewsDao.findDomesticOffers(authentication, exchangeYear, states, false, page);
        } else {
            found = viewsDao.findDomesticOffersByOfferIds(authentication, exchangeYear, offerIds);
        }

        byte[] result = null;
        if (!found.isEmpty()) {
            result = convertOffersToCsv(found, OfferFields.Type.DOMESTIC);
        }

        return result;
    }

    private byte[] findSharedOffers(final Authentication authentication, final OfferCSVDownloadRequest request) {
        final List<String> offerIds = request.getIdentifiers();
        final Page page = request.getPage();
        final Integer exchangeYear = request.getExchangeYear();
        final Set<OfferState> states = EnumSet.allOf(OfferState.class);
        states.remove(OfferState.DELETED);

        final List<SharedOfferView> found;
        if (offerIds.isEmpty()) {
            //paging could make a problem here if it returns only some offers
            found = viewsDao.findSharedOffers(authentication, exchangeYear, states, false, page);
        } else {
            found = viewsDao.findSharedOffersByOfferIds(authentication, exchangeYear, offerIds);
        }

        byte[] data = null;
        if (!found.isEmpty()) {
            data = convertOffersToCsv(found, OfferFields.Type.FOREIGN);
        }

        return data;
    }

    private static CSVParser getDefaultCsvParser(final Reader input, final char delimiter) {
        try {
            return CSVFormat.RFC4180
                    .withDelimiter(delimiter)
                    .withHeader()
                    .parse(input);
        } catch (IOException e) {
            throw new IWSException(IWSErrors.PROCESSING_FAILURE, "Creating CSVParser failed", e);
        }
    }

    private static CSVPrinter getDefaultCsvPrinter(final Appendable output) {
        try {
            return CSVFormat.RFC4180
                    .withDelimiter(DELIMITER.getDescription())
                    .withNullString("")
                    .print(output);
        } catch (IOException e) {
            throw new IWSException(IWSErrors.PROCESSING_FAILURE, "Creating CSVPrinter failed", e);
        }
    }

    private static <V extends AbstractView> byte[] convertOffersToCsv(final List<V> offers, final OfferFields.Type type) {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream();
             OutputStreamWriter streamWriter = new OutputStreamWriter(stream, IWSConstants.DEFAULT_ENCODING);
             BufferedWriter writer = new BufferedWriter(streamWriter);
             CSVPrinter printer = getDefaultCsvPrinter(writer)) {
            printer.printRecord(createFirstRow(type));

            for (final V offer : offers) {
                printer.printRecord(ViewTransformer.transformOfferToObjectList(offer, type));
            }

            writer.flush();
            streamWriter.flush();
            stream.flush();
            return stream.toByteArray();
        } catch (IOException e) {
            throw new IWSException(IWSErrors.PROCESSING_FAILURE, "Serialization to CSV failed", e);
        }
    }

    private static Offer extractOfferFromCSV(final Authentication authentication, final Map<String, String> errors, final CSVRecord record) {
        // Extract the Country from the Authentication Information
        final Country country = CommonTransformer.transform(authentication.getGroup().getCountry());

        // Read the Address from the CSV and assign the found Country to it
        final Address address = CommonTransformer.addressFromCsv(record, errors);
        address.setCountry(country);

        // Read the Employer from the CSV, and assign the transformed Address from it
        final Employer employer = ExchangeTransformer.employerFromCsv(record, errors);
        employer.setAddress(address);

        // Read the Offer from the CSV, and assign the transformed Employer to it
        final Offer offer = ExchangeTransformer.offerFromCsv(record, errors);
        offer.setEmployer(employer);

        // As all the Setters from the Offer has been invoked, all errors for
        // this Offer has already been caught. Invoking the validator will only
        // generate additional false error messages, since the validator will
        // apply null checks to those values that failed the Setter checks and
        // has not been set. Example, if the RefNo is wrong, then the Setter
        // will reject it but not set it, the Validator will see it as null and
        // set that as error as well, which is incorrect.
        return offer;
    }

    private void process(final Map<String, OfferCSVUploadResponse.ProcessingResult> processingResult, final Map<String, CSVProcessingErrors> errors, final Authentication authentication, final CSVRecord record) {
        final Map<String, String> conversionErrors = new HashMap<>(0);
        String refNo = "";

        try {
            refNo = record.get(OfferFields.REF_NO.getField());
            final Offer csvOffer = extractOfferFromCSV(authentication, conversionErrors, record);

            final CSVProcessingErrors validationErrors = new CSVProcessingErrors(conversionErrors);
            if (validationErrors.isEmpty()) {
                processingResult.put(refNo, processOffer(authentication, refNo, csvOffer));
            } else {
                LOG.warn(formatLogMessage(authentication, "CSV Offer with RefNo " + refNo + " has some Problems: " + conversionErrors));
                processingResult.put(refNo, OfferCSVUploadResponse.ProcessingResult.ERROR);
                errors.put(refNo, validationErrors);
            }
        } catch (IllegalArgumentException | IWSException e) {
            LOG.debug(e.getMessage(), e);
            LOG.warn(formatLogMessage(authentication, "CSV Offer with RefNo " + refNo + " has a Problem: " + e.getMessage()));
            processingResult.put(refNo, OfferCSVUploadResponse.ProcessingResult.ERROR);
            if (errors.containsKey(refNo)) {
                errors.get(refNo).put("general", e.getMessage());
            } else {
                final CSVProcessingErrors generalError = new CSVProcessingErrors();
                generalError.put("general", e.getMessage());
                if (!conversionErrors.isEmpty()) {
                    generalError.putAll(conversionErrors);
                }
                errors.put(refNo, generalError);
            }
        }
    }

    private OfferCSVUploadResponse.ProcessingResult processOffer (final Authentication authentication, final String refNo, final Offer csvOffer) {
        final OfferEntity existingEntity = dao.findOfferByRefNo(authentication, refNo);
        final OfferEntity newEntity = ExchangeTransformer.transform(csvOffer);
        final OfferCSVUploadResponse.ProcessingResult result;

        if (existingEntity != null) {
            permissionCheck(authentication, authentication.getGroup());

            //keep original offer state
            newEntity.setStatus(existingEntity.getStatus());

            csvOffer.getEmployer().setEmployerId(existingEntity.getEmployer().getExternalId());
            final EmployerEntity employerEntity = process(authentication, csvOffer.getEmployer());
            existingEntity.setEmployer(employerEntity);

            newEntity.setExternalId(existingEntity.getExternalId());
            dao.persist(authentication, existingEntity, newEntity);
            LOG.info(formatLogMessage(authentication, "CSV Update of Offer with RefNo '%s' completed.", newEntity.getRefNo()));
            result = OfferCSVUploadResponse.ProcessingResult.UPDATED;
        } else {
            // First, we need an Employer for our new Offer. The Process
            // method will either find an existing Employer or create a
            // new one.
            final EmployerEntity employer = process(authentication, csvOffer.getEmployer());

            // Add the Group to the Offer, otherwise our ref.no checks will fail
            employer.setGroup(authentication.getGroup());

            newEntity.setEmployer(employer);

            ExchangeService.verifyRefnoValidity(newEntity);

            newEntity.setExchangeYear(Verifications.calculateExchangeYear());
            // Add the employer to the Offer
            newEntity.setEmployer(employer);
            // Set the Offer status to New
            newEntity.setStatus(OfferState.NEW);

            // Persist the Offer with history
            dao.persist(authentication, newEntity);
            LOG.info(formatLogMessage(authentication, "CSV Import of Offer with RefNo '%s' completed.", newEntity.getRefNo()));
            result = OfferCSVUploadResponse.ProcessingResult.ADDED;
        }

        return result;
    }

    /**
     * Processes an Employer from the CSV file. This is done by first trying to
     * lookup the Employer via the unique characteristics for an Employer - and
     * only of no existing records is found, will a new record be created. If
     * a record is found, the changes will be merged and potentially also
     * persisted.<br />
     *   If more than one Employer is found, then an Identification Exception is
     * thrown.
     *
     * @param authentication The users Authentication information
     * @param employer       The Employer to find / create
     * @return Employer Entity found or created
     */
    private EmployerEntity process(final Authentication authentication, final Employer employer) {
        // If the Employer provided is having an Id set - then we need to update
        // the existing record, otherwise we will try to see if we can find a
        // similar Employer and update it. If we can neither find an Employer by
        // the Id, not the unique information - then we will create a new one.
        EmployerEntity entity;
        if (employer.getEmployerId() != null) {
            // Id exists, so we simply find the Employer based on that
            entity = dao.findEmployer(authentication, employer.getEmployerId());
            LOG.debug(formatLogMessage(authentication, "Employer lookup for Id '%s' gave '%s'.", employer.getEmployerId(), entity.getName()));
        } else {
            // No Id was set, so we're trying to find the Employer based on the
            // Unique information
            entity = dao.findUniqueEmployer(authentication, employer);
            LOG.debug(formatLogMessage(authentication, "Unique Employer for name '%s' gave '%s'.", employer.getName(), (entity != null) ? entity.getName() : "null"));
        }

        if (entity == null) {
            entity = ExchangeTransformer.transform(employer);
            final GroupEntity nationalGroup = accessDao.findNationalGroup(authentication.getUser());
            entity.setGroup(nationalGroup);
            processAddress(authentication, entity.getAddress());
            dao.persist(authentication, entity);
            LOG.info(formatLogMessage(authentication, "Have added the Employer '%s' for '%s'.", employer.getName(), authentication.getGroup().getGroupName()));
        } else {
            final EmployerEntity updated = ExchangeTransformer.transform(employer);
            processAddress(authentication, entity.getAddress(), employer.getAddress());
            dao.persist(authentication, entity, updated);
            LOG.info(formatLogMessage(authentication, "Have updated the Employer '%s' for '%s'.", employer.getName(), authentication.getGroup().getGroupName()));
        }

        return entity;
    }

    private static List<String> createFirstRow(final OfferFields.Type type) {
        final List<String> result = new ArrayList<>();

        addField(result, OfferFields.REF_NO, type);
        addField(result, OfferFields.OFFER_TYPE, type);
        addField(result, OfferFields.EXCHANGE_TYPE, type);
        addField(result, OfferFields.DEADLINE, type);
        addField(result, OfferFields.COMMENT, type);
        addField(result, OfferFields.EMPLOYER, type);
        addField(result, OfferFields.DEPARTMENT, type);
        addField(result, OfferFields.STREET1, type);
        addField(result, OfferFields.STREET2, type);
        addField(result, OfferFields.POSTBOX, type);
        addField(result, OfferFields.POSTAL_CODE, type);
        addField(result, OfferFields.CITY, type);
        addField(result, OfferFields.STATE, type);
        addField(result, OfferFields.COUNTRY, type);
        addField(result, OfferFields.WEBSITE, type);
        addField(result, OfferFields.WORKPLACE, type);
        addField(result, OfferFields.BUSINESS, type);
        addField(result, OfferFields.RESPONSIBLE, type);
        addField(result, OfferFields.AIRPORT, type);
        addField(result, OfferFields.TRANSPORT, type);
        addField(result, OfferFields.EMPLOYEES, type);
        addField(result, OfferFields.HOURS_WEEKLY, type);
        addField(result, OfferFields.HOURS_DAILY, type);
        addField(result, OfferFields.CANTEEN, type);
        addField(result, OfferFields.FACULTY, type);
        addField(result, OfferFields.SPECIALIZATION, type);
        addField(result, OfferFields.TRAINING_REQUIRED, type);
        addField(result, OfferFields.OTHER_REQUIREMENTS, type);
        addField(result, OfferFields.WORK_KIND, type);
        addField(result, OfferFields.WEEKS_MIN, type);
        addField(result, OfferFields.WEEKS_MAX, type);
        addField(result, OfferFields.FROM, type);
        addField(result, OfferFields.TO, type);
        addField(result, OfferFields.STUDY_COMPLETED_BEGINNING, type);
        addField(result, OfferFields.STUDY_COMPLETED_MIDDLE, type);
        addField(result, OfferFields.STUDY_COMPLETED_END, type);
        addField(result, OfferFields.WORK_TYPE_P, type);
        addField(result, OfferFields.WORK_TYPE_R, type);
        addField(result, OfferFields.WORK_TYPE_W, type);
        addField(result, OfferFields.WORK_TYPE_N, type);
        addField(result, OfferFields.LANGUAGE_1, type);
        addField(result, OfferFields.LANGUAGE_1_LEVEL, type);
        addField(result, OfferFields.LANGUAGE_1_OR, type);
        addField(result, OfferFields.LANGUAGE_2, type);
        addField(result, OfferFields.LANGUAGE_2_LEVEL, type);
        addField(result, OfferFields.LANGUAGE_2_OR, type);
        addField(result, OfferFields.LANGUAGE_3, type);
        addField(result, OfferFields.LANGUAGE_3_LEVEL, type);
        addField(result, OfferFields.CURRENCY, type);
        addField(result, OfferFields.PAYMENT, type);
        addField(result, OfferFields.PAYMENT_FREQUENCY, type);
        addField(result, OfferFields.DEDUCTION, type);
        addField(result, OfferFields.LODGING, type);
        addField(result, OfferFields.LODGING_COST, type);
        addField(result, OfferFields.LODGING_COST_FREQUENCY, type);
        addField(result, OfferFields.LIVING_COST, type);
        addField(result, OfferFields.LIVING_COST_FREQUENCY, type);
        addField(result, OfferFields.NO_HARD_COPIES, type);
        addField(result, OfferFields.STATUS, type);
        addField(result, OfferFields.PERIOD_2_FROM, type);
        addField(result, OfferFields.PERIOD_2_TO, type);
        addField(result, OfferFields.HOLIDAYS_FROM, type);
        addField(result, OfferFields.HOLIDAYS_TO, type);
        addField(result, OfferFields.ADDITIONAL_INFO, type);
        addField(result, OfferFields.SHARED, type);
        addField(result, OfferFields.LAST_MODIFIED, type);
        addField(result, OfferFields.NS_FIRST_NAME, type);
        addField(result, OfferFields.NS_LAST_NAME, type);

        return result;
    }

    private static void addField(final List<String> row, final OfferFields field, final OfferFields.Type type) {
        if (field.useField(type)) {
            row.add(field.getField());
        }
    }
}
