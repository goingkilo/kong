package lego.user;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.NonEmptyStringParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	public static final String APP_PATH = "/api";

	UserDAO dao;

	public UserResource(UserDAO dao) {
		this.dao  = dao;
	}

	@POST
	@Path("/login")
	@Consumes("application/x-www-form-urlencoded")
	@UnitOfWork
	@Produces(MediaType.TEXT_HTML)
	public Response  login (
			@FormParam("username") NonEmptyStringParam username,
			@FormParam("password") NonEmptyStringParam password,
			@Context HttpServletRequest request

	) {
		System.out.println( "hello world");

		String token = validate( username.get().get(), password.get().get());
		 if( token != null ) {

			 String s = "";
			 for( String s1 : request.getParameterMap().keySet()) {
				 s += ":" + s1 + "/" + request.getParameter(s1);
			 }

			 System.out.println( s);
			 return Response.ok()
					 .entity( s )
					 .cookie(new NewCookie("letoken", "star wars fan"))
					 .build();
		 }

		URI uri = UriBuilder.fromUri( "/index.html").build();
		return Response.seeOther(uri).build();
	}

	@GET
	@RolesAllowed("donkey")
	public Response getME(){
		return Response.ok().entity("only donkeys").build();
	}

	@GET
	@Path("/all")
	@PermitAll
	public Response getAll(){
		return Response.ok().entity("you are all donkeys").build();
	}


	@GET
	@Path("/dump")
	public Response dumpAllHere(){
		return Response.ok().entity("You are seeing this page because 42").build();
	}


	private String validate(
			String s, String s1) {
		return ( s.equalsIgnoreCase("gun@powder.plot") && s1.equalsIgnoreCase("password")) ? "token" : null;
	}

}
