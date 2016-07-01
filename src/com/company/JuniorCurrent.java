package com.company;

import java.util.*;
import java.text.*;

public class JuniorCurrent extends Account {

	private int GuardianId;

	public JuniorCurrent(String newAccountType, int newCustomerId, int GuardianId, Database db) {
		super(newAccountType, newCustomerId, db);

		this.GuardianId = GuardianId;
		this.setInterestRate(1.5);
		this.setCreditLimit(1000);

		Map<Integer, Account> AccList = db.getAccountList();

		if (!AccList.containsKey(GuardianId) || !isAllowed(Customer.getDob(newCustomerId, db)))
		{



			System.out.println("The customer is not allowed to open a Junior Current Account or the Guardian does not exist.");
			this.closeAccount(db);


		}

	}



	public static boolean isAllowed(String dob) {


		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date today = Calendar.getInstance().getTime();
		Date dateob=null;
		try {
			dateob = format.parse(dob);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		double diffinMS = today.getTime() - dateob.getTime();
		double diffinYears = diffinMS/(1000*60*60*24);
		diffinYears = diffinYears/365;



		if(diffinYears >= 16){
			return false;
		}else{
			return true;
		}


	}

}
