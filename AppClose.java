package com.intellect.lendertaskwithjdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AppClose
 */
@WebServlet("/appclose")
public class AppClose extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();  
		request.getRequestDispatcher("customerlogin.html").include(request, response);  
              
		HttpSession session = request.getSession();
		session.removeAttribute("username"); 
	 
		session.invalidate();  
              
		out.print("<h3 align = 'center' >You are successfully logged out!</h3>");  
              
		out.close();  
	}

}
