package model;

import java.util.*;

import exception.InsufficientBalanceException;

public abstract class Account {
	
	private int accountNumber;
	private String accountHolderName;
	private double balance;
	private String branch;
	
	private List<Transaction> transactions;
	
	protected Account(int accountNumber,String accountHolderName,double balance,String branch){
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
		this.branch = branch;
		
		this.transactions = new ArrayList<>();
	}
	
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	
	@Override
	public String toString() {
	    return "Account Number: " + accountNumber +
	           "\nAccount Holder: " + accountHolderName +
	           "\nBalance: " + balance +
	           "\nBranch: " + branch;
	}
	
	public void deposit(double amount) {
		balance += amount;
	}
	
	public void withdraw(double amount) throws InsufficientBalanceException {
		if(amount>balance) {
			throw  new InsufficientBalanceException("Insufficient Balance");
		}else {
			balance -= amount;
		}
		
		
	}

	

}
