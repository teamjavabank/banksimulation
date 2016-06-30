package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import static com.company.Account.getBalance;

public class Test {

	public static void main(String[] args) {

		//Initialize DB
		Database db = new Database();

		//Create Customers
		new Customer(db, "Tom Smith", "Broadway 203, New York", "023454421992", "male", "12.03.1974", "6343");
		new Customer(db, "Chris Coleman", "Edge Street 7, London", "0421356356", "male", "05.11.1982", "3367");
		new Customer(db, "Anne Lain", "North Ave 22, Mumbai", "04677352345", "female", "26.09.1964", "4976");

		//Create Accounts of all types
		new Current("current", 1000, db);
		new StudentSavings("student", 1001, db);
		new CorporateSavings("corporate", 1002, db);

		//Create Transactions of all types for every account
		//transfer
		new Transaction(1000, 1001, 100, db);
		new Transaction(1001, 1000, 100, db);
		new Transaction(1002, 1000, 100, db);

		//print balances
		printBalances(db);

		//deposit
		new Transaction(0, 1000, 100, db);
		new Transaction(0, 1001, 100, db);
		new Transaction(0, 1002, 100, db);

		//print balances
		printBalances(db);

		//withdraw
		new Transaction(1000, 0, 100, db);
		new Transaction(1001, 0, 100, db);
		new Transaction(1002, 0, 100, db);

		//print balances
		printBalances(db);
	}

	//Print account balance for all accounts
	private static void printBalances(Database db) {
		double balance = 0;
		balance = getBalance(1000, db);
		System.out.printf("Balance (1000): %5.2f\n", balance);
		balance = getBalance(1001, db);
		System.out.printf("Balance (1001): %5.2f\n", balance);
		balance = getBalance(1002, db);
		System.out.printf("Balance (1002): %5.2f\n\n", balance);
	}
}