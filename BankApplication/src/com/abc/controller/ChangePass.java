package com.abc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.Model;

@WebServlet("/ChangePass")
public class ChangePass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String accno = (String) session.getAttribute("accno");
		
		String password = request.getParameter("password");
		
		try {
			Model m = new Model();
			m.setAccno(accno);
			m.setPassword(password);
			
			boolean status = m.change();
			
			if(status==true) {
				response.sendRedirect("PassChangeSuccessfully.html");
			}
			else {
				response.sendRedirect("PassChangeFail.html");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
