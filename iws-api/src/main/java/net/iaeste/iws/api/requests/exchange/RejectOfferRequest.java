/*
 * =============================================================================
 * Copyright 1998-2014, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.requests.exchange.RejectOfferRequest
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.requests.exchange;

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.util.AbstractVerification;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  Pavel Fiala / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
public final class RejectOfferRequest extends AbstractVerification {

    /** {@link net.iaeste.iws.api.constants.IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;

    private String offerId;

    // =========================================================================
    // Object Constructors
    // =========================================================================

    /**
     * Empty Constructor, to use if the setters are invoked. This is required
     * for WebServices to work properly.
     */
    public RejectOfferRequest() {
        offerId = null;
    }

    /**
     * The Id of the Offer to reject.
     *
     * @param offerId Id of the Offer to reject
     */
    public RejectOfferRequest(final String offerId) {
        this.offerId = offerId;
    }

    // =========================================================================
    // Standard Setters & Getters
    // =========================================================================

    public void setOfferId(final String offerId) {
        this.offerId = offerId;
    }

    public String getOfferId() {
        return offerId;
    }

    // =========================================================================
    // Standard Request Methods
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> validate() {
        final Map<String, String> validation = new HashMap<>(0);

        isNotNull(validation, "offerId", offerId);

        return validation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RejectOfferRequest)) {
            return false;
        }

        final RejectOfferRequest that = (RejectOfferRequest) obj;
        return !((offerId != null) ? !offerId.equals(that.offerId) : (that.offerId != null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return (offerId != null) ? offerId.hashCode() : 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RejectOfferRequest{" +
                "offerId=" + offerId +
                '}';
    }
}