package bankSimulation;

import java.util.*;

public class Database {
	
	private Map<Integer, Customer> customerList = new HashMap<>();
	private int id;
	/* private List<Account> accountList = new ArrayList<>();
	private List <String> transactionList = new ArrayList<>(); */
	
	// Constructor
	public Database() {
		id = 0;
	}
	
	
	
	public int setCustomer (Customer c) {
		id++;
		return customerList.put(id-1, c);
	}
	
	public Customer getCustomer (int customerId ) {
		return customerList.get(customerId);
	}
	
	public int removeCustomer (int customerId) {
		return customerList.remove(customerId);
	}
	
}
