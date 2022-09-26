package com.threshold.practice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import com.threshold.util.DbUtil;

@SuppressWarnings("serial")
@WebServlet("/OTPCode")
public class OTPCode extends GenericServlet {

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		String phone = arg0.getParameter("nm");
		Connection conn = null;
		List<Map<String, Object>> rec = null;
		try {
			conn = DbUtil.getConnection("localhost", 3306, "threshold", "admin", "12345");
			rec = DbUtil.getMapList(conn, "SELECT * FROM `employee` WHERE `phone_number`=?", phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter writer = arg1.getWriter();
		if (rec != null) {
			int OTP = new Random().nextInt(10000);
			if (OTP < 1000) OTP = OTP + 1000;
			writer.println(OTP);
		} else {
			writer.println("Invalid phone num");
		}
	}
}
