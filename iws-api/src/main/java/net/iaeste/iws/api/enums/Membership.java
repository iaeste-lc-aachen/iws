/*
 * =============================================================================
 * Copyright 1998-2012, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.enums.Membership
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
public enum Membership {

    /**
     * The list starts with the ordinal value 1, thus this is a placeholder, to
     * ensure that the values are correct. It should never be used!
     */
    UNKNOWN,

    /**
     * Status; Full Member.
     */
    FULL_MEMBER,

    /**
     * Status; Associate Member
     */
    ASSOCIATE_MEMBER,

    /**
     * Status; Co-operating Institution
     */
    COOPERATING_INSTITUTION,

    /**
     * Status; Former Member, i.e. country had earlier Full,Associate or
     * Co-operating membership status.
     */
    FORMER_MEMBER,

    /**
     * List of known countries, which is not having status as either Full,
     * Associate, Co-operating or Former member.
     */
    LISTED,

    /**
     * List of UN countries, which for various reasons is not necessary to list
     * in the ordinary list.
     */
    UNLISTED,

    /**
     * Self Administrated Regions or SAR's, are countries which have their own
     * entry in the UN table, can act in almost all capacities on its own, but
     * is for various reasons controlled by a different entity.<br />
     *   Example; Greenland, is a SAR under Denmark.
     */
    SELF_ADMINISTRATED_REGION

}