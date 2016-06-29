package com.company;

public class Main {

    public static void main(String[] args) {
	
    	Database db = new Database();
    	
    	Customer c = new Customer(db);
    	
    	StudentSavings s = new StudentSavings("student", 1000, db);
    	
    	
    	c.showPersonDetails(db);
    	
    }
}
