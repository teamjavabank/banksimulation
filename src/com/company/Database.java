package bankSimulation;

import java.util.*;

public class Database {
	
	private Map<Integer, Customer> customerList = new HashMap<>();
	private int cid;
	private int aid;
	/* private List<Account> accountList = new ArrayList<>();
	private List <String> transactionList = new ArrayList<>(); */
	
	// Constructor
	public Database() {
		cid = 0;
		aid = 0;
		tid = 0;
	}
	
	
	
	public Customer setCustomer (Customer c) {
		cid++;
		return customerList.put(cid-1, c);
	}
	
	public Customer getCustomer (int customerId ) {
		return customerList.get(customerId);
	}
	
	public Customer removeCustomer (int customerId) {
		return customerList.remove(customerId);
	}
	
}
