package com.jschlim.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyDeleteAllClient {

	public static void main(String[] args) {
		
		Client client = Client.create();
		
		WebResource webR = client.resource(
				"http://localhost:8080/JerseyHelloWorld/rest/json/employees/clear");
		
		ClientResponse response = webR.type("application/json")
				.delete(ClientResponse.class);
		
		if ( response.getStatus() != 200 ) {
			throw new RuntimeException("Failed : HTTP invalid response : " 
		     + response.getStatus() );
		}
		
		String output = response.getEntity(String.class);
		
		System.out.println("Output from Server ....");
		System.out.println(output);
	}

}
