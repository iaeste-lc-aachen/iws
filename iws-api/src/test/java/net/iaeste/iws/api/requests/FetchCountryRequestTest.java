/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.requests.FetchCountryRequestTest
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.requests;

import net.iaeste.iws.api.enums.Membership;
import net.iaeste.iws.api.exceptions.VerificationException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 * @noinspection ResultOfObjectAllocationIgnored
 */
public final class FetchCountryRequestTest {

    /**
     * Our internal deep copying mechanism follows a standard best practice, and
     * returns empty lists, rather than null values.
     */
    private static final List<String> EMPTY_LIST = new ArrayList<>(0);

    @Test(expected = VerificationException.class)
    public void testEmptyRequest() {
        final FetchCountryRequest request = new FetchCountryRequest();
        request.verify();
    }

    @Test
    public void testMembershipConstructor() {
        final Membership membership = Membership.FULL_MEMBER;

        final FetchCountryRequest request = new FetchCountryRequest(membership);
        request.verify();

        assertThat(request.getCountryIds(), is(EMPTY_LIST));
        assertThat(request.getMembership(), is(membership));
    }

    @Test
    public void testCountryIdsConstructor() {
        final List<String> countryIds = new ArrayList<>(2);
        countryIds.add("DE");
        countryIds.add("FR");

        final FetchCountryRequest request = new FetchCountryRequest(countryIds);
        request.verify();

        assertThat(request.getCountryIds(), is(countryIds));
        assertThat(request.getMembership(), is(nullValue()));
        assertThat(request.getCountryIds(), is(not(sameInstance(countryIds))));
    }

    @Test
    public void testOverridingMembership() {
        final Membership membership = Membership.FULL_MEMBER;
        final List<String> countryIds = new ArrayList<>(2);
        countryIds.add("DE");
        countryIds.add("FR");

        final FetchCountryRequest request = new FetchCountryRequest(membership);
        request.setCountryIds(countryIds);

        assertThat(request.getCountryIds(), is(countryIds));
        assertThat(request.getMembership(), is(nullValue()));
        assertThat(request.getCountryIds(), is(not(sameInstance(countryIds))));
    }

    @Test
    public void testOverridingCountryIds() {
        final Membership membership = Membership.FULL_MEMBER;
        final List<String> countryIds = new ArrayList<>(2);
        countryIds.add("DE");
        countryIds.add("FR");

        final FetchCountryRequest request = new FetchCountryRequest(countryIds);
        request.setMembership(membership);

        assertThat(request.getCountryIds(), is(EMPTY_LIST));
        assertThat(request.getMembership(), is(membership));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNullCountryIds() {
        final List<String> countryIds = null;

        new FetchCountryRequest(countryIds);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNullMembership() {
        final Membership membership = null;

        new FetchCountryRequest(membership);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSettingNullCountryIds() {
        final FetchCountryRequest request = new FetchCountryRequest();

        request.setCountryIds(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSettingNullMembership() {
        final FetchCountryRequest request = new FetchCountryRequest();

        request.setMembership(null);
    }
}
