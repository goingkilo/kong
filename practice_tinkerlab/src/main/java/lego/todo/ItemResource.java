package lego.todo;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import io.dropwizard.jersey.params.NonEmptyStringParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemResource.class);

	ItemDAO dao;

	public ItemResource(ItemDAO dao) {
		this.dao  = dao;
	}

	@GET
	@UnitOfWork
	@Produces(MediaType.TEXT_HTML)
	public ItemsView get() {
		List<Item> l = dao.findAll();
		return new ItemsView( l);
	}

	@GET
	@UnitOfWork
	@Path("{id}")
	@Produces(MediaType.TEXT_HTML)
	public ItemView get(@PathParam("id") Long id) {
		Item item = dao.findById(id);
		return new ItemView( item);
	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_HTML)
	@UnitOfWork
	public Response createPost(@FormParam("data") NonEmptyStringParam data) {
		Item item = new Item();
		item.setDetail(data.get().get());
		dao.create( item);

		return redirectToGet();
	}

	@POST
	@Path("/update")
	@Consumes("application/x-www-form-urlencoded")
	@UnitOfWork
	@Produces(MediaType.TEXT_HTML)
	public Response update(
			@FormParam("id") LongParam id,
			@FormParam("data") String data
	) {
		Item item = dao.findById(id.get());

		if( !StringUtils.isBlank(data)){
			item.setDetail(data);
		}
		dao.update(item);

		return redirectToGet();
	}

	@GET
	@Path("/delete/{id}")
	@UnitOfWork
	@Produces(MediaType.TEXT_HTML)
	public ItemsView delById(@PathParam("id") LongParam id) {
		Item item = new Item();
		item.setId(id.get());
		dao.delete(item);
		return get();
	}

	private Response redirectToGet(){
		URI uri = UriBuilder.fromUri("/api/items").build();
		return Response.seeOther(uri).build();
	}


}
