package com.ec.springresttemplate.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ec.springresttemplate.vo.UserVo;

public class Client {

	public static final String REST_SERVICE_URI = "http://localhost:8080/spring-rest-template/user";

	private static void listAllUsers() {

		RestTemplate restTemplate = new RestTemplate();
		String usersList = restTemplate.getForObject(REST_SERVICE_URI + "/fetchUsers", String.class);
		System.out.println(usersList.toString());
	}

	private static void createUser() {
		RestTemplate restTemplate = new RestTemplate();
		UserVo user = new UserVo();
		user.setUserId(1);
		user.setName("Mahesh");
		restTemplate.put(REST_SERVICE_URI + "/createUser", user);
	}

	private static void updateUser() {
		RestTemplate restTemplate = new RestTemplate();
		UserVo user = new UserVo();
		user.setUserId(1);
		user.setName("Mahesh_updated");
		ResponseEntity<Boolean> entity = restTemplate.postForEntity(REST_SERVICE_URI + "/updateUser", user,
				Boolean.class);
		System.out.println(entity.getBody());
	}

	private static void deleteUser() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		restTemplate.delete(REST_SERVICE_URI + "/deleteUser/{id}", params);
	}

	public static void main(String args[]) {
		listAllUsers();
		createUser();
		listAllUsers();
		updateUser();
		listAllUsers();
		deleteUser();
		listAllUsers();
	}
}