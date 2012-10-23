/*
 * =============================================================================
 * Copyright 1998-2012, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IWS (iws-fitnesse)
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import net.iaeste.iws.api.enums.Language;
import net.iaeste.iws.api.enums.LanguageLevel;
import org.junit.Test;

import java.util.Date;

/**
 * @author Michal Knapik / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since 1.7
 */
public class SaveOfferTest {

    @Test
    public void testSaveOffers() {
        final FetchOffers fetch = new FetchOffers();
        fetch.setUsername("Michl");
        fetch.setPassword("frodo");
        fetch.setRequestType("ALL");
        fetch.fetch();

        // some offers might have been fetched
        assertThat(fetch.isRequestOk(), is(true));
        final int noOfOffers = fetch.numberOfFetchedOffers();

        // trying to save new Offer
        final SaveOffer cut = new SaveOffer();
        cut.setUsername("Michl");
        cut.setPassword("frodo");
        cut.setRefNo("AT-2012-0001");
        cut.setEmployerName("test employer name");

        assertThat(cut.verify(), is(false));

        cut.setStudyLevels("E");
        cut.setFieldOfStudies("IT|ARCHITECTURE");
        cut.setLanguage1(Language.ENGLISH);
        cut.setLanguage1Level(LanguageLevel.E);
        cut.setWorkDescription("some desc");
        cut.setMaximumWeeks(52);
        cut.setMinimumWeeks(6);
        cut.setWeeklyHours(40.0f);
        cut.setFromDate(new Date());
        cut.setToDate(new Date(new Date().getTime() + 3600 * 24 * 360));

        assertThat(cut.verify(), is(true));

        cut.save();

        // Offer request should success
        assertThat(cut.isRequestOk(), is(true));

        // try to fetch offers once again
        final FetchOffers fetch2 = new FetchOffers();
        fetch2.setUsername("Michl");
        fetch2.setPassword("frodo");
        fetch2.setRequestType("ALL");
        fetch2.fetch();

        // expect one offer more that before
        assertThat(fetch2.isRequestOk(), is(true));
        assertThat(fetch2.numberOfFetchedOffers(), is(noOfOffers + 1));
    }
}