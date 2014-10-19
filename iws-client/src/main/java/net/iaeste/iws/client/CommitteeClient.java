/*
 * =============================================================================
 * Copyright 1998-2014, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-client) - net.iaeste.iws.client.CommitteeClient
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.client;

import net.iaeste.iws.api.Committees;
import net.iaeste.iws.api.dtos.AuthenticationToken;
import net.iaeste.iws.api.requests.CommitteeRequest;
import net.iaeste.iws.api.requests.FetchSurveyOfCountryRequest;
import net.iaeste.iws.api.requests.InternationalGroupRequest;
import net.iaeste.iws.api.requests.SurveyOfCountryRequest;
import net.iaeste.iws.api.responses.FetchSurveyOfCountryRespose;
import net.iaeste.iws.api.util.Fallible;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
public final class CommitteeClient implements Committees {

    private final Committees client;

    /**
     * Default Constructor.
     */
    public CommitteeClient() {
        client = ClientFactory.getInstance().getCommitteeImplementation();
    }

    // =========================================================================
    // Implementation of methods from Committees in the API
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public Fallible processCommittee(final AuthenticationToken token, final CommitteeRequest request) {
        return client.processCommittee(token, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fallible processInternationalGroup(final AuthenticationToken token, final InternationalGroupRequest request) {
        return client.processInternationalGroup(token, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FetchSurveyOfCountryRespose fetchSurveyOfCountry(final AuthenticationToken token, final FetchSurveyOfCountryRequest request) {
        return client.fetchSurveyOfCountry(token, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fallible processSurveyOfCountry(final AuthenticationToken token, final SurveyOfCountryRequest request) {
        return client.processSurveyOfCountry(token, request);
    }
}
