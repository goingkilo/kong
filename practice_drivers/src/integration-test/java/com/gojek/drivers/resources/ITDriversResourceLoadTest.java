package com.gojek.drivers.resources;

import com.gojek.drivers.api.Driver;
import com.gojek.drivers.api.Location;
import com.squareup.okhttp.*;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

public class ITDriversResourceLoadTest {

    private Location location;
    private Driver driver;

    @Test
    public void testPut50000Driver() {
        OkHttpClient client = new OkHttpClient();

        Random r = new Random( System.currentTimeMillis());

        for( int i = 1 ; i <= 50000 ; i++) {
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");

            int multiplier = r.nextFloat() > 0.5f ? +1 : -1 ;
            int generatedLat = r.nextInt(90) * multiplier;

            multiplier = r.nextFloat() > 0.5f ? +1 : -1 ;
            int generatedLon =  r.nextInt(90) * multiplier;

            RequestBody body = RequestBody.create(mediaType, "latitude="+generatedLat+".97161923&longitude="+generatedLon+".59463452&accuracy=1");
            Request request = new Request.Builder()
                    .url("http://localhost:8080/drivers/"+i+"/location")
                    .put(body)
                    .addHeader("content-type", "application/x-www-form-urlencoded")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "2f18cc26-de2c-594a-adba-5ea9a7e0d926")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                //Assert.assertEquals(200, response.code());
                if( response.code() != 200 ) {
                    i = i -1;
                    continue;
                }
                System.out.println(  i + "," + generatedLon + ".59463452," + generatedLat + ".97161923");
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
