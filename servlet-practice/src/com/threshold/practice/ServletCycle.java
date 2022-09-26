package com.threshold.practice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/servlet")
@SuppressWarnings("serial")
public class ServletCycle extends HttpServlet {
	private String output;
	public void init() throws ServletException {
		output= "from intit";
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html"); 
        PrintWriter out = resp.getWriter(); 
        out.println(output); 
	}

	public void destroy() {
		System.out.println("destroy"); 
	}
}
