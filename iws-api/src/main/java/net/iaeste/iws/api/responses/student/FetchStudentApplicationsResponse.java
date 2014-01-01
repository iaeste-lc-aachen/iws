/*
 * =============================================================================
 * Copyright 1998-2014, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.responses.student.FetchStudentApplicationsResponse
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.responses.student;

import static net.iaeste.iws.api.util.Copier.copy;

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.constants.IWSError;
import net.iaeste.iws.api.constants.IWSErrors;
import net.iaeste.iws.api.dtos.exchange.StudentApplication;
import net.iaeste.iws.api.util.AbstractFallible;

import java.util.List;

/**
 * @author  Matej Kosco / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 */
public final class FetchStudentApplicationsResponse extends AbstractFallible {

    /** {@link IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;

    private List<StudentApplication> studentApplications;

    // =========================================================================
    // Object Constructors
    // =========================================================================

    /**
     * Empty Constructor, to use if the setters are invoked. This is required
     * for WebServices to work properly.
     */
    public FetchStudentApplicationsResponse() {
        super(IWSErrors.SUCCESS, IWSConstants.SUCCESS);
        studentApplications = null;
    }

    /**
     * Default Constructor.
     *
     * @param studentApplications List of Applications found
     */
    public FetchStudentApplicationsResponse(final List<StudentApplication> studentApplications) {
        this.studentApplications = copy(studentApplications);
    }

    /**
     * Error Constructor.
     *
     * @param error   IWS Error Object
     * @param message Error Message
     */
    public FetchStudentApplicationsResponse(final IWSError error, final String message) {
        super(error, message);

        studentApplications = null;
    }

    // =========================================================================
    // Standard Setters & Getters
    // =========================================================================

    public void setStudentApplications(final List<StudentApplication> studentApplications) {
        this.studentApplications = copy(studentApplications);
    }

    public List<StudentApplication> getStudentApplications() {
        return studentApplications;
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

        if (!(obj instanceof FetchStudentApplicationsResponse)) {
            return false;
        }

        final FetchStudentApplicationsResponse that = (FetchStudentApplicationsResponse) obj;
        return !(studentApplications != null ? !studentApplications.equals(that.studentApplications) : that.studentApplications != null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();

        result = IWSConstants.HASHCODE_MULTIPLIER * result + (studentApplications != null ? studentApplications.hashCode() : 0);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "FetchStudentApplicationsResponse{" +
                "studentApplications=" + studentApplications +
                '}';
    }
}
