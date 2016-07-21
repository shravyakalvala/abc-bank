package com.abc;

import org.junit.Test;

import com.abc.enums.AccountType;

import static org.junit.Assert.assertEquals;

public class BankTest {
	private static final double DOUBLE_DELTA = 1e-15;

	@Test
	public void customerSummaryTest() {
		Bank bank = new Bank();
		Customer john = new Customer("John");
		john.openAccount(new Account(AccountType.CHECKING));
		bank.addCustomer(john);

		assertEquals("Customer Summary\n - John (1 account)",
				bank.getCustomerSummary());
	}

	@Test(expected=IllegalArgumentException.class)
	public void checkingAccountZeroDepositTest() {
		Bank bank = new Bank();
		Account checkingAccount = new Account(AccountType.CHECKING);
		Customer bill = new Customer("Bill").openAccount(checkingAccount);
		bank.addCustomer(bill);

		checkingAccount.deposit(0.0);
	}
	
	@Test
	public void checkingAccountTest() {
		Bank bank = new Bank();
		Account checkingAccount = new Account(AccountType.CHECKING);
		Customer bill = new Customer("Bill").openAccount(checkingAccount);
		bank.addCustomer(bill);

		checkingAccount.deposit(100.0);

		assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void savingsAccountTest() {
		Bank bank = new Bank();
		Account savingsAccount = new Account(AccountType.SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

		savingsAccount.deposit(1500.0);

		assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}

	@Test
	public void maxiSavingsAccountTest() {
		Bank bank = new Bank();
		Account maxiSavingsAccount = new Account(AccountType.MAXI_SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(maxiSavingsAccount));

		maxiSavingsAccount.deposit(3000.0);

		assertEquals(150.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}
	
	@Test
	public void maxiSavingsAccountTransferTest() {
		Bank bank = new Bank();
		Customer Bill = new Customer("Bill");
		Account maxiSavingsAccount = new Account(AccountType.MAXI_SAVINGS);
		bank.addCustomer(Bill.openAccount(maxiSavingsAccount));
		
		Account savingsAccount = new Account(AccountType.SAVINGS);
		bank.addCustomer(Bill.openAccount(savingsAccount));

		maxiSavingsAccount.deposit(3000.0);
		savingsAccount.deposit(2000.0);
		
		maxiSavingsAccount.transfer(1000.0, savingsAccount);

		assertEquals(3000,savingsAccount.getBalance() , DOUBLE_DELTA);
		assertEquals(2000,maxiSavingsAccount.getBalance() , DOUBLE_DELTA);
	}
	
	
	@Test
	public void maxiSavingsAccountWithdrawTest() {
		Bank bank = new Bank();
		Account checkingAccount = new Account(AccountType.MAXI_SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

		checkingAccount.deposit(3000.0);
		checkingAccount.withdraw(2000.0);

		assertEquals(1000,checkingAccount.getBalance() , DOUBLE_DELTA);
		
	}
	
	@Test
	public void withdrawWithinTenDaysTest() {
		Bank bank = new Bank();
		Account checkingAccount = new Account(AccountType.MAXI_SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

		checkingAccount.deposit(3000.0);
		checkingAccount.withdraw(2000.0);
		
		assertEquals(1.0, bank.totalInterestPaid(), DOUBLE_DELTA);
	}
	
	
	@Test
	public void getFirstCustomer() {
		Bank bank = new Bank();
		Account checkingAccount = new Account(AccountType.MAXI_SAVINGS);
		bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
		bank.addCustomer(new Customer("Gill").openAccount(checkingAccount));
				
		assertEquals("Bill", bank.getFirstCustomer());
	}
	
	
}
