package com.jschlim.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyGetClient {

	public static void main(String[] args) {
		
		try {
			Client client = Client.create();
			
			//init map with employee at key 1
			
			String input = "{\"name\":\"Jim\", \"id\": 1}";
			
			WebResource webR = client.resource("http://localhost:8080/JerseyHelloWorld/rest/json/employees/post");
			
			ClientResponse response = webR.type("application/json")
					.post(ClientResponse.class, input);
			
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP post once : "
				     + response.getStatus());
			}
			
			//perform get on placed employee
			webR = client.resource("http://localhost:8080/JerseyHelloWorld/rest/json/employees/get/1");
			
			response = webR.accept("application/json")
					.get(ClientResponse.class);
			
			if ( response.getStatus() != 200 ) {
				throw new RuntimeException("Failed : HTTP invalid response : " 
			     + response.getStatus() );
			}
			
			String output = response.getEntity(String.class);
			
			System.out.println("Output from Server ....");
			System.out.println(output);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
