package com.company;

public class Main {

    public static void main(String[] args) {
	
    	Database db = new Database();
    	
    	Customer c = new Customer();
    	
    	db.setCustomer(c);
    	
    	Customer.updatePersonDetails(0, db);
    	
    	Customer.showPersonDetails(0, db);
    	
    }
}
