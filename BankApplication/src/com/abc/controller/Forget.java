package com.abc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.Model;
import com.abc.sms.OtpGenerate;
import com.abc.sms.SendSms;

@WebServlet("/Forget")
public class Forget extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String accno = req.getParameter("accno");
    	String email = req.getParameter("email");
    	HttpSession session = req.getSession();
    	try {
    		Model m = new Model();
    		m.setAccno(accno);
    		m.setEmail(email);
    		
    		boolean status = m.forget();
    		
    		if(status==true) {
    			session.setAttribute("accno", m.getAccno());
    			session.setAttribute("phone", m.getPhone());
    			int otp = OtpGenerate.otpGenerate();
    			session.setAttribute("otp", otp);
    			String message = "Your password reset otp is "+otp;
    			String number = m.getPhone();
    			
    			SendSms.sendSms(message, number);
    			
    			res.sendRedirect("Changepassotp.jsp");
    		}
    		else {
    			res.sendRedirect("PasswordFail.html");
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
	
}
