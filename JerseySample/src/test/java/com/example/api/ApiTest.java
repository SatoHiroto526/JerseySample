package com.example.api;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.jupiter.api.Test;

public class ApiTest {
	
	//URI定義
	private final String getUri = "http://localhost:8080/JerseySample/rest/api/getlist";
	private final String postUri = "http://localhost:8080/JerseySample/rest/api/getbyid";
	
	//パラメーター定義
	private int postId = 3;

	//GetRequestTest
	@Test
	public void getRequestTest() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target(getUri);
		
		Response response = target.register(HttpAuthenticationFeature.basic("test", "test"))
				.request(MediaType.APPLICATION_JSON)
				.get();
		
		System.out.println("GET(Response Status):" + response.getStatus());
		System.out.println("GET(Response Body):" + response.readEntity(String.class));
		
		assertEquals(200, response.getStatus());
		
		response.close();
		client.close();
		
	}
	
	//PostRequestTest
	@Test
	public void postRequestTest() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target(postUri);
		
		String requestBody = "{\"id\":" + postId + "}";
		
		Response response = target.register(HttpAuthenticationFeature.basic("test", "test"))
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(requestBody, MediaType.APPLICATION_JSON));
		
		System.out.println("POST(Response Status):" + response.getStatus());
		System.out.println("POST(Response Body):" + response.readEntity(String.class));
		
		assertEquals(200, response.getStatus());
		
		response.close();
		client.close();
		
	}

}
