package com.gojek.drivers.api;


import com.gojek.drivers.core.HaversineDistanceCalculator;

/**
 * Created by kraghunathan on 12/11/16.
 */
public class Location {

    private double latitude, longitude;

    public Location(double latitude, double longitude) throws InvalidLocationException {

        validate(latitude,longitude);

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double distanceTo(Location target) {
        // TODO: change static to instance
        return HaversineDistanceCalculator.distance(this.getLatitude(), this.getLongitude(), target.getLatitude(), target.getLongitude());
    }

    public void validate(double latitude, double longitude) throws InvalidLocationException {
        if ( latitude  > 90.0d || latitude < -90.0d || longitude  > 90.0d || longitude < -90.0d) throw new InvalidLocationException( "Invalid co-ordinates provided !!");;
    }



}
