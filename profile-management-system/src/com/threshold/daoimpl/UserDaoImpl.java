package com.threshold.daoimpl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.threshold.dao.UserDao;
import com.threshold.dao.util.DbUtil;
import com.threshold.util.Utility;

public class UserDaoImpl extends UserDao<Map<String, Object>> {

	public UserDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public int add(Map<String, Object> obj) throws Exception {
		//String[] cols = obj.keySet().toArray(new String[obj.keySet().size() - 1]);
		return DbUtil.add(conn, TABLE_NAME, COLUMN_NAMES, obj);
	}

	@Override
	public int update(Map<String, Object> obj, int id) throws Exception {
		Map<String, Object> rec = find(id);
		if (rec != null) {
			Map<String, Object> newObj = Utility.modifyMapWithNewValues(COLUMN_NAMES, obj);
			String[] columns  = newObj.keySet().toArray(new String[newObj.keySet().size()]);
			return DbUtil.update(conn, TABLE_NAME, columns, newObj, id);
		}
		return -1;
	}

	@Override
	public List<Map<String, Object>> findAll() throws Exception {
		return DbUtil.queryForList(conn,TABLE_NAME);
	}

	@Override
	public Map<String, Object> find(int id) throws Exception {
		return DbUtil.queryForMap(conn, TABLE_NAME, id);
	}

	public boolean delete(int id) throws Exception {
		return find(id) != null ? DbUtil.delete(conn, TABLE_NAME, id) : false;
	}

	@Override
	public Map<String, Object> findUserByNameAndPwd(String user, String pwd) throws Exception {
		return DbUtil.queryForMap(conn, TABLE_NAME, user,pwd);
	}

}
