/*
 * =============================================================================
 * Copyright 1998-2012, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.responses.AuthenticationResponse
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
import net.iaeste.iws.api.data.AuthenticationToken;

/**
 * The Result Object from an Authentication Call, the object will either
 * contain a successful result, i.e. an AuthenticationToken for the requested
 * user. Or it will contain an error message.
 *
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 * @noinspection SuppressionAnnotation
 */
public final class AuthenticationResponse extends AbstractResponse {

    /** {@link IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;

    private AuthenticationToken token;

    /**
     * Empty Constructor, to use if the setters are invoked. This is required
     * for WebServices to work properly.
     */
    public AuthenticationResponse() {
        token = null;
    }

    /**
     * Default Constructor.
     *
     * @param token Authentication Token
     */
    public AuthenticationResponse(final AuthenticationToken token) {
        this.token = token;
    }

    /**
     * Error Constructor.
     *
     * @param error    IWS Error Object
     * @param message  Error Message
     */
    public AuthenticationResponse(final IWSError error, final String message) {
        super(error, message);

        token = null;
    }

    /**
     * Sets the users Authentication Token.
     *
     * @param token Authentication Token
     */
    public void setToken(final AuthenticationToken token) {
        this.token = token;
    }

    /**
     * Returns the users Authentication Token.
     *
     * @return Authentication Token
     */
    public AuthenticationToken getToken() {
        return token;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AuthenticationResponse)) {
            return false;
        }

        //noinspection CastToConcreteClass
        final AuthenticationResponse that = (AuthenticationResponse) obj;
        return !(token != null ? !token.equals(that.token) : that.token != null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = super.hashCode();

        hash = IWSConstants.HASHCODE_MULTIPLIER * hash + (token != null ? token.hashCode() : 0);

        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "AuthenticationResponse[token=" + token + ",error=" + error + ",message=" + message + ']';
    }
}