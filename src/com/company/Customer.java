package com.company;

import java.util.*;

public class Customer {

	private String name;
	private String address;
	private String phone;
	private String sex;
	private String dob;
	private List<Integer> AccountList;
	private String pin;

	//
	public Customer ()
	{
		Scanner inputScanner = new Scanner(System.in);
		String inputs;

		System.out.println("insert your name!");
		inputs =  (String) inputScanner.next();
		this.name = inputs;
		System.out.println("insert your address!");
		inputs =  (String) inputScanner.next();
		this.address = inputs;
		System.out.println("insert your phonenumber!");
		inputs =  (String) inputScanner.next();
		this.phone = inputs;
		System.out.println("insert your gender!");
		inputs =  (String) inputScanner.next();
		this.sex = inputs;
		System.out.println("insert your date of birth! (DD/MM/YYYY)");
		inputs =  (String) inputScanner.next();
		this.dob = inputs;
		System.out.println("insert your PIN! ");
		inputs =  (String) inputScanner.next();
		this.pin = inputs;
		this.AccountList = new ArrayList<>();
	
	}

	public static void addNewCustomer (Customer c, Database d)
	{

		// ID is generated in database

		d.setCustomer(c);

	}
	
	// -------------------------------------------------
	// prints all personal details
	// -------------------------------------------------
	public static void showPersonDetails (int customerId, Database d)
	{

		Customer details = d.getCustomer(customerId);

		System.out.println(details.name + ", " + details.address + ", " + details.phone + ", " + details.sex + ", " + details.dob);
		for (int account: details.AccountList)
		{

			System.out.println("Account: " + account);

		}

	}
	// -------------------------------------------------
	// updates personal details by checking each value
	// -------------------------------------------------
	public static void updatePersonDetails (int customerId, Database d)
	{

		Customer oldCustomer = d.getCustomer(customerId);
		Customer newCustomer = oldCustomer;

		Scanner inputScanner = new Scanner(System.in);

		boolean update = true;
		while(update)
		{
			System.out.println("Your current personal details are: " + oldCustomer.name + " , " + oldCustomer.address + " , " + oldCustomer.phone + " , "  + oldCustomer.sex  + " , " + oldCustomer.dob);
			System.out.println("Press a number to change a value: name (1), address (2), phone(3), sex (4), dob(5), pin(6)");
			System.out.println("To exit the updating sesson press 0.");
			String input = inputScanner.next();
			String newvalue;

			if (input.equals("0")){
				update = false;
				System.out.println("Updating stopped, changes are saved.");
			}
			else if (input.equals("1")){
				System.out.println("insert new value!");
				newvalue = (String) inputScanner.next();
				newCustomer.name = newvalue;
			}
			else if (input.equals("2")){
				System.out.println("insert new value!");
				newvalue =  (String) inputScanner.next();
				newCustomer.address = newvalue;
			}
			else if (input.equals("3")){
				System.out.println("insert new value!");
				newvalue =  (String) inputScanner.next();
				newCustomer.phone = newvalue;
			}
			else if (input.equals("4")){
				System.out.println("insert new value!");
				newvalue =  (String) inputScanner.next();
				newCustomer.sex = newvalue;
			}
			else if (input.equals("5")){
				System.out.println("insert new value!");
				newvalue =  (String) inputScanner.next();
				newCustomer.dob = newvalue;
			}
			else if (input.equals("6")){
				System.out.println("insert new pin!");
				newvalue =  (String) inputScanner.next();
				System.out.println("repeat new pin!");
				String newvalue2 =  (String) inputScanner.next();
				if(newvalue.equals(newvalue2)){
					System.out.println("insert old pin!");
					newvalue =  (String) inputScanner.next();
					if((oldCustomer.pin).equals(newvalue)){
						newCustomer.pin = newvalue2;
					}else{
						System.out.println("Old PIN was not correct!");
					}
				}else{
					System.out.println("The PINs did not match!");
				}
			}
			else {
				System.out.println("please enter a number between 0 and 5 ");
			}
		}

		oldCustomer = newCustomer;
		
	}

	public static void removeCustomer (int customerId, Database d)
	{

		d.removeCustomer(customerId);

	}
	
	public static List<Integer> getAccounts (int customerId, Database d)
	{
		
		Customer c = d.getCustomer(customerId);
		
		return c.AccountList;
			
	}
}