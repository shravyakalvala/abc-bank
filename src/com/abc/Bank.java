package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	private List<Customer> customers;

	public Bank() {
		customers = new ArrayList<Customer>();
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

		public String getCustomerSummary() {
		String summary = "Customer Summary";
		for (Customer c : customers)
			summary += "\n - " + c.getName() + " ("
					+ format(c.getNumberOfAccounts(), "account") + ")";
		return summary;
	}

	// Make sure correct plural of word is created based on the number passed
	// in:
	// If number passed in is 1 just return the word otherwise add an 's' at the
	// end
	private String format(int number, String word) {
		return number + " " + (number == 1 ? word : word + "s");
	}

	
	/**
	 * Get total interest paid per annum
	 * 
	 * @return
	 */
	public double totalInterestPaid() {
		double total = 0;
		for (Customer c : customers)
			total += c.getTotalInterestEarned();
		return total;
	}

	
	public String getFirstCustomer() {
		
		if(!customers.isEmpty()){
			return customers.get(0).getName();
		}else{
			return "ERROR";
		}
		
	
	}
}
