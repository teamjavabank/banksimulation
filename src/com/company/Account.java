package com.company;

public class Account {
	
	
	protected double balance;
	private int creditLimit;
	private double interestRate;
	private String accountType;
	private int customerId;
	private int accountId;
	
	public Account (String newAccountType, int newCustomerId, Database db) {
		this.accountType = newAccountType;
		this.customerId = newCustomerId;
		this.balance = 0;
		
		this.accountId = db.setAccount(this);
		int customerId = this.customerId;
		
		Customer.addAccount(customerId, accountId, db);
	}
	
	/* --- Close Account --- */
	// -------------------------------------------------
	// for closing a account
	// -------------------------------------------------
	
	public void closeAccount (Database d) {
		d.removeAccount(this.accountId);
	}
	/* --- Balance --- */
	// -------------------------------------------------
	// to show current balance and increase the balance
	// -------------------------------------------------
	public static double getBalance (int accountId, Database d) {
		double value = d.getAccount(accountId).balance;

		return value;
	}
	
	public static void addMoney (double value, int accountId, Database d) {
		d.getAccount(accountId).balance += value;
	}
	
	public static void subtractMoney (double value, int accountId, Database d) {
		d.getAccount(accountId).balance -= value;
	}
	
	/* --- Set Limit and Rate --- */
	// -------------------------------------------------
	// setters for interest rate and credit limit
	// -------------------------------------------------
	public void setInterestRate (double interestRate) {
		this.interestRate = interestRate;
	}
	
	public void setCreditLimit (int creditLimit) {
		this.creditLimit = creditLimit;
	}
	
	public static int getCreditLimit (int accountId, Database db)
	{
		
		Account acc = db.getAccount(accountId);
		
		return acc.creditLimit;
		
	}
	
	public String getAccType ()
	{
		
		return this.accountType;
		
	}
	
}

