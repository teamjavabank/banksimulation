package com.company;

public class Mortgage extends Account {
	public int id;
	Database db;
	public double yearlyPay;

	// constructor
	public Mortgage(String newAccountType, int newCustomerId, Database db1){
		super(newAccountType, newCustomerId, db1);
		setInterestRate(3.8);
		db = db1;
	}

	// set start balance
	// 1.013 is 1.3 percent interest to open a mortgage
	public void setStartBalance(double b){
		subtractMoney(b*1.013, id,db);
	}

	// end mortgage pay
	// calculates the money needed to end the mortgage
	public static double endMortgagePay(int accountId, Database db1){
		double bal = Account.getBalance(accountId, db1);

		System.out.printf("You will have to pay %5.2f EUR to end the mortgage/n", Math.abs(bal*1.0735));
		return bal*1.0735;
	}

}