/**
 * 
 */
package com.ec.springresttemplate.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ec.springresttemplate.dao.DataRepo;
import com.ec.springresttemplate.vo.ErrorDetail;
import com.ec.springresttemplate.vo.UserVo;

@RestController
@RequestMapping("user")
public class UserController {

	@RequestMapping(value = "/fetchUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserVo> fetchUsers() {
		List<UserVo> usersList = DataRepo.getAll();
		return usersList;
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean createUser(@RequestBody UserVo user) {
		try {
			DataRepo.insert(user);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean updateUser(@RequestBody UserVo user) {
		try {
			DataRepo.update(user);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
	public boolean deleteUser(@PathVariable("id") int id) {
		try {
			DataRepo.delete(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserVo getUser(@PathVariable("id") int id) {
		UserVo user = DataRepo.get(id);
		if (null == user) {
			throw new UserNotFoundException(id);
		}
		return user;
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ErrorDetail myError(HttpServletRequest request, Exception exception) {
		ErrorDetail error = new ErrorDetail();
		error.setStatus(500);
		error.setMessage(exception.getLocalizedMessage());
		return error;
	}

}
