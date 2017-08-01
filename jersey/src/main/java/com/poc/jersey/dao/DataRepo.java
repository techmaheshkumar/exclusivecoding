/**
 * 
 */
package com.poc.jersey.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.poc.jersey.vo.UserVo;

/**
 * @author MSL6COB
 *
 */
public class DataRepo {

	private static Map<Integer, UserVo> dataMap = new HashMap<Integer, UserVo>();

	/**
	 * To Get All.
	 * 
	 * @return List<UserVo>
	 * @throws Exception
	 */
	public static List<UserVo> getAll() {
		List<UserVo> userList = new ArrayList<UserVo>();
		for (Entry<Integer, UserVo> entry : dataMap.entrySet()) {
			userList.add(entry.getValue());
		}		
		return userList;
	}

	/**
	 * To Save User.
	 * 
	 * @param userVo
	 */
	public static void insert(UserVo userVo) throws Exception {
		dataMap.put(userVo.getUserId(), userVo);
		//throw new Exception();
	}

	public static void update(UserVo userVo) {
		dataMap.put(userVo.getUserId(), userVo);
	}

	public static void delete(int userId) {
		dataMap.remove(userId);
	}

}
