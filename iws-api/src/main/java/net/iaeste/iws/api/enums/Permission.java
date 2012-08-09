/*
 * =============================================================================
 * Copyright 1998-2012, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.enums.Permission
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.enums;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 */
public enum Permission {

    // Access Control
    AUTHENTICATE,
    AUTHORIZE,

    // Exchange
    PROCESS_OFFERS,
    LOOKUP_FACULTIES,
    PROCESS_FACULTIES,
    LOOKUP_OFFERS,
    PROCESS_OFFER_TEMPLATES,
    LOOKUP_OFFER_TEMPLATES,
    PROCESS_OFFER_PUBLISH_GROUPS,
    LOOKUP_OFFER_PUBLISH_GROUPS,
    PROCESS_STUDENTS,
    PROCESS_USERS, FETCH_USERS, FETCH_GROUPS, PROCESS_GROUPS, PROCESS_COUNTRIES, PROCESS_USER_GROUP_ASSIGNMENT, FETCH_COUNTRIES, LOOKUP_STUDENTS

}