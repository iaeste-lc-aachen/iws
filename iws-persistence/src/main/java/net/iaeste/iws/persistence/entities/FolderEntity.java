/*
 * =============================================================================
 * Copyright 1998-2016, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-persistence) - net.iaeste.iws.persistence.entities.FolderEntity
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

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.enums.MonitoringLevel;
import net.iaeste.iws.api.enums.Privacy;
import net.iaeste.iws.persistence.Externable;
import net.iaeste.iws.persistence.monitoring.Monitored;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.1
 */
@Entity
@Table(name = "folders")
@Monitored(name = "Folder", level = MonitoringLevel.DETAILED)
public class FolderEntity extends AbstractUpdateable<FolderEntity> implements Externable<FolderEntity> {

    /** {@link IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "folder_sequence")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence")
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id = null;

    /**
     * The content of this Entity is exposed externally, however to avoid that
     * someone tries to spoof the system by second guessing our Sequence values,
     * An External Id is used, the External Id is a unique UUID value, which in
     * all external references is referred to as the "Id". Although this can be
     * classified as StO (Security through Obscurity), there is no need to
     * expose more information than necessary.
     */
    @Column(name = "external_id", length = 36, unique = true, nullable = false, updatable = false)
    private String externalId = null;

    @Column(name = "parent_id", updatable = false)
    private Long parentId = null;

    /**
     * Some Files are of a general Purpose nature, and thus cannot have a Group
     * associated, which will otherwise limit the viewing.
     */
    @ManyToOne(targetEntity = GroupEntity.class)
    @JoinColumn(name = "group_id", referencedColumnName = "id", updatable = false)
    private GroupEntity group = null;

    @Monitored(name="Folder Name", level = MonitoringLevel.DETAILED)
    @Column(name = "foldername", length = 100, nullable = false)
    private String foldername = null;

    @Enumerated(EnumType.STRING)
    @Column(name = "privacy", nullable = false)
    private Privacy privacy = null;

    @Column(name = "old_iw3_file_id")
    private Long oldIW3FileId = null;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified", nullable = false)
    private Date modified = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created = new Date();

    // =========================================================================
    // Entity Setters & Getters
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setId(final Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setExternalId(final String externalId) {
        this.externalId = externalId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getExternalId() {
        return externalId;
    }

    public final void setParentId(final Long parentId) {
        this.parentId = parentId;
    }

    public final Long getParentId() {
        return parentId;
    }

    public final void setGroup(final GroupEntity group) {
        this.group = group;
    }

    public final GroupEntity getGroup() {
        return group;
    }

    public final void setFoldername(final String foldername) {
        this.foldername = foldername;
    }

    public final String getFoldername() {
        return foldername;
    }

    public final void setPrivacy(final Privacy privacy) {
        this.privacy = privacy;
    }

    public final Privacy getPrivacy() {
        return privacy;
    }

    public final void setOldIW3FileId(final Long oldIW3FileId) {
        this.oldIW3FileId = oldIW3FileId;
    }

    public final Long getOldIW3FileId() {
        return oldIW3FileId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setModified(final Date modified) {
        this.modified = modified;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Date getModified() {
        return modified;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setCreated(final Date created) {
        this.created = created;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Date getCreated() {
        return created;
    }

    // =========================================================================
    // Other Methods required for this Entity
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean diff(final FolderEntity obj) {
        // Until properly implemented, better return true to avoid that we're
        // missing updates!
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void merge(final FolderEntity obj) {
        if (canMerge(obj)) {
            // Note; Id & ExternalId are *not* allowed to be updated!
            foldername = which(foldername, obj.foldername);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return "FolderEntity{" +
                "externalId='" + externalId + '\'' +
                ", parentId=" + parentId +
                ", group=" + group +
                ", foldername='" + foldername + '\'' +
                ", privacy='" + privacy + '\'' +
                '}';
    }
}
