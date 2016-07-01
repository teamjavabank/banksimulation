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

	private static Scanner inputScanner = new Scanner(System.in);

	// -------------------------------------------------
	// constructor
	// -------------------------------------------------
	
	public Customer(Database db, String newName, String newAddress, String newPhone, String newSex, String newDob, String newPin)
	{

		this.name = newName;
		this.address = newAddress;
		this.phone = newPhone;
		this.sex = newSex;
		this.dob = newDob;
		this.pin = newPin;
		this.AccountList = new ArrayList<>();

		this.customerId = db.setCustomer(this);

		System.out.printf("Success! Customer with the id %d has been added.\n", this.customerId);

	}
	// -------------------------------------------------
	// add an account, individual specifications for the input values
	// -------------------------------------------------
	public Customer(Database db) {
		String inputs;
		boolean[] checklist = new boolean[6];
		for (int c = 0; c < 6; c++) {
			checklist[c] = false;
		}
		while (checklist[0] == false) {
			System.out.println("Insert costumer name!");
			inputs = (String) inputScanner.nextLine();
			if (inputs.length() >= 3 && inputs.length() < 25) {
				this.name = inputs;
				checklist[0] = true;
			} else {
				System.out.println("Please enter a name between 3 and 25 characters.");
			}
		}

		while (checklist[1] == false) {
			System.out.println("Insert costumer address!");
			inputs = (String) inputScanner.nextLine();
			if (inputs.length() >= 5 && inputs.length() < 40) {
				this.address = inputs;
				checklist[1] = true;
			} else {
				System.out.println("Please enter a name between 5 and 40 characters.");
			}
		}
		while (checklist[2] == false) {
			System.out.println("Insert costumer phone number!");
			inputs = (String) inputScanner.next();
			if (inputs.length() >= 9 && inputs.length() < 21) {
				this.phone = inputs;
				checklist[2] = true;
			} else {
				System.out.println("Please enter a number between 9 and 20 characters.");
			}
		}
		while (checklist[3] == false) {
			System.out.println("Insert costumer gender!");
			inputs = (String) inputScanner.next();
			if (inputs.equals("male") || inputs.equals("female") || inputs.equals("Male") || inputs.equals("Female")) {
				this.sex = inputs;
				checklist[3] = true;
			} else {
				System.out.println("Please write if the costumer is a ``male`` or a ``female``.");
			}
		}

		System.out.println("Insert the costumers date of birth! (DD.MM.YYYY)");
		inputs = (String) inputScanner.next();
		this.dob = inputs;

		while (checklist[5] == false) {
			System.out.println("Insert a PIN!");
			inputs = (String) inputScanner.next();
			if (inputs.length() >= 4) {
				this.pin = inputs;
				checklist[5] = true;
			} else {
				System.out.println("The PIN has to be at least 4 characters long.");
			}
		}

		this.AccountList = new ArrayList<>();

		this.customerId = db.setCustomer(this);

		System.out.printf("Success! Customer with the id %d has been added.\n", this.customerId);
	}




	// -------------------------------------------------
	// prints all personal details
	// -------------------------------------------------
	public static void showPersonDetails (int customerId, Database db)
	{
		while(true) {
			try {
				Customer details = db.getCustomer(customerId);

				System.out.println("Customer " + details.customerId + ": " + details.name + ", " + details.address + ", " + details.phone + ", " + details.sex + ", " + details.dob);
				if (!details.AccountList.isEmpty()) {
					for (int account : details.AccountList) {
						System.out.printf("Account %d: %5.2f EUR.\n", account, Account.getBalance(account, db));
					}
				}
				break;
			} catch (NullPointerException e) {
				System.out.println("Not a valid account.");
				break;
			}
		}
	}
	// -------------------------------------------------
	// updates personal details by checking each value
	// -------------------------------------------------
	public static void updatePersonDetails(int customerId, Database d) {


		Customer oldCustomer = d.getCustomer(customerId);
		Customer newCustomer = oldCustomer;

		boolean update = true;
		boolean[] updatecheck = new boolean[6];
		for (int c = 0; c < 6; c++) {

			updatecheck[c] = false;

		}

		while (update) {

			System.out.println("Your current personal details are: " + oldCustomer.name + " , " + oldCustomer.address
					+ " , " + oldCustomer.phone + " , " + oldCustomer.sex + " , " + oldCustomer.dob);
			System.out.println(
					"Press a number to change a value: name (1), address (2), phone(3), sex (4), dob(5), pin(6)");
			System.out.println("To exit the updating sesson press 0.");
			String input = inputScanner.next();
			String newvalue = "";

			// stops the updating process
			if (input.equals("0")) {
				update = false;
				System.out.println("Updating stopped, changes are saved.");
			// changes the customers name
			} else if (input.equals("1")) {
				System.out.println("Insert new value!");
				while (updatecheck[1] == false) {
					newvalue = (String) inputScanner.next();
					if (newvalue.length() >= 3 && newvalue.length() < 25) {
						newCustomer.name = newvalue;
						updatecheck[0] = true;
					} else {
						System.out.println("Please enter a name between 3 and 25 charakters.");
					}
				}
			// changes the customers address
			} else if (input.equals("2")) {
				System.out.println("Insert new value!");
				while (updatecheck[1] == false) {
					newvalue = (String) inputScanner.next();
					if (newvalue.length() >= 5 && newvalue.length() < 40) {
						newCustomer.address = newvalue;
						updatecheck[1] = true;
					} else {
						System.out.println("Please enter a name between 5 and 40 charakters.");
					}
				}
			// changes the customers phonenumber	
			} else if (input.equals("3")) {
				System.out.println("Insert new value!");
				while (updatecheck[2] == false) {
					newvalue = (String) inputScanner.next();
					if (newvalue.length() >= 9 && newvalue.length() < 20) {
						newCustomer.phone = newvalue;
						updatecheck[2] = true;
					} else {
						System.out.println("Please enter a name between 9 and 20 charakters.");
					}
				}
			// changes the customers gender
			} else if (input.equals("4")) {
				System.out.println("Insert new value!");
				while (updatecheck[3] == false) {
					newvalue = (String) inputScanner.next();
					if (newvalue.equals("male") || newvalue.equals("female") || newvalue.equals("Male") || newvalue.equals("Female")) {
						newCustomer.sex = newvalue;
						updatecheck[3] = true;
					} else {
						System.out.println("please write if the costumer is a ``male`` or a ``female``");
					}
				}
			// changes the customers pin, the old PIN is necessary for updating
			} else if (input.equals("5")) {
				System.out.println("Insert new value!");
				newvalue = (String) inputScanner.next();
				newCustomer.dob = newvalue;
			} else if (input.equals("6")) {
				System.out.println("Insert new pin!");
				while (updatecheck[5] == false) {
					newvalue = (String) inputScanner.next();
					if (newvalue.length() >= 0 && newvalue.length() < 5) {
						updatecheck[5] = true;
					} else {
						System.out.println("Please enter a number between 0 and 5.");
					}
				}
				System.out.println("Repeat new pin!");
				String newvalue2 = (String) inputScanner.next();				
				if (newvalue.equals(newvalue2)) {
					System.out.println("Insert old pin!");
					newvalue = (String) inputScanner.next();
					if ((oldCustomer.pin).equals(newvalue)) {
						newCustomer.pin = newvalue2;
					} else {
						System.out.println("Old PIN was not correct!");
					}
				} else {
					System.out.println("The PINs did not match!");
				}
			} 

			oldCustomer = newCustomer;

		}

	}
	// -------------------------------------------------
	// removes a customer
	// -------------------------------------------------
	public static void removeCustomer (int customerId, Database d)
	{

		d.removeCustomer(customerId);
		System.out.println("The customer was removed.");

	}
	// -------------------------------------------------
	// lists all accounts
	// -------------------------------------------------
	public static List<Integer> getAccounts (int customerId, Database d)
	{

		Customer c = d.getCustomer(customerId);

		return c.AccountList;

	}
	// -------------------------------------------------
	// return the customers date of birth (is needed for junior account provement)
	// -------------------------------------------------
	public static String getDob (int customerId, Database d)
	{

		Customer c = d.getCustomer(customerId);

		return c.dob;

	}
	// -------------------------------------------------
	// adds an account to a costumer
	// -------------------------------------------------
	public static void addAccount (int customerId, int accountId, Database d)
	{

		Customer c = d.getCustomer(customerId);

		c.AccountList.add(accountId);

	}
	// -------------------------------------------------
	// removes an account of a costumer
	// -------------------------------------------------
	public static void removeAccount (int customerId, int accountId, Database d)
	{

		Customer c = d.getCustomer(customerId);

		List <Integer> accList = c.AccountList;
		
		for (int i = 0; i < accList.size(); i++)
		{
			
			if (accList.get(i) == accountId)
			{
				
				c.AccountList.remove(i);
				
			}
			
		}

	}

}