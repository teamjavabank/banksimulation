package com.company;

import java.text.SimpleDateFormat;
import java.util.*;


public class Main {

	private static Scanner inputScanner = new Scanner(System.in);
	private static boolean fromMenu = false;

	public static void main(String[] args) {

		Database db = new Database();

		new Customer(db, "Tom Smith", "Broadway 203, New York", "023454421992", "male", "12.03.1974", "6343");
		new Customer(db, "Chris Coleman", "Edge Street 7, London", "0421356356", "male", "05.11.1982", "3367");
		new Customer(db, "Anne Lain", "North Ave 22, Mumbai", "04677352345", "female", "26.09.1964", "4976");
		System.out.println("Created computed customers\n");

		new StudentSavings("student", 1000, db);
		new Current("current", 1001, db);
		new CorporateSavings("corporate", 1002, db);
		new Current("current", 1002, db);
		System.out.println("Created computed accounts\n");

		new Transaction (0, 1000, 2500, db);
		new Transaction (0, 1001, 2300, db);
		new Transaction (0, 1002, 500, db);
		new Transaction (0, 1003, 200, db);
		new Transaction (1001, 1003, 500, db);
		new Transaction (1002, 0, 20, db);
		System.out.println("Created computed transactions\n");

		while (true)
		{

			if (fromMenu)
			{
				inputScanner.nextLine();
			}

			System.out.println("Please enter your id.");

			String input = inputScanner.nextLine();
			String id = input;

			System.out.println("Please enter your PIN.");
			input = inputScanner.nextLine();
			String PIN = input;

			Main.menu(id, PIN, db);
		}

	}

	public static void menu (String id, String PIN, Database db){

		if (id.equals("1000") && PIN.equals("1234"))
		{

			boolean menuActive = true;

			while(menuActive)
			{
				System.out.println("\nYou are logged in as cashier " + id + ".");
				System.out.println("Welcome to the main menu!");
				System.out.println("Press a number to select your desired function: Customer actions (1), Account actions (2), Transaction (3)");
				System.out.println("To leave the menu press 0.");


				int input = inputScanner.nextInt();

				switch (input) {
				case 0:

					fromMenu = true;
					menuActive = false;
					break;

				case 1:

					System.out.println("\nWelcome to the Customer menu.");
					System.out.println("Press a number to select your desired function: Add customer (1), Remove customer (2), Update information (3), Show customer details (4)");
					System.out.println("To leave the menu press 0.");

					input = inputScanner.nextInt();

					switch (input) {
					case 0:

						break;

					case 1:

						new Customer(db);

						break;
					case 2:

						System.out.println("Which customer do you want to remove?");
						input = inputScanner.nextInt();

						Customer.removeCustomer(input, db);

						break;
					case 3:

						System.out.println("Which customer's information do you want to edit?");
						input = inputScanner.nextInt();

						Customer.updatePersonDetails(input, db);

						break;
					case 4:

						System.out.println("Which customer's information do you want to see?");
						input = inputScanner.nextInt();

						Customer.showPersonDetails(input, db);

						break;
					default:
						System.out.println("Please select a valid option!");
						break;
					}

					break;
				case 2:

					System.out.println("\nWelcome to the Accounts menu.");
					System.out.println("Press a number to select your desired function: Add account (1), Remove account (2), Get balance (3)");
					System.out.println("To leave the menu press 0.");

					input = inputScanner.nextInt();

					switch (input) {
					case 0:

						break;

					case 1:
						System.out.println("\nWhat kind of account do you want to create?");
						System.out.println("Press a number to select your desired function: Current (1), Junior Current (2), Student Savings (3), Corporate Savings (4), Mortgage (5)");
						System.out.println("To leave the menu press 0.");

						input = inputScanner.nextInt();

						switch (input) {
						case 0:

							fromMenu = true;
							break;

						case 1:

							System.out.println("Who will be the new account's owner?");
							input = inputScanner.nextInt();

							new Current("current", input, db);

							break;

						case 2:

							System.out.println("Please enter the owner's ID.");
							input = inputScanner.nextInt();
							int owner = input;

							System.out.println("Please enter the guardian's ID.");
							input = inputScanner.nextInt();
							int guardian = input;

							new JuniorCurrent("junior", owner, guardian, db);

							break;

						case 3:

							System.out.println("Who will be the new account's owner?");
							input = inputScanner.nextInt();

							new StudentSavings("student", input, db);

							break;

						case 4:

							System.out.println("Who will be the new account's owner?");
							input = inputScanner.nextInt();

							new CorporateSavings("corporate", input, db);

							break;

						case 5:

							System.out.println("Who will be the new account's owner?");
							input = inputScanner.nextInt();

							new MortgageApplication("mortgage", input, db);

							break;

						default:
							System.out.println("Please select a valid option!");
							break;
						}

						break;
					case 2:

						System.out.println("Which account do you want to remove?");
						input = inputScanner.nextInt();

						Account acc = db.getAccount(input);

						if (acc.getAccType().equals("mortgage"))
						{

							double toPay = Mortgage.endMortgagePay(input, db);

							double diff = Math.abs(toPay - Account.getBalance(input, db));

							System.out.printf("To close the Mortgage account the customer needs to pay an additional %5.2f EUR.\n", diff);
							System.out.println("Press (1) to pay, press (2) if the customer is not able to.");

							input = inputScanner.nextInt();

							if (input == 1)
							{

								System.out.println("The mortgage account was closed.");
								acc.closeAccount(db);

							} else if (input == 2){

								System.out.println("Since the customer is not able to pay back, the mortgage will remain.");

							}

						} else {

							System.out.println("The account was closed.");
							acc.closeAccount(db);

						}

						break;
					case 3:

						System.out.println("Which account's balance do you want to see?");
						input = inputScanner.nextInt();

						System.out.printf("The balance is %5.2f EUR.\n", Account.getBalance(input, db));

						break;
					default:
						System.out.println("Please select a valid option!");
						break;
					}

					break;
				case 3:

					System.out.println("Please enter the customer's ID.");
					input = inputScanner.nextInt();

					new Transaction(input, db);

					break;
				default:
					System.out.println("Please select a valid option!");
					break;
				}
			}

		} else if (id.equals("2000") && PIN.equals("1234")) {

			boolean menuActive = true;

			while(menuActive)
			{
				System.out.println("\nYou are logged in as the manager.");
				System.out.println("Press (1) to access the transaction logs, press (2) to see all customers and their balances.");
				System.out.println("To leave the menu press 0.");

				int input = inputScanner.nextInt();

				switch (input) {
				case 0:

					fromMenu = true;
					menuActive = false;
					break;

				case 1:

					Transaction.showTransactionLog(db);

					break;

				case 2:

					Map<Integer, Customer> customerList = db.getCustomerList();

					for (Map.Entry<Integer, Customer> entry: customerList.entrySet())
					{

						Customer.showPersonDetails(entry.getKey(), db);

					}


					break;

				default:
					System.out.println("Please select a valid option!");
					break;
				}
			}

		} else {
			System.out.println("\nLogin was not successful, please try again.");
		}

	}

}