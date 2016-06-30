package com.company;

public class Account {
	
	
	private double balance;
	private int creditLimit;
	private double interestRate;
	private String accountType;
	private int customerId;
	
	public Account (String newAccountType, int newCustomerId, Database db) {
		this.accountType = newAccountType;
		this.customerId = newCustomerId;
		this.balance = 0;
		
		int accountId = db.setAccount(this);
		int customerId = this.customerId;
		
		Customer.addAccount(customerId, accountId, db);
	}
	
	/* --- Close Account --- */
	// -------------------------------------------------
	// for closing a account
	// -------------------------------------------------
	
	public void closeAccount (int accountId, Database d) {
		d.removeAccount(accountId);
	}
	
	/* --- Balance --- */
	// -------------------------------------------------
	// to show current balance and increase the balance
	// -------------------------------------------------
	public static double getBalance (int accountId, Database d) {
		double value = d.getAccount(accountId).balance;

		return value;
	}
	
	public void addMoney (double value, int accountId, Database d) {
		d.getAccount(accountId).balance += value;
	}
	
	public void subtractMoney (double value, int accountId, Database d) {
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
}

