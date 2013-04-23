/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.responses.exchange.FetchEmployerInformationResponse
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.responses.exchange;

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.constants.IWSError;
import net.iaeste.iws.api.constants.IWSErrors;
import net.iaeste.iws.api.dtos.exchange.EmployerInformation;
import net.iaeste.iws.api.util.AbstractFallible;
import net.iaeste.iws.api.util.Copier;

import java.util.List;

/**
 * @author  Pavel Fiala / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 * @noinspection CastToConcreteClass
 */
public final class FetchEmployerInformationResponse extends AbstractFallible {

    /** {@link IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;
    private List<EmployerInformation> employers;

    /**
     * Empty Constructor, to use if the setters are invoked. This is required
     * for WebServices to work properly.
     */
    public FetchEmployerInformationResponse() {
        super(IWSErrors.SUCCESS, IWSConstants.SUCCESS);
        employers = null;
    }

    /**
     * Error Constructor.
     *
     * @param error   IWS Error Object
     * @param message Error Message
     */
    public FetchEmployerInformationResponse(final IWSError error, final String message) {
        super(error, message);
        employers = null;
    }

    /**
     * Default Constructor.
     *
     * @param employers List of Employers
     */
    public FetchEmployerInformationResponse(final List<EmployerInformation> employers) {
        this.employers = Copier.copy(employers);
    }

    // =========================================================================
    // Standard Setters & Getters
    // =========================================================================

    public void setEmployers(final List<EmployerInformation> employers) {
        this.employers = Copier.copy(employers);
    }

    public List<EmployerInformation> getEmployers() {
        return Copier.copy(employers);
    }

    // =========================================================================
    // Standard Response Methods
    // =========================================================================

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FetchEmployerInformationResponse)) {
            return false;
        }

        final FetchEmployerInformationResponse that = (FetchEmployerInformationResponse) obj;
        return employers.equals(that.employers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();

        result = IWSConstants.HASHCODE_MULTIPLIER * result + (employers != null ? employers.hashCode() : 0);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "FetchEmployerInformationResponse{" +
                "employers=" + employers +
                '}';
    }
}