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
		subtractMoney(b*1.013, id,db);
	}
	
	public double endMortgagePay(){
		System.out.println("You will have to pay "+ balance*1.0735 + "€ to end the mortgage");
		return balance*1.0735;
	}

}
