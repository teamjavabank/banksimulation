package com.company;

import static com.company.Account.getBalance;
import static com.company.Customer.showPersonDetails;

public class TestMortgage {
	public static void main(String[] args) {

		//Initialize DB
		Database db = new Database();

		//Create Customers
		System.out.println("Create test customers.\n");

		new Customer(db, "Tom Smith", "Broadway 203, New York", "023454421992", "male", "12.03.1974", "6343");
		showPersonDetails(1000, db);
		System.out.printf("\n");

		new Customer(db, "Chris Coleman", "Edge Street 7, London", "0421356356", "male", "05.11.1982", "3367");
		showPersonDetails(1001, db);
		System.out.printf("\n");

		new Customer(db, "Anne Lain", "North Ave 22, Mumbai", "04677352345", "female", "26.09.1964", "4976");
		showPersonDetails(1002, db);
		System.out.printf("\n");

		//Create accounts of all types
		System.out.println("Create accounts of all types.\n");

		//current
		new Current("current", 1000, db);
		System.out.println("current account created for user 1000.");
		printBalance(1000, db);

		//mortgage
		new Mortgage("mortgage", 1001, db);
		System.out.println("mortageg account created for user 1001.");
		printBalance(1001, db);


		//Create a Mortgage
		System.out.println("Create a Mortgage.\n");
		new MortgageApplication("mortgage", 1002, db);
		printBalance(1002, db);
	}

	//Print account balance for all accounts
	private static void printBalance(int account, Database db) {
		double balance = 0;
		balance = getBalance(account, db);
		System.out.printf("Balance (%d): %5.2f\n\n", account, balance);
	}
}
