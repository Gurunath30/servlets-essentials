package com.threshold.controller;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.threshold.base.AccessControl;
import com.threshold.base.BaseServlet;
import com.threshold.dao.UserDao;
import com.threshold.daoimpl.UserDaoImpl;
import com.threshold.util.PageConstants;
@WebServlet("/deletecontroller")
@SuppressWarnings("serial")
public class DeleteController extends BaseServlet{
	@Override
	protected void doAction(AccessControl control) throws Exception {
		UserDao<Map<String, Object>> daoImpl = new UserDaoImpl(control.connection);
		if (daoImpl.delete((int)control.sessionUser.get("pk_id"))) {
			control.message("Account successfully deleted...");
			control.page = PageConstants.LOGIN_PAGE;
		}
	    else {
			control.message("Issues while deleting...try again");
			control.page = PageConstants.WELCOME_PAGE;
		}
	}
}

