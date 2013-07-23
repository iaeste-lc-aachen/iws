/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-persistence) - net.iaeste.iws.persistence.entities.Mergeable
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

import java.util.Date;

/**
 * Classes implementing this interface, are capable of updating the current
 * content, with the content of a second IWSEntity of the same type (and Id).
 *
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 */
public interface Updateable<T> extends IWSEntity {

    /**
     * Allows a merge between two objects of the same type. The method updates
     * the current object with the changes from the second.<br />
     *   Both Objects must be persisted beforehand, i.e. have Id values, and
     * these Id's differ, no merge will take place. Merging is purely intended
     * for updating Objects, where we wish to control which fields are updated.
     */
    void merge(T obj);

    void setExternalId(String externalId);

    String getExternalId();

    /**
     * To ensure that the field modified is always updated, it must be
     * controlled by the DAO, as part of the update process. Hence, it must be
     * possible to set the Modified field to a given Date.
     *
     * @param modified Date of Modification
     */
    void setModified(Date modified);

    Date getModified();
}