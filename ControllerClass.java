package com.intellect.lendertaskwithjdbc;
import java.util.*;

public class ControllerClass {
	
	JDBC jdbc = null;	
	public boolean nameValidation(String name)
	{
		
		jdbc = new JDBC();
		ArrayList<CustomerLoanDetails> customerList = jdbc.viewrecords();
		
		System.out.println(customerList);
		
		boolean checkName = false;
		for(CustomerLoanDetails customer: customerList)
		{
			if(customer.getName().equalsIgnoreCase(name))
			{
				checkName = true;
				break;
			}
			
		}
		return checkName;
	}
	
	
	public boolean dateValidation(String dateCheck)
	{
		Calendar startDate = Calendar.getInstance();
				
		String dateArray[] = dateCheck.split("-");
		byte day = Byte.parseByte(dateArray[2]);
		byte month = Byte.parseByte(dateArray[1]);
		short year = Short.parseShort(dateArray[0]);
		
		Calendar currentDate = Calendar.getInstance();
			
		int currentDay = currentDate.get(Calendar.DATE);
		int currentMonth =  currentDate.get(Calendar.MONTH) + 1;
		int  currentYear =  currentDate.get(Calendar.YEAR);
			

		if( year == currentYear && month == currentMonth)
		{
				if(day <= currentDay )
					return true;
				else
					return false;
			
		}
		else if( year == currentYear)
		{
			if(  month > 0 && month <= currentMonth)
			{
				startDate.set(year ,month - 1,1);
	
				int numberOfDays = startDate.getActualMaximum(Calendar.DAY_OF_MONTH);
	
				if( day > 0 && day <= numberOfDays )
				{
					return true;
				}
				else 
				{
					return false;
				}
			}
		}	
		else if(year > 1970 && year < currentYear )
		{
				if( month > 0 && month <= 12)
				{
					startDate.set(year ,month - 1,1);
					int numberOfDays = startDate.getActualMaximum(Calendar.DAY_OF_MONTH);
					if( day > 0 && day <= numberOfDays )
						return true;
					else 
						return false;
				}
				else 
				{
					return false;
				}
		}
		else
		{	
			
			return false;
		}
			return false;
	}

	public boolean newCustomer(CustomerPersonalDetails customerPersonal,CustomerLoanDetails customerLoan)
	{
		jdbc = new JDBC();
		boolean status = jdbc.newCustomer(customerPersonal,customerLoan);
		return status;
	}


	public float calculation(CustomerLoanDetails customer)
	{
			String date = customer.getDate();
			float principal = customer.getPrincipal();
			float rate = customer.getRate();
			String dateArray[] = date.split("-");
			byte day = Byte.parseByte(dateArray[2]);
			byte month = Byte.parseByte(dateArray[1]);
			short year = Short.parseShort(dateArray[0]);

			Calendar startDate = Calendar.getInstance();

			startDate.set(year ,month - 1,day);
			
			Calendar currentDate = Calendar.getInstance();

			long miliSecondForStartDate = startDate.getTimeInMillis();
			long miliSecondForCurrentDate = currentDate.getTimeInMillis();
			
			float diffInMilis = ((miliSecondForCurrentDate - miliSecondForStartDate) / 1000 / 60 / 60 / 24f);
			
			float interest = ((principal * rate * 1) / 100)/30;
			double amount = principal + (interest * diffInMilis);
			 double roundOffAmount = Math.round(amount * 100.0) / 100.0;
               		float totalAmount = (float)roundOffAmount;

			return totalAmount;
	}

	public boolean close(CustomerLoanDetails customerupdate,PaymentHistory history)
	{
		jdbc = new JDBC();
		boolean status = jdbc.close(customerupdate,history);

		return status;
	
	}

	

	public  ArrayList<CustomerLoanDetails> viewCustomer(int startingPage,int endingPage)                        
	{
		jdbc = new JDBC();
		ArrayList<CustomerLoanDetails> customerList = jdbc.viewAll(startingPage,endingPage);
		return customerList;
	}


	public  ArrayList<CustomerLoanDetails> viewrecords()                        
	{
		jdbc = new JDBC();
		ArrayList<CustomerLoanDetails> customerList = jdbc.viewrecords();
		return customerList;
	}
		
	public int countrecord()
	{
		jdbc = new JDBC();
		int count = jdbc.countrecord();
		return count;
	}

	public CustomerLoanDetails nameSearch(String name)
	{
		jdbc = new JDBC();
		CustomerLoanDetails customer = jdbc.nameSearch(name);

		return customer;
	}

	public CustomerLoanDetails viewName(String name)
	{
		jdbc = new JDBC();
		CustomerLoanDetails customer = jdbc.viewName(name);

		return customer;
	}	

	public ArrayList<PaymentHistory> paymentHistory(String name,int startPage,int endPage)
	{

		jdbc = new JDBC();
		ArrayList<PaymentHistory> historyList = jdbc.paymentHistory(name,startPage,endPage);

		return historyList;
	}

	public CustomerPersonalDetails view(String name)
	{	

		jdbc = new JDBC();
		CustomerPersonalDetails customer = jdbc.view(name);
		return customer;
	}

	public int paymentrecords(String name)
	{

		jdbc = new JDBC();

		int noOfRecords = jdbc.paymentrecords(name);
		
		return noOfRecords;
	
	}

	public boolean login(String name,String pwd)
	{
		jdbc = new JDBC();

		boolean status = jdbc.login(name,pwd);

		return status;
		 
	}


}
