package com.threshold.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Part;

public class Utility {
	public static final char GRAVE = '`';
	public static Part part = null;
	public static final String FILE_PATH = "F:\\Threshold\\LoginTask\\WebContent\\images";
	public static final List<String> FILE_TYPE = Arrays.asList(new String[]{"image/jpg","image/jpeg","image/png"});

	public static boolean isBlank(Object o) {
		if (o == null)
			return true;
		else if (o instanceof String) {
			if (((String) o).trim().equals(""))
				return true;
		} else if (o instanceof Collection<?>) {
			if (((Collection<?>) o).isEmpty())
				return true;
		} else if (o instanceof Integer) {
			if (((Integer) o) <= 0)
				return true;
		} else if (o instanceof Long) {
			if (((Long) o) <= 0)
				return true;
		} else if (o instanceof Short) {
			if (((Short) o) <= 0)
				return true;
		} else if (o instanceof Byte) {
			if (((Byte) o) <= 0)
				return true;
		} else if (o instanceof Double) {
			if (((Double) o) <= 0)
				return true;
		} else if (o instanceof Float) {
			if (((Float) o) <= 0)
				return true;
		} else if (o instanceof Map<?, ?>) {
			if (((Map<?, ?>) o).isEmpty())
				return true;
		} else if (o.getClass().isArray()) {
			return Array.getLength(o) == 0;
		} else {
			if (o.toString().trim().equals(""))
				return true;
		}
		return false;
	}

	public static String encloseGrave(String s) {
		if (Utility.isBlank(s))
			return null;
		else
			return GRAVE + s.trim() + GRAVE;
	}

	public static String generateInsertQuery(String tableName, String[] columns) {
		String colNames = "", placeHolders = "";
		for (String c : columns) {
			colNames += encloseGrave(c) + ",";
			placeHolders += "?,";
		}
		colNames = colNames.substring(0, colNames.length() - 1);
		placeHolders = placeHolders.substring(0, placeHolders.length() - 1);
		return "INSERT INTO " + tableName + " (" + colNames + ") VALUES (" + placeHolders + ");";
	}

	public static String generateUpdateQuery(String tableName, String[] columns) {
		String colNames = "";
		for (String c : columns) {
			colNames += encloseGrave(c) + "=?, ";
		}
		colNames = colNames.substring(0, colNames.length() - 2);
		return "UPDATE " + encloseGrave(tableName) + " SET " + colNames + " WHERE `pk_id`=?;";
	}

	public static String generateDeleteQuery(String tableName) {
		return "DELETE FROM " + encloseGrave(tableName) + " WHERE `pk_id`=?;";
	}

	public static String generateSelectAllQuery(String tableName) {
		return "SELECT * FROM " + encloseGrave(tableName);
	}

	public static String generateSelectById(String tableName) {
		return "SELECT * FROM " + encloseGrave(tableName) + " WHERE " + encloseGrave("pk_id") + "=?";
	}

	public static String genQueryForUserAndPwd(String tableName) {
		return "SELECT * FROM " + encloseGrave(tableName) + " WHERE " + encloseGrave("user_name") + "=? AND "
				+ encloseGrave("password") + "=?;";
	}

	public static Map<String, Object> modifyMapData(Map<String, Object> fromDb, Map<String, Object> fromUser) {
		Set<String> s = fromUser.keySet();
		for (String key : s) {
			fromDb.replace(key, fromDb.get(key), fromUser.get(key));
		}
		return fromDb;
	}

	public static Map<String, Object> modifyMapWithNewValues(String[] cols, Map<String, Object> fromUser) {
		Set<String> s = fromUser.keySet();
		Map<String, Object> modifiedMap = new LinkedHashMap<String, Object>();
		for (String key : cols) {
			if (s.contains(key)) {
				modifiedMap.put(key, fromUser.get(key));
			}
		}
		return modifiedMap;
	}

	/**
	 * Converts input blob into bytes and returns a string of base64 format.
	 */
	public static String convertToBase64(Blob blob) throws Exception {
		if (isBlank(blob))
			return "";
		InputStream inputStream = blob.getBinaryStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1)
			outputStream.write(buffer, 0, bytesRead);
		byte[] imageBytes = outputStream.toByteArray();
		String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		return base64Image;
	}

	public static String fileToPath(Part part) throws Exception{
		if(FILE_TYPE.contains(part.getContentType())) {
			String fileName = part.getSubmittedFileName();
			InputStream inStream = part.getInputStream();
			System.out.println("from filetopath()"+part.getContentType());
			File file = new File(FILE_PATH+File.separator + part.getSubmittedFileName());
			Files.copy(inStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			inStream.close();
			return fileName;
		}
		return null;
	}

}
