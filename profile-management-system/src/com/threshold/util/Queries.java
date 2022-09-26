package com.threshold.util;

public class Queries {
	
	public static final String INSERT_USER = "INSERT INTO `user`(name, phone,  gender, user_name, password) VALUES(?,?,?,?,?)";
	public static final String UPDATE_USER = "UPDATE `user` SET name=?, phone=?, gender=?,password=? WHERE pk_id=?";
	public static final String DELETE_USER = "DELETE FROM `user` WHERE user_name = ?"; 
	public static final String LIST_OF_USERS = "SELECT * FROM `user`";
	public static final String VALIDATE_USER="SELECT * FROM `user` WHERE user_name=? AND password=?";
	
}
