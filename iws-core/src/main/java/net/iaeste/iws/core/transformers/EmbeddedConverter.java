/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-core) - net.iaeste.iws.core.transformers.EmbeddedTransformer
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.core.transformers;

import net.iaeste.iws.api.dtos.Address;
import net.iaeste.iws.api.dtos.Country;
import net.iaeste.iws.api.dtos.Group;
import net.iaeste.iws.api.dtos.User;
import net.iaeste.iws.api.dtos.exchange.Employer;
import net.iaeste.iws.api.dtos.exchange.Offer;
import net.iaeste.iws.api.dtos.exchange.Student;
import net.iaeste.iws.api.enums.exchange.FieldOfStudy;
import net.iaeste.iws.api.enums.exchange.Specialization;
import net.iaeste.iws.api.enums.exchange.StudyLevel;
import net.iaeste.iws.api.util.DateTime;
import net.iaeste.iws.persistence.views.EmbeddedAddress;
import net.iaeste.iws.persistence.views.EmbeddedCountry;
import net.iaeste.iws.persistence.views.EmbeddedEmployer;
import net.iaeste.iws.persistence.views.EmbeddedGroup;
import net.iaeste.iws.persistence.views.EmbeddedOffer;
import net.iaeste.iws.persistence.views.EmbeddedStudent;
import net.iaeste.iws.persistence.views.EmbeddedUser;

/**
 * Transforms all Embedded View, which is used by the read-only queries. The
 * embedded classes are written, so they only contain the primary information,
 * i.e. no Id's (except our externalId) or foreign keys.
 *
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 */
public final class EmbeddedConverter {

    /**
     * Private Constructor, this is a utility class.
     */
    private EmbeddedConverter() {
    }

    public static User convert(final EmbeddedUser embedded) {
        final User result = new User();

        result.setUserId(embedded.getExternalId());
        result.setUsername(embedded.getUsername());
        result.setFirstname(embedded.getFirstname());
        result.setLastname(embedded.getLastname());
        result.setStatus(embedded.getStatus());

        return result;
    }

    public static Address convert(final EmbeddedAddress embedded) {
        final Address result = new Address();

        result.setStreet1(embedded.getStreet1());
        result.setStreet2(embedded.getStreet2());
        result.setZip(embedded.getZip());
        result.setCity(embedded.getCity());
        result.setState(embedded.getState());

        return result;
    }

    public static Country convert(final EmbeddedCountry embedded) {
        final Country result = new Country();

        result.setCountryCode(embedded.getCountryCode());
        result.setCountryName(embedded.getCountryName());
        result.setNationality(embedded.getNationality());
        result.setPhonecode(embedded.getPhonecode());
        result.setCurrency(embedded.getCurrency());
        result.setMembership(embedded.getMembership());
        result.setMemberSince(embedded.getMemberSince());

        return result;
    }

    public static Group convert(final EmbeddedGroup embedded) {
        final Group group = new Group();

        group.setGroupId(embedded.getExternalId());
        group.setGroupName(embedded.getGroupName());
        group.setGroupType(embedded.getGroupType());

        return group;
    }

    public static Employer convert(final EmbeddedEmployer embedded) {
        final Employer employer = new Employer();

        // First, read out the the common Employer fields
        employer.setEmployerId(embedded.getExternalId());
        employer.setName(embedded.getName());
        employer.setDepartment(embedded.getDepartment());
        employer.setBusiness(embedded.getBusiness());
        employer.setEmployeesCount(embedded.getNumberOfEmployees());
        employer.setWebsite(embedded.getWebsite());
        employer.setWorkingPlace(embedded.getWorkingPlace());
        employer.setCanteen(embedded.getCanteen());
        employer.setNearestAirport(embedded.getNearestAirport());
        employer.setNearestPublicTransport(embedded.getNearestPublicTransport());
        employer.setWeeklyHours(embedded.getWeeklyHours());
        employer.setDailyHours(embedded.getDailyHours());

        return employer;
    }

    public static Offer convert(final EmbeddedOffer embedded) {
        final Offer result = new Offer();

        result.setOfferId(embedded.getExternalId());
        result.setRefNo(embedded.getRefNo());
        result.setWorkDescription(embedded.getWorkDescription());
        result.setTypeOfWork(embedded.getTypeOfWork());
        result.setStudyLevels(CollectionTransformer.explodeEnumSet(StudyLevel.class, embedded.getStudyLevels()));
        result.setFieldOfStudies(CollectionTransformer.explodeEnumSet(FieldOfStudy.class, embedded.getFieldOfStudies()));
        result.setSpecializations(CollectionTransformer.explodeStringSet(embedded.getSpecializations()));
        result.setPreviousTrainingRequired(embedded.getPrevTrainingRequired());
        result.setOtherRequirements(embedded.getOtherRequirements());
        result.setLanguage1(embedded.getLanguage1());
        result.setLanguage1Level(embedded.getLanguage1Level());
        result.setLanguage1Operator(embedded.getLanguage1Operator());
        result.setLanguage2(embedded.getLanguage2());
        result.setLanguage2Level(embedded.getLanguage2Level());
        result.setLanguage2Operator(embedded.getLanguage2Operator());
        result.setLanguage3(embedded.getLanguage3());
        result.setLanguage3Level(embedded.getLanguage3Level());
        result.setMinimumWeeks(embedded.getMinimumWeeks());
        result.setMaximumWeeks(embedded.getMaximumWeeks());
        result.setPeriod1(CommonTransformer.transform(embedded.getFromDate(), embedded.getToDate()));
        result.setPeriod2(CommonTransformer.transform(embedded.getFromDate2(), embedded.getToDate2()));
        result.setUnavailable(CommonTransformer.transform(embedded.getUnavailableFrom(), embedded.getUnavailableTo()));
        result.setPayment(embedded.getPayment());
        result.setPaymentFrequency(embedded.getPaymentFrequency());
        result.setCurrency(embedded.getCurrency());
        result.setDeduction(embedded.getDeduction());
        result.setLivingCost(embedded.getLivingCost());
        result.setLivingCostFrequency(embedded.getLivingCostFrequency());
        result.setLodgingBy(embedded.getLodgingBy());
        result.setLodgingCost(embedded.getLodgingCost());
        result.setLodgingCostFrequency(embedded.getLodgingCostFrequency());
        result.setNominationDeadline(CommonTransformer.convert(embedded.getNominationDeadline()));
        result.setNumberOfHardCopies(embedded.getNumberOfHardCopies());
        result.setAdditionalInformation(embedded.getAdditionalInformation());
        result.setStatus(embedded.getStatus());
        result.setModified(new DateTime(embedded.getModified()));
        result.setCreated(new DateTime(embedded.getCreated()));

        return result;
    }

    public static Student convert(final EmbeddedStudent embedded) {
        final Student result = new Student();

        result.setStudyLevel(embedded.getStudyLevel());
        result.setFieldOfStudies(CollectionTransformer.explodeEnumSet(FieldOfStudy.class, embedded.getFieldOfStudies()));
        result.setSpecializations(CollectionTransformer.explodeEnumSet(Specialization.class, embedded.getSpecializations()));
        result.setAvailable(CommonTransformer.transform(embedded.getAvailableFrom(), embedded.getAvailableTo()));
        result.setLanguage1(embedded.getLanguage1());
        result.setLanguage1Level(embedded.getLanguage1Level());
        result.setLanguage2(embedded.getLanguage2());
        result.setLanguage2Level(embedded.getLanguage2Level());
        result.setLanguage3(embedded.getLanguage3());
        result.setLanguage3Level(embedded.getLanguage3Level());

        return result;
    }
}
