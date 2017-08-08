package com.ec.springresttemplate.controller;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(int key) {
		super(" userId : " + key + " is not available");
	}
}
