package com.intellect.lendertaskwithjdbc;

public class CustomerLoanDetails {
	
	private String name;
	private String date;
	private float rate;
	private float principal;
	private float totalAmount;
		
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return this.name;
	}
	public void setDate(String date)
	{
		this.date=date;
	}
	public String getDate()
	{
		return this.date;
	}
	public void setRate(float rate)
	{
		this.rate = rate;
	}
	public float getRate()
	{
		return this.rate;
	}
	public void setPrincipal(float principal)
	{
		this.principal=principal;
	}
	public float getPrincipal()
	{
		return this.principal;
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
