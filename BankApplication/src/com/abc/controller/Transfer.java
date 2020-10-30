package com.abc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.Model;
import com.abc.sms.SendSms;


@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String raccno = request.getParameter("raccno");
		String samount = request.getParameter("amount");
		int amount = Integer.parseInt(samount);
		
		HttpSession session = request.getSession();
		String accno = (String) session.getAttribute("accno");
		
		try {
			Model m = new Model();
			m.setAccno(accno);
			m.setRaccno(raccno);
			m.setAmount(amount);
			
			boolean status = m.transfer();
			
			if(status==true) {
				String message = "Amount "+amount+" credit in your account";
				String number = m.getPhone();
				SendSms.sendSms(message, number);
				response.sendRedirect("SuccessTransfer.html");
			}
			else {
				response.sendRedirect("ErrorTransfer.html");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
