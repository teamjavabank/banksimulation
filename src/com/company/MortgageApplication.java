package com.company;

import java.util.*;


public class MortgageApplication {
	
	double amount;
	String newAccountType;
	int newCustomerId;
	Database db;
	
	public MortgageApplication(String newAccountType1, int newCustomerId1, Database db1){
		
		newAccountType = newAccountType1;
		newCustomerId = newCustomerId1;
		db = db1;
		
		interview();
		mortgageConditions();
		makeMortage();
	}
	
	
	private void interview(){
		Scanner inputScanner = new Scanner(System.in);
		String inputs;
		int done = 0;
		
		while(done == 0){
		
			System.out.println("How much money is the object worth?");
			amount = (double) inputScanner.nextDouble();
			if(Double.isNaN(amount)){
				System.out.println("Please enter a valid number next time.");
			}else{
				done = 1;
			}
		}
	}
	
	private void mortgageConditions(){
		System.out.println("The Customer gets" + amount*0.8 + "€ and can pay back the mortgage for " + (amount*1.013*1.0735*0.8) + "€");
		System.out.println(" ");
		System.out.println("The Customer can get a mortgage of " + amount*0.8 +" € with a yearly interest of 3.8 percent for the first two years and an interest of 5 percent afterwards");
		System.out.println("The mortgage will be paid off in 5 years, if: Yearly Rate:" + (amount*0.8*0.2*1.038*1.038*1.05*1.05*1.05)+ "€");
		
	}
	
	private void makeMortage(){
	
		Mortgage m = new Mortgage( newAccountType, newCustomerId, db);
		int id = db.setAccount(m);
		m.id = id;
		m.setStartBalance(amount*0.8);
		
	}
	

	
	

}
