/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.dtos.Change
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.dtos;

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.util.Copier;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * If requested, all changes can be monitored internally. The monitoring means
 * that the changes are stored, either complete or just the individual fields,
 * in a history.<br />
 *   This Object contain the list of differences for a single update. Complete
 * with who and when. Note, that the control what is to be stored, is set by the
 * Group. By default. the IWS will try to uphold the strictest privacy
 * guidelines, meaning that the initial settings is to not store any such
 * information.<br />
 *   It is important to note, that this Object is reading out information that
 * is stored as part of a previous change, meaning that it is not possible to
 * make any changes using this Object.
 *
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 */
public final class Change implements Serializable {

    /** {@link IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;

    private User user = null;
    private Group group = null;
    private List<Field> fields = null;
    private Date changed = null;

    // =========================================================================
    // Object Constructors
    // =========================================================================

    /**
     * Empty Constructor, to use if the setters are invoked. This is required
     * for WebServices to work properly.
     */
    public Change() {
    }

    /**
     * Default Constructor.
     *
     * @param user    The user who made this change
     * @param group   The group, which the Object belongs to
     * @param fields  The changed fields
     * @param changed The date of the change
     */
    public Change(final User user, final Group group, final List<Field> fields, final Date changed) {
        this.user = user;
        this.group = group;
        this.fields = Copier.copy(fields);
        this.changed = Copier.copy(changed);
    }

    /**
     * Copy Constructor.
     *
     * @param change Change Object to copy
     */
    public Change(final Change change) {
        if (change != null) {
            user = change.user;
            group = change.group;
            fields = Copier.copy(change.fields);
            changed = Copier.copy(change.changed);
        }
    }

    // =========================================================================
    // Standard Setters & Getters
    // =========================================================================

    /**
     * The User who made the change.
     *
     * @param user User
     */
    public void setUser(final User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    /**
     * The Group, which the Data belongs too.
     *
     * @param group Owning Group
     */
    public void setGroup(final Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    /**
     * List of all Fields which was changed.
     *
     * @param fields Changed Fields
     */
    public void setFields(final List<Field> fields) {
        this.fields = Copier.copy(fields);
    }

    public List<Field> getFields() {
        return Copier.copy(fields);
    }

    /**
     * The Date of the change.
     *
     * @param changed Date of Change
     */
    public void setChanged(final Date changed) {
        this.changed = Copier.copy(changed);
    }

    public Date getChanged() {
        return Copier.copy(changed);
    }

    // =========================================================================
    // Standard DTO Methods
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Change)) {
            return false;
        }

        final Change change = (Change) obj;

        if (changed != null ? !changed.equals(change.changed) : change.changed != null) {
            return false;
        }

        if (fields != null ? !fields.equals(change.fields) : change.fields != null) {
            return false;
        }

        if (group != null ? !group.equals(change.group) : change.group != null) {
            return false;
        }

        return !(user != null ? !user.equals(change.user) : change.user != null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = IWSConstants.HASHCODE_INITIAL_VALUE;

        result = IWSConstants.HASHCODE_MULTIPLIER * result + (user != null ? user.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + (group != null ? group.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + (fields != null ? fields.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + (changed != null ? changed.hashCode() : 0);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Change{" +
                "user=" + user +
                ", group=" + group +
                ", fields=" + fields +
                ", changed=" + changed +
                '}';
    }
}
