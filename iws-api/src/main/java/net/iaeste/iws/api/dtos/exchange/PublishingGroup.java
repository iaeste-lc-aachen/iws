/*
 * =============================================================================
 * Copyright 1998-2014, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.dtos.exchange.PublishingGroup
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.dtos.exchange;

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.dtos.Group;
import net.iaeste.iws.api.util.AbstractVerification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Publishing Group, is a helping tool for the Exchange process, so it is
 * possible to save a selection of countries to make an exchange with. Examples
 * of usage, EU, Latin America, French Speaking countries, etc.<br />
 *   The Group consists of a name and the selection of countries. And it is
 * important to note, that a Publishing Group is only available for the country
 * of origin, meaning that no other Committees than the owning Committee may
 * see this Publishing Group.
 *
 * @author  Sondre Naustdal / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
public final class PublishingGroup extends AbstractVerification {

    /** {@link IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;

    private static final int MAX_NAME_LENGTH = 50;

    /**
     * The Id of the Publishing Group. If null, then a new will be created.
     */
    private String publishingGroupId = null;

    /**
     * The name of the Publishing Group, the name must be non-null and unique
     * for the Committee, and will not be shared or shown to other Committees.
     */
    private String name = null;

    /** The List of Committees, to make up this Publishing Group. */
    private List<Group> groups = null;

    // =========================================================================
    // Object Constructors
    // =========================================================================

    /**
     * Empty Constructor, to use if the setters are invoked. This is required
     * for WebServices to work properly.
     */
    public PublishingGroup() {
    }

    /**
     * Default Constructor, for creating a new Publishing Group.
     *
     * @param name   Name of the Group
     * @param groups List of Countries to be part of this Publishing Group
     */
    public PublishingGroup(final String name, final List<Group> groups) {
        setName(name);
        setGroups(groups);
    }

    /**
     * Default Constructor, for maintaining a Publishing Group.
     *
     * @param publishingGroupId The Id of the existing Publishing Group
     * @param name              Name of the Group
     * @param groups            List of Countries to be part of this Publishing Group
     */
    public PublishingGroup(final String publishingGroupId, final String name, final List<Group> groups) {
        setPublishingGroupId(publishingGroupId);
        setName(name);
        setGroups(groups);
    }

    /**
     * Copy Constructor.
     *
     * @param publishingGroup PublishingGroup Object to copy
     */
    public PublishingGroup(final PublishingGroup publishingGroup) {
        if (publishingGroup != null) {
            publishingGroupId = publishingGroup.publishingGroupId;
            name = publishingGroup.name;
            groups = publishingGroup.groups;
        }
    }

    // =========================================================================
    // Standard Setters & Getters
    // =========================================================================

    /**
     * Sets the Id of the PublishingGroup. The value is generated by the IWS, if
     * set, then the system will assume that an record with this Id already
     * exists and attempt to process it, causing an error if it doesn't. If not
     * set, then the IWS will assume that this is a new record, and will
     * likewise throw an error of a matching record exists.<br />
     *   The method will throw an {@code IllegalArgumentException} if the given
     * value is not a valid Id.
     *
     * @param publishingGroupId PublishingGroup Id
     * @throws IllegalArgumentException if the given value is invalid
     */
    public void setPublishingGroupId(final String publishingGroupId) {
        ensureValidId("publishingGroupId", publishingGroupId);
        this.publishingGroupId = publishingGroupId;
    }

    public String getPublishingGroupId() {
        return publishingGroupId;
    }

    /**
     * Sets the name of the {@code PublishingGroup} to the given. If the name is
     * null, not set (empty) or too long (a maximum of 50 characters is
     * allowed), then the method will thrown an
     * {@code IllegalArgumentException}.
     *
     * @param name PublishingGroup Name
     */
    public void setName(final String name) {
        ensureNotNullOrEmptyOrTooLong("name", name, MAX_NAME_LENGTH);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Sets the Groups for this {@code PublishingGroup} Object. The Groups must
     * be a valid list, i.e. not null. If the list is invalid, then the method
     * will thrown an {@code IllegalArgumentException}.
     *
     * @param groups PublishingGroup Group List
     */
    public void setGroups(final List<Group> groups) {
        ensureNotNull("groups", groups);
        this.groups = groups;
    }

    public List<Group> getGroups() {
        return groups;
    }

    // =========================================================================
    // DTO required methods
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> validate() {
        final Map<String, String> validation = new HashMap<>(0);

        // As all fields are validated, if set - we just need to check that the
        // required fields are not null
        isNotNull(validation, "name", name);
        isNotNull(validation, "groups", groups);

        return validation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PublishingGroup)) {
            return false;
        }

        final PublishingGroup that = (PublishingGroup) obj;

        if ((groups != null) ? !groups.equals(that.groups) : (that.groups != null)) {
            return false;
        }

        if ((publishingGroupId != null) ? !publishingGroupId.equals(that.publishingGroupId) : (that.publishingGroupId != null)) {
            return false;
        }

        return !((name != null) ? !name.equals(that.name) : (that.name != null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = IWSConstants.HASHCODE_INITIAL_VALUE;

        result = IWSConstants.HASHCODE_MULTIPLIER * result + ((publishingGroupId != null) ? publishingGroupId.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + ((name != null) ? name.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + ((groups != null) ? groups.hashCode() : 0);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "PublishingGroup{" +
                "publishingGroupId='" + publishingGroupId + '\'' +
                ", name='" + name + '\'' +
                ", groups=" + groups +
                '}';
    }
}
