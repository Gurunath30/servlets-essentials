package com.threshold.base;

import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessControl {
	public HttpServletRequest request;
	public HttpServletResponse response;
	public Connection connection;
	public String page;
	public Map<String, Object> reqData;
	public Map<String, Object> respData;
	public boolean callDoAction = true;
	public Map<String, Object> sessionUser;
	public String redirectUrl;
	public String msg = null;

	@SuppressWarnings("unchecked")
	public AccessControl(HttpServletRequest request, HttpServletResponse response, Map<String, Object> reqData,
			Map<String, Object> respData) {
		super();
		this.request = request;
		this.response = response;
		this.reqData = reqData;
		this.respData = respData;
		this.sessionUser = (Map<String, Object>) request.getSession().getAttribute("sessionUser");
		this.msg = (String) request.getAttribute("msg");
		if (msg != null) msg = null;
	}

	public void setSession(Map<String, Object> user) {
		request.getSession().setAttribute("sessionUser", user);
	}

	public void message(String msg) {
		request.setAttribute("msg", msg);
	}

	public String param(String key) {
		return (String) reqData.get(key);
	}

	public String[] params(String key) {
		return (String[]) reqData.get(key);
	}
	
	public int sessionId(String key) {
		return (int) sessionUser.get(key);
	}

}
