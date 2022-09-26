package com.threshold.controller;

import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;

import com.threshold.base.AccessControl;
import com.threshold.base.BaseServlet;
import com.threshold.dao.UserDao;
import com.threshold.daoimpl.UserDaoImpl;
import com.threshold.util.PageConstants;
import com.threshold.util.Utility;
@WebServlet("/updatecontroller")
@MultipartConfig(maxFileSize = 1610241024)
@SuppressWarnings("serial")
public class UpdateController extends BaseServlet {
	@Override
	protected void doAction(AccessControl control) throws Exception {
		UserDao<Map<String, Object>> dao = new UserDaoImpl(control.connection);
		Part part = control.request.getPart("photo");
		control.reqData.put("photo", Utility.fileToPath(part));
		int key = dao.update(control.reqData, control.sessionId("pk_id"));
		if (key > 0) {
			control.message("Successfully updated...login again");
			control.page = PageConstants.LOGIN_PAGE;
		} else {
			control.message("Issues...while updating");
			control.page = PageConstants.WELCOME_PAGE;
		}
	}

}
