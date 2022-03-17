package com.intellect.lendertaskwithjdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CloseServlet
 */
@WebServlet("/close")
public class CloseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
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
			
			
			String cname = request.getParameter("cname");
			if(cname == null)
			{
		
				cname = request.getParameter("id");
			}

			ControllerClass controller = new ControllerClass();
			ArrayList<CustomerLoanDetails> customerList = controller.viewrecords();
			int recordcount = controller.paymentrecords(cname);
			float dueAmount = 0.0f;
			String name = "name";
			if(cname != null)
			{

				CustomerLoanDetails customer = controller.nameSearch(cname);
				float totalAmount = controller.calculation(customer);
				customer.setTotalAmount(totalAmount);
				session.setAttribute("customer",customer);
				name=customer.getName();
				dueAmount = customer.getTotalAmount();	 

			}

			int page = 1;
        		int recordsPerPage = 5;
        		if(request.getParameter("page") != null)
           			 page = Integer.parseInt(request.getParameter("page"));

			ArrayList<PaymentHistory> historyList = controller.paymentHistory(cname,(page-1)*recordsPerPage,recordsPerPage);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
    			Date currentDate = new Date();  
 			String date = formatter.format(currentDate);

			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='settle.css'></head>");
			out.println("<body>");
			out.println("<h2 align = 'center'>payment</h2>");
			out.println("<table class = 'customer'><form method='get' action='close'>");
			out.println("<tr><th><h3 >Customer Name:</h3><th>");
			out.println("<td><select name='cname'>");
			for(CustomerLoanDetails customerNames:customerList)
			{
				if(customerNames.getTotalAmount() != 0)
				{
					if(customerNames.getName().equalsIgnoreCase(cname))
					{
						out.println("<option selected>"+customerNames.getName()+"</option>");
					}
					else
					{
						out.println("<option>"+customerNames.getName()+"</option>");
					}
				}
			}
			out.println("</select></td></tr>");
			out.println("<tr><td><input type='submit' value='submit'</td></tr></table>");
			out.println("</form>");

			

			out.println("<div class='row'><div class='column'>");
			out.println("<table class='details'><form method='post' action='closing'>");
      			out.println("<tr><th>customer Name</th>");
        		out.println("<td>"+name+"</td></tr>");
			out.println("<tr><th>Due Amount</th>");
        		out.println("<td>"+dueAmount+"</td></tr>");
        		out.println("<tr><th>Payment</th>");
			out.println("<td><input type='text' name='camount' class='camount' placeholder = 'Enter Payment' pattern='[0-9.]{1,7}' required/></td></tr>");
			out.println("<tr><th>Repay Date</th><td>"+date+"</td></tr>");
      			out.println("<tr><td><input type ='submit' value='submit'/></td> </tr>");
			out.println("</form></table></div>");
			out.println("<div class='column'><table class='payment'>");
			out.println("<tr><th>Date</th><th>Amount</th><th>Due Amount</th></tr>");
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
				out.println("<a href ='close?page="+i+"&id="+name+"'>"+i+"</a>");
			}
			out.println("</div>");
			

			

			out.println("</body></html>");

			RequestDispatcher requestDispatcherfooter = request.getRequestDispatcher("footer");
			requestDispatcherfooter.include(request , response);
		}
	}

}
