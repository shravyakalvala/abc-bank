package com.abc;

import java.util.Date;
import com.abc.enums.TransactionType;

public class Transaction {
	private double amount;
	private Date transactionDate;
	private TransactionType transactionType;

	public Transaction(double amount, TransactionType transactionType) {
		this.amount = amount;
		this.transactionDate = DateProvider.getDateProviderInstance()
				.getTimeStamp();
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	
	
}
