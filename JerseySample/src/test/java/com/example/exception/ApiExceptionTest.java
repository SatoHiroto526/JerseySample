package com.example.exception;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.jupiter.api.Test;

public class ApiExceptionTest {

	//URI定義
	private final String dataNotFoundExceptionUri = "http://localhost:8080/JerseySample/rest/api/getbyid";
	private final String notFoundExceptionUri = "http://localhost:8080/JerseySample/rest/api/get";
	private final String notAllowdExceptionUri = "http://localhost:8080/JerseySample/rest/api/getlist";
	private final String unAuthorizedExceptionUri_1 = "http://localhost:8080/JerseySample/rest/api/getlist";
	private final String unAuthorizedExceptionUri_2 = "http://localhost:8080/JerseySample/rest/api/getlist";

	//パラメーター定義
	private int postId = 6;

	//DataNotFoundExceptionTest
	@Test
	public void dataNotFoundExceptionTest() {

		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(dataNotFoundExceptionUri);

		String requestBody = "{\"id\":" + postId + "}";

		Response response = target.register(HttpAuthenticationFeature.basic("test", "test"))
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(requestBody, MediaType.APPLICATION_JSON));

		System.out.println("POST(Response Status):" + response.getStatus());
		System.out.println("POST(Response Body):" + response.readEntity(String.class));

		assertEquals(404, response.getStatus());

		response.close();
		client.close();

	}

	//NotFoundExceptionTest
	@Test
	public void notFoundExceptionTest() {

		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(notFoundExceptionUri);

		Response response = target.register(HttpAuthenticationFeature.basic("test", "test"))
				.request(MediaType.APPLICATION_JSON)
				.get();

		System.out.println("POST(Response Status):" + response.getStatus());
		System.out.println("POST(Response Body):" + response.readEntity(String.class));

		assertEquals(404, response.getStatus());

		response.close();
		client.close();

	}

	//NotAllowdExceptionTest
	@Test
	public void notAllowdExceptionTest() {

		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(notAllowdExceptionUri);

		Response response = target.register(HttpAuthenticationFeature.basic("test", "test"))
				.request(MediaType.APPLICATION_JSON)
				.post(null);

		System.out.println("POST(Response Status):" + response.getStatus());
		System.out.println("POST(Response Body):" + response.readEntity(String.class));

		assertEquals(405, response.getStatus());

		response.close();
		client.close();

	}

	//unAuthorizedException_1_Test
	@Test
	public void unAuthorizedException_1_Test() {

		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(unAuthorizedExceptionUri_1);

		Response response = target.register(HttpAuthenticationFeature.basic("te", "te"))
				.request(MediaType.APPLICATION_JSON)
				.get();

		System.out.println("POST(Response Status):" + response.getStatus());
		System.out.println("POST(Response Body):" + response.readEntity(String.class));

		assertEquals(401, response.getStatus());

		response.close();
		client.close();

	}

	//unAuthorizedException_2_Test
	@Test
	public void unAuthorizedException_2_Test() {

		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(unAuthorizedExceptionUri_2);

		Response response = target.request(MediaType.APPLICATION_JSON)
				.get();

		System.out.println("POST(Response Status):" + response.getStatus());
		System.out.println("POST(Response Body):" + response.readEntity(String.class));

		assertEquals(401, response.getStatus());

		response.close();
		client.close();

	}

}
