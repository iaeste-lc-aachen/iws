/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.enums.exchange.OfferState
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.enums.exchange;

import net.iaeste.iws.api.constants.IWSConstants;

/**
 * Defines the possible states for an Offer
 *
 * @author  Matej Kosco / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 */
public enum OfferState {

    /**
     * In an offer is not shared
     */
    NEW,

    /**
     * If an offer is shared to multiple countries
     */
    SHARED,

    /**
     * If an offer is shared to one country only
     */
    EXCHANGED,

    /**
     * If an offer has student applications
     */
    APPLICATIONS,

    /**
     * If an offer has student nominations
     */
    NOMINATIONS;

    public String getDescription() {
        return name().substring(0,1) + name().substring(1).toLowerCase(IWSConstants.DEFAULT_LOCALE);
    }
}
