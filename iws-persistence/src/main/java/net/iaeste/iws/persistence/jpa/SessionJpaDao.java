/*
 * =============================================================================
 * Copyright 1998-2012, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-persistence) - net.iaeste.iws.persistence.jpa.SessionJpaDao
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.persistence.jpa;

import net.iaeste.iws.api.exceptions.NotImplementedException;
import net.iaeste.iws.persistence.SessionDao;
import net.iaeste.iws.persistence.entities.SessionEntity;

import javax.persistence.EntityManager;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 */
public class SessionJpaDao extends BasicJpaDao implements SessionDao {

    /**
     * Default Constructor.
     *
     * @param entityManager  Entity Manager instance to use
     */
    public SessionJpaDao(final EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionEntity findSession(final String key) {
        throw new NotImplementedException("Missing :-(");
    }
}
