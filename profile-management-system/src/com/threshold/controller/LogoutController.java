package com.threshold.controller;

import javax.servlet.annotation.WebServlet;

import com.threshold.base.AccessControl;
import com.threshold.base.BaseServlet;
@WebServlet("/logoutcontroller")
@SuppressWarnings("serial")
public class LogoutController extends BaseServlet{
	@Override
	protected void doAction(AccessControl control) throws Exception {
		control.request.getSession().removeAttribute("sessionUser");
		control.request.getSession().invalidate();
		control.redirectUrl = control.request.getContextPath();
		//control.page = PageConstants.INDEX_PAGE;
	}
}
