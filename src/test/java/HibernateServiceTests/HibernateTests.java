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

		System.out.println("\n--Starting test post--");
		Client client = Client.create();

		String input1 = "{\"name\":\"Bill\", \"id\": " + 1 + "}";
		String input2 = "{\"name\":\"James\", \"id\": " + 2 + "}";
		String input3 = "{\"name\":\"Jim\", \"id\": " + 3 + "}";
		String[] arr = { input1, input2, input3 };

		WebResource webR = client.resource("http://localhost:8080/JerseyHelloWorld/rest/hibernate/");

		System.out.println("HibernateTest: POST output from HibernateService .... ");
		//post each employee
		for ( String s : arr ) {

			ClientResponse response = webR.type("application/json").post(ClientResponse.class, s );

			assertTrue("Post returns: " + response.getStatus(), response.getStatus() == 201);

			String output = response.getEntity(String.class);
			System.out.println(output);
		}
	}

	@Test
	public void getEmployeeJSONreturns200() {

		System.out.println("\n--Starting test get--");
		Client client = Client.create();
		WebResource webR = client.resource("http://localhost:8080/JerseyHelloWorld/rest/hibernate/2");

		ClientResponse response = webR.accept("application/json").get(ClientResponse.class);

		assertTrue("Get returns: " + response.getStatus(), response.getStatus() == 200);

		String output = response.getEntity(String.class);

		System.out.println("HibernateTest: GET output from HibernateService .... ");
		System.out.println(output);
	}

	@Test
	public void deleteEmployeeJSONreturns200() {

		System.out.println("\n--Starting test delete--");
		Client client = Client.create();
		WebResource webR = client.resource("http://localhost:8080/JerseyHelloWorld/rest/hibernate/1");

		ClientResponse response = webR.type("application/json").delete(ClientResponse.class);

		assertTrue("Delete returns: " + response.getStatus(), response.getStatus() == 200);

		String output = response.getEntity(String.class);

		System.out.println("HibernateTest: DELETE output from HibernateService .... ");
		System.out.println(output);
	}
	
	@Test
	public void updateEmployeeJSONreturns200() {
		
		System.out.println("\n--Starting test update--");
		Client client = Client.create();
		WebResource webR = client.resource("http://localhost:8080/JerseyHelloWorld/rest/hibernate");

		String input2 = "{\"name\":\"NewJames\", \"id\": " + 2 + "}";
		
		ClientResponse response = webR.type("application/json")
				.put(ClientResponse.class, input2 );

		assertTrue("Update returns: " + response.getStatus(), response.getStatus() == 200);

		String output = response.getEntity(String.class);

		System.out.println("HibernateTest: PUT output from HibernateService .... ");
		System.out.println(output);
	}
}
