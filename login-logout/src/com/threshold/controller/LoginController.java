package com.threshold.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threshold.pojo.User;
import com.threshold.util.Service;

@SuppressWarnings("serial")
public class LoginController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setName(request.getParameter("user"));
		user.setPassword(request.getParameter("pwd"));
		if (Service.validateUser(user)) {
			request.getSession().setAttribute("username", user.getName());
			response.sendRedirect("dashboard");
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
		    out.println("alert('User Name or password incorrect');");
		    out.println("</script>");
			response.setHeader("Refresh", "1;url=./login");
		}
	}

}
