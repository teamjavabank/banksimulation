package com.company;

import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
	
	private static Scanner inputScanner = new Scanner(System.in);
	private static boolean fromMenu = false;


    public static void main(String[] args) {
    	
	
    	Database db = new Database();
    	
    	new Customer(db, "Tom Smith", "Broadway 203, New York", "023454421992", "male", "12.03.1974", "6343");
    	new Customer(db, "Chris Coleman", "Edge Street 7, London", "0421356356", "male", "05.11.1982", "3367");
    	new Customer(db, "Anne Lain", "North Ave 22, Mumbai", "04677352345", "female", "26.09.1964", "4976");
    	
    	new StudentSavings("student", 1000, db);
    	new Current("current", 1001, db);
    	new CorporateSavings("corporate", 1002, db);
    	new Current("current", 1002, db);
    	
    	
    	
    	while (true)
    	{
    		
    		if (fromMenu)
    		{
    			inputScanner.nextLine();
    		}
    			
    		System.out.println("Please enter your id.");
    		
        	String input = inputScanner.nextLine();
        	String id = input;
        	
        	System.out.println("Please enter your PIN.");
        	input = inputScanner.nextLine();
        	String PIN = input;
    		
    		Main.menu(id, PIN, db);
    	}
    	
    }
    
    public static void menu (String id, String PIN, Database db){
    	
    	if (id.equals("1000") && PIN.equals("1234"))
    	{
    		
    		
			   	
			
			   	boolean menuActive = true;
			   	
			   	while(menuActive)
			   	{
			   		System.out.println("You have logged in as cashier " + id + ".");
		    		System.out.println("Welcome to the main menu!");
		            System.out.println("Press a number to select your desired function: Customer actions (1), Account actions (2), Transaction (3)");
		            System.out.println("To leave the menu press 0.");
		            
			   		
			   		int input = inputScanner.nextInt();
			   		
				   	switch (input) {
					   	case 0:
					   		
					   		fromMenu = true;
					   		menuActive = false;
					   		break;
					   		
					   	case 1:
					   		
					   		boolean customerMenuActive = true;
					   		
					   		System.out.println("Welcome to the Customer menu.");
					   		System.out.println("Press a number to select your desired function: Add customer (1), Remove customer (2), Update information (3)");
					   		System.out.println("To leave the menu press 0.");
					   		
					   		input = inputScanner.nextInt();
					   		
						   	switch (input) {
							   	case 0:
							   		
							   		fromMenu = true;
							   		customerMenuActive = false;
							   		break;
							   		
							   	case 1:
							   		
							   		new Customer(db);
							   		
									break;
							   	case 2:
							        
							   		System.out.println("Which customer do you want to remove?");
							   		input = inputScanner.nextInt();
							   		
							   		Customer.removeCustomer(input, db);
							   		
							        break;
							    case 3:
							    	
							    	System.out.println("Which customer's information do you want to edit?");
							    	input = inputScanner.nextInt();
							    	
							    	Customer.updatePersonDetails(input, db);
							        
							        break;
							    default:
							        System.out.println("Please select a valid option!");
							        break;
						   	}
					   		
							break;
					   	case 2:
					        
					        break;
					    case 3:
					        
					        break;
					    default:
					        System.out.println("Please select a valid option!");
					        break;
				   	}
			   	}
    		
    	} else if (id.equals("2000") && PIN.equals("1101")) {
    		
    		System.out.println("You have logged in as the manager.");
    		System.out.println("Press (1) to access the transaction logs.");
            System.out.println("To leave the menu press 0.");
            
            while (!inputScanner.hasNextInt()) inputScanner.next();
		   	int input = inputScanner.nextInt();
		
		   	boolean menuActive = true;
		   	
		   	while(menuActive)
		   	{
			   	switch (input) {
			   	case 0:
			   		
			   		fromMenu = true;
			   		menuActive = false;
			   		break;
			   		
			   	case 1:
			   		
			   		// transaction log
			   		
			   		
					break;
			   	
			    default:
			        System.out.println("Please select a valid option!");
			        break;
		   	}
		}
    		
    		
    	}

    	
    }
    
    
}