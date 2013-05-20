/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-core) - net.iaeste.iws.core.ExchangeController
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.core;

import net.iaeste.iws.api.Exchange;
import net.iaeste.iws.api.dtos.AuthenticationToken;
import net.iaeste.iws.api.enums.Permission;
import net.iaeste.iws.api.exceptions.IWSException;
import net.iaeste.iws.api.requests.exchange.DeleteOfferRequest;
import net.iaeste.iws.api.requests.exchange.FacultyRequest;
import net.iaeste.iws.api.requests.exchange.FetchEmployerInformationRequest;
import net.iaeste.iws.api.requests.exchange.FetchFacultiesRequest;
import net.iaeste.iws.api.requests.exchange.FetchGroupsForSharingRequest;
import net.iaeste.iws.api.requests.exchange.FetchOfferTemplatesRequest;
import net.iaeste.iws.api.requests.exchange.FetchOffersRequest;
import net.iaeste.iws.api.requests.exchange.FetchPublishGroupsRequest;
import net.iaeste.iws.api.requests.exchange.FetchPublishOfferRequest;
import net.iaeste.iws.api.requests.exchange.OfferTemplateRequest;
import net.iaeste.iws.api.requests.exchange.ProcessOfferRequest;
import net.iaeste.iws.api.requests.exchange.PublishGroupRequest;
import net.iaeste.iws.api.requests.exchange.PublishOfferRequest;
import net.iaeste.iws.api.responses.exchange.FetchEmployerInformationResponse;
import net.iaeste.iws.api.responses.exchange.FetchFacultyResponse;
import net.iaeste.iws.api.responses.exchange.FetchGroupsForSharingResponse;
import net.iaeste.iws.api.responses.exchange.FetchOfferTemplateResponse;
import net.iaeste.iws.api.responses.exchange.FetchOffersResponse;
import net.iaeste.iws.api.responses.exchange.FetchPublishGroupResponse;
import net.iaeste.iws.api.responses.exchange.FetchPublishOfferResponse;
import net.iaeste.iws.api.responses.exchange.OfferResponse;
import net.iaeste.iws.api.responses.exchange.PublishOfferResponse;
import net.iaeste.iws.api.util.Fallible;
import net.iaeste.iws.core.services.ExchangeService;
import net.iaeste.iws.core.services.FacultyService;
import net.iaeste.iws.core.services.ServiceFactory;
import net.iaeste.iws.persistence.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 */
public final class ExchangeController extends StudentController implements Exchange {

    private static final Logger LOG = LoggerFactory.getLogger(ExchangeController.class);
    private final ServiceFactory factory;

    /**
     * Default Constructor, takes a ServiceFactory as input parameter, and uses
     * this in all the request methods, to get a new Service instance.
     *
     * @param factory The ServiceFactory
     */
    public ExchangeController(final ServiceFactory factory) {
        //super(factory.getAccessDao());
        super(factory);

        this.factory = factory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FetchEmployerInformationResponse fetchEmployers(final AuthenticationToken token, final FetchEmployerInformationRequest request) {
        LOG.trace("Starting fetchFaculties()");
        FetchEmployerInformationResponse response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.LOOKUP_OFFERS);
            verify(request);

            final ExchangeService service = factory.prepareExchangeService();
            response = service.fetchEmployers(authentication, request);
        } catch (IWSException e) {
            response = new FetchEmployerInformationResponse(e.getError(), e.getMessage());
        }

        LOG.trace("Finished fetchEmployers()");
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Deprecated
    public Fallible manageFaculties(final AuthenticationToken token, final FacultyRequest request) {
        LOG.trace("Starting manageFaculties()");
        Fallible response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.PROCESS_FACULTIES);
            verify(request);

            final FacultyService service = factory.prepareFacultyService();
            service.processFaculties(authentication, request);
            response = new FetchFacultyResponse();
        } catch (IWSException e) {
            response = new FetchFacultyResponse(e.getError(), e.getMessage());
        }

        LOG.trace("Finished manageFaculties()");
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Deprecated
    public FetchFacultyResponse fetchFaculties(final AuthenticationToken token, final FetchFacultiesRequest request) {
        LOG.trace("Starting fetchFaculties()");
        FetchFacultyResponse response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.LOOKUP_FACULTIES);
            verify(request);

            final FacultyService service = factory.prepareFacultyService();
            response = service.fetchFaculties(authentication, request);
        } catch (IWSException e) {
            response = new FetchFacultyResponse(e.getError(), e.getMessage());
        }

        LOG.trace("Finished fetchFaculties()");
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OfferResponse processOffer(final AuthenticationToken token, final ProcessOfferRequest request) {
        LOG.trace("Starting processOffer()");
        OfferResponse response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.MANAGE_OFFERS);
            verify(request);

            final ExchangeService service = factory.prepareExchangeService();
            response = service.processOffer(authentication, request);
        } catch (IWSException e) {
            response = new OfferResponse(e.getError(), e.getMessage());
        }

        LOG.trace("Finished processOffer()");
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OfferResponse deleteOffer(final AuthenticationToken token, final DeleteOfferRequest request) {
        LOG.trace("Starting deleteOffer()");
        OfferResponse response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.MANAGE_OFFERS);
            verify(request);

            final ExchangeService service = factory.prepareExchangeService();
            service.deleteOffer(authentication, request);
            response = new OfferResponse();
        } catch (IWSException e) {
            response = new OfferResponse(e.getError(), e.getMessage());
        }

        LOG.trace("Finished processOffer()");
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FetchOffersResponse fetchOffers(final AuthenticationToken token, final FetchOffersRequest request) {
        LOG.trace("Starting fetchOffers()");
        FetchOffersResponse response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.LOOKUP_OFFERS);
            verify(request);

            final ExchangeService service = factory.prepareExchangeService();
            response = service.fetchOffers(authentication, request);
        } catch (IWSException e) {
            response = new FetchOffersResponse(e.getError(), e.getMessage());
        }

        LOG.trace("Finished fetchOffers()");
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fallible processOfferTemplate(final AuthenticationToken token, final OfferTemplateRequest request) {
        LOG.trace("Starting processOfferTemplate()");
        Fallible response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.PROCESS_OFFER_TEMPLATES);
            verify(request);

            final ExchangeService service = factory.prepareExchangeService();
            service.processOfferTemplates(authentication, request);
            response = new FetchOfferTemplateResponse();
        } catch (IWSException e) {
            response = new FetchOfferTemplateResponse(e.getError(), e.getMessage());
        }

        LOG.trace("Finished processOfferTemplate()");
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FetchOfferTemplateResponse fetchOfferTemplates(final AuthenticationToken token, final FetchOfferTemplatesRequest request) {
        LOG.trace("Starting fetchOfferTemplates()");
        FetchOfferTemplateResponse response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.LOOKUP_OFFER_TEMPLATES);
            verify(request);

            final ExchangeService service = factory.prepareExchangeService();
            response = service.fetchOfferTemplates(authentication, request);
        } catch (IWSException e) {
            response = new FetchOfferTemplateResponse(e.getError(), e.getMessage());
        }

        LOG.trace("Finished fetchOfferTemplates()");
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fallible managePublishGroup(final AuthenticationToken token, final PublishGroupRequest request) {
        LOG.trace("Starting managePublishGroup()");
        Fallible response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.PROCESS_OFFER_PUBLISH_GROUPS);
            verify(request);

            final ExchangeService service = factory.prepareExchangeService();
            service.processPublishGroups(authentication, request);
            response = new FetchPublishGroupResponse();
        } catch (IWSException e) {
            response = new FetchPublishGroupResponse(e.getError(), e.getMessage());
        }

        LOG.trace("Finished managePublishGroup()");
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FetchPublishGroupResponse fetchPublishGroups(final AuthenticationToken token, final FetchPublishGroupsRequest request) {
        LOG.trace("Starting fetchPublishGroups()");
        FetchPublishGroupResponse response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.LOOKUP_OFFER_PUBLISH_GROUPS);
            verify(request);

            final ExchangeService service = factory.prepareExchangeService();
            response = service.fetchPublishGroups(authentication, request);
        } catch (IWSException e) {
            response = new FetchPublishGroupResponse(e.getError(), e.getMessage());
        }

        LOG.trace("Finished fetchPublishGroups()");
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FetchGroupsForSharingResponse fetchGroupsForSharing(final AuthenticationToken token, final FetchGroupsForSharingRequest request) {
        LOG.trace("Starting fetchNationalGroups()");
        FetchGroupsForSharingResponse response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.FETCH_GROUPS);
            verify(request);

            final ExchangeService service = factory.prepareExchangeService();
            response = service.fetchGroupsForSharing(authentication, request);
        } catch (IWSException e) {
            response = new FetchGroupsForSharingResponse(e.getError(), e.getMessage());
        }

        LOG.trace("Finished fetchNationalGroups()");
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PublishOfferResponse processPublishOffer(final AuthenticationToken token, final PublishOfferRequest request) {
        LOG.trace("Starting processPublishOffer()");
        PublishOfferResponse response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.PROCESS_PUBLISH_OFFER);
            verify(request);

            final ExchangeService service = factory.prepareExchangeService();
            service.processPublishOffer(authentication, request);
            response = new PublishOfferResponse();
        } catch (IWSException e) {
            response = new PublishOfferResponse(e.getError(), e.getMessage());
        }

        LOG.trace("Finished processPublishOffer()");
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FetchPublishOfferResponse fetchPublishedOffer(final AuthenticationToken token, final FetchPublishOfferRequest request) {
        LOG.trace("Starting fetchPublishedOffer()");
        FetchPublishOfferResponse response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.LOOKUP_PUBLISH_OFFER);
            verify(request);

            final ExchangeService service = factory.prepareExchangeService();
            response = service.fetchPublishedOfferInfo(authentication, request);
        } catch (IWSException e) {
            response = new FetchPublishOfferResponse(e.getError(), e.getMessage());
        }

        LOG.trace("Finished fetchPublishedOffer()");
        return response;
    }
}
