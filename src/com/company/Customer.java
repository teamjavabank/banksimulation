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
	private int customerId;

	
	public Customer(Database db) {
		Scanner inputScanner = new Scanner(System.in);
		String inputs;
		boolean[] checklist = new boolean[6];
		for (int c = 0; c < 6; c++) {
			checklist[c] = false;
		}
		while (checklist[0] == false) {
			System.out.println("insert your name!");
			inputs = (String) inputScanner.next();
			if (inputs.length() >= 3 && inputs.length() < 25) {
				this.name = inputs;
				checklist[0] = true;
			} else {
				System.out.println("please enter a name between 3 and 25 characters");
			}
		}

		while (checklist[1] == false) {
			System.out.println("insert your address!");
			inputs = (String) inputScanner.next();
			if (inputs.length() >= 5 && inputs.length() < 40) {
				this.address = inputs;
				checklist[1] = true;
			} else {
				System.out.println("please enter a name between 5 and 40 characters");
			}
		}
		while (checklist[2] == false) {
			System.out.println("insert your phone number!");
			inputs = (String) inputScanner.next();
			if (inputs.length() >= 9 && inputs.length() < 21) {
				this.phone = inputs;
				checklist[2] = true;
			} else {
				System.out.println("please enter a number between 9 and 20 characters");
			}
		}
		while (checklist[3] == false) {
			System.out.println("insert your gender!");
			inputs = (String) inputScanner.next();
			if (inputs.equals("male") || inputs.equals("female")) {
				this.sex = inputs;
				checklist[3] = true;
			} else {
				System.out.println("please write if you are a ``male`` or a ``female``");
			}
		}

		System.out.println("insert your date of birth! (DD/MM/YYYY)");
		inputs = (String) inputScanner.next();
		this.dob = inputs;

		while (checklist[5] == false) {
			System.out.println("insert your PIN!");
			inputs = (String) inputScanner.next();
			if (inputs.length() >= 4) {
				this.pin = inputs;
				checklist[5] = true;
			} else {
				System.out.println("your PIN has to be at least 4 characters long");
			}
		}
		
		this.AccountList = new ArrayList<>();
		
		this.customerId = db.setCustomer(this);

	}
	

	
	
	// -------------------------------------------------
	// prints all personal details
	// -------------------------------------------------
	public void showPersonDetails (Database db)
	{

		Customer details = db.getCustomer(this.customerId);

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

	public void removeCustomer (Database d)
	{

		d.removeCustomer(this.customerId);

	}
	
	public static List<Integer> getAccounts (int customerId, Database d)
	{
		
		Customer c = d.getCustomer(customerId);
		
		return c.AccountList;
			
	}
	
	public static String getDob (int customerId, Database d)
	{
		
		Customer c = d.getCustomer(customerId);
		
		return c.dob;
		
	}
	
	public static void addAccount (int customerId, int accountId, Database d)
	{
		
		Customer c = d.getCustomer(customerId);
		
		c.AccountList.add(accountId);
		
	}
}