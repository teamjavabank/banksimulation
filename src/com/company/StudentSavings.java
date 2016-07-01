package com.company;

public class StudentSavings extends Account {

	public StudentSavings(String newAccountType, int newCustomerId, Database db) {
		super(newAccountType, newCustomerId, db);

		this.setInterestRate(2.5);
		this.setCreditLimit(800);

	}

}
