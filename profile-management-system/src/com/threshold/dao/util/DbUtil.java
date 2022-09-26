package com.threshold.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.threshold.util.Utility;

public class DbUtil {
	
	public static Connection getConnection(boolean transaction) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/threshold", "admin", "12345");
		connection.setAutoCommit(!transaction);
		return connection;
	}

	public static void closeConnection(Connection connection) throws SQLException {
		if (!connection.getAutoCommit()) connection.commit();
		if (connection != null && !connection.isClosed()) connection.close();	
	}
	
	public static int add(Connection connection, String tableName,String[] columns, Map<String,Object> obj) throws Exception  {
		String sql = Utility.generateInsertQuery(tableName, columns);
		PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		for (int i =0; i<columns.length;i++) {
			/*if(columns[i].equalsIgnoreCase("photo")) ps.setBlob(i+1, Utility.part.getInputStream());
			else */ps.setObject(i+1, obj.get(columns[i]));
		}
		ps.executeUpdate();
		ResultSet resultSet = ps.getGeneratedKeys();
		if (resultSet.next()) {
			int id = resultSet.getInt(1);
			ps.close();
			resultSet.close();
			return id;
		}
		return 0;
	}
	
	public static int update(Connection conn, String tableName,String[] columns, Map<String,Object> obj,int id) throws Exception {
		String sql = Utility.generateUpdateQuery(tableName, columns);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(columns.length+1, id);
		for (int i =0; i<columns.length;i++) {
			/*if(columns[i].equalsIgnoreCase("photo")) ps.setBlob(i+1, Utility.part.getInputStream());
			else */ps.setObject(i+1, obj.get(columns[i]));
		}
		int result = ps.executeUpdate();
		ps.close();
		return result;	
	}
	
	public static boolean delete(Connection conn, String tableName, int id) throws SQLException {
		String sql = Utility.generateDeleteQuery(tableName);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, id);
		int result = ps.executeUpdate();
		ps.close();
		return result>0?true:false;				
	}
	
	public static Map<String, Object> queryForMap(Connection conn, String tableName,Object... args) throws Exception{
		List<Map<String, Object>> list = queryForList(conn, tableName, args);
		return list==null || list.size()==0 ? null : list.get(0);
	}
	public static List<Map<String, Object>> queryForList(Connection conn, String tableName, Object... args) throws Exception {
		String sql = genQueryForMapOrList(tableName,args);
		PreparedStatement ps = conn.prepareStatement(sql);
		int index = 0;
		for (Object value : args)
			ps.setObject(++index, value);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> record = null;
		while (rs.next()) {
			record = new LinkedHashMap<String, Object>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//				if(rsmd.getColumnName(i).toString().equalsIgnoreCase("photo")) {
//					record.put(rsmd.getColumnName(i), Utility.convertToBase64(rs.getBlob("photo")));
//				}else 
				record.put(rsmd.getColumnName(i), rs.getObject(i));
			}
			list.add(record);
		}
		ps.close();
		rs.close();
		return list;
	}
	
	private static String genQueryForMapOrList(String tableName,Object... args) {
		if(Utility.isBlank(args)) return Utility.generateSelectAllQuery(tableName);
		else if(args.length==1) return Utility.generateSelectById(tableName);
		else return Utility.genQueryForUserAndPwd(tableName);
	}
	
	public static void batchUpdate(Connection conn, String sql, List<List<Object>> list) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		for (int record = 0; record < list.size(); record++) {
			int index = 0;
			for (Object value : list.get(record)) {
				ps.setObject(++index, value);
			}
			ps.addBatch();
		}
		ps.executeBatch();
		ps.close();
	}

}
