package com.abc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.Model;
@WebServlet("/Login")

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String email = req.getParameter("email");
    	String password = req.getParameter("password");
    	HttpSession session = req.getSession(true);
		
    	try {
    		Model m = new Model();
    		m.setEmail(email);
    		m.setPassword(password);
    		boolean status = m.login();
    		
    		if(status==true) {
    			session.setAttribute("accno", m.getAccno());
    			res.sendRedirect("MainApp.html");
    		}
    		else {
    			res.sendRedirect("LoginFail.html");
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
