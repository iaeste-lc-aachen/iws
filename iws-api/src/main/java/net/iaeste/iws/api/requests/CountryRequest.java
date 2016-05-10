/*
 * Licensed to IAESTE A.s.b.l. (IAESTE) under one or more contributor
 * license agreements.  See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership. The Authors
 * (See the AUTHORS file distributed with this work) licenses this file to
 * You under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a
 * copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.iaeste.iws.api.requests;

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.dtos.Country;
import net.iaeste.iws.api.enums.Action;
import net.iaeste.iws.api.util.Verifications;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "countryRequest", propOrder = {"country", "action"})
public final class CountryRequest extends Verifications implements Actionable {

    /** {@link IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;

    /** Default allowed Actions for the Country Request. */
    private static final Set<Action> ALLOWED = EnumSet.of(Action.PROCESS);

    @XmlElement(required = true)
    private Country country = null;

    /**
     * <p>Action to perform on a Country, by default we're assuming that it
     * should be processed, i.e. either created or updated.</p>
     */
    @XmlElement(required = true) private Action action = Action.PROCESS;

    // =========================================================================
    // Object Constructors
    // =========================================================================

    /**
     * Empty Constructor, to use if the setters are invoked. This is required
     * for WebServices to work properly.
     */
    public CountryRequest() {
        // Required for WebServices to work. Comment added to please Sonar.
    }

    /**
     * Default Constructor, for creating or updating a country.
     *
     * @param country Country Object
     */
    public CountryRequest(final Country country) {
        setCountry(country);
    }

    // =========================================================================
    // Standard Setters & Getters
    // =========================================================================

    /**
     * <p>Sets the internal Country Object, provided that the given Object is
     * valid, i.e. that it is not null and that it passes the verification
     * test.</p>
     *
     * <p>Upon successful verification, a copy of the given Object is stored
     * internally.</p>
     *
     * @param country Country
     */
    public void setCountry(final Country country) {
        ensureNotNullAndVerifiable("country", country);
        this.country = new Country(country);
    }

    /**
     * Returns a copy of the internal Country Object.
     *
     * @return Copy of the Country Object
     */
    public Country getCountry() {
        return new Country(country);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAction(final Action action) {
        ensureNotNullAndContains("action", action, ALLOWED);
        this.action = action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action getAction() {
        return action;
    }

    // =========================================================================
    // Standard Request Methods
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Action> allowedActions() {
        return immutableSet(ALLOWED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> validate() {
        final Map<String, String> validation = new HashMap<>(0);

        isNotNull(validation, "country", country);
        isNotNull(validation, "action", action);

        return validation;
    }
}
