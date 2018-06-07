package com.jschlim.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jschlim.Employee;;

@Path("/json/employees")
public class JSONService {

	private static Map<Integer, Employee> m = new HashMap<Integer,Employee>();
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployee( @PathParam("id") Integer key ) {
		
		return Response.status(200).entity( m.get(key) ).build();
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEmployees() {
		
		String output = "";
		for (Integer k : m.keySet()) {
			
			output += m.get(k) + "\n";
		}
		
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmployee( Employee e ) {
		
		m.put( e.getId(), e );
		String result = "Employee saved : " + m.get(e.getId());
		
		return Response.status(201).entity(result).build();
	}
	
	@PUT
	@Path("/{Employee}/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployee( @PathParam("Employee") Employee eNew, @PathParam("id") Integer key ) {
		
		Employee eReplace = m.get(key);
		String output = "Updated: " + eReplace + " to:\t"
				+ "" + eNew;
		eReplace = eNew;
		
		return Response.status(200).entity(output).build();
	}
	
	@DELETE
	@Path("/{Employee}/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteEmployee( @PathParam("Employee") Employee eDelete, @PathParam("id") Integer key ) {
		
		m.put(key, null);
		String output = "Deleted: " + eDelete;
		
		return Response.status(200).entity(output).build();
	}
}
