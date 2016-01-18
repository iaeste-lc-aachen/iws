/*
 * =============================================================================
 * Copyright 1998-2016, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.constants.IWSErrors
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.constants;

/**
 * Defined IWS Errors.
 *
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
public final class IWSErrors {

    /** Private Constructor, this is a Constants Class. */
    private IWSErrors() {
    }

    /**
     * All requests, which is processed normally, i.e. without any issues should
     * have this as the default error.
     */
    public static final IWSError SUCCESS = new IWSError(0, "Success.");

    /**
     * All requests, where the input data, is unreliable, and will prevent the
     * processing to be properly handled, or in case something internally has
     * happened, which the system managed to recover from via other mechanisms,
     * should return this as the basic error.
     */
    public static final IWSError WARNING = new IWSError(100, "A problem occurred while processing the request.");

    /**
     * All requests, where something happened internally, which prevented the
     * system from properly respond with a valid result, will have this as
     * default error.
     */
    public static final IWSError ERROR = new IWSError(200, "An unknown error occurred, which prevented the IWS from completing the request.");

    /**
     * Unrecoverable internal errors, from which it is not possible to proceed
     * without manual intervention, i.e. database is down or other critical
     * systems is inaccessible, should return an error of this type.
     */
    public static final IWSError FATAL = new IWSError(300, "A fatal error occurred, which will prevent the IWS from working properly.");

    /**
     * The data required for a given request is insufficient to be properly
     * processed.
     */
    public static final IWSError VERIFICATION_ERROR = new IWSError(401, "Given data is insufficient to properly handle request.");

    /**
     * If the user making the request is not properly authenticated, i.e. no
     * proper user credentials like username/password or session checksum.
     */
    public static final IWSError AUTHENTICATION_ERROR = new IWSError(402, "User Authentication problem.");

    /**
     * If the user making the request is not allowed to perform the desired
     * action.
     */
    public static final IWSError AUTHORIZATION_ERROR = new IWSError(403, "User Authorization problem.");

    /**
     * If the user is attempting to access/process an Object, without being
     * allowed to do so (missing Group ownership).
     */
    public static final IWSError NOT_PERMITTED = new IWSError(404, "User is not permitted to process the requested Object.");

    /**
     * If the User tries to create a new Session, while already having an
     * Active Session, an error should be thrown. This will prevent that users
     * try to log in multiple places, but forget to log out again.
     */
    public static final IWSError SESSION_EXISTS = new IWSError(405, "User can only hold one active Session at the time.");

    /**
     * The system has an upper limit of how many active sessions may exists.
     */
    public static final IWSError TOO_MANY_ACTIVE_SESSIONS = new IWSError(406, "The system has reached the maximum allowed number of concurrently active users.");

    /**
     * Session Expiration.
     */
    public static final IWSError SESSION_EXPIRED = new IWSError(407, "The session has expired, and can no longer be used.");

    /**
     * The user account has seen too many attempts at login in.
     */
    public static final IWSError EXCEEDED_LOGIN_ATTEMPTS = new IWSError(408, "Too many login attempts for this account.");

    /**
     * The user account has seen too many attempts at login in.
     */
    public static final IWSError DATA_SERIALIZATION_ERROR = new IWSError(420, "Serialization Error with the given Object.");

    /**
     * The database is inaccessible.
     */
    public static final IWSError DATABASE_UNREACHABLE = new IWSError(501, "Database unreachable.");

    /**
     * If a situation arise, where there exists multiple similar records, which
     * should not be allowed.
     */
    public static final IWSError DATABASE_CONSTRAINT_INCONSISTENCY = new IWSError(502, "Database Constraint Inconsistency.");

    /**
     * Unknown Persistence Error.
     */
    public static final IWSError PERSISTENCE_ERROR = new IWSError(503, "Persistence Error.");

    /**
     * The Identification Error is passed when trying to access Objects, that
     * either doesn't exist, or where the requesting user do not have sufficient
     * permissions.
     */
    public static final IWSError OBJECT_IDENTIFICATION_ERROR = new IWSError(504, "Object Identification Error.");

    /**
     * The processing of the request failed.
     */
    public static final IWSError PROCESSING_FAILURE = new IWSError(505, "Request cannot be processed.");

    /**
     * Error reading or writing the monitoring data in serialized form.
     */
    public static final IWSError MONITORING_FAILURE = new IWSError(506, "Monitoring Serialization Error.");

    public static final IWSError ILLEGAL_ACTION = new IWSError(507, "User attempted to perform an illegal action.");

    public static final IWSError STORAGE_ERROR = new IWSError(508, "Internal Storage Problem.");

    public static final IWSError USER_ACCOUNT_EXISTS = new IWSError(601, "User Account Already exists.");

    public static final IWSError NO_USER_ACCOUNT_FOUND = new IWSError(602, "No User Account exists.");

    public static final IWSError INVALID_NOTIFICATION = new IWSError(603, "Notification Type is not allowed in this context.");

    public static final IWSError CANNOT_UPDATE_PASSWORD = new IWSError(604, "The provided old Password is invalid.");

    public static final IWSError GENERAL_EXCHANGE_ERROR = new IWSError(700, "A General error occurred in the Exchange Module.");

    public static final IWSError CANNOT_DELETE_OFFER = new IWSError(701, "Shared Offers cannot be deleted.");

    public static final IWSError CANNOT_PROCESS_SHARED_OFFER = new IWSError(702, "Shared Offers cannot be updated.");

    public static final IWSError INVALID_EXCHANGE_TYPE = new IWSError(703, "Invalid Exchange Type.");

    public static final IWSError PDF_ERROR = new IWSError(990, "PDF Generator Error.");

    /**
     * The current method is not yet implemented.
     */
    public static final IWSError NOT_IMPLEMENTED = new IWSError(999, "Not Implemented");
}
