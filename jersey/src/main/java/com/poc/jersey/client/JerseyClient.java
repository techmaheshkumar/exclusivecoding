package com.poc.jersey.client;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import com.poc.jersey.vo.UserVo;

public class JerseyClient {
	private static String URL = "http://localhost:8090/jersey/poc";

	/**
	 * Main Method.
	 */
	public static void main(String[] args) throws IOException {
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "admin");
		final Client client = ClientBuilder.newClient();
		client.register(feature);
		put(client);
		get(client);
		//post(client);
		//get(client);
		//delete(client);
//		get(client);
		//fileUpload();
	}

	/**
	 * GET Test.
	 */
	private static void get(Client client) {
		WebTarget webTarget = client.target(URL).path("getAll");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		System.out.println("---Get--- " + response.getStatus());
		System.out.println(response.readEntity(String.class));
	}

	/**
	 * PUT Test.
	 */
	private static void put(Client client) {
		WebTarget webTarget = client.target(URL).path("saveUser");

		UserVo user = new UserVo();
		user.setUserId(1);
		user.setName("Mahesh-Client");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.put(Entity.entity(user, MediaType.APPLICATION_JSON));

		System.out.println("---Put--- " + response.getStatus());
		System.out.println(response.readEntity(String.class));
	}

	/**
	 * POST Test.
	 */
	private static void post(Client client) {
		WebTarget webTarget = client.target(URL).path("updateUser");

		UserVo user = new UserVo();
		user.setUserId(1);
		user.setName("Mahesh-Client-1");
		user.setEmail("maheshkumar@gmail.com");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));

		System.out.println("---Post--- " + response.getStatus());
		System.out.println(response.readEntity(String.class));
	}

	/**
	 * DELETE Test.
	 */
	private static void delete(Client client) {
		WebTarget webTarget = client.target(URL).path("deleteUser").path("1");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.delete();

		System.out.println("---Delete--- " + response.getStatus());
		System.out.println(response.readEntity(String.class));
	}

	/**
	 * FileUpload Test.
	 */
	private static void fileUpload() throws IOException {
		final Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "admin");
		client.register(feature);

		final FileDataBodyPart filePart = new FileDataBodyPart("file", new File("C:/temp/download.txt"));
		FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
		final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.bodyPart(filePart);

		final WebTarget target = client.target(URL).path("uploadFile");
		final Response response = target.request().post(Entity.entity(multipart, multipart.getMediaType()));
		System.out.println("---fileUpload--- " + response.getStatus());
		System.out.println(response.readEntity(String.class));

		formDataMultiPart.close();
		multipart.close();
	}

}
