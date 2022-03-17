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
 * Servlet implementation class NewCustomer
 */
@WebServlet("/new")
public class NewCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		
		String username = (String) session.getAttribute("username");
		
		if( username == null)
		{
			response.sendRedirect("customerlogin.html");

		}
		else
		{

			RequestDispatcher requestDispatcherheader = request.getRequestDispatcher("header");
			requestDispatcherheader.include(request , response);
	
			RequestDispatcher requestDispatchermenu = request.getRequestDispatcher("menu");
			requestDispatchermenu.include(request , response);

			session.setAttribute("status",null);
			session.setAttribute("msg",null);
		
			out.println("<html><head>");
	
			out.println("<link rel='stylesheet' href='newcustomer.css'></head><body>");


			out.println("<h2 align ='center'>New Customer Details</h2>");
			out.println("<div class='row'><div  class = 'column'><table>");
			out.println("<form method ='post' action = 'newservlet'>");
			out.println("<tr><th>Name</th>");
			out.println("<td><input type='text' class='cname' name='cname' placeholder='Enter your Name' pattern='[a-zA-Z]{3,15}' required/><td></tr>");
			out.println("<tr><th>Age</th>");
			out.println("<td><input type='text' class='cage' name='cage' placeholder='Enter your Age' pattern='[0-9]{2}' required/><td></tr>");
			out.println("<tr><th>Gender</th>");
			out.println("<td><input type='radio' value='Male' name='cgender' required/>Male<input type='radio' value='Female' name='cgender' required/>Female<td></tr>");
			out.println("<tr><th>Mobile no:</th>");
			out.println("<td><input type = 'text'  class='cmobile'  name='cmobile' placeholder = 'Enter your moblie no' pattern='[0-9]{10}' required /></td></tr>");
			out.println("<tr><th>Aadhar no:</th>");
			out.println("<td><input type = 'text' class='caadhar' name='caadhar' placeholder = 'Enter your aadhar no' pattern='[0-9]{12}' required /></td></tr>");

			out.println("<tr colspan='2'><td><input type='submit' class='sub' value='submit'/></td></tr>");
			
			out.println("</table></div>");	

			out.println("<div align='right' class='column'><table >");

			out.println("<tr><th>Principal:</th>");
			out.println("<td><input type = 'text'  class='cprincipal'  name='cprincipal' placeholder = 'Enter your principal' pattern='[0-9.]{1,9}' required /></td></tr>");
			out.println("<tr><th>RateofInterest:</th>");
			out.println("<td><input type = 'text' class='crate' name='crate' placeholder = 'Enter your rate' pattern='[0-9.]{1,4}' required /></td></tr>");
			out.println("<tr><th>Dispense Date:</th>");
			out.println("<td><input type = 'date' class = 'cdate' name='cdate' required /></td></tr>");
			out.println("<tr><th>Repay Date:</th>");
			out.println("<td><input type = 'date' class = 'cdate' name='cenddate' /></td></tr>");
			
			out.println("</table></table></div></div></form></body></html>");

			
			
	
		}
	}

}
