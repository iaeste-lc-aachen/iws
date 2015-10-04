/*
 * =============================================================================
 * Copyright 1998-2015, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.responses.FetchFolderResponse
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.responses;

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.constants.IWSError;
import net.iaeste.iws.api.dtos.Folder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.1
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fetchFolderResponse", propOrder = { "folder" })
public final class FetchFolderResponse extends FallibleResponse {

    /** {@link IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;

    /** The Found Folder Object. */
    @XmlElement(required = true, nillable = true)
    private Folder folder = null;

    // =========================================================================
    // Object Constructors
    // =========================================================================

    /**
     * Empty Constructor, to use if the setters are invoked. This is required
     * for WebServices to work properly.
     */
    public FetchFolderResponse() {
    }

    /**
     * Default Constructor.
     *
     * @param folder Meta data
     */
    public FetchFolderResponse(final Folder folder) {
        setFolder(folder);
    }

    /**
     * Error Constructor.
     *
     * @param error   IWS Error Object
     * @param message Error Message
     */
    public FetchFolderResponse(final IWSError error, final String message) {
        super(error, message);
    }

    // =========================================================================
    // Standard Setters & Getters
    // =========================================================================

    public void setFolder(final Folder folder) {
        this.folder = new Folder(folder);
    }

    public Folder getFolder() {
        return new Folder(folder);
    }

    // =========================================================================
    // Standard Response Methods
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FetchFolderResponse)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }

        final FetchFolderResponse that = (FetchFolderResponse) obj;
        return !((folder != null) ? !folder.equals(that.folder) : (that.folder != null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();

        result = IWSConstants.HASHCODE_MULTIPLIER * result + ((folder != null) ? folder.hashCode() : 0);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "FetchFolderResponse{" +
                "folder=" + folder +
                '}';
    }
}
