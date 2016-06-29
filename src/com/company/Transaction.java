package com.company;

import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Transaction {

	private int sender;
	private int receiver;
	private double amount;
	private String timestamp;

    private static Scanner inputScanner = new Scanner(System.in);

	public Transaction (int customerId, Database d) {

        //Initialize variables
        this.sender = 0;
        this.receiver = 0;
        this.amount = 0;
        this.timestamp = null;

        //Define input
        int input;

        //Menu
        System.out.println("Welcome to the transaction menu!");
        System.out.println("Press a number to select your desired function: transfer (1), deposit (2), withdraw (3)");
        System.out.println("to abort the transaction the transaction sesson press 0.");

        //Make sure input is an int
        while (!inputScanner.hasNextInt()) inputScanner.next();
        input = inputScanner.nextInt();

        switch (input) {
            case 1:
                //transfer
                this.sender = inputSender(customerId, d);
                if (sender == 0) break;
                this.receiver = inputReceiver(customerId, d);
                if (receiver == 0) break;
                this.amount = inputAmount(customerId, d);
                if (amount == 0) break;
                this.timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                d.setTransaction(this);
                break;
            case 2:
                //deposit
                this.receiver = inputReceiver(customerId, d);
                if (receiver == 0) break;
                this.amount = inputAmount(customerId, d);
                if (amount == 0) break;
                this.timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                d.setTransaction(this);
                break;
            case 3:
                //withdraw
                this.sender = inputSender(customerId, d);
                if (sender == 0) break;
                this.amount = inputAmount(customerId, d);
                if (amount == 0) break;
                this.timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                d.setTransaction(this);
                break;
            default:
                System.out.println("Please select a valid option!");
                break;
        }
        inputScanner.close();
    }

    private int inputSender(int customerId, Database d) {
        //Define input
        int input;

        //Let the user select a sender account
        while (true) {
            List<Integer> UserAccountList = Customer.getAccounts(customerId, d);
            System.out.println("Which one of your accounts do you want to use?");

            for (int i = 1; i <= UserAccountList.size(); i++) {
                System.out.printf("Account (%d): %s\n", i, UserAccountList.get(i - 1));
            }
            System.out.println("Press (0) to abort the transaction.");

            //Make sure input is an int
            while (!inputScanner.hasNextInt()) inputScanner.next();
            input = inputScanner.nextInt();

            try {
                if (input == 0) {
                    sender = 0;
                    System.out.println("Transaction stopped, returning to last menu.");
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

        //return the given sender account
        return sender;
    }

    private int inputReceiver(int customerId, Database d) {
        //Define input
        int input;

        //Let the user select a receiver account
        Map<Integer, Account> AccountList = d.getAccountList();

        while (true) {
            System.out.println("To which account do you want to transfer?");
            System.out.println("Press (0) to abort the transaction.");

            //Make sure input is an int
            while (!inputScanner.hasNextInt()) inputScanner.next();
            input = inputScanner.nextInt();

            if (input == 0) {
                receiver = 0;
                System.out.println("Transaction stopped, returning to last menu.");
                break;
            /*} else if (input == this.sender) {
                System.out.println("A transaction to the same account is not possible.");
                continue;*/
            } else if (AccountList.containsKey(input)) {
                receiver = input;
                System.out.printf("Selected receiver account %d.\n", receiver);
                break;
            } else {
                System.out.println("Please input a valid account!");
            }
        }

        //return the given receiver account
        return receiver;
    }

    private double inputAmount(int customerId, Database d) {
        //Define input
        double input;

        //Let the user select an amount to transfer
        boolean isdeposit = true;
        double UserAccountBalance = 0;
        int UserCreditLimit = 0;

        if (sender != 0) {
            UserAccountBalance = Account.getBalance(sender, d);
            UserCreditLimit = Account.getCreditLimit(sender, d);
            isdeposit = false;
        }
        while (true) {
            if (!isdeposit) {
                System.out.printf("Current balance of account %d: %f?\n", sender, UserAccountBalance);
            }
            System.out.println("Which amount do you want to transfer?");
            System.out.println("Press (0) to abort the transaction.");

            //Make sure input is a double
            while (!inputScanner.hasNextDouble()) inputScanner.next();
            input = inputScanner.nextDouble();

            if (input == 0) {
                amount = 0;
                System.out.println("Transaction stopped, returning to last menu.");
                break;
            } else if(input <= 0) {
                System.out.println("Amount must be a positive number!");
                continue;
            } else if(!isdeposit && (UserCreditLimit + UserAccountBalance - input < 0)) {
                System.out.printf("With this transaction you would exceed your credit limit of %d!\n", UserCreditLimit);
                continue;
            } else  {
                amount = input;
                System.out.printf("Amount to transfer: %f.\n", amount);
                break;
            }
        }

        //return the given amount
        return amount;
    }

	public static void showTransactionLog (int customerId, Database d)
	{



	}

}
