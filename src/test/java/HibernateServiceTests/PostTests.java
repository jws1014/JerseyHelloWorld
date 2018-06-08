package HibernateServiceTests;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PostTests {
	
	@Test
	public void postingEmployeeJSONreturns200() {
		
			Client client = Client.create();
			
			String input = "{\"name\":\"Bill\", \"id\": " + 5 + "}";	
			WebResource webR = client.resource( "http://localhost:8080/JerseyHelloWorld/rest/hibernate/" );
			
			ClientResponse response = webR.type("application/json")
					.post( ClientResponse.class, input );
			
			
			assert( response.getStatus() == 200 );
			
			String output = response.getEntity(String.class);
			
			System.out.println("Output from HibernateService .... ");
			System.out.println(output);
		
	}
}
