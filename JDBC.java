package com.intellect.lendertaskwithjdbc;
import java.util.*;
import java.sql.*;


public class JDBC {
	
	Connection connection = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	public ArrayList<CustomerLoanDetails> viewAll(int startingPage,int endingPage)
	{
		try
		{
			Connection connection = ConnectionImpl.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM customerloan LIMIT "+startingPage +" , "+endingPage);

			
			ArrayList<CustomerLoanDetails> customerList = new ArrayList<CustomerLoanDetails>();
			

			while(resultSet.next())
			{
				
				CustomerLoanDetails customer = new CustomerLoanDetails();
				customer.setName(resultSet.getString("name"));
				customer.setDate(resultSet.getString("date"));
				customer.setPrincipal(resultSet.getFloat("principal"));
				customer.setRate(resultSet.getFloat("rate"));
				customer.setTotalAmount(resultSet.getFloat("totalAmount"));
				
				
				customerList.add(customer);
			}
			
			return customerList;
		}
		catch(Exception except)
		{
			except.printStackTrace();
			return null;
		}
	}


	public ArrayList<CustomerLoanDetails> viewrecords()
	{
		try
		{
			Connection connection = ConnectionImpl.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM customerloan");

			
			ArrayList<CustomerLoanDetails> customerList = new ArrayList<CustomerLoanDetails>();
			

			while(resultSet.next())
			{
				
				CustomerLoanDetails customer = new CustomerLoanDetails();
				customer.setName(resultSet.getString("name"));
				customer.setDate(resultSet.getString("date"));
				customer.setPrincipal(resultSet.getFloat("principal"));
				customer.setRate(resultSet.getFloat("rate"));
				customer.setTotalAmount(resultSet.getFloat("totalAmount"));
				
				
				customerList.add(customer);
			}
			
			return customerList;
		}
		catch(Exception except)
		{
			except.printStackTrace();
			return null;
		}
	
	}
		

	public boolean newCustomer(CustomerPersonalDetails customerPersonal,CustomerLoanDetails customerLoan)
	{
		
		try
		{
			
			Connection connection = ConnectionImpl.getConnection();
			PreparedStatement customerdetails = connection.prepareStatement("insert into customerdetails values(?,?,?,?,?)");
			customerdetails.setString(1,customerPersonal.getName());
			customerdetails.setString(2,customerPersonal.getAge());
			customerdetails.setString(3,customerPersonal.getGender());
			customerdetails.setString(4,customerPersonal.getMobile());
			customerdetails.setString(5,customerPersonal.getAadhar());
			
			int rowCount = customerdetails.executeUpdate();
		
			
			if(rowCount == 1)
			{
				PreparedStatement customerloan = connection.prepareStatement("insert into customerloan values(?,?,?,?,?)");
				customerloan.setString(1,customerLoan.getName());
				customerloan.setString(2,customerLoan.getDate());
				customerloan.setFloat(3,customerLoan.getPrincipal());
				customerloan.setFloat(4,customerLoan.getRate());
				customerloan.setFloat(5,customerLoan.getTotalAmount());
				customerloan.executeUpdate();

				PreparedStatement customerloanupdate = connection.prepareStatement("insert into customerloanupdate values(?,?,?,?)");
				
				customerloanupdate.setString(1,customerLoan.getName());
				customerloanupdate.setString(2,customerLoan.getDate());
				customerloanupdate.setFloat(3,customerLoan.getPrincipal());
				customerloanupdate.setFloat(4,customerLoan.getRate());
				customerloanupdate.executeUpdate();

				
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception except)
		{
			except.printStackTrace();
			return false;
		}
		
		
	}

	public boolean close(CustomerLoanDetails customerupdate,PaymentHistory history)
	{
		try
		{
			
				Connection connection = ConnectionImpl.getConnection();
			
				
				
				PreparedStatement customerloanupdate = connection.prepareStatement("update customerloanupdate set principal = ? , date = ? where name = ?");
				
			
				customerloanupdate.setFloat(1,customerupdate.getPrincipal());
				customerloanupdate.setString(2,customerupdate.getDate());
				customerloanupdate.setString(3,customerupdate.getName());
				
				int row =customerloanupdate.executeUpdate();
			
				if(row == 1)
				{

					PreparedStatement paymentHistory = connection.prepareStatement("insert into  paymenthistory values(?,?,?,?) ");
					paymentHistory.setString(1,history.getName());
					paymentHistory.setString(2,history.getDate());
					paymentHistory.setFloat(3,history.getAmount());
					paymentHistory.setFloat(4,history.getTotalAmount());
					paymentHistory.executeUpdate();



					PreparedStatement customerloan = connection.prepareStatement("update customerloan set totalAmount = ?  where name = ? ");
					customerloan.setFloat(1,customerupdate.getPrincipal());
					customerloan.setString(2,customerupdate.getName());
					customerloan.executeUpdate();
					return true;
				}
				else
					return false;
			
		}
		catch(Exception except)
		{
			except.printStackTrace();
			return false;
		}
			
	}

	public int countrecord()
	{
		int count = 1;

		try
		{
			Connection connection = ConnectionImpl.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select count(*) from  customerloan");
			
			while(resultSet.next())
			{
				 count= resultSet.getInt(1);	
			}
			
			return count;
		}
		catch(Exception except)
		{
			except.printStackTrace();
			return count;
		}
		
	}

	public CustomerLoanDetails viewName(String name)
	{
		try
		{
			Connection connection = ConnectionImpl.getConnection();
			//statement = connection.createStatement();
			//resultSet = statement.executeQuery("select * from  customerloan");

			PreparedStatement customerloanupdate = connection.prepareStatement("select * from  customerloan where name = ? ");
			customerloanupdate.setString(1,name);
			resultSet = customerloanupdate.executeQuery();
			
			CustomerLoanDetails customer = new CustomerLoanDetails();

			while(resultSet.next())
			{
				
				
				customer.setName(resultSet.getString("name"));
				customer.setDate(resultSet.getString("date"));
				customer.setPrincipal(resultSet.getFloat("principal"));
				customer.setRate(resultSet.getFloat("rate"));
				
				
			
			}
			
			return customer;
		}
		catch(Exception except)
		{
			except.printStackTrace();
			return null;
		}
	}

	public CustomerLoanDetails nameSearch(String name)
	{
		try
		{
			Connection connection = ConnectionImpl.getConnection();
			//statement = connection.createStatement();
			//resultSet = statement.executeQuery("select * from  customerloan");

			PreparedStatement customerloanupdate = connection.prepareStatement("select * from  customerloanupdate where name = ? ");
			customerloanupdate.setString(1,name);
			resultSet = customerloanupdate.executeQuery();
			
			CustomerLoanDetails customer = new CustomerLoanDetails();

			while(resultSet.next())
			{
				
				
				customer.setName(resultSet.getString("name"));
				customer.setDate(resultSet.getString("date"));
				customer.setPrincipal(resultSet.getFloat("principal"));
				customer.setRate(resultSet.getFloat("rate"));
				
				
			
			}
			
			return customer;
		}
		catch(Exception except)
		{
			except.printStackTrace();
			return null;
		}
	}


	public ArrayList<PaymentHistory> paymentHistory(String name,int startPage,int endPage)
	{
		try
		{
			Connection connection = ConnectionImpl.getConnection();
			//statement = connection.createStatement();
			//resultSet = statement.executeQuery("select * from  customerloan");

			PreparedStatement customerloanupdate = connection.prepareStatement("select * from  paymenthistory where name = ? limit ? , ?");
			customerloanupdate.setString(1,name);
			customerloanupdate.setInt(2,startPage);
			customerloanupdate.setInt(3,endPage);
			resultSet = customerloanupdate.executeQuery();
			
			ArrayList<PaymentHistory> historyList = new ArrayList<PaymentHistory>();
			

			while(resultSet.next())
			{
				
				PaymentHistory history = new PaymentHistory();

				history.setDate(resultSet.getString("date"));
				history.setAmount(resultSet.getFloat("amount"));
				history.setTotalAmount(resultSet.getFloat("totalAmount"));
				
				historyList.add(history);
				
			
			}
			
			return historyList;
		}
		catch(Exception except)
		{
			except.printStackTrace();
			return null;
		}
	}


	public CustomerPersonalDetails view(String name)
	{
		try
		{
			Connection connection = ConnectionImpl.getConnection();
			PreparedStatement customerPersonal = connection.prepareStatement("select * from  customerdetails where name = ? ");
			customerPersonal.setString(1,name);
			resultSet = customerPersonal.executeQuery();

			
			CustomerPersonalDetails customer = new CustomerPersonalDetails();
			

			while(resultSet.next())
			{
				
				
				customer.setName(resultSet.getString("name"));
				customer.setAge(resultSet.getString("age"));
				customer.setGender(resultSet.getString("gender"));
				customer.setMobile(resultSet.getString("moblie"));
				customer.setAadhar(resultSet.getString("aadhar"));
					
				
			}
			
			return customer;
		}
		catch(Exception except)
		{
			except.printStackTrace();
			return null;
		}
	}

	public int paymentrecords(String cname)
	{
		int count = 1;

		try
		{
			Connection connection = ConnectionImpl.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select count(*) from  paymenthistory where name =  '"+cname+"' ");
			
			while(resultSet.next())
			{
				 count= resultSet.getInt(1);	
			}
			
			return count;
		}
		catch(Exception except)
		{
			except.printStackTrace();
			return count;
		}
	}

	public boolean login(String name,String pwd)
	{
		try
		{
			Connection connection = ConnectionImpl.getConnection();
			PreparedStatement login = connection.prepareStatement("select * from  customerlogin where username = ? and password = ? ");
			login.setString(1,name);
			login.setString(2,pwd);
			resultSet = login.executeQuery();

			boolean status = resultSet.next();

			return status;
		}
		catch(Exception except)
		{
			except.printStackTrace();
			return false;
		}

	}

}
