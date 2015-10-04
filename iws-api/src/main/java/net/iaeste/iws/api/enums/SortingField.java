/*
 * =============================================================================
 * Copyright 1998-2015, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.enums.SortingField
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

import javax.xml.bind.annotation.XmlType;

/**
 * This enum contains all allowed fields to be sorted by. However, the
 * individual requess will only allow a sub-set of these, so please check the
 * documentation in the Request for the actually allowed fields.
 *
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
@XmlType(name = "sortingField")
public enum SortingField {

    /**
     * The default sort order is the date of creation (timestamp), i.e. at which
     * time the Object was saved in the database.
     */
    CREATED,

    /**
     * The principel name of the Object to sort by. For Offers, this is the
     * Reference Number.
     */
    NAME
}
