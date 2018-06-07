package com.jschlim.rest;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jschlim.Employee;;

@Path("/json/employees")
public class JSONService {

	private HashMap<Integer, Employee> m = new HashMap<Integer,Employee>();
	
	@GET
	@Path("/get/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMsg( @PathParam("param") Integer key ) {
		
		System.out.println(m.get(key));

		return Response
				.status(200)
				.entity("Employee: " + m.get(key) + " id: " + key )
				.build();
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmployeeInJSON( Employee e ) {
		
		m.put( e.getId(), e );
		String result = "Employee saved : " + m.get(e.getId());
		
		return Response.status(201).entity(result).build();
	}
}
