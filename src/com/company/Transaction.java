package com.company;

import java.util.Scanner;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Transaction {

	private int sender;
	private int receiver;
	private double amount;
	private String timestamp;

	public Transaction (int User)
	{

		this.sender = newSender;
		this.receiver = newReceiver;
		this.amount = newAmount;
		this.timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	}

	public static void addNewTransaction (Transaction t, Database d)
	{

		// ID is generated in database
		d.setTransaction(t);

	}

	public static void transferTransaction (int customerId, Database d)
	{



	}

	public static void depositTransaction (int customerId, Database d)
	{



	}

	public static void withdrawTransaction (int transactionId, Database d)
	{


	}

	public static void showTransactionLog (int customerId, Database d)
	{



	}

}
