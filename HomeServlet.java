package com.intellect.lendertaskwithjdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
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
			ControllerClass controller = new ControllerClass();

			

			int recordcount = controller.countrecord();

			int page = 1;
        		int recordsPerPage = 5;
        		if(request.getParameter("page") != null)
           	 	page = Integer.parseInt(request.getParameter("page"));

			ArrayList<CustomerLoanDetails> customerList = controller.viewCustomer((page-1)*recordsPerPage,recordsPerPage);

	
			out.println("<html>");
			out.println("<head><link rel='stylesheet' href='home.css'>");
			out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>");
			out.println("</head><body>");
			if(customerList.isEmpty())
			{
				out.println("<h2 class='customer' align ='center'>Customer Details</h2>");
				out.println("<a class='add' href='new'><i class='fa fa-plus-circle' aria-hidden='true'></i></a>");
				out.println("<table class ='details' align='center' border = '1'>");
				out.println("<tr><th>Name</th><th>Date</th><th>Principal</th><th>RateofInterest</th><th>TotalDueAmount</th><th colspan = '2'>Action</th></tr></table>");
			}
			else
			{
				out.println("<h2 class='customer' align ='center'>Customer Details</h2>");
				out.println(recordcount);
				out.println("<a class='add' href='new'><i class='fa fa-plus-circle' aria-hidden='true'></i></a>");
				out.println("<table class ='details' border = '1'>");
				out.println("<tr><th>Name</th><th>Date</th><th>Principal</th><th>RateofInterest</th><th>TotalDueAmount</th><th colspan = '2'>  Action  </th></tr>");

		
				for(CustomerLoanDetails customer: customerList)
				{	
					out.print("<tr><td>"+customer.getName()+"</td>");
					out.print("<td>"+customer.getDate()+"</td>");
					out.print("<td>"+customer.getPrincipal()+"</td>");
					out.print("<td>"+customer.getRate()+"</td>");
					out.print("<td>"+customer.getTotalAmount()+"</td>");
					if( customer.getTotalAmount() != 0)
					{
					out.print("<td><a href='view?id="+customer.getName()+"'><i class='fa fa-eye' aria-hidden='true'></i></a>&nbsp&nbsp <a href='close?id="+customer.getName()+"'><i class='fa fa-pencil-square-o' aria-hidden='true'></i></i></a></td></tr>");
					}
					else
					{
						out.print("<td><a href='view?id="+customer.getName()+"'><i class='fa fa-eye' aria-hidden='true'></i></a></td></tr>");
					}
				

				}
				out.println("</table>");
				float pagecount =(float) recordcount/5;
				int pageno = (int) Math.ceil(pagecount);
				out.println("<div class='pagination'>");
				for(byte i=1;i<=pageno;i++)
				{ 
					out.println("<a href ='home?page="+i+"'>"+i+"</a>");
				}
				out.println("</div>");

			
			}

			String status = (String) session.getAttribute("status");
			if(status != null)
				out.println("<h3 class ='status' align='center'>"+status+"</h3>");

			String msg = (String) session.getAttribute("msg");

			if(msg != null)
				out.println("<h3 class ='msg'>"+msg+"</h3>");
			
			out.println("</body>");
			out.println("</html>");
			
			

			RequestDispatcher requestDispatcherfooter = request.getRequestDispatcher("footer");
			requestDispatcherfooter.include(request , response);	
		}
		
	}
		
	

}
