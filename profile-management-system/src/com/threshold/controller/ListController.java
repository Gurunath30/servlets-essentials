package com.threshold.controller;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.threshold.base.AccessControl;
import com.threshold.base.BaseServlet;
import com.threshold.dao.UserDao;
import com.threshold.daoimpl.UserDaoImpl;
import com.threshold.util.PageConstants;
@WebServlet("/listcontroller")
@SuppressWarnings("serial")
public class ListController extends BaseServlet {
	@Override
	protected void doAction(AccessControl control) throws Exception {
		UserDao<Map<String, Object>> dao = new UserDaoImpl(control.connection);
		List<Map<String, Object>> maps = dao.findAll();
		control.request.setAttribute("maps", maps);
		control.page = PageConstants.LIST_PAGE;
	}
}
