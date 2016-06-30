package com.company;

import java.util.*;
import java.text.SimpleDateFormat;
import static com.company.Account.addMoney;
import static com.company.Account.subtractMoney;

public class Transaction {

	private int sender;
	private int receiver;
	private double amount;
	private String timestamp;

    private static Scanner inputScanner = new Scanner(System.in);

    //Computed values constructor
    public Transaction (int newsender, int newreceiver, double newamount, Database db) {

        //Initialize variables
        this.sender = newsender;
        this.receiver = newreceiver;
        this.amount = newamount;
        this.timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        //Make Transaction
        db.setTransaction(this);


        if(this.sender != 0 && this.receiver != 0) {
            //transfer
            subtractMoney(this.amount, this.sender, db);
            addMoney(this.amount, this.receiver, db);
        } else if (this.receiver != 0){
            //deposit
            addMoney(this.amount, this.receiver, db);
        } else {
            //withdraw
            subtractMoney(this.amount, this.sender, db);
        }
    }
    //User input constructor
	public Transaction (int customerId, Database db) {

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

        selectOption: while (true) {
            switch (input) {
                case 0:
                    System.out.println("Transaction stopped, returning to last menu.");
                    break selectOption;
                case 1:
                    //transfer
                    this.sender = selectAccount(customerId, db, false);
                    if (sender == 0) break;
                    this.receiver = inputReceiver(this.sender, db);
                    if (receiver == 0) break;
                    this.amount = inputAmount(this.sender, db, false);
                    if (amount == 0) break;
                    this.timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

                    //Make transaction
                    db.setTransaction(this);
                    subtractMoney(this.amount, this.sender, db);
                    addMoney(this.amount, this.receiver, db);
                    break selectOption;
                case 2:
                    //deposit
                    this.receiver = selectAccount(customerId, db, true);
                    if (receiver == 0) break;
                    this.amount = inputAmount(this.receiver, db, true);
                    if (amount == 0) break;
                    this.timestamp = new SimpleDateFormat("yyyy.MM.ddb.HH.mm.ss").format(new Date());

                    //Make transaction
                    db.setTransaction(this);
                    addMoney(this.amount, this.receiver, db);
                    break selectOption;
                case 3:
                    //withdraw
                    this.sender = selectAccount(customerId, db, false);
                    if (sender == 0) break;
                    this.amount = inputAmount(this.sender, db, false);
                    if (amount == 0) break;
                    this.timestamp = new SimpleDateFormat("yyyy.MM.ddb.HH.mm.ss").format(new Date());

                    //Make transaction
                    db.setTransaction(this);
                    subtractMoney(this.amount, this.sender, db);
                    break selectOption;
                default:
                    System.out.println("Please select a valid option!");
                    break;
            }
        }
        inputScanner.close();
    }

    private int selectAccount(int customerId, Database db, boolean isDeposit) {
        //Define variables
        int input;
        int customerAccount;

        //Let the user select a sender account
        while (true) {
            //Is the transaction a deposit?
            if (isDeposit) {
                System.out.println("On which one of the customers accounts do you want to deposit?");
            } else {
                System.out.println("Which one of the customers accounts do you want to use?");
            }

            //Get user account list
            List<Integer> customerAccountList = Customer.getAccounts(customerId, db);
            for (int i = 1; i <= customerAccountList.size(); i++) {
                System.out.printf("Account (%d): %s\n", i, customerAccountList.get(i - 1));
            }

            System.out.println("Press (0) to abort the transaction.");

            //Make sure input is an int
            while (!inputScanner.hasNextInt()) inputScanner.next();
            input = inputScanner.nextInt();

            try {
                if (input == 0) {
                    customerAccount = 0;
                    System.out.println("Transaction stopped, returning to last menu.");
                    break;
                } else {
                    customerAccount = customerAccountList.get(input - 1);
                    if(isDeposit) {
                        System.out.printf("Selected receiver account %d.\n", customerAccount);
                    } else {
                        System.out.printf("Selected sender account %d.\n", customerAccount);
                    }
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please select a valid account!");
            }
        }

        //return the given sender account
        return customerAccount;
    }

    private int inputReceiver(int customerAccount, Database db) {
        //Define input
        int input;

        //Let the user type in a receiver account
        Map<Integer, Account> AccountList = db.getAccountList();

        while (true) {
            System.out.println("To which account does the customer want to transfer?");
            System.out.println("Press (0) to abort the transaction.");

            //Make sure input is an int
            while (!inputScanner.hasNextInt()) inputScanner.next();
            input = inputScanner.nextInt();

            if (input == 0) {
                receiver = 0;
                System.out.println("Transaction stopped, returning to last menu.");
                break;
            } else if (input == customerAccount) {
                System.out.println("A transaction to the same account is not possible.");
                continue;
            } else if (AccountList.containsKey(input)) {
                receiver = input;
                System.out.printf("Selected receiver account %db.\n", receiver);
                break;
            } else {
                System.out.println("Please input a valid account!");
            }
        }

        //return the given receiver account
        return receiver;
    }

    private double inputAmount(int customerAccount, Database db, boolean isDeposit) {
        //Define input
        double input;

        //Let the user select an amount to transfer
        double customerAccountBalance = Account.getBalance(customerAccount, db);
        int UserCreditLimit = Account.getCreditLimit(customerAccount, db);

        while (true) {
            if (!isDeposit) {
                System.out.printf("Current balance of account %d: %5.2f?\n", customerAccount, customerAccountBalance);
            }
            System.out.println("Which amount should be transferred?");
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
            } else if(!isDeposit && (UserCreditLimit + customerAccountBalance - input < 0)) {
                System.out.printf("With this transaction you would exceed the customers credit limit of %d!\n", UserCreditLimit);
                continue;
            } else  {
                amount = input;
                System.out.printf("Amount to transfer: %5.2f EUR.\n", amount);
                customerAccountBalance = Account.getBalance(customerAccount, db);
                System.out.printf("The new balance of the account %d is: %5.2f\n", customerAccount, customerAccountBalance);
                break;
            }
        }

        //return the given amount
        return amount;
    }

	public static void showTransactionLog (Database db) {
        Map<Integer, Transaction> transactionList = db.getTransactionLog();
        for (Map.Entry<Integer, Transaction> entry : transactionList.entrySet())
        {
            //Get transaction key and value
            int transactionKey = entry.getKey();
            Transaction transaction = entry.getValue();

            //Get transaction object values
            int sender = transaction.sender;
            int receiver = transaction.receiver;
            double amount = transaction.amount;
            String timestamp = transaction.timestamp;

            //Print transaction details
            System.out.printf("Transaction: %d\t", transactionKey);
            System.out.printf("Sender: %d\t", sender);
            System.out.printf("Receiver: %d\t", receiver);
            System.out.printf("Amount: %5.2f EUR\t", amount);
            System.out.printf("Timestamp: %s\n", timestamp);
        }
	}

}
