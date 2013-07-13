/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-client) - net.iaeste.iws.client.ExchangeClient
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

import net.iaeste.iws.api.Exchange;
import net.iaeste.iws.api.dtos.AuthenticationToken;
import net.iaeste.iws.api.requests.exchange.*;
import net.iaeste.iws.api.responses.exchange.*;
import net.iaeste.iws.api.util.Fallible;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 * @noinspection OverlyCoupledClass
 */
public final class ExchangeClient implements Exchange {

    private final Exchange client;

    /**
     * Default Constructor.
     */
    public ExchangeClient() {
        client = ClientFactory.getInstance().getExchangeImplementation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployerResponse processEmployer(final AuthenticationToken token, final ProcessEmployerRequest request) {
        return client.processEmployer(token, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FetchEmployerInformationResponse fetchEmployers(final AuthenticationToken token, final FetchEmployerInformationRequest request) {
        return client.fetchEmployers(token, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OfferResponse processOffer(final AuthenticationToken token, final ProcessOfferRequest request) {
        return client.processOffer(token, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OfferResponse deleteOffer(final AuthenticationToken token, final DeleteOfferRequest request) {
        return client.deleteOffer(token, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FetchOffersResponse fetchOffers(final AuthenticationToken token, final FetchOffersRequest request) {
        return client.fetchOffers(token, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fallible processOfferTemplate(final AuthenticationToken token, final OfferTemplateRequest request) {
        return client.processOfferTemplate(token, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FetchOfferTemplateResponse fetchOfferTemplates(final AuthenticationToken token, final FetchOfferTemplatesRequest request) {
        return client.fetchOfferTemplates(token, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fallible processPublishGroup(final AuthenticationToken token, final PublishGroupRequest request) {
        return client.processPublishGroup(token, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FetchPublishGroupResponse fetchPublishGroups(final AuthenticationToken token, final FetchPublishGroupsRequest request) {
        return client.fetchPublishGroups(token, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FetchGroupsForSharingResponse fetchGroupsForSharing(final AuthenticationToken token, final FetchGroupsForSharingRequest request) {
        return client.fetchGroupsForSharing(token, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PublishOfferResponse processPublishOffer(final AuthenticationToken token, final PublishOfferRequest request) {
        return client.processPublishOffer(token, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FetchPublishedGroupsResponse fetchPublishedGroups(final AuthenticationToken token, final FetchPublishedGroupsRequest request) {
        return client.fetchPublishedGroups(token, request);
    }
}
