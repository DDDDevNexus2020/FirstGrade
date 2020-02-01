package com.fg.staff.teacher.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class AddressTest {

    // ------------------------------------------------- TEST METHODS --------------------------------------------------

    @Test
    void addressBuilder() {
        // Builder
        Address address = Address.builder() //
                .streetName("1600 Pennsylvania Ave") //
                .city("Washington") //
                .state("DC") //
                .postalCode("20006") //
                .build();
        assertEquals("1600 Pennsylvania Ave", address.getStreetName());

        // Pure functional
        Address correctAddress = address.withStreetName("1600 Pennsylvania Ave NW");
        assertEquals("1600 Pennsylvania Ave NW", correctAddress.getStreetName());
        assertNotSame(address, correctAddress);
    }
}