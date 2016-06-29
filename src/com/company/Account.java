package com.company;

public class Account {
	
	
	private double balance;
	private int creditLimit;
	private double interestRate;
	private String accountType;
	private int customerId;
	
	public Account (String newAccountType, int newCustomerId) {
		this.accountType = newAccountType;
		this.customerId = newCustomerId;
		this.balance = 0;
	}
	
	/* --- Open/Close Account --- */
	// -------------------------------------------------
	// for opening and closing a account
	// -------------------------------------------------
	public void openAccount (Account a, Database d) {
		d.setAccount(a);
	}
	
	public void closeAccount (int accountId, Database d) {
		d.removeAccount(accountId);
	}
	
	/* --- Balance --- */
	// -------------------------------------------------
	// to show current balance and increase the balance
	// -------------------------------------------------
	public void showBalance (int accountId, Database d) {
		double value = d.getAccount(accountId).balance;
		
		System.out.println("Current Balance: " + value);
	}
	
	public void addMoney (double value, int accountId, Database d) {
		d.getAccount(accountId).balance += value;
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
}
