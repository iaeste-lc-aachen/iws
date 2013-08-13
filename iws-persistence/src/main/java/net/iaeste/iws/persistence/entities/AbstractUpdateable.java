/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-persistence) - net.iaeste.iws.persistence.entities.AbstractUpdateable
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.persistence.entities;

/**
 * @author Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since 1.7
 */
public abstract class AbstractUpdateable<T> implements Updateable<T> {

    /**
     * Returns 1 (one) if the two Objects are different, otherwise a 0 (zero)
     * is returned.
     *
     * @param first  The Object to compare against
     * @param second The other Object to compare with
     * @return 1 (one) if the Objects are different, otherwise 0 (zero)
     */
    protected static <T> int different(final T first, final T second) {
        return (first != null ? first.equals(second) : (second == null)) ? 0 : 1;
    }

}
