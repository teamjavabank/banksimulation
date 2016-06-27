package com.company;

public class Account {
	
	
	private double balance;
	private int creditLimit;
	private double interestRate;
	private String accountType;
	private int customerId;
	
	public Account (String inAccountType, int inCustomerId) {
		this.accountType = inAccountType;
		this.customerId = inCustomerId;
		this.balance = 0;
	}
	
	/* --- Open/Close Account --- */
	public void openAccount (Account a, Database d) {
		d.setAccount(a);
	}
	
	public void closeAccount (int accountId, Database d) {
		d.removeAccount(accountId);
	}
	
	/* --- Balance --- */
	public void showBalance (int accountId, Database d) {
		double value = d.getAccount(accountId).balance;
		
		System.out.println("Current Balance: " + value);
	}
	
	public void addMoney (double value ,int accountId, Database d) {
		d.getAccount(accountId).balance += value;
	}
	
	/* --- Set Limit and Rate --- */
	public void setInterestRate (double iRate) {
		this.interestRate = iRate;
	}
	
	public void setCreditLimit (int cLimit) {
		this.creditLimit = cLimit;
	}
}
