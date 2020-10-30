package com.abc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/OtpValidation")
public class OtpValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int otp = (int) session.getAttribute("otp");
		String sotp = request.getParameter("otp");
		int cotp = Integer.parseInt(sotp);
		
		if(otp==cotp) {
			response.sendRedirect("ForgetChangepassword.html");
		}
		else {
			response.sendRedirect("PasswordFail.html");
		}
	}

}
