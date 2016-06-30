package com.company;


public class Mortgage extends Account {
	public int id;
	Database db;
	
	public Mortgage(String newAccountType, int newCustomerId, Database db1){
		super(newAccountType, newCustomerId, db1);
		setInterestRate(3.8);
		db = db1;
	}
	
	public void setStartBalance(double b){
		subtractMoney(b, id,db);
	}

}
