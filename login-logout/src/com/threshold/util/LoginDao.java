package com.threshold.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginDao {
	public static Connection getConnection(String host, int portNumber, String schema, String userName, String password) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + portNumber + "/" + schema+"?useUnicode=true&characterEncoding=UTF-8" +
		        "&rewriteBatchedStatements=true" , userName, password);
		connection.setAutoCommit(false);
		return connection;
	}

	public static void closeConnection(Connection connection) throws SQLException {
		if (!connection.getAutoCommit()) connection.commit();
		if (connection != null && !connection.isClosed()) connection.close();	
	}

	public static Integer getGeneratedKey(Connection connection, String sql, Object... args) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		int index = 0;
		for (Object value : args) preparedStatement.setObject(++index, value);
		preparedStatement.executeUpdate();
		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		if (resultSet.next()) {
			int id = resultSet.getInt(1);
			preparedStatement.close();
			resultSet.close();
			return id;
		}
		return 0;
	}

	private static PreparedStatement query(Connection conn, String sql, Object... args) throws SQLException {
		int index = 0;
		PreparedStatement preStmt = conn.prepareStatement(sql);
		for (Object value : args)
			preStmt.setObject(++index, value);
		return preStmt;
	}
	
	public static int execute(Connection conn, String sql, Object... args) throws SQLException {
		PreparedStatement preStmt = query(conn, sql, args);
		int result = preStmt.executeUpdate();
		preStmt.close();
		return result;	
	}
	
	public static Map<String, Object> get(Connection conn, String sql, Object... args) throws SQLException {
		return getMapList(conn, sql, args)!=null? getMapList(conn, sql, args).get(0):null;
	}

	public static List<Map<String, Object>> getMapList(Connection conn, String sql, Object... args) throws SQLException {
		PreparedStatement preStmt = query(conn, sql, args);
		ResultSet resultSet = preStmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		List<Map<String, Object>> listOfRec = new ArrayList<Map<String, Object>>();
		Map<String, Object> record = null;
		while (resultSet.next()) {
			record = new HashMap<String, Object>();
			for (int i = 1; i <= metaData.getColumnCount(); i++) record.put(metaData.getColumnName(i), resultSet.getObject(i));
			listOfRec.add(record);
		}
		preStmt.close();
		resultSet.close();
		return listOfRec.size()==0?null:listOfRec;
	}

	public static void batchUpdate(Connection conn, String sql, List<List<Object>> list) throws SQLException {
		PreparedStatement preStmt = conn.prepareStatement(sql);
		for (int record = 0; record < list.size(); record++) {
			int index = 0;
			for (Object value : list.get(record)) {
				preStmt.setObject(++index, value);
			}
			preStmt.addBatch();
		}
		preStmt.executeBatch();
		preStmt.close();
	}
}
