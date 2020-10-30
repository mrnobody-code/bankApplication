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

@WebServlet ("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  @Override
protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	String fname = req.getParameter("fname");
	String lname = req.getParameter("lname");
	String accno = req.getParameter("accno");
	String email = req.getParameter("email");
	String phone = req.getParameter("phone");
	
	String sbalance = req.getParameter("balance");
	int balance = Integer.parseInt(sbalance);
	
	String password = req.getParameter("password");
	HttpSession session = req.getSession();
	try {
		Model m = new Model();
		m.setFname(fname);
		m.setLname(lname);
		m.setAccno(accno);
		m.setEmail(email);
		m.setPhone(phone);
		m.setBalance(balance);
		m.setPassword(password);
		
		boolean status=m.register();
		
		if(status==true) {
			session.setAttribute("email", m.getEmail());
			System.out.println(m.getEmail());
			String message="Hello,"+fname+" your account registered successfully. Your user id is "+m.getEmail();
			String number = phone;
			SendSms.sendSms(message, number);
			res.sendRedirect("SuccessRegister.jsp");
		}
		else {
			res.sendRedirect("FailRegister.html");
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}

}
