package bankSimulation;

import java.util.*;

public class Database {
	
	private Map<Integer, Customer> customerList = new HashMap<>();
	private Map<Integer, Account> accountList = new HashMap<>();
	private int cid, aid, tid;
	
	// Constructor
	public Database() {
		cid = 0;
		aid = 0;
		tid = 0;
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
	public Account setAccount(Account a) {
		aid++;
		return accountList.put(aid-1, a);
	}

	//getAccount
	public Account getAccount (int accountId ) {
		return customerList.get(accountId);
	}

	//removeAccount
	public Account removeAccount (int accountId) {
		return customerList.remove(accountId);
	}

	//Transaction
	
}
