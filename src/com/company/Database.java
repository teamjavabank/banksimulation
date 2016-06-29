package com.company;

import java.util.*;

public class Database {

	//Create Maps
	private Map<Integer, Customer> customerList = new HashMap<>();
	private Map<Integer, Account> accountList = new HashMap<>();
	private Map<Integer, Transaction> transactionList = new HashMap<>();

	//Variables for IDs
	private int cid, aid, tid;
	
	//Constructor
	public Database() {
		cid = 1000;
		aid = 1000;
		tid = 1000;
	}

	/* --- Customer --- */

	//setCustomer
	public Customer setCustomer (Customer c) {
		cid++;
		return customerList.put(cid-1, c);
	}

	//getCustomer
	public Customer getCustomer (int customerId ) {
		return customerList.get(customerId);
	}

	//removeCustomer
	public Customer removeCustomer (int customerId) {
		return customerList.remove(customerId);
	}

	/* --- Account --- */

	//setAccount
	public int setAccount(Account a) {
		aid++;
		accountList.put(aid-1, a);
		return aid-1;
	}

	//getAccount
	public Account getAccount (int accountId ) {
		return accountList.get(accountId);
	}

	//removeAccount
	public Account removeAccount (int accountId) {
		return accountList.remove(accountId);
	}

	//getAccountList
	public Map<Integer, Account> getAccountList () {
		return accountList;
	}

	/* --- Transaction --- */

	//setTransaction
	public Transaction setTransaction(Transaction t) {
		tid++;
		return transactionList.put(tid-1, t);
	}

	//getTransaction
	public Transaction getTransaction (int transactionId ) {
		return transactionList.get(transactionId);
	}

	//getTransactionLog
	public Map<Integer, Transaction> getTransactionLog () {
		return transactionList;
	}
}
