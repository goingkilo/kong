import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/todo")
public class ItemResource {

    ItemDAO dao;

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemResource.class);

    public ItemResource(ItemDAO dao) {
        this.dao  = dao;
    }

    @GET
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getAll() {
        return dao.getAllItems();
//        return Response.ok().entity(l).build();
    }

    @POST
    @UnitOfWork
    public Response create(@FormParam("item") Item item) {
        dao.create(item);
        return Response.ok().entity( item ).build();
    }

    @PUT
    @UnitOfWork
    public Response update(@FormParam("item") Item item) {
        dao.update(item);
        return Response.ok().entity(item).build();
    }

    @DELETE
    @UnitOfWork
    public Response delete(@PathParam("id") Long id) {
        dao.delete(id);
        return Response.ok().build();
    }

}
