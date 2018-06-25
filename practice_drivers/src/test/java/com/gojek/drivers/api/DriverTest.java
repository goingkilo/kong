package com.gojek.drivers.api;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Driver Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>12/19/2016</pre>
 */
public class DriverTest extends TestCase {

    public  Location validLocation, pickupLocation   = null;


    public final int validDriverId1 = 1;
    public final int validDriverId2 = 5000;

    public final int invalidDriverId1 = -1;
    public final int invalidDriverId2 = -5000;

    public DriverTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();

        validLocation = new Location( 12.97161923d, 77.59463452d);
        pickupLocation = new Location( 32.97161923d, 77.59463452d);
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreateDriver() throws Exception {

        Driver driver1 = null;
        try {
            driver1 = new Driver( validDriverId1, validLocation);
        }
        catch (InvalidDriverException e) {
            org.junit.Assert.assertNull(e);
        }
        org.junit.Assert.assertNotNull(driver1);


        Driver driver2 = null;
        try {
        driver2 = new Driver(validDriverId2, validLocation);
        }
        catch (InvalidDriverException e) {
            org.junit.Assert.assertNull(e);
        }

        org.junit.Assert.assertNotNull(driver2);

    }

    public void testCreateDriverWithInvalidId() throws Exception {


        Driver driver1 = null;
        try {
            driver1 = new Driver( invalidDriverId1, validLocation);
        }
        catch(InvalidDriverException e) {
            org.junit.Assert.assertNotNull(e);
            org.junit.Assert.assertEquals( "Invalid driver id provided", e.getMessage());
        }
        org.junit.Assert.assertNull(driver1);

        Driver driver2 = null;
        try {
            driver1 = new Driver( invalidDriverId2, validLocation);
        }
        catch(InvalidDriverException e) {
            org.junit.Assert.assertNotNull(e);
            org.junit.Assert.assertEquals( "Invalid driver id provided", e.getMessage());
        }
        org.junit.Assert.assertNull(driver2);

    }

    public void testCreateDriverDistance() throws Exception {

        try {
            Driver driver1  = new Driver( validDriverId1, validLocation);
            double l = driver1.distanceTo(pickupLocation);
            assertEquals( 2224.526851421893d,l);
        }
        catch(InvalidDriverException e) {
        }

    }

    public static Test suite() {
        return new TestSuite(DriverTest.class);
    }
}
