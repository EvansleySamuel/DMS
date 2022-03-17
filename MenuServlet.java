package com.intellect.lendertaskwithjdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("").append(request.getContextPath());
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><link rel='stylesheet' href='menu.css'></head>");
		out.println("<body><div class = 'menu'>");
		out.println("<a class='home' href='home'>HOME |</a>");
		out.println("<a class='view' href='home'>VIEW ALL|</a>");
		out.println("<a class='close' href='close'>CLOSE |</a>");
		out.println("<a class='new' href='new'>NEW |</a>");
		out.println("<a class ='search' href='#'>SEARCH</a>");
		out.println("</div><body></html>");
	}

}
