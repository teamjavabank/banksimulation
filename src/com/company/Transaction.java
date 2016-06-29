package com.company;

import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Transaction {

	private int sender;
	private int receiver;
	private double amount;
	private String timestamp;

	public Transaction (int customerId, Database d)
	{

		Scanner inputScanner = new Scanner(System.in);

		System.out.println("Welcome to the transaction menu!");
		System.out.println("Press a number to select your desired function: transfer (1), deposit (2), withdraw (3)");
		System.out.println("To exit the transaction sesson press 0.");

		String input = inputScanner.next();

		switch (input) {
			case "1":  transferTransaction(customerId, d);
				break;
			case "2":  depositTransaction(customerId, d);
				break;
			case "3":  withdrawTransaction(customerId, d);
				break;
			default: System.out.println("Please select a valid option!");
				break;
		}

		inputScanner.close();

		//this.sender = newSender;
		//this.receiver = newReceiver;
		//this.amount = newAmount;
		this.timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	}

	private static void addNewTransaction (Transaction t, Database d)
	{

		// ID is generated in database
		d.setTransaction(t);

	}

	private void transferTransaction (int customerId, Database d)
	{
		boolean update = true;
		while(update) {

            //Define input scanner
			Scanner inputScanner = new Scanner(System.in);
			int input = 0;

            //Show the accounts of the current user
			List<Integer> UserAccountList = Customer.getAccounts(customerId, d);
			System.out.println("Which one of your accounts do you want to use?");

			for (int i = 1; i <= UserAccountList.size(); i++) {
				System.out.printf("Account (%d): %s\n", i, UserAccountList.get(i - 1));
			}
			System.out.println("Press (0) to exit.");

            //Let the user select a sender account
			boolean validsender = false;
			while (!validsender) {

				while (!inputScanner.hasNextInt()) inputScanner.next();
				input = inputScanner.nextInt();
				try {
					if (input == 0) {
						validsender = true;
						update = false;
						System.out.println("Transfer stopped, returning to last menu.");
					} else {
						int sender = UserAccountList.get(input - 1);
						validsender = true;
                        System.out.printf("Selected sender account %d.\n", sender);
					}
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Please select a valid account!");
				}
			}

            //Let the user select a receiver account
			Map<Integer, Account> AccountList = d.getAccountList();
			System.out.println("To which account do you want to transfer?");
			System.out.println("Press (0) to exit.");

			boolean validreceiver = false;
			while (!validreceiver) {
				while (!inputScanner.hasNextInt()) inputScanner.next();
				input = inputScanner.nextInt();
				if (input == 0) {
					validreceiver = true;
					update = false;
					System.out.println("Transfer stopped, returning to last menu.");
				} else if (AccountList.containsValue(input)) {
					int receiver = input;
					validreceiver = true;
                    System.out.printf("Selected receiver account %d.\n", receiver);
				} else {
					System.out.println("Please input a valid account!");
				}
			}

            //Let the user select an amount to transfer
            double UserAccountBalance = Account.getBalance(sender, d);
            System.out.printf("Which amount do you want to transfer from the account %d to the account %d?\n", sender, receiver);
            System.out.println("Press (0) to exit.");

            boolean validamount = false;
            while (!validamount) {
                while (!inputScanner.hasNextDouble()) inputScanner.next();
                double input2 = inputScanner.nextDouble();
                if (input2 == 0) {
                    validreceiver = true;
                    update = false;
                    System.out.println("Transfer stopped, returning to last menu.");
                } else if (AccountList.containsValue(input2)) {
                    int receiver = input;
                    validreceiver = true;
                    System.out.printf("Selected receiver account %d.\n", receiver);
                } else {
                    System.out.println("Please input a valid account!");
                }
            }
		}

	}

	private static void depositTransaction (int customerId, Database d)
	{



	}

	private static void withdrawTransaction (int customerId, Database d)
	{


	}

	public static void showTransactionLog (int customerId, Database d)
	{



	}

}
