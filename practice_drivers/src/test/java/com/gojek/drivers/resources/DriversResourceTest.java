//package com.gojek.drivers.resources;
//
//import com.gojek.drivers.api.Driver;
//import com.gojek.drivers.db.DriverDAO;
//import io.dropwizard.testing.junit.ResourceTestRule;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.ClassRule;
//
//import static org.mockito.Mockito.*;
//
//public class PersonResourceTest {
//
//    private static final DriverDAO dao = mock(DriverDAO.class);
//
//    @ClassRule
//    public static final ResourceTestRule resources = ResourceTestRule.builder()
//            .addResource(new Driver(dao))
//            .build();
//
//    private final Driver person = new Driver(1,new Location(1,1));
//
//    @Before
//    public void setup() {
//        when(dao.get(eq("blah"))).thenReturn(person);
//    }
//
//    @After
//    public void tearDown(){
//        // we have to reset the mock after each test because of the
//        // @ClassRule, or use a @Rule as mentioned below.
//        reset(dao);
//    }
//
//    @Test
//    public void testGetPerson() {
//        assertThat(resources.client().target("/person/blah").request().get(Person.class))
//                .isEqualTo(person);
//        verify(dao).fetchPerson("blah");
//    }
//}