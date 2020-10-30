package com.abc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.Model;

@WebServlet("/CheckBalance")
public class CheckBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String accno = (String) session.getAttribute("accno");
		
		try {
			Model m = new Model();
			m.setAccno(accno);
			
			boolean status = m.checkBalance();
			
			if(status==true) {
				session.setAttribute("balance", m.getBalance());
				response.sendRedirect("BalanceSuccessfull.jsp");
			}
			else {
				response.sendRedirect("BalanceFail.html");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
