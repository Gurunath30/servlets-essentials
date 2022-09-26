package com.threshold.controller;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.threshold.base.AccessControl;
import com.threshold.base.BaseServlet;
import com.threshold.dao.UserDao;
import com.threshold.daoimpl.UserDaoImpl;
import com.threshold.util.PageConstants;

@WebServlet("/logincontroller")
@SuppressWarnings("serial")
public class LoginController extends BaseServlet {

	@Override
	protected void doAction(AccessControl control) throws Exception {
		UserDao<Map<String, Object>> daoImpl = new UserDaoImpl(control.connection);
		Map<String, Object> user = daoImpl.findUserByNameAndPwd(control.param("user_name"), control.param("password"));
		if (user != null) {
			control.setSession(user);
			control.page = PageConstants.WELCOME_PAGE;
		} else {
			control.message("Try again wrong user or password");
			control.page = PageConstants.LOGIN_PAGE;
		}
	}

}
