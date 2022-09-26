package com.threshold.controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;

import com.threshold.base.AccessControl;
import com.threshold.base.BaseServlet;
import com.threshold.daoimpl.UserDaoImpl;
import com.threshold.util.PageConstants;
import com.threshold.util.Utility;

@WebServlet("/registercontroller")
@MultipartConfig(maxFileSize = 1610241024)
@SuppressWarnings("serial")
public class RegisterController extends BaseServlet {
	@Override
	protected void doAction(AccessControl control) throws Exception {
		Part part = control.request.getPart("photo");
		control.reqData.put("photo", Utility.fileToPath(part));
		int key = 0;
		try {
			key = new UserDaoImpl(control.connection).add(control.reqData);
			if (key > 0) {
				control.message("Successfully registered...please login here");
				control.page = PageConstants.LOGIN_PAGE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			control.message("username exists...try again with different one");
			control.page = PageConstants.REGISTRATION_PAGE;
		}
	}
}
