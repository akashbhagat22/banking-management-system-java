package model;

import java.time.*;

public class Transaction {

	private int transactionId;
	private int accountNumber;
	private String transactionType;
	private double amount;
	private LocalDateTime dateTime;
	
	
	public Transaction(int transactionId,int accountNumber,String transactionType,double amount,LocalDateTime dateTime) {
		this.transactionId = transactionId;
		this.accountNumber = accountNumber;
		this.transactionType = transactionType;
		this.amount = amount;
		this.dateTime = dateTime;
	}
	
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	
	@Override
	public String toString() {
	    return "Transaction ID: " + transactionId +
	           "\nAccount Number: " + accountNumber +
	           "\nType: " + transactionType +
	           "\nAmount: " + amount +
	           "\nDate: " + dateTime;
	}

}
