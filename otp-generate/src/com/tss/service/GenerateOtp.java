package com.tss.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/GenerateOtp")
@SuppressWarnings("serial")
public class GenerateOtp extends GenericServlet {

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		String phone = arg0.getParameter("phone");
		PrintWriter writer = arg1.getWriter();
		if (Utility.phoneValidation(phone)) {
			int otp = new Random().nextInt(1000);
			if (otp < 1000) {
				otp = otp + 1000;
			}
			writer.println("OTP is " + otp);
		} else {
			writer.println("Please enter valid number");
		}
	}

}
