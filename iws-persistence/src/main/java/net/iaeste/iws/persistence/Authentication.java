/*
 * =============================================================================
 * Copyright 1998-2012, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-persistence) - net.iaeste.iws.persistence.bo.AuthenticationBO
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.persistence;

import net.iaeste.iws.persistence.entities.GroupEntity;
import net.iaeste.iws.persistence.entities.UserEntity;

/**
 * For our internal handling, we need to have the live entities for both the
 * user and the group at hand. This Object is created as part of the
 * Authorization mechanism.<br />
 *   The Monitoring mechanism requires that we have some sort of identification
 * at hand, and this Object will provide it.
 *
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 */
public class Authentication {

    private final UserEntity user;
    private final GroupEntity group;

    /**
     * Default Constructor.
     *
     * @param user   User Entity for the current user
     * @param group  Group Entity for the group being worked with
     */
    public Authentication(final UserEntity user, final GroupEntity group) {
        this.user = user;
        this.group = group;
    }

    public UserEntity getUser() {
        return user;
    }

    public GroupEntity getGroup() {
        return group;
    }
}