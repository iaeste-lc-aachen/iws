package net.iaeste.iws.fitnesse;

import net.iaeste.iws.api.Exchange;
import net.iaeste.iws.api.requests.exchange.FetchOfferTemplatesRequest;
import net.iaeste.iws.api.responses.exchange.FetchOfferTemplateResponse;
import net.iaeste.iws.fitnesse.callers.ExchangeCaller;
import net.iaeste.iws.fitnesse.exceptions.StopTestException;

/**
 * @author  Martin Eisfeld / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 */
public final class FetchOfferTemplates extends AbstractFixture<FetchOfferTemplateResponse> {

    private final Exchange exchange = new ExchangeCaller();
    private FetchOfferTemplatesRequest request = new FetchOfferTemplatesRequest();

    public void processOfferTemplate() {
        execute();
    }

    @Override
    public void execute() throws StopTestException {
        createSession();
        setResponse(exchange.fetchOfferTemplates(getToken(), request));
    }

    @Override
    public void reset() {
        // Resets the Response Object
        super.reset();

        request = null;
    }
}