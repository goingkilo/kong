package com.gojek.drivers.db;

import com.gojek.drivers.api.Driver;
import com.gojek.drivers.api.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kraghunathan on 12/19/16.
 */
public class DriverDAO {


    public void put(Driver driver) {

    }

    public Map<Driver,Double> getDriversNearest(Location location, int radius, int limit) {
        Map<Driver,Double> nearestDrivers  = new HashMap<Driver,Double>();
        return nearestDrivers;
    }
}
