package com.abc.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


public class ChangePassFilter implements Filter {

  
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		
		if(password.equals(cpassword)) {
			request.getServletContext().getRequestDispatcher("/ChangePass").forward(request, response);
		}
		else {
			((HttpServletResponse) response).sendRedirect("PassChangeFail.html");
		}
	}

}
