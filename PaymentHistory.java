package com.intellect.lendertaskwithjdbc;

public class PaymentHistory {

	private String name;
	private String date;
	private float amount;
	private float totalAmount;

	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}


	public void setDate(String date)
	{
		this.date = date;
	}
	public String getDate()
	{
		return this.date;
	} 


	public void setAmount(float amount)
	{
		this.amount = amount;
	}
	public float getAmount()
	{
		return this.amount;
	} 

	public void setTotalAmount(float totalAmount)
	{
		this.totalAmount = totalAmount;
	}
	public float getTotalAmount()
	{
		return this.totalAmount;
	} 

}
