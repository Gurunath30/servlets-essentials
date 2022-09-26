package com.threshold.base;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threshold.dao.util.DbUtil;
import com.threshold.util.RequestValidator;

@SuppressWarnings("serial")
public abstract class BaseServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		Map<String, Object> reqData = new LinkedHashMap<String, Object>();
		Map<String, Object> respData = new LinkedHashMap<String, Object>();
		for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			reqData.put(entry.getKey(), entry.getValue() != null ? (entry.getValue().length == 1 ? entry.getValue()[0] : entry.getValue()):null);
		}
		AccessControl control = new AccessControl(request, response, reqData, respData);
		System.out.println(reqData+" "+request.getParameter("photo"));
		RequestValidator.validateGetRequest(control);
		if (control.callDoAction) {
			try {
				control.connection = DbUtil.getConnection(true);
			} catch (SQLException  | ClassNotFoundException c) {
				c.printStackTrace();
				control.connection = null;
			}
			if (control.connection == null) control.callDoAction = false;
		}
		if (control.callDoAction) {
			try {
				doAction(control);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					DbUtil.closeConnection(control.connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (control.redirectUrl != null && !control.redirectUrl.trim().equals("")) response.sendRedirect(control.redirectUrl);
		else request.getRequestDispatcher(control.page).forward(request, response);
	}
	
	protected abstract void doAction(AccessControl control) throws Exception;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}
	
}
