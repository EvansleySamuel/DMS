package com.intellect.lendertaskwithjdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HeaderServlet
 */
@WebServlet("/header")
public class HeaderServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head>");
		out.println("<link rel='stylesheet' href='header.css'><header>");
		out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>");
		out.println("</head><body>");
		out.println("<div class='header'>");
		out.println("<img src='loan.jpg' alt='loadpic' class='image'>");
		out.println("<h1 align='center' class ='heading'>DEBTOR MANAGEMENT SYSTEM</h1>");
		out.println("<div align='right'><a  href='appclose' class='out'><i class='fa fa-sign-out' aria-hidden='true'></i>logout</a></div>");
		out.println("</header></div></body></html>");	
	}

}
