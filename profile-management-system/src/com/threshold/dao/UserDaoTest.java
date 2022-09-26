package com.threshold.dao;

import java.sql.Connection;
import java.util.Map;

import com.threshold.dao.util.DbUtil;
import com.threshold.daoimpl.UserDaoImpl;

public class UserDaoTest {
	public static void main(String[] args) throws Exception {
//		Map<String, Object> map = new LinkedHashMap<String, Object>();
//		map.put("name", "guru");
//		map.put("phoned", "");
//		map.put("gender", "");
//		map.put("user_name", "laxmi40");
//		map.put("password", "The@1234");
//		map.values().removeAll(Collections.singleton(""));
//		System.out.println(map);
		Connection connection = DbUtil.getConnection(true);
		UserDao<Map<String, Object>> dao = new UserDaoImpl(connection);
//		System.out.println(dao.add(map));
//	    System.out.println(dao.update(map, 4));
//		System.out.println(dao.delete(7));
		System.out.println(dao.find(30));
		System.out.println(dao.findAll());
//		DbUtil.closeConnection(connection);
	}
}
