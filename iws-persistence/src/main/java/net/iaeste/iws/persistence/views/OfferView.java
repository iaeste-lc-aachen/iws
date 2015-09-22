/*
 * =============================================================================
 * Copyright 1998-2015, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-persistence) - net.iaeste.iws.persistence.views.OfferView
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.persistence.views;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "view.findDomesticOffersByGroupAndYears",
            query = "select o from OfferView o " +
                    "where o.groupId = :gid" +
                    "  and o.exchangeYear in :years" +
                    "  and o.offer.status in :states"),
        @NamedQuery(name = "view.findDomesticOffersByGroupAndYearAndOfferExternalId",
            query = "select o from OfferView o " +
                    "where o.groupId = :gid" +
                    "  and o.exchangeYear = :year" +
                    "  and o.offer.externalId in (:eids)")
})
@Table(name = "offer_view")
public class OfferView extends AbstractView<OfferView> {

    @Id
    @Column(name = "offer_id", insertable = false, updatable = false)
    private Long id = null;

    @Column(name = "offer_exchange_year", insertable = false, updatable = false)
    private Integer exchangeYear = null;

    @Column(name = "group_id", insertable = false, updatable = false)
    private Long groupId = null;

    @Column(name = "ns_firstname", insertable = false, updatable = false)
    private String nsFirstname = null;

    @Column(name = "ns_lastname", insertable = false, updatable = false)
    private String nsLastname = null;

    @Embedded
    private EmbeddedOffer offer = null;

    @Embedded
    private EmbeddedEmployer employer = null;

    @Embedded
    private EmbeddedAddress address = null;

    @Embedded
    private EmbeddedCountry country = null;

    @Embedded
    private EmbeddedGroup group = null;

    // =========================================================================
    // Entity Setters & Getters
    // =========================================================================

    public void setId(final Long offerId) {
        this.id = offerId;
    }

    public Long getId() {
        return id;
    }

    public void setExchangeYear(final Integer exchangeYear) {
        this.exchangeYear = exchangeYear;
    }

    public Integer getExchangeYear() {
        return exchangeYear;
    }

    public void setGroupId(final Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setNsFirstname(final String nsFirstname) {
        this.nsFirstname = nsFirstname;
    }

    public String getNsFirstname() {
        return nsFirstname;
    }

    public void setNsLastname(final String nsLastname) {
        this.nsLastname = nsLastname;
    }

    public String getNsLastname() {
        return nsLastname;
    }

    public void setOffer(final EmbeddedOffer offer) {
        this.offer = offer;
    }

    public EmbeddedOffer getOffer() {
        return offer;
    }

    public void setEmployer(final EmbeddedEmployer employer) {
        this.employer = employer;
    }

    public EmbeddedEmployer getEmployer() {
        return employer;
    }

    public void setAddress(final EmbeddedAddress address) {
        this.address = address;
    }

    public EmbeddedAddress getAddress() {
        return address;
    }

    public void setCountry(final EmbeddedCountry country) {
        this.country = country;
    }

    public EmbeddedCountry getCountry() {
        return country;
    }

    public void setGroup(final EmbeddedGroup group) {
        this.group = group;
    }

    public EmbeddedGroup getGroup() {
        return group;
    }

    // =========================================================================
    // Standard View Methods
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof OfferView)) {
            return false;
        }

        // As the view is reading from the current data model, and the Id is
        // always unique. It is sufficient to compare against this field.
        final OfferView that = (OfferView) obj;
        return !(id != null ? !id.equals(that.id) : that.id != null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final OfferView o) {
        final int result = id.compareTo(o.id);

        return sortAscending ? result : -result;
    }
}
