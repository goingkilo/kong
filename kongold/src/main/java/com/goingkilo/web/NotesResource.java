package com.goingkilo.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.goingkilo.web.db.*;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import  com.goingkilo.web.entity.Note;
import java.util.List;

 
@Path("/notes")
public class NotesResource {
 
	@GET
	@Path("/add/{notebook}/{param}")
	public Response f1(@PathParam("notebook") String nname, @PathParam("param")  String msg) {
		String output = "Jersey say : " + nname;

 		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                Note note = new Note( msg );
                session.save(note);
                session.getTransaction().commit();
                session.close();

		return Response.status(200).entity(output).build();
	}


	@GET
	@Path("/get")
	public Response f2(@PathParam("notebookname") String msg) {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from Note ");
		List list = query.list();
		String html = "<div>";
		for( Object o : list ) {
			 html = html + "<p>" + ((Note)o).getMessage() ;
		}

		html += "</div>";
		
		session.close();
		return Response.status(200).entity(html).build();
	}

}
