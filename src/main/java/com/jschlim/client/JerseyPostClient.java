package com.jschlim.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyPostClient {

	public static void main(String[] args) {
		
		JerseyPostClient j = new JerseyPostClient();
		j.postToService( 5 );
	}
	
	private void postToService( int num ) {
		
		for ( int i = 0; i < num; i++ ) {
			
			try {
				Client client = Client.create();
				
				String input = "{\"name\":\"Bill\", \"id\": " + i + "}";
				
				WebResource webR = client.resource("http://localhost:8080/JerseyHelloWorld/rest/json/employees/");
				
				ClientResponse response = webR.type("application/json")
						.post(ClientResponse.class, input);
				
				if (response.getStatus() != 201) {
					throw new RuntimeException("Failed : HTTP post once : "
					     + response.getStatus());
				}
				
				String output = response.getEntity(String.class);
				
				System.out.println("Output from Server .... ");
				System.out.println(output);
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
		}
	}

}
