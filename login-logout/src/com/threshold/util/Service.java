package com.threshold.util;

import java.sql.Connection;
import java.util.Map;

import com.threshold.pojo.User;

public class Service {
	public static boolean validateUser(User user) {
		Connection conn =null;
		Map<String, Object> data = null;
		try {
			conn = LoginDao.getConnection("localhost", 3306, "threshold", "admin", "12345");
			data= LoginDao.get(conn, "SELECT `name`,`password` FROM `user` WHERE `name`=? AND  `password`=?",user.getName(),user.getPassword() );
			LoginDao.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data!= null? true : false;
	}
	
}
