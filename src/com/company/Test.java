package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import static com.company.Account.getBalance;
import static com.company.Customer.showPersonDetails;

public class Test {

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

		//student
		new StudentSavings("student", 1001, db);
		System.out.println("student account created for user 1001.");
		printBalance(1001, db);

		//corporate
		new CorporateSavings("corporate", 1002, db);
		System.out.println("corporate account created for user 1002.");
		printBalance(1002, db);

		//Create Transactions of all types for every account
		System.out.println("Create Transactions of all types for every account.\n");

		//transfer 1
		new Transaction(1000, 1001, 100, db);
		System.out.println("Transfer of 100 EUR from account 1000 to account 1001.");
		printBalance(1000, db);

		//transfer 2
		new Transaction(1001, 1000, 100, db);
		System.out.println("Transfer of 100 EUR from account 1001 to account 1000.");
		printBalance(1001, db);

		//transfer 3
		new Transaction(1002, 1000, 100, db);
		System.out.println("Transfer of 100 EUR from account 1002 to account 1000.");
		printBalance(1002, db);


		//deposit 1
		new Transaction(0, 1000, 100, db);
		System.out.println("Deposit of 100 EUR to account 1000.");
		printBalance(1000, db);

		//deposit 2
		new Transaction(0, 1001, 100, db);
		System.out.println("Deposit of 100 EUR to account 1001.");
		printBalance(1001, db);

		//deposit 3
		new Transaction(0, 1002, 100, db);
		System.out.println("Deposit of 100 EUR to account 1002.");
		printBalance(1002, db);


		//withdraw 1
		new Transaction(1000, 0, 100, db);
		System.out.println("Withdraw of 100 EUR from account 1000.");
		printBalance(1000, db);

		//withdraw 2
		new Transaction(1001, 0, 100, db);
		System.out.println("Withdraw of 100 EUR from account 1001.");
		printBalance(1001, db);

		//withdraw 3
		new Transaction(1002, 0, 100, db);
		System.out.println("Withdraw of 100 EUR from account 1002.");
		printBalance(1002, db);
	}

	//Print account balance for all accounts
	private static void printBalance(int account, Database db) {
		double balance = 0;
		balance = getBalance(account, db);
		System.out.printf("Balance (%d): %5.2f\n\n", account, balance);
	}
}