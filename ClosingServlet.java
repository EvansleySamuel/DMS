package com.intellect.lendertaskwithjdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ClosingServlet
 */
@WebServlet("/closing")
public class ClosingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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

			float amount = Float.parseFloat(request.getParameter("camount"));

			//HttpSession session = request.getSession();
			CustomerLoanDetails customer = (CustomerLoanDetails) session.getAttribute("customer");

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
    			Date currentDate = new Date();  
 			String date = formatter.format(currentDate);
		
			//out.println(customer.getTotalAmount()+"....");
			String msg = " ";

			if(amount <= customer.getTotalAmount())
			{
			
				float balance = customer.getTotalAmount() - amount;
				CustomerLoanDetails customerupdate = new CustomerLoanDetails();
			
				//out.println(balance);

				customerupdate.setName(customer.getName());
				customerupdate.setDate(date);
				customerupdate.setPrincipal(balance);
				customerupdate.setRate(customer.getRate());

				PaymentHistory history = new PaymentHistory();

				history.setName(customer.getName());
				history.setDate(date);
				history.setAmount(amount);
				history.setTotalAmount(balance);

				ControllerClass controller = new ControllerClass();
				boolean status = controller.close(customerupdate,history);

		
				

				msg = "Record successfully upadated";
			}
			else
			{
				
				msg = "your Entered amount is  more than DueAmout";
				
			}
			session.setAttribute("msg",msg);
			response.sendRedirect("home");
		}
	}

}
