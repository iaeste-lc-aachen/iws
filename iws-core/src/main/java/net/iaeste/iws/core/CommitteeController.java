/*
 * =============================================================================
 * Copyright 1998-2014, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-core) - net.iaeste.iws.core.CommitteeController
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

import static net.iaeste.iws.core.util.LogUtil.formatLogMessage;

import net.iaeste.iws.api.Committees;
import net.iaeste.iws.api.dtos.AuthenticationToken;
import net.iaeste.iws.api.enums.Permission;
import net.iaeste.iws.api.exceptions.IWSException;
import net.iaeste.iws.api.requests.CommitteeRequest;
import net.iaeste.iws.api.requests.FetchSurveyOfCountryRequest;
import net.iaeste.iws.api.requests.InternationalGroupRequest;
import net.iaeste.iws.api.requests.SurveyOfCountryRequest;
import net.iaeste.iws.api.responses.FallibleResponse;
import net.iaeste.iws.api.responses.FetchSurveyOfCountryRespose;
import net.iaeste.iws.api.util.Fallible;
import net.iaeste.iws.core.services.CommitteeService;
import net.iaeste.iws.core.services.ServiceFactory;
import net.iaeste.iws.persistence.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
public final class CommitteeController extends CommonController implements Committees {

    private static final Logger log = LoggerFactory.getLogger(CommitteeController.class);

    /**
     * Default Constructor, takes a ServiceFactory as input parameter, and uses
     * this in all the request methods, to get a new Service instance.
     *
     * @param factory  The ServiceFactory
     */
    public CommitteeController(final ServiceFactory factory) {
        super(factory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fallible processCommittee(final AuthenticationToken token, final CommitteeRequest request) {
        if (log.isTraceEnabled()) {
            log.trace(formatLogMessage(token, "Starting processCommittee()"));
        }
        Fallible response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.PROCESS_COMMITTEE);
            verify(request);

            final CommitteeService service = factory.prepareCommitteeService();
            response = service.createCommittee(authentication, request);
        } catch (IWSException e) {
            response = new FallibleResponse(e.getError(), e.getMessage());
        }

        if (log.isTraceEnabled()) {
            log.trace(formatLogMessage(token, "Finished processCommittee()"));
        }
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fallible processInternationalGroup(final AuthenticationToken token, final InternationalGroupRequest request) {
        if (log.isTraceEnabled()) {
            log.trace(formatLogMessage(token, "Starting processInternationalGroup()"));
        }
        Fallible response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.PROCESS_INTERNATIONAL_GROUP);
            verify(request);

            final CommitteeService service = factory.prepareCommitteeService();
            response = service.manageInternationalGroup(authentication, request);
        } catch (IWSException e) {
            response = new FallibleResponse(e.getError(), e.getMessage());
        }

        if (log.isTraceEnabled()) {
            log.trace(formatLogMessage(token, "Finished processInternationalGroup()"));
        }
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FetchSurveyOfCountryRespose fetchSurveyOfCountry(final AuthenticationToken token, final FetchSurveyOfCountryRequest request) {
        if (log.isTraceEnabled()) {
            log.trace(formatLogMessage(token, "Starting fetchSurveyOfCountry()"));
        }
        FetchSurveyOfCountryRespose response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.FETCH_SURVEY_OF_COUNTRIES);
            verify(request);

            final CommitteeService service = factory.prepareCommitteeService();
            response = service.fetchSurveyOfCountry(authentication, request);
        } catch (IWSException e) {
            response = new FetchSurveyOfCountryRespose(e.getError(), e.getMessage());
        }

        if (log.isTraceEnabled()) {
            log.trace(formatLogMessage(token, "Finished fetchSurveyOfCountry()"));
        }
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fallible processSurveyOfCountry(final AuthenticationToken token, final SurveyOfCountryRequest request) {
        if (log.isTraceEnabled()) {
            log.trace(formatLogMessage(token, "Starting processSurveyOfCountry()"));
        }
        Fallible response;

        try {
            final Authentication authentication = verifyAccess(token, Permission.PROCESS_SURVEY_OF_COUNTRIES);
            verify(request);

            final CommitteeService service = factory.prepareCommitteeService();
            response = service.processSurveyOfCountry(authentication, request);
        } catch (IWSException e) {
            response = new FallibleResponse(e.getError(), e.getMessage());
        }

        if (log.isTraceEnabled()) {
            log.trace(formatLogMessage(token, "Finished processSurveyOfCountry()"));
        }
        return response;
    }
}
