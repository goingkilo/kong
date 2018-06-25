//package com.gojek.drivers.resources;
//
//import com.gojek.drivers.api.Driver;
//import com.gojek.drivers.api.Location;
//import com.squareup.okhttp.*;
//import io.dropwizard.testing.junit.ResourceTestRule;
//import org.junit.*;
//
//import java.io.IOException;
//import java.util.Random;
//
//public class ITDriversResourceTest {
//
//
//    @ClassRule
//    public static final ResourceTestRule resources = ResourceTestRule.builder()
//            .addResource(new DriversResource())
//            .build();
//
//    private Location location;
//    private Driver driver;
//
//    @Before
//    public void setup() {
//        //when(dao.fetchPerson(eq("blah"))).thenReturn(person);
//    }
//
//    @After
//    public void tearDown() {
//        // we have to reset the mock after each test because of the
//        // @ClassRule, or use a @Rule as mentioned below.
//        //reset(dao);
//    }
//
//    @Test
//    public void testPutDriver() {
//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//        RequestBody body = RequestBody.create(mediaType, "latitude=12.97161923&longitude=77.59463452&accuracy=1");
//        Request request = new Request.Builder()
//                .url("http://localhost:8080/drivers/1/location")
//                .put(body)
//                .addHeader("content-type", "application/x-www-form-urlencoded")
//                .addHeader("cache-control", "no-cache")
//                .addHeader("postman-token", "2f18cc26-de2c-594a-adba-5ea9a7e0d926")
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            Assert.assertEquals(200, response.code());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Test
//    public void testPut50000Driver() {
//        OkHttpClient client = new OkHttpClient();
//
//        Random r = new Random( System.currentTimeMillis());
//
//        for( int i = 1 ; i <= 5000 ; i++) {
//            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//
//            int multiplier = r.nextFloat() > 0.5f ? +1 : -1 ;
//            int generatedLat = r.nextInt(90) * multiplier;
//
//            multiplier = r.nextFloat() > 0.5f ? +1 : -1 ;
//            int generatedLon =  r.nextInt(90) * multiplier;
//
//            RequestBody body = RequestBody.create(mediaType, "latitude="+generatedLat+".97161923&longitude="+generatedLon+".59463452&accuracy=1");
//            Request request = new Request.Builder()
//                    .url("http://localhost:8080/drivers/"+i+"/location")
//                    .put(body)
//                    .addHeader("content-type", "application/x-www-form-urlencoded")
//                    .addHeader("cache-control", "no-cache")
//                    .addHeader("postman-token", "2f18cc26-de2c-594a-adba-5ea9a7e0d926")
//                    .build();
//
//            try {
//                Response response = client.newCall(request).execute();
//                //Assert.assertEquals(200, response.code());
//                if( response.code() != 200 ) {
//                    i = i -1;
//                    continue;
//                }
//                System.out.println(  "driver "+i+" at ("+generatedLon+","+generatedLat+")" );
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    @Test
//    public void testPutDriverInvalidDriverId() {
//
//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//        RequestBody body = RequestBody.create(mediaType, "latitude=12.97161923&longitude=77.59463452&accuracy=1");
//        Request request = new Request.Builder()
//                .url("http://localhost:8080/drivers/50001/location")
//                .put(body)
//                .addHeader("content-type", "application/x-www-form-urlencoded")
//                .addHeader("cache-control", "no-cache")
//                .addHeader("postman-token", "90c4e8d0-d80d-9820-5777-d10cd49612d7")
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            Assert.assertEquals(404, response.code());
//
//            // check body {}
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testPutDriverInvalidLocation() {
//
//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//        RequestBody body = RequestBody.create(mediaType, "latitude=92.97161923&longitude=77.59463452&accuracy=1");
//        Request request = new Request.Builder()
//                .url("http://localhost:8080/drivers/1/location")
//                .put(body)
//                .addHeader("content-type", "application/x-www-form-urlencoded")
//                .addHeader("cache-control", "no-cache")
//                .addHeader("postman-token", "90c4e8d0-d80d-9820-5777-d10cd49612d7")
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            Assert.assertEquals(422, response.code());
//
//            ResponseBody a = response.body();
//            // check body {"errors": ["Latitude should be between +/- 90"]}
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetDriver() {
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("http://localhost:8080/drivers?latitude=12.97161923&longitude%3A77.59463452=&radius=100&limit=2")
//                .get()
//                .addHeader("cache-control", "no-cache")
//                .addHeader("postman-token", "5cf3e985-415b-34ae-4918-f11406ec5db6")
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            Assert.assertEquals(200, response.code());
//            //parse response
//            // response count 2
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetDriverWithoutLimit() {
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("http://localhost:8080/drivers?latitude=12.97161923&longitude%3A77.59463452=&radius=100")
//                .get()
//                .addHeader("cache-control", "no-cache")
//                .addHeader("postman-token", "5cf3e985-415b-34ae-4918-f11406ec5db6")
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            Assert.assertEquals(200, response.code());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetDriverInvalidLocation() {
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("http://localhost:8080/drivers?latitude=92.97161923&longitude%3A77.59463452=&radius=100&limit=2")
//                .get()
//                .addHeader("cache-control", "no-cache")
//                .addHeader("postman-token", "5cf3e985-415b-34ae-4918-f11406ec5db6")
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            Assert.assertEquals(400, response.code());
//            // check response body {"errors": ["Latitude should be between +/- 90"]}
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
