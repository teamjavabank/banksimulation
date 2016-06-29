package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class TestTransaction {

	public static void main(String[] args) {
		// initialize database
		Database database = new Database();
		Customer customer = new Customer();
		Customer.addNewCustomer(customer, database);
		StudentSavings s = new StudentSavings("student", 1000);
		s.openAccount(database);

		new Transaction(1000, database);
	}
}