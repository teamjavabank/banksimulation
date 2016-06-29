package com.company;

public class CorporateSavings extends Account{
	
	public CorporateSavings(String newAccountType, int newCustomerId) {
		super(newAccountType, newCustomerId);
		
		this.setInterestRate(1);
		this.setCreditLimit(10000);
		
	}

}
