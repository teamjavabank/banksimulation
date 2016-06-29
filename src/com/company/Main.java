package com.company;

public class Main {

    public static void main(String[] args) {
	
    	Database db = new Database();
    	
    	Customer c = new Customer();
    	
    	db.setCustomer(c);
    	
    	StudentSavings s = new StudentSavings("student", 0);
    	
    	s.openAccount(db);
    	
    	c.showPersonDetails(0, db);
    	
    }
}
