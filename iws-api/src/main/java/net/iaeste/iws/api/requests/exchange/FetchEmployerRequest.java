/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.requests.exchange.FetchEmployerRequest
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
import net.iaeste.iws.api.enums.SortingField;
import net.iaeste.iws.api.enums.exchange.EmployerFetchType;
import net.iaeste.iws.api.util.AbstractPaginatable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since 1.7
 */
public final class FetchEmployerRequest extends AbstractPaginatable {

    /** {@link IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;

    private EmployerFetchType type = EmployerFetchType.ALL;
    private String field = null;

    // =========================================================================
    // Standard Setters & Getters
    // =========================================================================

    public void setFetchAll() {
        this.type = EmployerFetchType.ALL;
        this.field = null;
    }

    public void setFetchById(final String id) {
        this.type = EmployerFetchType.ID;
        this.field = id;
    }

    public void setFetchByPartialName(final String partialName) {
        this.type = EmployerFetchType.NAME;
        this.field = partialName;
    }

    public EmployerFetchType getFetchType() {
        return type;
    }

    public String getFetchField() {
        return field;
    }

    // =========================================================================
    // Standard Response Methods
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> validate() {
        return new HashMap<>(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSortBy(final SortingField sortBy) {
        if (sortBy == null) {
            throw new IllegalArgumentException("The SortingField cannot be null.");
        }

        switch (sortBy) {
            case NAME:
                page.setSortBy(sortBy);
                break;
            default:
                // If unsupported, we're going to revert to the default
                page.setSortBy(SortingField.CREATED);
        }
    }
}