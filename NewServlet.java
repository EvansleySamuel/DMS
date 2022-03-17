package com.intellect.lendertaskwithjdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/newservlet")
public class NewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
		
			String name = request.getParameter("cname");
			String age = request.getParameter("cage");
			String mobile = request.getParameter("cmobile");
			String aadhar = request.getParameter("caadhar");
			String gender = request.getParameter("cgender");

			Float principal = Float.parseFloat(request.getParameter("cprincipal"));
			Float rate = Float.parseFloat(request.getParameter("crate"));
			String date = request.getParameter("cdate");

			String enddate = request.getParameter("cenddate");

			ControllerClass controller = new ControllerClass();
		
			boolean checkName =controller.nameValidation(name);

			String status = " ";
			out.println("<htm><body>");

			if(checkName)
			{
				status ="This Customer already Existed";
			}
			else
			{

				boolean checkDate = controller.dateValidation(date);
				if(checkDate)
				{
					CustomerPersonalDetails customerPersonal = new CustomerPersonalDetails();
		
					customerPersonal.setName(name);
					customerPersonal.setAge(age);
					customerPersonal.setMobile(mobile);
					customerPersonal.setAadhar(aadhar);
					customerPersonal.setGender(gender);
		
					CustomerLoanDetails customerLoan = new CustomerLoanDetails();

					customerLoan.setName(name);
					customerLoan.setDate(date);
					customerLoan.setPrincipal(principal);
					customerLoan.setRate(rate);
					
		
					float totalAmount = controller.calculation(customerLoan);

					customerLoan.setTotalAmount(totalAmount);
		

					boolean check = controller.newCustomer(customerPersonal,customerLoan);

					if(check)
					{
					
						status = "Record successfuly Saved";
					}
					else
					{
					
						status = "Record Not saved";
					}
				}
				else
				{
					status = "Invalid Date";
				}

			}

			session.setAttribute("status",status);
			response.sendRedirect("home");
			out.println("</html></body>");
		}
	}

}
