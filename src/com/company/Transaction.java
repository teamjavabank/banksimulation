package com.company;

import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Transaction {

	private int sender;
	private int receiver;
	private double amount;
	private String timestamp;

	public Transaction (int customerId, Database d) {

		Scanner inputScanner = new Scanner(System.in);

		System.out.println("Welcome to the transaction menu!");
		System.out.println("Press a number to select your desired function: transfer (1), deposit (2), withdraw (3)");
		System.out.println("To exit the transaction sesson press 0.");

        int input;
        while (!inputScanner.hasNextInt()) inputScanner.next();
        input = inputScanner.nextInt();

		switch (input) {
			case 1:  transferTransaction(customerId, d);
				break;
			case 2:  depositTransaction(customerId, d);
				break;
			case 3:  withdrawTransaction(customerId, d);
				break;
			default: System.out.println("Please select a valid option!");
				break;
		}

		inputScanner.close();

        //Initialize variables
        this.sender = 0;
        this.receiver = 0;
        this.amount = 0;
		this.timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        private void inputSender (int customerId, Database d) {
            //Define input scanner
            Scanner inputScanner = new Scanner(System.in);
            int input = 0;

            //Let the user select a sender account
            while (true) {
                List<Integer> UserAccountList = Customer.getAccounts(customerId, d);
                System.out.println("Which one of your accounts do you want to use?");

                for (int i = 1; i <= UserAccountList.size(); i++) {
                    System.out.printf("Account (%d): %s\n", i, UserAccountList.get(i - 1));
                }
                System.out.println("Press (0) to exit.");

                while (!inputScanner.hasNextInt()) inputScanner.next();
                input = inputScanner.nextInt();
                try {
                    if (input == 0) {
                        System.out.println("Transfer stopped, returning to last menu.");
                        transfer = false;
                        break;
                    } else {
                        sender = UserAccountList.get(input - 1);
                        System.out.printf("Selected sender account %d.\n", sender);
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please select a valid account!");
                }
            }

            //Close input scanner
             inputScanner.close();
        }

	}

	private void transferTransaction (int customerId, Database d)
	{
		boolean transfer = true;
		while(transfer) {

            //Initialize variables
            int sender = 0;
            int receiver = 0;
            double amount = 0;

            //Define input scanner
			Scanner inputScanner = new Scanner(System.in);
			int input = 0;

            //Let the user select a sender account
			while (true) {
                List<Integer> UserAccountList = Customer.getAccounts(customerId, d);
                System.out.println("Which one of your accounts do you want to use?");

                for (int i = 1; i <= UserAccountList.size(); i++) {
                    System.out.printf("Account (%d): %s\n", i, UserAccountList.get(i - 1));
                }
                System.out.println("Press (0) to exit.");

                while (!inputScanner.hasNextInt()) inputScanner.next();
				input = inputScanner.nextInt();
				try {
					if (input == 0) {
                        System.out.println("Transfer stopped, returning to last menu.");
                        transfer = false;
						break;
					} else {
						sender = UserAccountList.get(input - 1);
                        System.out.printf("Selected sender account %d.\n", sender);
						break;
					}
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Please select a valid account!");
				}
			}
            if(!transfer){
                break;
            }

            //Let the user select a receiver account
			Map<Integer, Account> AccountList = d.getAccountList();

			while (true) {
                System.out.println("To which account do you want to transfer?");
                System.out.println("Press (0) to exit.");
				while (!inputScanner.hasNextInt()) inputScanner.next();
				input = inputScanner.nextInt();
				if (input == 0) {
					transfer = false;
					System.out.println("Transfer stopped, returning to last menu.");
                    break;
				} else if (AccountList.containsKey(input)) {
					receiver = input;
                    System.out.printf("Selected receiver account %d.\n", receiver);
                    break;
				} else {
					System.out.println("Please input a valid account!");
				}
			}
            if(!transfer){
                break;
            }

            //Let the user select an amount to transfer
            double UserAccountBalance = Account.getBalance(sender, d);
            int UserCreditLimit = Account.getCreditLimit(sender, d);

            while (true) {
                System.out.printf("Current balance of account %d: %f?\n", sender, UserAccountBalance);
                System.out.printf("Which amount do you want to transfer from the account %d to the account %d?\n", sender, receiver);
                System.out.println("Press (0) to exit.");
                while (!inputScanner.hasNextDouble()) inputScanner.next();
                double input2 = inputScanner.nextDouble();
                if (input2 == 0) {
                    transfer = false;
                    System.out.println("Transfer stopped, returning to last menu.");
                    break;
                } else if(input2 <= 0) {
                    System.out.println("Amount must be a positive number!");
                    continue;
                } else if(UserCreditLimit + UserAccountBalance - input2 < 0) {
                    System.out.println("With this transaction you would exceed your credit limit!");
                    continue;
                } else  {
                    amount = input2;
                    System.out.printf("Amount to transfer: %f.\n", amount);
                    break;
                }
            }
            if(!transfer){
                break;
            }
            transfer = false;
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
