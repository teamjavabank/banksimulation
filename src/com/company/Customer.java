package com.company;

import java.util.*;

public class Customer {

	public String name;
	private String address;
	private String phone;
	private String sex;
	private String dob;
	private List<Integer> AccountList;
	private int customerId;

	public Customer (String newName, String newAddress, String newPhone, String newSex, String newDob)
	{
		this.name = newName;
		this.address = newAddress;
		this.phone = newPhone;
		this.sex = newSex;
		this.dob = newDob;
		this.AccountList = new ArrayList<>();
	}

	public static void addNewCustomer (Customer c, Database d)
	{

		// ID is generated in database

		d.setCustomer(c);

	}

	public static void showPersonDetails (int customerId, Database d)
	{

		Customer details = d.getCustomer(customerId);

		System.out.println(details.name + ", " + details.address + ", " + details.phone + ", " + details.sex + ", " + details.dob);
		for (int account: details.AccountList)
		{

			System.out.println("Account: " + account);

		}

	}

	public static void updatePersonDetails (int customerId, Database d)
	{

		Customer oldCustomer = d.getCustomer(customerId);
		Customer newCustomer = oldCustomer;

		Scanner inputScanner = new Scanner(System.in);

		System.out.println("Current name: " + oldCustomer.name + ". Changes?");
		String input = inputScanner.nextLine();
		if (!(input.equals(""))){
			newCustomer.name = input;
		}

		System.out.println("Current address: " + oldCustomer.address + ". Changes?");
		input = inputScanner.nextLine();
		if (!(input.equals(""))){
			newCustomer.address = input;
		}

		System.out.println("Current phone: " + oldCustomer.phone + ". Changes?");
		input = inputScanner.nextLine();
		if (!(input.equals(""))){
			newCustomer.phone = input;
		}

		System.out.println("Current sex: " + oldCustomer.sex + ". Changes?");
		input = inputScanner.nextLine();
		if (!(input.equals(""))){
			newCustomer.sex = input;
		}

		System.out.println("Current dob: " + oldCustomer.dob + ". Changes?");
		input = inputScanner.nextLine();
		if (!(input.equals(""))){
			newCustomer.dob = input;
		}

		newCustomer.AccountList = oldCustomer.AccountList;
		newCustomer.customerId = customerId;

		oldCustomer = newCustomer;
	}

	public void removeCustomer (int customerId, Database d)
	{

		d.removeCustomer(customerId);

	}


}
