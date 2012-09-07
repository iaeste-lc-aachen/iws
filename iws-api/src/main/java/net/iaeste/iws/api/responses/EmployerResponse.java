/*
 * =============================================================================
 * Copyright 1998-$today.year, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services ($module.name) - $file.qualifiedClassName
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.responses;

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.constants.IWSError;
import net.iaeste.iws.api.constants.IWSErrors;
import net.iaeste.iws.api.dtos.Employer;
import net.iaeste.iws.api.utils.Copier;

import java.util.List;

/**
 * ToDo Kim; Pavel, there is no way to create a positive response, i.e. without error
 * ToDo Kim; Pavel, we need both setters and getters, including for the errors
 *
 * @author  Pavel Fiala / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 * @noinspection CastToConcreteClass
 */
public final class EmployerResponse extends AbstractResponse {

    /** {@link IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;
    private final Employer employer;
    private final List<String> errors;

    /**
     * Empty Constructor, to use if the setters are invoked. This is required
     * for WebServices to work properly.
     */
    public EmployerResponse() {
        super(IWSErrors.SUCCESS, IWSConstants.SUCCESS);
        employer = null;
        errors = null;
    }

    /**
     * Error Constructor.
     *
     * @param error   IWS Error Object
     * @param message Error Message
     */
    public EmployerResponse(final IWSError error, final String message) {
        super(error, message);
        employer = null;
        errors = null;
    }

    /**
     * Response is created when processing the employer failed.
     * <p/>
     * Incorrect Employer should never be passed to this constructor. Instead
     * use constructor without list of errors parameter.
     *
     * @param failedEmployer Employer Object, which could not be processed
     * @param errors         List of processing errors
     */
    public EmployerResponse(final Employer failedEmployer, final List<String> errors) {
        super(IWSErrors.PROCESSING_FAILURE, "processing of the Offer failed");
        employer = new Employer(failedEmployer);
        this.errors = Copier.copy(errors);
    }

    // =========================================================================
    // Standard Setters & Getters
    // =========================================================================

    public Employer getEmployer() {
        return new Employer(employer);
    }

    // =========================================================================
    // Standard Response Methods
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final EmployerResponse that = (EmployerResponse) obj;

        if (errors != null ? !errors.equals(that.errors) : that.errors != null) {
            return false;
        }

        return !(employer != null ? !employer.equals(that.employer) : that.employer != null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();

        result = IWSConstants.HASHCODE_MULTIPLIER * result + (employer != null ? employer.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + (errors != null ? errors.hashCode() : 0);

        return result;
    }

    /**
     * {@inheritDoc}
     */
     @Override
    public String toString() {
        return "EmployerResponse{" +
                "employer=" + employer +
                ", errors=" + errors +
                '}';
    }
}
