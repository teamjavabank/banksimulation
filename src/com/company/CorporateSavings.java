package com.company;

public class CorporateSavings extends Account{

	public CorporateSavings(String newAccountType, int newCustomerId, Database db) {
		super(newAccountType, newCustomerId, db);

		this.setInterestRate(1);
		this.setCreditLimit(10000);

	}

}
