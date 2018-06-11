package HibernateServiceTests;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HibernateTests {
	
	@Test
	public void postingEmployeeJSONreturns201() {
		
			System.out.println("starting test post");
			Client client = Client.create();
			
			String input = "{\"name\":\"Bill\", \"id\": " + 1 + "}";	
			WebResource webR = client.resource( "http://localhost:8080/JerseyHelloWorld/rest/hibernate/" );
			
			ClientResponse response = webR.type("application/json")
					.post( ClientResponse.class, input );
			
			assertTrue( "Null Response", response != null );
			assertTrue( "Post returns: " + response.getStatus(), response.getStatus() == 201 );
			
			String output = response.getEntity(String.class);
			
			System.out.println("Test 1 : output from HibernateService .... ");
			System.out.println( output );
		
	}
	
	@Test
	public void getEmployeeJSONreturns200() {
		
		System.out.println("starting test get");
		Client client = Client.create();
		WebResource webR = client.resource( "http://localhost:8080/JerseyHelloWorld/rest/hibernate/1" );
		
		ClientResponse response = webR.type("application/json")
				.get( ClientResponse.class );
		
		assertTrue( "Null Response", response != null );
		assertTrue( "Get returns: " + response.getStatus(), response.getStatus() == 200 );
		
		String output = response.getEntity(String.class);
		
		System.out.println("Test 2 : output from HibernateService .... ");
		System.out.println(output);
	}
	
	@Test
	public void deleteEmployeeJSONreturns200() {
		
		System.out.println("starting test delete");
		Client client = Client.create();
		WebResource webR = client.resource( "http://localhost:8080/JerseyHelloWorld/rest/hibernate/1" );
		
		ClientResponse response = webR.type("application/json")
				.delete( ClientResponse.class );
		
		assertTrue( "Null Response", response != null );
		assertTrue( "Delete returns: " + response.getStatus(), response.getStatus() == 200 );
		
		String output = response.getEntity(String.class);
		
		System.out.println("Test 3 : output from HibernateService .... ");
		System.out.println(output);
	}
}
