package com.threshold.util;

import java.util.Arrays;

import com.threshold.base.AccessControl;

public class SessionValidator {
	private static final String[] NO_SESSION_URLS = { "/logoutcontroller", "/logincontroller", "/index" };

	public static void validate(AccessControl control) {
		String requestUri = control.request.getServletPath();
		if (control.sessionUser == null && !Arrays.asList(NO_SESSION_URLS).contains(requestUri)) {
			control.callDoAction = false;
			control.redirectUrl = "/index.html";
		}
		if (control.sessionUser != null && "/index.html".equals(requestUri)) {
			control.callDoAction = false;
			control.page = PageConstants.WELCOME_PAGE;
		}
	}
}
