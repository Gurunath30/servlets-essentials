package com.threshold.util;

import java.util.Arrays;

import com.threshold.base.AccessControl;

public class RequestValidator {
	private final static String[] URLS = { "/registercontroller", "/updatecontroller","/logincontroller"};
	public static void validateGetRequest(AccessControl control) {
		String reqUri = control.request.getServletPath();
		if (control.request.getMethod().contains("GET") && Arrays.asList(URLS).contains(reqUri)) {
			control.callDoAction = false;
			if ("/registercontroller".equalsIgnoreCase(reqUri)) control.page = PageConstants.REGISTRATION_PAGE;
			else if ("/logincontroller".equalsIgnoreCase(reqUri)) control.page = PageConstants.LOGIN_PAGE;
			else control.page = PageConstants.UPDATE_PAGE;
		}
	}
}
