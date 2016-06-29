package com.company;

public class Current extends Account{

	public Current(String newAccountType, int newCustomerId, Database db) {
		super(newAccountType, newCustomerId, db);
		
		this.setInterestRate(1.5);
		this.setCreditLimit(1000);
		
	}

}
