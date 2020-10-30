package com.abc.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


public class RegisterFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		System.out.println("Filter executed");
		if(email.endsWith("@gmail.com") && phone.length()==10 && password.length()>7) {
			request.getServletContext().getRequestDispatcher("/Register").forward(request, response);
		}
		else {
			((HttpServletResponse) response).sendRedirect("FailRegister.html");
		}
	}

}
