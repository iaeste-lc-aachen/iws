/*
 * =============================================================================
 * Copyright 1998-2012, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.data.Offer
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.data;

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.enums.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Michael Pickelbauer / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since 1.7
 */
public class Offer implements Serializable {

    /** {@link net.iaeste.iws.api.constants.IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;

    /**
     * Empty Constructor, required for some communication frameworks.
     */
    public Offer() {
        this.refNo = null;
        this.nominationDeadline = null;
        this.employerName = null;
        //this.studyLevels is already initialized
        this.gender = null;
        this.language1 = null;
        this.language1Level = null;
        this.workDescription = null;
        this.maximumWeeks = null;
        this.minimumWeeks = null;
        this.weeklyHours = null;
        this.dailyHours = null;
    }

    /**
     * Default Constructor.
     *
     * @param refNo                 Reference Number
     * @param nominationDeadline    nomination deadline
     * @param employerName          employer name
     * @param studyLevels           list of study levels
     * @param gender                Gender
     * @param language1             Language 1
     * @param language1Level        Language 1 level
     * @param workDescription       work description
     * @param minimumWeeks          minimum weeks
     * @param maximumWeeks          maximum weeks
     * @param weeklyHours           weekly hours
     * @param dailyHours            daily hours
     */
    public Offer(final String refNo,
                 final Date nominationDeadline,
                 final String employerName,
                 final List<StudyLevel> studyLevels,
                 final Gender gender,
                 final Language language1,
                 final LanguageLevel language1Level,
                 final String workDescription,
                 final Integer minimumWeeks,
                 final Integer maximumWeeks,
                 final Float weeklyHours,
                 final Float dailyHours) {

        //set required fields
        setRefNo(refNo);
        setNominationDeadline(nominationDeadline);
        setEmployerName(employerName);
        this.studyLevels.addAll(studyLevels);
        setGender(gender);
        setLanguage1(language1);
        setLanguage1Level(language1Level);
        setWorkDescription(workDescription);
        setMaximumWeeks(maximumWeeks);
        setMinimumWeeks(minimumWeeks);
        setWeeklyHours(weeklyHours);
        setDailyHours(dailyHours);
    }

    private Long id;
    private String refNo;
    private Date nominationDeadline;

    // Employer information
    private String employerName;
    private String employerAddress;
    private String employerAddress2;
    private String employerBusiness;
    private Integer employerEmployeesCount;
    private String employerWebsite;

    //Student Information
    private List<FieldOfStudy> fieldOfStudies;
    /**
     * Has to be defined as a List of Strings because
     * the user should be able to add custom
     * specializations in addition to the predefined ones.
     */
    private List<String> specializations;
    private List<StudyLevel> studyLevels = new ArrayList<>();
    private Boolean prevTrainingRequired;
    private String otherRequirements;
    private Gender gender;
    private Language language1;
    private LanguageLevel language1Level;
    private LanguageOperator language1Operator;
    private Language language2;
    private LanguageLevel language2Level;
    private LanguageOperator language2Operator;
    private Language language3;
    private LanguageLevel language3Level;
    
    // Work offered
    private String workDescription;
    private TypeOfWork typeOfWork;
    private Integer minimumWeeks;
    private Integer maximumWeeks;
    private Date fromDate;
    private Date toDate;
    private Date fromDate2;
    private Date toDate2;
    private Date holidaysFrom;
    private Date holidaysTo;
    private String workingPlace;
    private String nearestAirport;
    private String nearestPubTransport;
    private Float weeklyHours;
    private Float dailyHours;
    /**
     * need big numbers, e.g. 1 EUR = 26.435,00 VND
     */
    private BigDecimal payment;
    private Currency currency;
    private PaymentFrequency paymentFrequency;
    private Integer deduction;
    
    // Accommodation
    private String lodgingBy;
    private BigDecimal lodgingCost;
    private PaymentFrequency lodgingPaymentFrequency;
    private BigDecimal livingCost;
    private int livingPaymentFrequency;
    private Boolean canteen;

    
    public Boolean getCanteen() {
        return canteen;
    }

    public void setCanteen(Boolean canteen) {
        this.canteen = canteen;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Float getDailyHours() {
        return dailyHours;
    }

    public void setDailyHours(Float dailyHours) {
        this.dailyHours = dailyHours;
    }

    public Integer getDeduction() {
        return deduction;
    }

    public void setDeduction(Integer deduction) {
        this.deduction = deduction;
    }

    public String getEmployerAddress2() {
        return employerAddress2;
    }

    public void setEmployerAddress2(String employerAddress2) {
        this.employerAddress2 = employerAddress2;
    }

    public String getEmployerAddress() {
        return employerAddress;
    }

    public void setEmployerAddress(String employerAddress) {
        this.employerAddress = employerAddress;
    }

    public String getEmployerBusiness() {
        return employerBusiness;
    }

    public void setEmployerBusiness(String employerBusiness) {
        this.employerBusiness = employerBusiness;
    }

    public Integer getEmployerEmployeesCount() {
        return employerEmployeesCount;
    }

    public void setEmployerEmployeesCount(Integer employerEmployeesCount) {
        this.employerEmployeesCount = employerEmployeesCount;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerWebsite() {
        return employerWebsite;
    }

    public void setEmployerWebsite(String employerWebsite) {
        this.employerWebsite = employerWebsite;
    }

    public List<FieldOfStudy> getFieldOfStudies() {
        return fieldOfStudies;
    }

    public void setFieldOfStudies(List<FieldOfStudy> fieldOfStudies) {
        this.fieldOfStudies = fieldOfStudies;
    }

    public Date getFromDate2() {
        return fromDate2;
    }

    public void setFromDate2(Date fromDate2) {
        this.fromDate2 = fromDate2;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getHolidaysFrom() {
        return holidaysFrom;
    }

    public void setHolidaysFrom(Date holidaysFrom) {
        this.holidaysFrom = holidaysFrom;
    }

    public Date getHolidaysTo() {
        return holidaysTo;
    }

    public void setHolidaysTo(Date holidaysTo) {
        this.holidaysTo = holidaysTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Language getLanguage1() {
        return language1;
    }

    public void setLanguage1(Language language1) {
        this.language1 = language1;
    }

    public LanguageLevel getLanguage1Level() {
        return language1Level;
    }

    public void setLanguage1Level(LanguageLevel language1Level) {
        this.language1Level = language1Level;
    }

    public LanguageOperator getLanguage1Operator() {
        return language1Operator;
    }

    public void setLanguage1Operator(LanguageOperator language1Operator) {
        this.language1Operator = language1Operator;
    }

    public Language getLanguage2() {
        return language2;
    }

    public void setLanguage2(Language language2) {
        this.language2 = language2;
    }

    public LanguageLevel getLanguage2Level() {
        return language2Level;
    }

    public void setLanguage2Level(LanguageLevel language2Level) {
        this.language2Level = language2Level;
    }

    public LanguageOperator getLanguage2Operator() {
        return language2Operator;
    }

    public void setLanguage2Operator(LanguageOperator language2Operator) {
        this.language2Operator = language2Operator;
    }

    public Language getLanguage3() {
        return language3;
    }

    public void setLanguage3(Language language3) {
        this.language3 = language3;
    }

    public LanguageLevel getLanguage3Level() {
        return language3Level;
    }

    public void setLanguage3Level(LanguageLevel language3Level) {
        this.language3Level = language3Level;
    }

    public BigDecimal getLivingCost() {
        return livingCost;
    }

    public void setLivingCost(BigDecimal livingCost) {
        this.livingCost = livingCost;
    }

    public int getLivingPaymentFrequency() {
        return livingPaymentFrequency;
    }

    public void setLivingPaymentFrequency(int livingPaymentFrequency) {
        this.livingPaymentFrequency = livingPaymentFrequency;
    }

    public String getLodgingBy() {
        return lodgingBy;
    }

    public void setLodgingBy(String lodgingBy) {
        this.lodgingBy = lodgingBy;
    }

    public BigDecimal getLodgingCost() {
        return lodgingCost;
    }

    public void setLodgingCost(BigDecimal lodgingCost) {
        this.lodgingCost = lodgingCost;
    }

    public PaymentFrequency getLodgingPaymentFrequency() {
        return lodgingPaymentFrequency;
    }

    public void setLodgingPaymentFrequency(PaymentFrequency lodgingPaymentFrequency) {
        this.lodgingPaymentFrequency = lodgingPaymentFrequency;
    }

    public Integer getMaximumWeeks() {
        return maximumWeeks;
    }

    public void setMaximumWeeks(Integer maximumWeeks) {
        this.maximumWeeks = maximumWeeks;
    }

    public Integer getMinimumWeeks() {
        return minimumWeeks;
    }

    public void setMinimumWeeks(Integer minimumWeeks) {
        this.minimumWeeks = minimumWeeks;
    }

    public String getNearestAirport() {
        return nearestAirport;
    }

    public void setNearestAirport(String nearestAirport) {
        this.nearestAirport = nearestAirport;
    }

    public String getNearestPubTransport() {
        return nearestPubTransport;
    }

    public void setNearestPubTransport(String nearestPubTransport) {
        this.nearestPubTransport = nearestPubTransport;
    }

    public Date getNominationDeadline() {
        return nominationDeadline;
    }

    public void setNominationDeadline(Date nominationDeadline) {
        this.nominationDeadline = nominationDeadline;
    }

    public String getOtherRequirements() {
        return otherRequirements;
    }

    public void setOtherRequirements(String otherRequirements) {
        this.otherRequirements = otherRequirements;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public PaymentFrequency getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(PaymentFrequency paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public Boolean getPrevTrainingRequired() {
        return prevTrainingRequired;
    }

    public void setPrevTrainingRequired(Boolean prevTrainingRequired) {
        this.prevTrainingRequired = prevTrainingRequired;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public List<String> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<String> specializations) {
        this.specializations = specializations;
    }

    public List<StudyLevel> getStudyLevels() {
        return studyLevels;
    }

    public void setStudyLevels(List<StudyLevel> studyLevels) {
        this.studyLevels = studyLevels;
    }

    public Date getToDate2() {
        return toDate2;
    }

    public void setToDate2(Date toDate2) {
        this.toDate2 = toDate2;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public TypeOfWork getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(TypeOfWork typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public Float getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(Float weeklyHours) {
        this.weeklyHours = weeklyHours;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    /**
     * first thought was that id should be sufficient, but what if two
     * NOT PRESISTED offers want to be compared, then there is no ID
     *
     * Even persisted offers can be updated differently, so still all
     * fields need to be taken into conscideration.
     *
     * {@inheritDoc}
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        if (!id.equals(offer.id)) return false;
        if (livingPaymentFrequency != offer.livingPaymentFrequency) return false;
        if (canteen != null ? !canteen.equals(offer.canteen) : offer.canteen != null) return false;
        if (currency != offer.currency) return false;
        if (dailyHours != null ? !dailyHours.equals(offer.dailyHours) : offer.dailyHours != null) return false;
        if (deduction != null ? !deduction.equals(offer.deduction) : offer.deduction != null) return false;
        if (employerAddress != null ? !employerAddress.equals(offer.employerAddress) : offer.employerAddress != null)
            return false;
        if (employerAddress2 != null ? !employerAddress2.equals(offer.employerAddress2) : offer.employerAddress2 != null)
            return false;
        if (employerBusiness != null ? !employerBusiness.equals(offer.employerBusiness) : offer.employerBusiness != null)
            return false;
        if (employerEmployeesCount != null ? !employerEmployeesCount.equals(offer.employerEmployeesCount) : offer.employerEmployeesCount != null)
            return false;
        if (employerName != null ? !employerName.equals(offer.employerName) : offer.employerName != null) return false;
        if (employerWebsite != null ? !employerWebsite.equals(offer.employerWebsite) : offer.employerWebsite != null)
            return false;
        if (fieldOfStudies != null ? !fieldOfStudies.equals(offer.fieldOfStudies) : offer.fieldOfStudies != null)
            return false;
        if (fromDate != null ? !fromDate.equals(offer.fromDate) : offer.fromDate != null) return false;
        if (fromDate2 != null ? !fromDate2.equals(offer.fromDate2) : offer.fromDate2 != null) return false;
        if (gender != offer.gender) return false;
        if (holidaysFrom != null ? !holidaysFrom.equals(offer.holidaysFrom) : offer.holidaysFrom != null) return false;
        if (holidaysTo != null ? !holidaysTo.equals(offer.holidaysTo) : offer.holidaysTo != null) return false;
        if (language1 != offer.language1) return false;
        if (language1Level != offer.language1Level) return false;
        if (language1Operator != offer.language1Operator) return false;
        if (language2 != offer.language2) return false;
        if (language2Level != offer.language2Level) return false;
        if (language2Operator != offer.language2Operator) return false;
        if (language3 != offer.language3) return false;
        if (language3Level != offer.language3Level) return false;
        if (livingCost != null ? !livingCost.equals(offer.livingCost) : offer.livingCost != null) return false;
        if (lodgingBy != null ? !lodgingBy.equals(offer.lodgingBy) : offer.lodgingBy != null) return false;
        if (lodgingCost != null ? !lodgingCost.equals(offer.lodgingCost) : offer.lodgingCost != null) return false;
        if (lodgingPaymentFrequency != offer.lodgingPaymentFrequency) return false;
        if (maximumWeeks != null ? !maximumWeeks.equals(offer.maximumWeeks) : offer.maximumWeeks != null) return false;
        if (minimumWeeks != null ? !minimumWeeks.equals(offer.minimumWeeks) : offer.minimumWeeks != null) return false;
        if (nearestAirport != null ? !nearestAirport.equals(offer.nearestAirport) : offer.nearestAirport != null)
            return false;
        if (nearestPubTransport != null ? !nearestPubTransport.equals(offer.nearestPubTransport) : offer.nearestPubTransport != null)
            return false;
        if (nominationDeadline != null ? !nominationDeadline.equals(offer.nominationDeadline) : offer.nominationDeadline != null)
            return false;
        if (otherRequirements != null ? !otherRequirements.equals(offer.otherRequirements) : offer.otherRequirements != null)
            return false;
        if (payment != null ? !payment.equals(offer.payment) : offer.payment != null) return false;
        if (paymentFrequency != offer.paymentFrequency) return false;
        if (prevTrainingRequired != null ? !prevTrainingRequired.equals(offer.prevTrainingRequired) : offer.prevTrainingRequired != null)
            return false;
        if (!refNo.equals(offer.refNo)) return false;
        if (specializations != null ? !specializations.equals(offer.specializations) : offer.specializations != null)
            return false;
        if (studyLevels != null ? !studyLevels.equals(offer.studyLevels) : offer.studyLevels != null) return false;
        if (toDate != null ? !toDate.equals(offer.toDate) : offer.toDate != null) return false;
        if (toDate2 != null ? !toDate2.equals(offer.toDate2) : offer.toDate2 != null) return false;
        if (typeOfWork != offer.typeOfWork) return false;
        if (weeklyHours != null ? !weeklyHours.equals(offer.weeklyHours) : offer.weeklyHours != null) return false;
        if (workDescription != null ? !workDescription.equals(offer.workDescription) : offer.workDescription != null)
            return false;
        if (workingPlace != null ? !workingPlace.equals(offer.workingPlace) : offer.workingPlace != null) return false;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = IWSConstants.HASHCODE_INITIAL_VALUE;
        
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + refNo.hashCode();
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (nominationDeadline != null ? nominationDeadline.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (employerName != null ? employerName.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (employerAddress != null ? employerAddress.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (employerAddress2 != null ? employerAddress2.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (employerBusiness != null ? employerBusiness.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (employerEmployeesCount != null ? employerEmployeesCount.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (employerWebsite != null ? employerWebsite.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (fieldOfStudies != null ? fieldOfStudies.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (specializations != null ? specializations.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (studyLevels != null ? studyLevels.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (prevTrainingRequired != null ? prevTrainingRequired.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (otherRequirements != null ? otherRequirements.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (gender != null ? gender.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (language1 != null ? language1.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (language1Level != null ? language1Level.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (language1Operator != null ? language1Operator.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (language2 != null ? language2.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (language2Level != null ? language2Level.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (language2Operator != null ? language2Operator.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (language3 != null ? language3.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (language3Level != null ? language3Level.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (workDescription != null ? workDescription.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (typeOfWork != null ? typeOfWork.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (minimumWeeks != null ? minimumWeeks.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (maximumWeeks != null ? maximumWeeks.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (fromDate != null ? fromDate.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (toDate != null ? toDate.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (fromDate2 != null ? fromDate2.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (toDate2 != null ? toDate2.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (holidaysFrom != null ? holidaysFrom.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (holidaysTo != null ? holidaysTo.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (workingPlace != null ? workingPlace.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (nearestAirport != null ? nearestAirport.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (nearestPubTransport != null ? nearestPubTransport.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (weeklyHours != null ? weeklyHours.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (dailyHours != null ? dailyHours.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (payment != null ? payment.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (currency != null ? currency.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (paymentFrequency != null ? paymentFrequency.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (deduction != null ? deduction.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (lodgingBy != null ? lodgingBy.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (lodgingCost != null ? lodgingCost.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (lodgingPaymentFrequency != null ? lodgingPaymentFrequency.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (livingCost != null ? livingCost.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + livingPaymentFrequency;
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (canteen != null ? canteen.hashCode() : 0);
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", refNo='" + refNo + '\'' +
                ", nominationDeadline=" + nominationDeadline +
                ", employerName='" + employerName + '\'' +
                ", employerAddress='" + employerAddress + '\'' +
                ", employerAddress2='" + employerAddress2 + '\'' +
                ", employerBusiness='" + employerBusiness + '\'' +
                ", employerEmployeesCount=" + employerEmployeesCount +
                ", employerWebsite='" + employerWebsite + '\'' +
                ", fieldOfStudies=" + fieldOfStudies +
                ", specializations=" + specializations +
                ", studyLevels=" + studyLevels +
                ", prevTrainingRequired=" + prevTrainingRequired +
                ", otherRequirements='" + otherRequirements + '\'' +
                ", gender=" + gender +
                ", language1=" + language1 +
                ", language1Level=" + language1Level +
                ", language1Operator=" + language1Operator +
                ", language2=" + language2 +
                ", language2Level=" + language2Level +
                ", language2Operator=" + language2Operator +
                ", language3=" + language3 +
                ", language3Level=" + language3Level +
                ", workDescription='" + workDescription + '\'' +
                ", typeOfWork=" + typeOfWork +
                ", minimumWeeks=" + minimumWeeks +
                ", maximumWeeks=" + maximumWeeks +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", fromDate2=" + fromDate2 +
                ", toDate2=" + toDate2 +
                ", holidaysFrom=" + holidaysFrom +
                ", holidaysTo=" + holidaysTo +
                ", workingPlace='" + workingPlace + '\'' +
                ", nearestAirport='" + nearestAirport + '\'' +
                ", nearestPubTransport='" + nearestPubTransport + '\'' +
                ", weeklyHours=" + weeklyHours +
                ", dailyHours=" + dailyHours +
                ", payment=" + payment +
                ", currency=" + currency +
                ", paymentFrequency=" + paymentFrequency +
                ", deduction=" + deduction +
                ", lodgingBy='" + lodgingBy + '\'' +
                ", lodgingCost=" + lodgingCost +
                ", lodgingPaymentFrequency=" + lodgingPaymentFrequency +
                ", livingCost=" + livingCost +
                ", livingPaymentFrequency=" + livingPaymentFrequency +
                ", canteen=" + canteen +
                '}';
    }
}
