package com.ec.springresttemplate.vo;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class JsonApi {

	public static void main(String[] args) throws IOException {
		useJackson();
		useGson();
	}

	private static void useJackson() {
		try {
			ObjectMapper mapper = new ObjectMapper();

			UserVo user = new UserVo();
			user.setUserId(1);
			user.setName("Mahesh");
			user.setEmail("mahesh@gmail.com");
			user.setMobile("9952262062");

			// Convert object to JSON string
			String jsonString = mapper.writeValueAsString(user);

			System.out.println("object to JSON :\n" + jsonString);

			// Convert JSON to Java string
			UserVo userVo = mapper.readValue(jsonString, UserVo.class);
			System.out.println("\nJSON to Object :\n" + userVo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void useGson() {
		Gson gson = new Gson();

		UserVo user = new UserVo();
		user.setUserId(1);
		user.setName("Mahesh");
		user.setEmail("mahesh@gmail.com");
		user.setMobile("9952262062");

		// Convert object to JSON string
		String jsonString = gson.toJson(user);
		System.out.println("object to JSON :\n" + jsonString);

		// Convert JSON to Java string
		UserVo userVo = gson.fromJson(jsonString, UserVo.class);
		System.out.println("\nJSON to Object :\n" + userVo);
	}

}
