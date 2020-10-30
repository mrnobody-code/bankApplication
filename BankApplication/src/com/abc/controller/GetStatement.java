package com.abc.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.Model;

@WebServlet("/GetStatement")
public class GetStatement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String accno = (String) session.getAttribute("accno");
		
		try {
			Model m = new Model();
			m.setAccno(accno);
			
			ArrayList<Integer> al = m.getStatement();
			
			if(al.isEmpty()==true) {
				response.sendRedirect("ErrorStatement.html");
			}
			else {
				session.setAttribute("al", m.getAl());
				session.setAttribute("sal", m.getSal());
				session.setAttribute("ral", m.getRal());
				response.sendRedirect("SuccessStatement.jsp");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
