/*
 * =============================================================================
 * Copyright 1998-2015, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-leargas) - net.iaeste.iws.leargas.clients.Mapper
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.leargas.clients;

import net.iaeste.iws.leargas.Settings;
import net.iaeste.iws.leargas.persistence.OfferEntity;
import net.iaeste.iws.ws.Currency;
import net.iaeste.iws.ws.Date;
import net.iaeste.iws.ws.DateTime;
import net.iaeste.iws.ws.Language;
import net.iaeste.iws.ws.LanguageLevel;
import net.iaeste.iws.ws.LanguageOperator;
import net.iaeste.iws.ws.Offer;
import net.iaeste.iws.ws.OfferState;
import net.iaeste.iws.ws.PaymentFrequency;
import net.iaeste.iws.ws.StudyLevel;
import net.iaeste.iws.ws.TypeOfWork;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

/**
 * General Mapper Class, for handling mapping from IWS WebService Objects to
 * Leargas Objects.
 *
 * @author  Kim Jensen <kim@dawn.dk>
 * @version Leargas 1.0
 * @since   Java 1.8
 */
public final class Mapper {

    private final Settings settings;

    public Mapper(final Settings settings) {
        this.settings = settings;
    }

    public OfferEntity map(final Offer wsOffer) {
        OfferEntity entity = null;

        if (wsOffer != null) {
            entity = new OfferEntity();

            entity.setRefNo(wsOffer.getRefNo());
            entity.setDeadline(map(wsOffer.getNominationDeadline()));
            // TODO Figure out what this one is for!
            entity.setComment(null);
            entity.setEmployer(wsOffer.getEmployer().getName());
            entity.setAddress1(wsOffer.getEmployer().getAddress().getStreet1());
            entity.setAddress2(wsOffer.getEmployer().getAddress().getStreet2());
            entity.setPostbox(wsOffer.getEmployer().getAddress().getPobox());
            entity.setPostalcode(wsOffer.getEmployer().getAddress().getPostalCode());
            entity.setCity(wsOffer.getEmployer().getAddress().getCity());
            entity.setState(wsOffer.getEmployer().getAddress().getState());
            entity.setCountry(wsOffer.getEmployer().getAddress().getCountry().getCountryName());
            entity.setWebsite(wsOffer.getEmployer().getWebsite());
            entity.setWorkplace(wsOffer.getEmployer().getWorkingPlace());
            entity.setBusiness(wsOffer.getEmployer().getBusiness());
            // TODO Figure out what this one is for!
            entity.setResponsible(null);
            entity.setAirport(wsOffer.getEmployer().getNearestAirport());
            entity.setTransport(wsOffer.getEmployer().getNearestPublicTransport());
            entity.setEmployees(wsOffer.getEmployer().getEmployeesCount());
            entity.setHoursweekly(map(wsOffer.getWeeklyHours()));
            entity.setHoursdaily(map(wsOffer.getDailyHours()));
            entity.setCanteen(map(wsOffer.getEmployer().isCanteen()));
            entity.setFaculty(map(wsOffer.getFieldOfStudies()));
            entity.setSpecialization(map(wsOffer.getSpecializations()));
            entity.setTrainingrequired(map(wsOffer.isPreviousTrainingRequired()));
            entity.setOtherrequirements(wsOffer.getOtherRequirements());
            entity.setWorkkind(map(wsOffer.getTypeOfWork()));
            entity.setWeeksmin(map(wsOffer.getMinimumWeeks()));
            entity.setWeeksmax(map(wsOffer.getMaximumWeeks()));
            entity.setFrom(map(wsOffer.getPeriod1().getFromDate()));
            entity.setTo(map(wsOffer.getPeriod1().getToDate()));
            entity.setStudycompletedBeginning(map(map(wsOffer.getStudyLevels(), StudyLevel.B)));
            entity.setStudycompletedMiddle(map(map(wsOffer.getStudyLevels(), StudyLevel.M)));
            entity.setStudycompletedEnd(map(map(wsOffer.getStudyLevels(), StudyLevel.E)));
            entity.setWorktypeP(map(wsOffer.getTypeOfWork() == TypeOfWork.O));
            entity.setWorktypeR(map(wsOffer.getTypeOfWork() == TypeOfWork.R));
            entity.setWorktypeW(map(wsOffer.getTypeOfWork() == TypeOfWork.F));
            // WorkType N has been deprecated
            entity.setWorktypeN(null);
            entity.setLanguage1(map(wsOffer.getLanguage1()));
            entity.setLanguage1level(map(wsOffer.getLanguage1Level()));
            entity.setLanguage1or(map(wsOffer.getLanguage1Operator()));
            entity.setLanguage2(map(wsOffer.getLanguage2()));
            entity.setLanguage2level(map(wsOffer.getLanguage2Level()));
            entity.setLanguage2or(map(wsOffer.getLanguage2Operator()));
            entity.setLanguage3(map(wsOffer.getLanguage3()));
            entity.setLanguage3level(map(wsOffer.getLanguage3Level()));
            entity.setCurrency(map(wsOffer.getCurrency()));
            entity.setPayment(map(wsOffer.getPayment()));
            entity.setPaymentfrequency(map(wsOffer.getPaymentFrequency()));
            entity.setDeduction(wsOffer.getDeduction());
            entity.setLodging(wsOffer.getLodgingBy());
            entity.setLodgingcost(map(wsOffer.getLodgingCost()));
            entity.setLodgingcostfrequency(map(wsOffer.getLodgingCostFrequency()));
            entity.setLivingcost(map(wsOffer.getLivingCost()));
            entity.setLivingcostfrequency(map(wsOffer.getLivingCostFrequency()));
            entity.setNohardcopies(map(wsOffer.getNumberOfHardCopies()));
            entity.setStatus(map(wsOffer.getStatus()));
            entity.setPeriod2From(map(wsOffer.getPeriod2().getFromDate()));
            entity.setPeriod2To(map(wsOffer.getPeriod2().getToDate()));
            entity.setHolidaysFrom(map(wsOffer.getUnavailable().getFromDate()));
            entity.setHolidaysTo(map(wsOffer.getUnavailable().getToDate()));
            entity.setAdditionalInfo(wsOffer.getAdditionalInformation());
            entity.setShared(map(wsOffer.getShared()));
            entity.setLastModified(map(wsOffer.getModified()));
            entity.setNsFirstName(wsOffer.getNsFirstname());
            entity.setNsLastName(wsOffer.getNsLastname());
        }

        return entity;
    }

    private static boolean map(final List<StudyLevel> studyLevels, final StudyLevel expected) {
        boolean result = false;

        for (final StudyLevel studyLevel : studyLevels) {
            if (studyLevel == expected) {
                result = true;
            }
        }

        return result;
    }

    private String map(final Date date) {
        final DateFormat format = new SimpleDateFormat(settings.readDateFormatMapping());

        return format.format(date.getMidnight().toGregorianCalendar().getTime());
    }

    private String map(final DateTime dateTime) {
        final DateFormat format = new SimpleDateFormat(settings.readDateTimeFormatMapping());

        return format.format(dateTime.getTimestamp().toGregorianCalendar().getTime());
    }

    private static String map(final Number number) {
        return number.toString();
    }

    private String map(final Boolean bool) {
        return bool ? settings.readBooleanTrueMapping() : settings.readBooleanFalseMapping();
    }

    private static <T extends Serializable> String map(final Collection<T> collection) {
        return collection.toString();
    }

    private static String map(final TypeOfWork type) {
        return type.toString();
    }

    /**
     * The values used in this mapping, is taken from the IWS API.
     *
     * @param state OfferState to map over
     * @return API State description value
     */
    private static String map(final OfferState state) {
        final String value;

        switch (state) {
            case NEW:
                value = "New";
                break;
            case OPEN:
                value = "Open";
                break;
            case SHARED:
                value = "Shared";
                break;
            case APPLICATIONS:
                value = "Applications";
                break;
            case NOMINATIONS:
                value = "Nominations";
                break;
            case DELETED:
                value = "Deleted";
                break;
            case CLOSED:
                value = "Closed";
                break;
            case COMPLETED:
                value = "Completed";
                break;
            case AT_EMPLOYER:
                value = "At Employer";
                break;
            case ACCEPTED:
                value = "Application accepted";
                break;
            case EXPIRED:
                value = "Expired";
                break;
            case REJECTED:
                value = "Rejected";
                break;
            default:
                value = "Unknown";
        }

        return value;
    }

    private static String map(final Currency currency) {
        return currency.toString();
    }

    /**
     * The values used in this mapping, is taken from the IWS API.
     *
     * @param frequency PaymentFrequency to map over
     * @return API PaymentFrequency description value
     */
    private static String map(final PaymentFrequency frequency) {
        final String value;

        switch (frequency) {
            case DAILY:
                value = "Daily";
                break;
            case WEEKLY:
                value = "Weekly";
                break;
            case BYWEEKLY:
                value = "By Weekly";
                break;
            case MONTHLY:
                value = "Monthly";
                break;
            case YEARLY:
                value = "Yearly";
                break;
            default:
                value = "Unknown";
        }

        return value;
    }

    private static String map(final Language language) {
        // TODO The language is an enum value, so it is purely upper case with underscores between words. This can be mapped better!
        return language.toString();
    }

    private static String map(final LanguageLevel level) {
        final String value;

        switch (level) {
            case E:
                value = "Excellent";
                break;
            case G:
                value = "Good";
                break;
            case F:
                value = "Fair";
                break;
            default:
                value = "Unknown";
        }

        return value;
    }

    /**
     * The values used in this mapping, is taken from the IWS API.
     *
     * @param operator LanguageOperator to map over
     * @return API LanguageOperator description value
     */
    private static String map(final LanguageOperator operator) {
        final String value;

        switch (operator) {
            case A:
                value = "And";
                break;
            case O:
                value = "Or";
                break;
            default:
                value = "Unknown";
        }

        return value;
    }
}
