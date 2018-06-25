import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("web")
public class BasicResource {

        private static final Logger LOGGER = LoggerFactory.getLogger(BasicResource.class);

        public BasicResource() {

        }

        @GET
        public Response tracer() {
            return Response.ok().entity( "Hello world" ).build();
        }


    }
