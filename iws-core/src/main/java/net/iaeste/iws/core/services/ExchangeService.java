/*
 * =============================================================================
 * Copyright 1998-2012, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-core) - net.iaeste.iws.core.services.OfferService
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.core.services;

import net.iaeste.iws.api.data.AuthenticationToken;
import net.iaeste.iws.api.data.Offer;
import net.iaeste.iws.api.exceptions.EntityIdentificationException;
import net.iaeste.iws.api.exceptions.NotImplementedException;
import net.iaeste.iws.api.requests.FetchOfferTemplatesRequest;
import net.iaeste.iws.api.requests.FetchOffersRequest;
import net.iaeste.iws.api.requests.FetchPublishGroupsRequest;
import net.iaeste.iws.api.requests.OfferRequest;
import net.iaeste.iws.api.requests.OfferTemplateRequest;
import net.iaeste.iws.api.requests.PublishGroupRequest;
import net.iaeste.iws.api.responses.OfferResponse;
import net.iaeste.iws.api.responses.OfferTemplateResponse;
import net.iaeste.iws.api.responses.PublishGroupResponse;
import net.iaeste.iws.core.convert.ConverterFactory;
import net.iaeste.iws.core.convert.OfferConverter;
import net.iaeste.iws.persistence.OfferDao;
import net.iaeste.iws.persistence.entities.OfferEntity;
import net.iaeste.iws.persistence.jpa.OfferJpaDao;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since 1.7
 */
public class ExchangeService {

    private final EntityManager entityManager;
    private final OfferDao offerDao;
    private final OfferConverter offerConverter;
    private final ConverterFactory converterFactory;


    public ExchangeService(final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.offerDao = new OfferJpaDao(entityManager);
        // TODO Michal: @Kim: is there any better place for the factory? should ConverterFactory be changed into singleton?
        this.converterFactory = new ConverterFactory(entityManager);
        this.offerConverter = converterFactory.getOfferConverter();
    }

    /**
     * @param token
     * @param request
     * @return OfferResponse contains list of Fallible Offers for which processing failed.
     */
    public OfferResponse processOffers(final AuthenticationToken token, final OfferRequest request) {
        final List<Offer> offers = new ArrayList<>(request.getEditOffers().size() + request.getDeleteOfferIDs().size());

        // If DTO objects has an id, we're trying to update the database.
        // If DTO has an id and there is no such entity, an exception is thrown.
        for (final Offer offer : request.getEditOffers()) {
            try {
                final OfferEntity offerEntity = offerConverter.toEntity(offer);
                offerDao.persist(offerEntity);
            } catch (EntityIdentificationException e) {
                offers.add(new Offer(offer, e));
            }
        }
        for (final Long offer : request.getDeleteOfferIDs()) {
            if (!offerDao.delete(offer)) {
                final Offer o = new Offer();
                o.setId(offer);
                offers.add(new Offer(o, new EntityIdentificationException("No entity for given Id.")));
            }
        }
        return new OfferResponse(offers);
    }

    public OfferResponse fetchOffers(final AuthenticationToken token, final FetchOffersRequest request) {
        final OfferJpaDao dao = new OfferJpaDao(entityManager);
        switch (request.getFetchType()) {
            case ALL:
                return new OfferResponse(offerConverter.toDTO(dao.findAll()));
            case NONE:
                throw new NotImplementedException("TBD");
            case BY_ID:
                return new OfferResponse(offerConverter.toDTO(dao.findOffers(request.getOffers())));
            case LIMIT:
                // return offers from 'a' to 'b' using SQL's LIMIT, used for pagination
                throw new NotImplementedException("TBD");
            case PROTOTYPE:
                throw new NotImplementedException("TBD");
        }
        return new OfferResponse();
    }

    public void processOfferTemplates(final AuthenticationToken token, final OfferTemplateRequest request) {
        throw new NotImplementedException("Method pending implementation.");
    }

    public OfferTemplateResponse fetchOfferTemplates(final AuthenticationToken token, final FetchOfferTemplatesRequest request) {
        throw new NotImplementedException("Method pending implementation.");
    }

    public void processPublishGroups(final AuthenticationToken token, final PublishGroupRequest request) {
        throw new NotImplementedException("Method pending implementation.");
    }

    public PublishGroupResponse fetchPublishGroups(final AuthenticationToken token, final FetchPublishGroupsRequest request) {
        throw new NotImplementedException("Method pending implementation.");
    }
}