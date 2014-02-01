/*
 * =============================================================================
 * Copyright 1998-2014, IAESTE Internet Development Team. All rights reserved.
 * ----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-fitnesse) - net.iaeste.iws.fitnesse.FetchEmployers
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.fitnesse;

import net.iaeste.iws.api.Exchange;
import net.iaeste.iws.api.requests.exchange.FetchEmployerRequest;
import net.iaeste.iws.api.responses.exchange.FetchEmployerResponse;
import net.iaeste.iws.fitnesse.callers.ExchangeCaller;
import net.iaeste.iws.fitnesse.exceptions.StopTestException;

/**
 * @author  Martin Eisfeld / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   IWS 1.0
 */
public final class FetchEmployers extends AbstractFixture<FetchEmployerResponse> {

    private final Exchange exchange = new ExchangeCaller();
    private FetchEmployerRequest request = new FetchEmployerRequest();

    public void fetchEmployers() {
        execute();
    }

    public void setName(final String name) {
        request.setFetchByPartialName(name);
    }

    public int numberOfEmployers() {
        return getResponse() == null ? -1 : getResponse().getEmployers().size();
    }

    public String printEmployerInformation(final int employerIndex) {
        final String retVal;

        if (getResponse() == null) {
            retVal = "no response";
        } else if ((employerIndex < 1) || (employerIndex > numberOfEmployers())) {
            retVal = "no employer for given index";
        } else {
            retVal = getResponse().getEmployers().get(employerIndex - 1).toString();
        }

        return retVal;
    }

    @Override
    public void execute() throws StopTestException {
        createSession();
        setResponse(exchange.fetchEmployers(getToken(), request));
    }

    @Override
    public void reset() {
        // Resets the Response Object
        super.reset();

        request = null;
    }
}
