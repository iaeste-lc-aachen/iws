/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-persistence) - net.iaeste.iws.persistence.jpa.ViewsJpaDao
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

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.util.Paginatable;
import net.iaeste.iws.persistence.ViewsDao;
import net.iaeste.iws.persistence.exceptions.IdentificationException;
import net.iaeste.iws.persistence.views.EmployerView;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 */
public final class ViewsJpaDao extends BasicJpaDao implements ViewsDao {

    public ViewsJpaDao(final EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployerView findEmployer(final Long groupId, final String externalId) {
        final Query query = entityManager.createNamedQuery("view.findEmployerByGroupAndId");
        query.setParameter("gid", groupId);
        query.setParameter("id", externalId);

        final List<EmployerView> found = query.getResultList();
        final EmployerView result;

        if (found.isEmpty()) {
            result = null;
        } else if (found.size() > 1) {
            throw new IdentificationException("Found multiple Employer Records.");
        } else {
            result = found.get(0);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EmployerView> findEmployers(final Long groupId, final Paginatable page) {
        final Query query = entityManager.createNamedQuery("view.findEmployerByGroup");
        query.setParameter("gid", groupId);

        return fetchList(query, page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EmployerView> findEmployers(final Long groupId, final Paginatable page, final String partialName) {
        final Query query = entityManager.createNamedQuery("view.findEmployerByGroupAndPartialName");
        query.setParameter("gid", groupId);
        query.setParameter("name", partialName.toLowerCase(IWSConstants.DEFAULT_LOCALE));

        return fetchList(query, page);
    }
}