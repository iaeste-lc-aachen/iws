/*
 * =============================================================================
 * Copyright 1998-2015, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-common) - net.iaeste.iws.common.notification.NotificationType
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.common.notification;

/**
 * The same Objects can have many different types of Notifications, this will
 * help the Class determine, exactly which one is suppose to be generated.
 *
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
public enum NotificationType {

    /**
     * If the Object only supports a single type of Notifications, then this
     * type should be used as the default.
     */
    GENERAL,

    /**
     * For updating a users username.
     */
    UPDATE_USERNAME,

    /**
     * For post user name update actions
     */
    USERNAME_UPDATED,

    /**
     * For Activating newly created User Account.
     */
    ACTIVATE_NEW_USER,

    /**
     * For Activating a suspended User Account.
     */
    ACTIVATE_SUSPENDED_USER,

    /**
     * For Suspending an Active User Account.
     */
    SUSPEND_ACTIVE_USER,

    /**
     * New user is being created
     */
    NEW_USER,

    /**
     * For sending of reset password requests.
     */
    RESET_PASSWORD,

    /**
     * For handling resetting Session requests.
     */
    RESET_SESSION,

    /**
     * When a new Group is created, the Owner should be informed about the new
     * Group, and additionally the mailinglist for the new Group should be
     * created.
     */
    NEW_GROUP,

    /**
     * Whenever there is a change in the membership of a Group, then the system
     * must be informed about it, so the mailinglists can be updated.
     */
    CHANGE_IN_GROUP_MEMBERS,

    /**
     * When the ownership of a Group is changing, the new owner should be
     * informed about this change.
     */
    NEW_GROUP_OWNER,

    PROCESS_EMAIL_ALIAS,

    PROCESS_MAILING_LIST,

    /**
     * When user access activation link and activation is successful,
     * some system setting has to be prepared
     */
    USER_ACTIVATED,

    NEW_STUDENT
}
