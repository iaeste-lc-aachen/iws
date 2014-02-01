/*
 * =============================================================================
 * Copyright 1998-2014, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.dtos.File
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

import static net.iaeste.iws.api.util.Copier.copy;

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.util.AbstractVerification;
import net.iaeste.iws.api.util.Date;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
public final class File extends AbstractVerification {

    /** {@link IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;

    private String fileId = null;
    private Group group = null;
    private User user = null;
    private String filename = null;
    // Filedata is omitted for equals, hashCode and toString, since the data
    // can be rather large
    private byte[] filedata = null;
    private Integer filesize = null;
    private String mimetype = null;
    private String description = null;
    private String keywords = null;
    private Long checksum = null;
    private Date modified = new Date();
    private Date created = new Date();

    // =========================================================================
    // Object Constructors
    // =========================================================================

    /**
     * Empty Constructor, to use if the setters are invoked. This is required
     * for WebServices to work properly.
     */
    public File() {
    }

    /**
     * Copy Constructor.
     *
     * @param file File Object to copy
     */
    public File(final File file) {
        if (file != null) {
            fileId = file.fileId;
            group = new Group(file.group);
            user = new User(file.user);
            filename = file.filename;
            filedata = copy(file.filedata);
            filesize = file.filesize;
            mimetype = file.mimetype;
            description = file.description;
            keywords = file.keywords;
            checksum = file.checksum;
            modified = file.modified;
            created = file.created;
        }
    }

    // =========================================================================
    // Standard Setters & Getters
    // =========================================================================

    /**
     * Sets the File Id for this File Object. The Id is generated by the IWS
     * upon saving the File.<br />
     *   Note, that if the value is invalid, then the method will thrown an
     * {@code IllegalArgumentException}.
     *
     * @param fileId File Id
     * @throws IllegalArgumentException if the File Id is invalid
     */
    public void setFileId(final String fileId) throws IllegalArgumentException {
        ensureValidId("fileId", fileId);
        this.fileId = fileId;
    }

    public String getFileId() {
        return fileId;
    }

    /**
     * The owning group for this file. All file management is handled via the
     * Group, and this this is a mandatory field.<br />
     *   The method will throw an {@code IllegalArgumentException} if the Group
     * is either null or invalid.
     *
     * @param group Group
     * @throws IllegalArgumentException if null or not verifiable
     */
    public void setGroup(final Group group) throws IllegalArgumentException {
        ensureNotNullAndVerifiable("group", group);
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    /**
     * Sets the User who uploaded the file. The field is mandatory, which means
     * that the method will thrown an {@code IllegalArgumentException} if the
     * value is either null or not verifiable.
     *
     * @param user User who uploaded the File
     * @throws IllegalArgumentException if null or not verifiable
     */
    public void setUser(final User user) throws IllegalArgumentException {
        ensureNotNullAndVerifiable("user", user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    /**
     * The fileName is mandatory in the IWS, and if not set or too long, the
     * method will throw an {@code IllegalArgumentException}.
     *
     * @param filename Name of the File
     * @throws IllegalArgumentException if null, empty or too long
     */
    public void setFilename(final String filename) throws IllegalArgumentException {
        ensureNotNullOrEmptyOrTooLong("filename", filename, 100);
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    /**
     * Sets the data for the File Object. The data is read as a raw byte array,
     * and stored as such internally. However, the byte array may not exceed
     * 25MB, if it exceeds this length, then the method will thrown an
     * {@code IllegalArgumentException}.
     *
     * @param filedata Raw file data
     * @throws IllegalArgumentException if the byte array is too long
     */
    public void setFiledata(final byte[] filedata) throws IllegalArgumentException {
        ensureNotTooLong("filedata", filedata, 26214400);
        this.filedata = copy(filedata);
    }

    public byte[] getFiledata() {
        return copy(filedata);
    }

    /**
     * The filesize is set by the IWS internally, and simply matches the size of
     * the file data upon creation.
     *
     * @param filesize Size of the File
     */
    public void setFilesize(final Integer filesize) {
        this.filesize = filesize;
    }

    public Integer getFilesize() {
        return filesize;
    }

    /**
     * The file MIME Type, which is used for Clients to determine what
     * Application should be used to handle the file.<br />
     *   The method will throw an {@code IllegalArgumentException} if the
     * MIME Type is too long.
     *
     * @param mimetype File MIME Type
     * @throws java.lang.IllegalArgumentException if the MIME Type value exceeds 50 characters
     */
    public void setMimetype(final String mimetype) throws IllegalArgumentException {
        ensureNotTooLong("mimetype", mimetype, 50);
        this.mimetype = mimetype;
    }

    public String getMimetype() {
        return mimetype;
    }

    /**
     * Sets the description of the File, the Description can be anything as long
     * as the length does not exceed the maximum allowed length of 250
     * characters.<br />
     *   The method will throw an {@code IllegalArgumentException} if the length
     * of the description exceeds 250 characters.
     *
     * @param description File Description
     * @throws IllegalArgumentException if the length exceeds 250 characters
     */
    public void setDescription(final String description) throws IllegalArgumentException {
        ensureNotTooLong("description", description, 250);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Sets the Keywords for the File, meaning a list of words used for indexing
     * the file. The Keywords are not used by the IWS, but is purely for client
     * side usage, and is thus an optional value.<br />
     *   The method will throw an {@code IllegalArgumentException} if the length
     * of the Keywords exceeds 250 characters.
     *
     * @param keywords File Keywords
     * @throws IllegalArgumentException if the length exceeds 250 characters.
     */
    public void setKeywords(final String keywords) throws IllegalArgumentException {
        ensureNotTooLong("keywords", keywords, 250);
        this.keywords = keywords;
    }

    public String getKeywords() {
        return keywords;
    }

    /**
     * The File Checksum, used for validation of the file, to ensure that the
     * data is not corrupted. The value is set by the IWS upon saving the File.
     *
     * @param checksum File Checksum
     */
    public void setChecksum(final Long checksum) {
        this.checksum = checksum;
    }

    public Long getChecksum() {
        return checksum;
    }

    /**
     * Sets the last time the file was modified. Note, that this value is
     * controlled by the IWS, so any changes to it will be ignored.
     *
     * @param modified Date of last modification
     */
    public void setModified(final Date modified) {
        this.modified = modified;
    }

    public Date getModified() {
        return modified;
    }

    /**
     * Sets the time the file was created. Note, that this value is controlled
     * by the IWS, so any changes to it will be ignored.
     *
     * @param created Date of creation
     */
    public void setCreated(final Date created) {
        this.created = created;
    }

    public Date getCreated() {
        return created;
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

        isNotNull(validation, "filename", filename);

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
        if (!(obj instanceof File)) {
            return false;
        }

        final File file = (File) obj;

        if ((checksum != null) ? !checksum.equals(file.checksum) : (file.checksum != null)) {
            return false;
        }
        if ((created != null) ? !created.equals(file.created) : (file.created != null)) {
            return false;
        }
        if ((description != null) ? !description.equals(file.description) : (file.description != null)) {
            return false;
        }
        if ((fileId != null) ? !fileId.equals(file.fileId) : (file.fileId != null)) {
            return false;
        }
        if ((filename != null) ? !filename.equals(file.filename) : (file.filename != null)) {
            return false;
        }
        if ((filesize != null) ? !filesize.equals(file.filesize) : (file.filesize != null)) {
            return false;
        }
        if ((group != null) ? !group.equals(file.group) : (file.group != null)) {
            return false;
        }
        if ((keywords != null) ? !keywords.equals(file.keywords) : (file.keywords != null)) {
            return false;
        }
        if ((mimetype != null) ? !mimetype.equals(file.mimetype) : (file.mimetype != null)) {
            return false;
        }
        if ((modified != null) ? !modified.equals(file.modified) : (file.modified != null)) {
            return false;
        }

        return !((user != null) ? !user.equals(file.user) : (file.user != null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = IWSConstants.HASHCODE_INITIAL_VALUE;

        result = IWSConstants.HASHCODE_MULTIPLIER * result + ((fileId != null) ? fileId.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + ((group != null) ? group.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + ((user != null) ? user.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + ((filename != null) ? filename.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + ((filesize != null) ? filesize.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + ((mimetype != null) ? mimetype.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + ((description != null) ? description.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + ((keywords != null) ? keywords.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + ((checksum != null) ? checksum.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + ((modified != null) ? modified.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + ((created != null) ? created.hashCode() : 0);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "File{" +
                "fileId='" + fileId + '\'' +
                ", group=" + group +
                ", user=" + user +
                ", filename='" + filename + '\'' +
                ", filesize=" + filesize +
                ", mimetype='" + mimetype + '\'' +
                ", description='" + description + '\'' +
                ", keywords='" + keywords + '\'' +
                ", checksum='" + checksum + '\'' +
                ", modified=" + modified +
                ", created=" + created +
                '}';
    }
}
