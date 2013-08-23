/*
 * =============================================================================
 * Copyright 1998-2013, IAESTE Internet Development Team. All rights reserved.
 * -----------------------------------------------------------------------------
 * Project: IntraWeb Services (iws-api) - net.iaeste.iws.api.dtos.Address
 * -----------------------------------------------------------------------------
 * This software is provided by the members of the IAESTE Internet Development
 * Team (IDT) to IAESTE A.s.b.l. It is for internal use only and may not be
 * redistributed. IAESTE A.s.b.l. is not permitted to sell this software.
 *
 * This software is provided "as is"; the IDT or individuals within the IDT
 * cannot be held legally responsible for any problems the software may cause.
 * =============================================================================
 */
package net.iaeste.iws.api.dtos;

import net.iaeste.iws.api.constants.IWSConstants;
import net.iaeste.iws.api.util.AbstractVerification;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  Kim Jensen / last $Author:$
 * @version $Revision:$ / $Date:$
 * @since   1.7
 * @noinspection OverlyComplexMethod
 */
public final class Address extends AbstractVerification {

    /** {@link IWSConstants#SERIAL_VERSION_UID}. */
    private static final long serialVersionUID = IWSConstants.SERIAL_VERSION_UID;

    /**
     * Most fields of type String in this Object, are allowed to be as big as
     * this number.
     */
    private static final int FIELD_LENGTH = 100;

    /**
     * Zip Codes (Postal Codes) are used by a rather large number of countries,
     * in fact according to <a href="http://en.wikipedia.org/wiki/Postal_code">wikipedia</a>,
     * most countries are using these as an integral part of an Address. The
     * length of them is between 3 and 10 characters, with an optional Country
     * Code (two letters) additionally, meaning that the Zip Code can be up to
     * 12 Characters long.
     */
    private static final int ZIP_LENGTH = 12;

    private String street1 = null;
    private String street2 = null;
    private String zip = null;
    private String city = null;
    private String state = null;
    private String pobox = null;
    private Country country = null;

    // =========================================================================
    // Object Constructors
    // =========================================================================

    /**
     * Empty Constructor, to use if the setters are invoked. This is required
     * for WebServices to work properly.
     */
    public Address() {
    }

    /**
     * Copy Constructor.
     *
     * @param address Address Object to copy
     */
    public Address(final Address address) {
        if (address != null) {
            street1 = address.street1;
            street2 = address.street2;
            zip = address.zip;
            city = address.city;
            state = address.state;
            pobox = address.pobox;
            country = new Country(address.country);
        }
    }

    // =========================================================================
    // Standard Setters & Getters
    // =========================================================================

    public void setStreet1(final String street1) {
        ensureNotEmptyOrTooLong("street1", street1, FIELD_LENGTH);
        this.street1 = street1;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet2(final String street2) {
        ensureNotTooLong("street2", street2, FIELD_LENGTH);
        this.street2 = street2;
    }

    public String getStreet2() {
        return street2;
    }

    public void setZip(final String zip) {
        ensureNotEmptyOrTooLong("zip", zip, ZIP_LENGTH);
        this.zip = zip;
    }

    public String getZip() {
        return zip;
    }

    public void setCity(final String city) {
        ensureNotEmptyOrTooLong("city", city, FIELD_LENGTH);
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setState(final String state) {
        ensureNotTooLong("state", state, FIELD_LENGTH);
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setPobox(final String pobox) {
        ensureNotTooLong("pobox", pobox, FIELD_LENGTH);
        this.pobox = pobox;
    }

    public String getPobox() {
        return pobox;
    }

    public void setCountry(final Country country) {
        ensureNotNull("country", country);
        this.country = new Country(country);
    }

    public Country getCountry() {
        return new Country(country);
    }

    // =========================================================================
    // Standard DTO Methods
    // =========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> validate() {
        // Since an Address is an optional Object, we're not going to make any
        // validity checks here
        return new HashMap<>(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Address)) {
            return false;
        }

        final Address address = (Address) obj;

        if (street1 != null ? !street1.equals(address.street1) : address.street1 != null) {
            return false;
        }
        if (street2 != null ? !street2.equals(address.street2) : address.street2 != null) {
            return false;
        }
        if (zip != null ? !zip.equals(address.zip) : address.zip != null) {
            return false;
        }
        if (city != null ? !city.equals(address.city) : address.city != null) {
            return false;
        }
        if (state != null ? !state.equals(address.state) : address.state != null) {
            return false;
        }

        return !(country != null ? !country.equals(address.country) : address.country != null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = IWSConstants.HASHCODE_INITIAL_VALUE;

        result = IWSConstants.HASHCODE_MULTIPLIER * result + (street1 != null ? street1.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + (street2 != null ? street2.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + (zip != null ? zip.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + (city != null ? city.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + (state != null ? state.hashCode() : 0);
        result = IWSConstants.HASHCODE_MULTIPLIER * result + (country != null ? country.hashCode() : 0);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Address{" +
                ", street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country=" + country +
                '}';
    }
}
