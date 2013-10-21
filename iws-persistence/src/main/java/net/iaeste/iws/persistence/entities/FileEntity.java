/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-persistence) - net.iaeste.iws.persistence.entities.FileEntity
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

import net.iaeste.iws.api.enums.FileType;
import net.iaeste.iws.common.monitoring.Monitored;
import net.iaeste.iws.common.monitoring.MonitoringLevel;
import net.iaeste.iws.persistence.Externable;

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
 * @since   1.7
 */
@Entity
@Table(name = "files")
@Monitored(name = "File", level = MonitoringLevel.DETAILED)
public class FileEntity implements Externable<FileEntity> {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "file_sequence")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pk_sequence")
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id = null;

    /**
     * The content of this Entity is exposed externally, however to avoid that
     * someone tries to spoof the system by second guessing our Sequence values,
     * An External Id is used, the External Id is a Uniqie UUID value, which in
     * all external references is referred to as the "Id". Although this can be
     * classified as StO (Security through Obscrutity), there is no need to
     * expose more information than necessary.
     */
    @Column(name = "external_id", length = 36, unique = true, nullable = false, updatable = false)
    private String externalId = null;

    /**
     * Some Files are of a general Purpose nature, and thus cannot have a Group
     * associated, which will otherwise limit the viewing.
     */
    @ManyToOne(targetEntity = GroupEntity.class)
    @JoinColumn(name = "group_id", updatable = false)
    private GroupEntity group = null;

    @Enumerated(EnumType.STRING)
    @Column(name = "filetype", nullable = false, updatable = false)
    private FileType filetype = FileType.FILE;

    @Monitored(name="File Name", level = MonitoringLevel.DETAILED)
    @Column(name = "filename", length = 100)
    private String filename = null;

    @Monitored(name="File Date", level = MonitoringLevel.DETAILED)
    @Column(name = "filedata")
    private byte[] filedata = null;

    @Monitored(name="File size", level = MonitoringLevel.DETAILED)
    @Column(name = "filesize")
    private Integer filesize = null;

    @Monitored(name="MIME Type", level = MonitoringLevel.DETAILED)
    @Column(name = "mimetype", length = 50)
    private String mimetype = null;

    @Column(name = "folder_id", nullable = false)
    private Integer folderId = null;

    @Monitored(name="Description", level = MonitoringLevel.DETAILED)
    @Column(name = "description", length = 250)
    private String description = null;

    @Monitored(name="Keywords", level = MonitoringLevel.DETAILED)
    @Column(name = "keywords", length = 250)
    private String keywords = null;

    @Monitored(name="Checksum", level = MonitoringLevel.DETAILED)
    @Column(name = "checksum", length = 128)
    private String checksum = null;

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
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setExternalId(final String externalId) {
        this.externalId = externalId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getExternalId() {
        return externalId;
    }

    public void setGroup(final GroupEntity group) {
        this.group = group;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setFiletype(final FileType filetype) {
        this.filetype = filetype;
    }

    public FileType getFiletype() {
        return filetype;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFiledata(final byte[] filedata) {
        this.filedata = filedata;
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFilesize(final Integer filesize) {
        this.filesize = filesize;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setMimetype(final String mimetype) {
        this.mimetype = mimetype;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setFolderId(final Integer folderId) {
        this.folderId = folderId;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setKeywords(final String keywords) {
        this.keywords = keywords;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setChecksum(final String checksum) {
        this.checksum = checksum;
    }

    public String getChecksum() {
        return checksum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setModified(final Date modified) {
        this.modified = modified;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getModified() {
        return modified;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCreated(final Date created) {
        this.created = created;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getCreated() {
        return created;
    }

    // =========================================================================
    // Other Methods required for this Entity
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean diff(final FileEntity obj) {
        // Until properly implemented, better return true to avoid that we're
        // missing updates!
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void merge(final FileEntity obj) {
        // don't merge if objects are not the same entity
        if ((id != null) && (obj != null) && externalId.equals(obj.externalId)) {
            // Note; Id & ExternalId are *not* allowed to be updated!
            filename = obj.filename;
            filedata = obj.filedata;
            filesize = obj.filesize;
            mimetype = obj.mimetype;
            description = obj.description;
            keywords = obj.keywords;
            checksum = obj.checksum;
        }
    }
}