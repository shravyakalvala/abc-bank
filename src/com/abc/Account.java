package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.abc.enums.AccountType;
import com.abc.enums.TransactionType;

public class Account {

	private final AccountType accountType;

	private List<Transaction> transactions;
	
	private double balance;


	public AccountType getAccountType() {
		return accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Account(AccountType accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	
	/**
	 * Deposits into the account
	 * 
	 * @param amount
	 */
	public void deposit(double amount) {
		if (amount <= 0d) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			balance +=amount;
			transactions.add(new Transaction(amount, TransactionType.DEPOSIT));
		}
	}

	/**
	 * Withdraws from the account
	 * 
	 * @param amount
	 */
	public void withdraw(double amount) {
		if (amount <= 0d) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			balance -=amount;
			transactions
					.add(new Transaction(-amount, TransactionType.WITHDRAW));
		}
	}

	
	/**
	 * 
	 * Withdraws amount from one account and adds it to other
	 * 
	 * @param amount
	 * @param toAccount
	 */
	public void transfer(double amount , Account toAccount ) {
		if (amount <= 0d) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			balance -=amount;			
			toAccount.setBalance(toAccount.getBalance()+amount);					
			transactions
					.add(new Transaction(-amount, TransactionType.TRANSFER));
		}
	}


	/**
	 * Different accounts have interest calculated in different ways 
	 * Checking accounts have a flat rate of 0.1% 
	 * Savings accounts have a rate of 0.1% for the first $1,000 then 0.2% 
	 * Maxi-Savings accounts to have an interest rate of 5% assuming no withdrawals in the past 10 days otherwise 0.1%
	 * 	
	 * @return interestCalculated
	 */
	public double calculateInterestEarned() {
		double amount = caluculateSumOfTransactions();
		switch (accountType) {		
		case SAVINGS:
			if (amount <= 1000d) {
				return amount * 0.001d;
			} else {
				return 1 + ((amount - 1000d) * 0.002d);//
			}
		case MAXI_SAVINGS:
			if (isWithdrawInLastTenDays()) {
				return (amount * 0.001d);
			} else {
				return (amount * 0.05d);
			}

		default:
			return (amount * 0.001d);
		}
	}


	/**
	 * Calculate the sum of all transactions
	 * @return
	 */
	public double caluculateSumOfTransactions() {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.getAmount();
		return amount;
	}


	/**
	 *Check if there was withdraw in the last 10 days
	 * 
	 * @return
	 */
	private boolean isWithdrawInLastTenDays() {
		
		for (Transaction t : transactions) {
			
			int numberOfDaysSinceTransaction = daysBetween(t.getTransactionDate(),new Date());		// Get the number of days
			
			if (numberOfDaysSinceTransaction<10 && t.getTransactionType().equals(TransactionType.WITHDRAW)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 
	 * Calculate the number of days between the two dates
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

}
