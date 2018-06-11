package com.jschlim.rest;

import com.jschlim.Employee;
import com.jschlim.persistence.HibernateUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;
import org.hibernate.Session;

@Path("/hibernate")
public class HibernateService {

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postEmployee(Employee employee) {

		System.out.println("Service: Posting Employee ... ");

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		System.out.println("Service: begin transaction ... ");
		session.beginTransaction();
		

		try {
			System.out.println("Service: save employee ... ");
			session.save(employee);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			
			/* any exceptions thrown by Hibernate are FATAL, you have to roll back the transaction 
			 * and close the current session immediately.
			 */ 
			if ( session.getTransaction() != null ) 
				session.getTransaction().rollback();
			
			System.out.println("Service: Post exception caught ... ");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		String output = "Posted: " + employee + "\n";

		return Response.status(201).entity(output).build();
	}

	@GET
	@Path("/{EmployeeID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployee(@PathParam("EmployeeID") Integer employeeID) {

		System.out.println("Getting Employee " + employeeID + " ... ");

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Employee employee = null;
		String output = "";
		try {
			employee = (Employee) session.get( Employee.class, employeeID );
			session.getTransaction().commit();
		} catch (HibernateException e) {

			if ( session.getTransaction() != null ) 
				session.getTransaction().rollback();
			
			e.printStackTrace();
		} finally {
			session.close();
		}

		System.out.println("Got: " + employee + "\n");
		output = "Got: " + employee + "\n";

		return Response.status(200).entity(output).build();
	}

	/* Method to DELETE an employee from the records */
	@DELETE
	@Path("/{EmployeeID}")
	public Response deleteEmployee(@PathParam("EmployeeID") Integer employeeID) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Employee employee = null;
		String output = "";
		try {
			employee = (Employee) session.get(Employee.class, employeeID);
			
			if ( employee != null )
				session.delete(employee);
			else {
				output = "Service: No employee to delete at " + employeeID;
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {

			if ( session.getTransaction() != null ) 
				session.getTransaction().rollback();
			
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		output = "Deleted: " + employee + "\n";
		return Response.status(200).entity(output).build();
	}

}
