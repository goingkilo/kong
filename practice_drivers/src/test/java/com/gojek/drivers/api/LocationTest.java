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
public class LocationTest extends TestCase {

    public final int validDriverId1     = 1;

    public static final double LATITUDE = 12.97161923d;
    public static final double LONGITUDE = 77.59463452d;

    public static final double INVALID_LATITUDE = 91.97161923d;
    public static final double INVALID_LONGITUDE = 91.59463452d;



    public LocationTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreateValidLocation() throws Exception {

        Location validLocation = null;
        try {
            validLocation     = new Location(LATITUDE, LONGITUDE);
        }
        catch (InvalidLocationException e) {
            org.junit.Assert.assertNull(e);
        }
        org.junit.Assert.assertNotNull(validLocation);

    }

    public void testCreateInvalidLocation() throws Exception {

        Location invalidLocation1 = null;
        try {
            invalidLocation1 = new Location(INVALID_LATITUDE, LONGITUDE);
        }
        catch(InvalidLocationException e) {
            org.junit.Assert.assertNotNull(e);
            org.junit.Assert.assertEquals("Invalid co-ordinates provided !!", e.getMessage());
        }
        org.junit.Assert.assertNull(invalidLocation1);

        Location invalidLocation2 = null;
        try {
            invalidLocation2 = new Location( -INVALID_LATITUDE, LONGITUDE);
        }
        catch(InvalidLocationException e) {
            org.junit.Assert.assertNotNull(e);
            org.junit.Assert.assertEquals( "Invalid co-ordinates provided !!", e.getMessage());
        }
        org.junit.Assert.assertNull(invalidLocation2);

        Location invalidLocation3 = null;
        try {
            invalidLocation3 = new Location(LATITUDE, INVALID_LONGITUDE);
        }
        catch(InvalidLocationException e) {
            org.junit.Assert.assertNotNull(e);
            org.junit.Assert.assertEquals( "Invalid co-ordinates provided !!", e.getMessage());
        }
        org.junit.Assert.assertNull(invalidLocation3);

        Location invalidLocation4 = null;
        try {
            invalidLocation4 = new Location(LATITUDE, -INVALID_LONGITUDE);
        }
        catch(InvalidLocationException e) {
            org.junit.Assert.assertNotNull(e);
            org.junit.Assert.assertEquals( "Invalid co-ordinates provided !!", e.getMessage());
        }
        org.junit.Assert.assertNull(invalidLocation4);

    }

    public static Test suite() {
        return new TestSuite(LocationTest.class);
    }
}
