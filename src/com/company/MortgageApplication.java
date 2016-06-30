package com.company;

import java.util.*;


public class MortgageApplication {
	
	double amount;
	String newAccountType;
	int newCustomerId;
	Database db;
	double yearlyPay;
	
	public MortgageApplication(String newAccountType1, int newCustomerId1, Database db1){
		
		newAccountType = newAccountType1;
		newCustomerId = newCustomerId1;
		db = db1;
		yearlyPay =0;
		
		interview();
		mortgageConditions();
		if(yearlyPay == 0){
			System.out.println("No mortage was created");
		}else{
			makeMortage();
		}
		
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
		System.out.println(" Option 1: The yearly rate for paying in 2 Years is:" + (amount*0.8*0.5*1.038* 1.038)+ "€");
		System.out.println(" Option 2: The yearly rate for paying in 5 Years is:" + (amount*0.8*0.2*1.038* 1.038*1.05*1.05*1.05)+ "€");
		System.out.println(" Option 3: The yearly rate for paying in 10 Years is:" + (amount*0.8*0.1*1.038* 1.038*1.05*1.05*1.05*1.05*1.05*1.05*1.05*1.05)+ "€");
		
		Scanner inputScanner = new Scanner(System.in);
		String inputs;
		inputs = "5";
		
		while(!(inputs.equals("0"))){
			System.out.println("Which Option would the customer like? (1/2/3) (0 to leave)");
			inputs = (String) inputScanner.next();
			if(inputs.equals("1")){
				yearlyPay = amount*0.8*0.5*1.038* 1.038;
				inputs = "0";
			}else if(inputs.equals("2")){
				yearlyPay = amount*0.8*0.2*1.038* 1.038*1.05*1.05*1.05;
				inputs = "0";
			}else if(inputs.equals("0")){
				inputs = "0";
			}else if(inputs.equals("3")){
				yearlyPay = amount*0.8*0.1*1.038* 1.038*1.05*1.05*1.05*1.05*1.05*1.05*1.05*1.05;
				inputs = "0";
			}else{
				System.out.println("Please use a shown option");
				inputs = "5";
			}
		}
	}
	
	private void makeMortage(){
	
		Mortgage m = new Mortgage( newAccountType, newCustomerId, db);
		int id = db.setAccount(m);
		m.id = id;
		m.setStartBalance(amount*0.8);
		m.yearlyPay = yearlyPay;
		
	}

}
