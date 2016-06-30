package com.company;

public class Mortgage extends Account {
	public int id;
	
	public Mortgage(String newAccountType, int newCustomerId, Database db){
		super(newAccountType, newCustomerId, db);
		this.setInterestRate(3.8);
	}
	
	public void setStartBalance(balance b){
		this.subtractMoney(b, id,db);
	}

}
