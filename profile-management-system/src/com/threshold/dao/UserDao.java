package com.threshold.dao;

import java.sql.Connection;
import java.util.List;

public abstract class UserDao<T> {
	protected Connection conn;
	public final static String TABLE_NAME = "user";
	public static final String[] COLUMN_NAMES = {"name", "phone", "gender", "user_name","photo" ,"password","city"};

	public UserDao(Connection conn) {
		super();
		this.conn = conn;
	}

	public abstract int add(T obj) throws Exception;

	public abstract int update(T obj, int id) throws Exception;

	public abstract List<T> findAll() throws Exception;

	public abstract T find(int id) throws Exception;

	public abstract boolean delete(int id) throws Exception;
	
	public abstract T findUserByNameAndPwd(String user, String pwd) throws Exception ;

}
