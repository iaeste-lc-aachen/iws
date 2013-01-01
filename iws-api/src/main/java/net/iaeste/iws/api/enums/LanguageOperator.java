/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.enums.LanguageOperator
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
 * Possible choices for selecting if something is required or optional
 *
 * @author  Marko Cilimkovic / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 * @noinspection EnumeratedConstantNamingConvention
 */
public enum LanguageOperator {

    A("And"),
    O("Or");

    private final String description;

    LanguageOperator(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
