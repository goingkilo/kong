package com.goingkilo.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.goingkilo.web.db.*;
 
@Path("/ship1")
public class GeneralResource {
 
	@GET
	@Path("/{param}")
	public Response f1(@PathParam("param") String msg) {
		String output = "Jersey say : " + msg;
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/checkDB")
	public Response f2() {
		String msg = null;
		String s = PostgresTest.test();
		if( s == null ) 
			msg = "FAILED";
		else
			msg = s;

		String output = "Jersey say : " + msg;
		return Response.status(200).entity(output).build();
	}

	
 
}
