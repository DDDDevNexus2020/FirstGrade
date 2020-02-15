package com.fg.test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Helper methods and properties for tests.
 */
public abstract class BaseTest {

    // ----------------------------------------------- MEMBER VARIABLES ------------------------------------------------

    // To create random data
    protected static final PodamFactory podamFactory = new PodamFactoryImpl();

}
