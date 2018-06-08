package com.jschlim.rest;

import com.jschlim.Employee;
import com.jschlim.persistence.HibernateUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;


@Path("/hibernate")
public class HibernateService {
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postEmployee( Employee employee ) {
		
		System.out.println("Posting Employee ... ");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(employee);
		session.getTransaction().commit();
		HibernateUtil.shutdown();
		
		String output = "Posted: " + employee + "\n";
		
		return Response.status(200).entity(output).build();
	}

}
