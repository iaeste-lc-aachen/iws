/*
 * =============================================================================
 * Copyright 1998-2014, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-core) - net.iaeste.iws.api.enums.exchange.OfferFields
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.enums.exchange;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.1
 */
public enum OfferFields {

    REFNO("Ref.No", true, true),
    OFFER_TYPE("OfferType", true, false),
    EXCHANGE_TYPE("ExchangeType", true, false),
    DEADLINE("Deadline",true, true),
    COMMENT("Comment", true, true),
    EMPLOYER("Employer", true, true),
    STREET_1("Street1", true, true),
    STREET_2("Street2", true, true),
    POST_BOX("PostBox", true, true),
    POSTAL_CODE("PostalCode", true, true),
    CITY("City", true, true),
    STATE("State", true, true),
    COUNTRY("Country", true, true),
    WEBSITE("Website", true, true),
    WORKPLACE("Workplace", true, true),
    BUSINESS("Business", true, true),
    RESPONSIBLE("Responsible", true, true),
    AIRPORT("Airport", true, true),
    TRANSPORT("Transport", true, true),
    EMPLOYEES("Employees", true, true),
    HOURS_WEEKLY("HoursWeekly", true, true),
    HOURS_DAILY("HoursDaily", true, true),
    CANTEEN("Canteen", true, true),
    FACULTY("Faculty", true, true),
    SPECIALIZATION("Specialization", true, true),
    TRAINING_REQUIRED("TrainingRequired", true, true),
    OTHER_REQUIREMENTS("OtherRequirements", true, true),
    WORKKIND("Workkind", true, true),
    WEEKS_MIN("WeeksMin", true, true),
    WEEKS_MAX("WeeksMax", true, true),
    FROM("From", true, true),
    TO("To", true, true),
    STUDY_COMPLETED_BEGINNING("StudyCompleted_Beginning", true, true),
    STUDY_COMPLETED_MIDDLE("StudyCompleted_Middle", true, true),
    STUDY_COMPLETED_END("StudyCompleted_End", true, true),
    WORK_TYPE_P("WorkType_P", true, true),
    WORK_TYPE_R("WorkType_R", true, true),
    WORK_TYPE_W("WorkType_W", true, true),
    WORK_TYPE_N("WorkType_N", true, true),
    LANGUAGE_1("Language1", true, true),
    LANGUAGE_1_LEVEL("Language1Level", true, true),
    LANGUAGE_1_OR("Language1Or", true, true),
    LANGUAGE_2("Language2", true, true),
    LANGUAGE_2_LEVEL("Language2Level", true, true),
    LANGUAGE_2_OR("Language2Or", true, true),
    LANGUAGE_3("Language3", true, true),
    LANGUAGE_3_LEVEL("Language3Level", true, true),
    CURRENCY("Currency", true, true),
    PAYMENT("Payment", true, true),
    PAYMENT_FREQUENCY("PaymentFrequency", true, true),
    DEDUCTION("Deduction", true, true),
    LODGING("Lodging", true, true),
    LODGING_COST("LodgingCost", true, true),
    LODGING_COST_FREQUENCY("LodgingCostFrequency", true, true),
    LIVING_COST("LivingCost", true, true),
    LIVING_COST_FREQUENCY("LivingCostFrequency", true, true),
    NO_HARD_COPIES("NoHardCopies", true, true),
    STATUS("Status", true, true),
    PERIOD_2_FROM("Period2_From", true, true),
    PERIOD_2_TO("Period2_To", true, true),
    HOLIDAYS_FROM("Holidays_From", true, true),
    HOLIDAYS_TO("Holidays_To", true, true),
    ADDITIONAL_INFO("Additional_Info", true, true),
    SHARED("Shared", true, true),
    LAST_MODIFIED("Last modified", false, true),
    NS_FIRST_NAME("NS First Name", false, true),
    NS_LAST_NAME("NS Last Name", false, true);

    // =========================================================================
    // Private Constructor & functionality
    // =========================================================================

    private final String field;
    private final boolean forDomesticCSVOffer;
    private final boolean forForeignCSVOffer;

    OfferFields(final String field, final boolean forDomesticCSVOffer, final boolean forForeignCSVOffer) {
        this.field = field;
        this.forDomesticCSVOffer = forDomesticCSVOffer;
        this.forForeignCSVOffer = forForeignCSVOffer;
    }

    public String getField() {
        return field;
    }

    public boolean isForDomesticCSVOffer() {
        return forDomesticCSVOffer;
    }

    public boolean isForForeignCSVOffer() {
        return forForeignCSVOffer;
    }
}
