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
 * Servlet implementation class ViewServlet
 */
@WebServlet("/view")
public class ViewServlet extends HttpServlet {
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

			String name = request.getParameter("id");  

			session.setAttribute("status",null);
			session.setAttribute("msg",null);

		

			ControllerClass controller = new ControllerClass();

			int recordcount = controller.paymentrecords(name);

			int page = 1;
        		int recordsPerPage = 5;
        		if(request.getParameter("page") != null)
           	 	page = Integer.parseInt(request.getParameter("page"));

			ArrayList<PaymentHistory> historyList = controller.paymentHistory(name,(page-1)*recordsPerPage,recordsPerPage);

			CustomerLoanDetails customer = controller.viewName(name);
		
			CustomerPersonalDetails customerDetails = controller.view(name);
		  
		
			out.println("<html><head><link rel='stylesheet' href='viewcustomer.css'></head><body>");
			out.println("<div class='row'><div class='column'><table>");
			out.println("<tr><th colspan ='2'><h2>Customer Details</h2></th></tr>");
    			out.println("<tr><th>Name</th><td>"+customerDetails.getName()+"</td></tr");
			out.println("<tr><th>Age</th><td>"+customerDetails.getAge()+"</td></tr>");
			out.println("<tr><th>Gender</th><td>"+customerDetails.getGender()+"</td></tr>");
			out.println("<tr><th>Mobile</th><td>"+customerDetails.getMobile()+"</td></tr>");
			out.println("<tr><th>Aadhar</th><td>"+customerDetails.getAadhar()+"</td></tr>");
			out.println("<tr><th>Principal</th><td>"+customer.getPrincipal()+"</td></tr>");
			out.println("<tr><th>RateOfInterest</th><td>"+customer.getRate()+"</td></tr>");
			out.println("<tr><th>Date</th><td>"+customer.getDate()+"</td></tr>");
			out.println("</table></div>");
		
			out.println("<div class='column'><table>");
			out.println("<tr><th colspan ='2'><h2>Customer payment History</h2></th></tr>");
			out.println("<tr><th>Date</th><th>paytement</th><th>DueAmount</th></tr>");
			if(!(historyList.isEmpty()))
			{
				for(PaymentHistory history: historyList)
				{
					out.println("<tr>");
					out.println("<td>"+history.getDate()+"</td>");
					out.println("<td>"+history.getAmount()+"</td>");
					out.println("<td>"+history.getTotalAmount()+"</td>");
					out.println("</td>");
				
				}
			}
			out.println("</table></div></div>");
			float pagecount =(float) recordcount/5;
			int pageno = (int) Math.ceil(pagecount);
			out.println("<div class='pagination'>");
			for(byte i=1;i<=pageno;i++)
			{
				out.println("<a href ='view?page="+i+"&id="+name+"'>"+i+"</a>");
			}
			out.println("</div>");

			out.println("</body></html>");

			RequestDispatcher requestDispatcherfooter = request.getRequestDispatcher("footer");
			requestDispatcherfooter.include(request , response);	
		}
	}

}
