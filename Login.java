package com.intellect.lendertaskwithjdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("user");
		String pwd = request.getParameter("pwd");

		
		

		
		if(username != null)
		{
			HttpSession session= request.getSession();
			session.setAttribute("username",username);

		
			ControllerClass controller = new ControllerClass();

			boolean status = controller.login(username,pwd);
			if(status)
			{
				response.sendRedirect("home");
			
			}
			else
			{
				RequestDispatcher requestDispatcherheader = request.getRequestDispatcher("customerlogin.html");
				requestDispatcherheader.include(request , response);
				out.println("<h3 align = 'center'>Entered wrong username/password</h3>");
			}
		
		}
		else
		{
			RequestDispatcher requestDispatcherheader = request.getRequestDispatcher("customerlogin.html");
			requestDispatcherheader.include(request , response);
			out.println("<h3 align = 'center'>Entered wrong username/password</h3>");
		}
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{

		doGet(request,response);
		

	}

}
