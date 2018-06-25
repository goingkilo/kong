package com.gojek.drivers.resources;

import com.gojek.drivers.api.Driver;
import com.gojek.drivers.api.InvalidDriverException;
import com.gojek.drivers.api.InvalidLocationException;
import com.gojek.drivers.api.Location;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kraghunathan on 12/8/16.
 */
@Path("/drivers")
@Produces(MediaType.APPLICATION_JSON)
public class DriversResource {


    public DriversResource(){}


    @PUT
    @Path("/{id}/location")
    public Response updateDriverLocation(@PathParam("id") int driverId,
                                       @FormParam("latitude") double latitude,
                                       @FormParam("longitude")  double longitude,
                                       @FormParam("accuracy") String accuracy ,
                                         @Context Jedis jedis) {

        try {

            Location location = new Location(latitude,longitude);
            Driver driver = new Driver( driverId, location);
            jedis.geoadd("drivers", location.getLongitude() , location.getLatitude(), String.valueOf(driver.getDriverId()));
        }
        catch (InvalidLocationException e) {
            return Response.status(422).entity("{\"errors\": [\"Latitude should be between +/- 90\"]}").build();
        }
        catch (InvalidDriverException e) {
            return Response.status(404).entity("{}").build();
        }
        catch(JedisDataException e){
            return Response.status(422).entity("{\"errors\": [\"Latitude should be between +/- 90\"]}").build();
        }

        return Response.ok().entity("{}").build();
    }

    @GET
    @Path("/")
    public Response getDrivers(@QueryParam("latitude") double latitude ,
                             @QueryParam("longitude") double longitude ,
                             @DefaultValue("500") @QueryParam("radius") int radius ,
                             @DefaultValue("10") @QueryParam("limit") int limit,
                               @Context Jedis jedis ) {

        try {

            Location location = new Location(latitude,longitude);

            List<GeoRadiusResponse> drivers = jedis.georadius("drivers", location.getLongitude(), location.getLatitude(), radius, GeoUnit.M, GeoRadiusParam.geoRadiusParam()
                    .sortAscending()
                    .withCoord()
                    .withDist()
                    .count(limit)
            );

            List<DriverResponse> responseList = getResponse(drivers);
            return Response.ok().entity(responseList).build();
        }
        catch (InvalidLocationException e) {
            return Response.status(422).entity("{\"errors\": [\"Latitude should be between +/- 90\"]}").build();
        }


    }

    private List<DriverResponse> getResponse(List<GeoRadiusResponse> drivers) {
        List<DriverResponse> ret = new ArrayList<DriverResponse>();
        for( GeoRadiusResponse geo : drivers) {
            ret.add( new DriverResponse(geo));
        }
        return ret;
    }

    class DriverResponse {
        private String id, latitude, longitude,distance;

        public DriverResponse(GeoRadiusResponse geo){
            this.id = geo.getMemberByString();
            this.longitude = String.valueOf(geo.getCoordinate().getLongitude());
            this.latitude = String.valueOf(geo.getCoordinate().getLatitude());
            this.distance = String.valueOf(geo.getDistance());

        }

        public String getDistance() {
            return distance;
        }

        public String getId() {
            return id;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }
    }

}
