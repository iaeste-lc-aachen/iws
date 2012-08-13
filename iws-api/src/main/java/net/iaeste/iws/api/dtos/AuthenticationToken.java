/*
 * =============================================================================
 * Copyright 1998-2012, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - AuthenticationToken
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
import net.iaeste.iws.api.exceptions.VerificationException;
import net.iaeste.iws.api.requests.Verifiable;

/**
 * All requests (with the exception of the initial Authorization request) is
 * made with an Object if this type as the first parameter. The Token contains
 * enough information to positively identify the user, who initiated a given
 * Request.
 *
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 * @noinspection SuppressionAnnotation
 */
public final class AuthenticationToken implements Verifiable {

    /** {@link IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;

    // The length of the supported Hashcode algorithms
    private static final int LENGTH_SHA2_512 = 128;
    private static final int LENGTH_SHA2_384 = 96;
    private static final int LENGTH_SHA2_256 = 64;
    private static final int LENGTH_MD5 = 32;

    /** The actual token, stored as an ASCII value. */
    private String token = null;

    /** For Group Authorization, the GroupId must also be provided. */
    private Integer groupId = null;

    /**
     * Empty Constructor, to use if the setters are invoked. This is required
     * for WebServices to work properly.
     */
    public AuthenticationToken() {
    }

    /**
     * Default Constructor.
     *
     * @param  token  The Token, i.e. currently active Cryptographical Checksum
     */
    public AuthenticationToken(final String token) {
        this.token = token;
    }

    /**
     * Full Constructor, for operations where it is not possible to uniquely
     * identify the Group for the request.
     *
     * @param  token  The Token, i.e. currently active Cryptographical Checksum
     * @param groupId GroupId for the Authorization check
     */
    public AuthenticationToken(final String token, final Integer groupId) {
        this.token = token;
        this.groupId = groupId;
    }

    /**
     * Copy Constructor.
     *
     * @param authenticationToken  AuthenticationToken Object to copy
     */
    public AuthenticationToken(final AuthenticationToken authenticationToken) {
        token = authenticationToken != null ? authenticationToken.token : null;
    }

    /**
     * Sets the users Cryptographical Authentication Token.
     *
     * @param  token  Cryptographical Authentication Token
     */
    public void setToken(final String token) {
        this.token = token;
    }

    /**
     * Retrieves the Token from the object.
     *
     * @return Cryptographical Token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the GroupId, for which the user wishes invoke a functionality. This
     * is required, if the functionality cannot be uniquely identified for the
     * user based on the implicit UserId & PermissionId.
     *
     * @param groupId  GroupId for the Authorization check
     */
    public void setGroupId(final Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * Retrieves the GroupId, for which the user wishes to invoke a
     * functionality.
     *
     * @return GroupIs for the Authorization check
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void verify() throws VerificationException {
        if ((token == null) || token.isEmpty()) {
            throw new VerificationException("The Token is undefined (null or empty).");
        }

        // The token should have a length, matching one of the allowed hashing
        // algorithms. If not, then we'll throw an exception.
        switch (token.length()) {
            case LENGTH_SHA2_512:
            case LENGTH_SHA2_384:
            case LENGTH_SHA2_256:
            case LENGTH_MD5:
                break;
            default:
                throw new VerificationException("The Token is invalid, the content is an unsupported or unallowed cryptographical hash value.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        //noinspection CastToConcreteClass
        final AuthenticationToken that = (AuthenticationToken) obj;

        return token != null ? token.equals(that.token) : that.token == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = IWSConstants.HASHCODE_INITIAL_VALUE;

        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (token != null ? token.hashCode() : 0);

        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "AuthenticationToken[token=" + token + ']';
    }
}