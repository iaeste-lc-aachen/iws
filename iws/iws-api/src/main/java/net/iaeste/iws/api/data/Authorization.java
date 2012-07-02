/*
 * =============================================================================
 * Copyright 1998-2012, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.data.Authorization
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.data;

import net.iaeste.iws.api.enums.GroupType;
import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.enums.Permission;

import java.io.Serializable;

/**
 * Contains the information about a permission.
 *
 * @author  Kim Jensen
 * @version $Revision:$ / $Date:$
 * @since   1.7
 * @noinspection SuppressionAnnotation
 */
public class Authorization implements Serializable {

    /** {@link IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;

    private Permission permission;
    private GroupType groupType;

    /**
     * Empty Constructor, required for some communication frameworks.
     */
    public Authorization() {
        permission = null;
        groupType = null;
    }

    /**
     * Default Constructor.
     *
     * @param permission  Authorization
     * @param groupType   Group Type
     */
    public Authorization(final Permission permission, final GroupType groupType) {
        this.permission = permission;
        this.groupType = groupType;
    }

    public void setPermission(final Permission permission) {
        this.permission = permission;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setGroupType(final GroupType groupType) {
        this.groupType = groupType;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Authorization)) {
            return false;
        }

        //noinspection CastToConcreteClass
        final Authorization that = (Authorization) obj;

        if (groupType != null ? groupType != that.groupType : that.groupType != null) {
            return false;
        }

        return !(permission != null ? permission != that.permission : that.permission != null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = IWSConstants.HASHCODE_INITIAL_VALUE;

        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (permission != null ? permission.hashCode() : 0);
        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (groupType != null ? groupType.hashCode() : 0);

        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Authorization{" +
                "permission='" + permission + '\'' +
                ", groupType='" + groupType + '\'' +
                '}';
    }
}
