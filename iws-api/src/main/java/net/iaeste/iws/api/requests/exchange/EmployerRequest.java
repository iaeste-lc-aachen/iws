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
package net.iaeste.iws.api.requests.exchange;

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.dtos.exchange.Employer;
import net.iaeste.iws.api.enums.Action;
import net.iaeste.iws.api.requests.Actions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employerRequest", propOrder = { "employer", "employerId" })
public final class EmployerRequest extends Actions {

    /** {@link IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;

    @XmlElement(required = true, nillable = true)  private Employer employer = null;
    @XmlElement(required = true, nillable = true)  private String employerId = null;

    // =========================================================================
    // Object Constructors
    // =========================================================================

    /**
     * Empty Constructor, to use if the setters are invoked. This is required
     * for WebServices to work properly.
     */
    public EmployerRequest() {
        super(EnumSet.of(Action.PROCESS, Action.DELETE), Action.PROCESS);
    }

    // =========================================================================
    // Standard Setters & Getters
    // =========================================================================

    public void setEmployer(final Employer employer) {
        ensureVerifiable("employer", employer);
        this.employer = new Employer(employer);
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployerId(final String employerId) {
        this.employerId = employerId;
    }

    public String getEmployerId() {
        return employerId;
    }

    // =========================================================================
    // Standard Request Methods
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> validate() {
        final Map<String, String> validation = new HashMap<>(0);

        isNotNull(validation, FIELD_ACTION, action);
        if (action != null) {
            switch (action) {
                case PROCESS:
                    isNotNull(validation, "employer", employer);
                    break;
                case DELETE:
                    isNotNull(validation, "employerId", employerId);
                    break;
                default:
                    validation.put(FIELD_ACTION, "The Action '" + action + "' is not allowed");
            }
        }

        return validation;
    }
}
