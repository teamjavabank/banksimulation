package com.company;

public class StudentSavings extends Account {
	
	public StudentSavings(String newAccountType, int newCustomerId) {
		super(newAccountType, newCustomerId);
		
		this.setInterestRate(2.5);
		this.setCreditLimit(800);
		
	}

}
