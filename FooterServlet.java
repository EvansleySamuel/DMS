package com.intellect.lendertaskwithjdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FooterServlet
 */
@WebServlet("/footer")
public class FooterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head>");
		out.println("<link rel='stylesheet' href='footer.css'>");
		out.println("</head><body><div class = 'footer'>");
		out.println("<h3 align = 'right' class='mobile'>MOblie :9876543210<h3>");
		out.println("<h3 class='copyright'>copyright@intellect<h3>");
		out.println("<h3 align = 'right' class='email'>email Id:intellectinfo@gmail.com<h3>");
		out.println("</div></body></html>");
	}

}
