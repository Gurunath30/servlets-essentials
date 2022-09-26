package com.threshold.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.threshold.util.PageConstants;
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		    HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse resp = (HttpServletResponse) response;
	        String requestUri = req.getServletPath();
	        if (req.getSession().getAttribute("sessionUser") != null && "/logincontroller".equalsIgnoreCase(requestUri)) {
				req.getRequestDispatcher(PageConstants.WELCOME_PAGE).forward(req, resp);
			} else {
				resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
				//resp.setDateHeader("Expires",11);
				chain.doFilter(request, response);
			}
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
