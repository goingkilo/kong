package com.gojek.drivers.api;

/**
 * Created by kraghunathan on 12/11/16.
 */
public class Driver {

    private int driverId;
    private Location location;

    public Driver(int driverId) {
        this.driverId = driverId;
    }

    // http://stackoverflow.com/questions/6086334/is-it-good-practice-to-make-the-constructor-throw-an-exception
    public Driver( int driverId, Location location) throws InvalidDriverException {
        validate(driverId) ;


        this.driverId = driverId;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public int getDriverId() {
        return driverId;
    }

    public double distanceTo(Location target) {
        return location.distanceTo(target);
    }

    public void validate(int driverId) throws InvalidDriverException {
        if (driverId  < 1 || driverId > 50000) throw new InvalidDriverException( "Invalid driver id provided");
    }
}
