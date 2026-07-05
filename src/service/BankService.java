package service;

import java.util.*;

import dao.AccountDAO;
import dao.TransactionDAO;
import exception.InsufficientBalanceException;
import model.Account;
import model.Transaction;
import java.time.*;

public class BankService {

	private Map<Integer,Account> accounts;
	
	private AccountDAO accountDAO;
	private TransactionDAO transactionDAO;
	
	public BankService() {

	    accounts = new HashMap<>();
	    accountDAO = new AccountDAO();
	    transactionDAO = new TransactionDAO();
	    

	    List<Account> accountList = accountDAO.getAllAccounts();

	    for (Account account : accountList) {

	        accounts.put(account.getAccountNumber(), account);

	    }
	}
	
	public void createAccount(Account account) {
		if(accounts.containsKey(account.getAccountNumber())) {
			System.out.println("Account already exist");
		}
		else {
			accounts.put(account.getAccountNumber(), account);
			accountDAO.saveAccount(account);
			System.out.println("Account created Successfully");
		}
		
	}
	
	
	public Account findAccount(int accountNumber) {
		if(accounts.containsKey(accountNumber)) {
			return accounts.get(accountNumber);
		}
		else {
			System.out.println("There is no account associated with "+accountNumber);
			return null;
		}
	}
	
	
	public void deposit(int accountNumber, double amount) {
		Account account = findAccount(accountNumber);
		if(account != null) {
			account.deposit(amount);
			accountDAO.updateAccount(account);
			
			Transaction t = new Transaction(0,accountNumber,"DEPOSIT",amount,LocalDateTime.now());
			account.getTransactions().add(t);
			transactionDAO.saveTransaction(t); 
			
			System.out.println("Amount deposited successfully");
		}
	}
	
	
	public void withdraw(int accountNumber, double amount) {
		try {
			Account account = findAccount(accountNumber);
			if(account != null) {
				account.withdraw(amount);
				accountDAO.updateAccount(account);
				
				Transaction t = new Transaction(0,accountNumber,"WITHDRAW",amount,LocalDateTime.now());
				account.getTransactions().add(t);
				transactionDAO.saveTransaction(t);
				
				System.out.println("Amount withdrawn successfully");
			}
		}catch(InsufficientBalanceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void transferMoney(int fromAccountNumber, int toAccountNumber, double amount) {
		try {
			Account source = findAccount(fromAccountNumber);
			Account destination = findAccount(toAccountNumber);
			
			if(source != null && destination != null) {
				if(fromAccountNumber != toAccountNumber) {
					source.withdraw(amount);
					Transaction t = new Transaction(0,fromAccountNumber,"TRANSFER_OUT",amount,LocalDateTime.now());
					source.getTransactions().add(t);
					
					transactionDAO.saveTransaction(t);
					
					destination.deposit(amount);
					Transaction t1 = new Transaction(0,toAccountNumber,"TRANSFER_IN",amount,LocalDateTime.now());
					destination.getTransactions().add(t1);
					
					transactionDAO.saveTransaction(t1);
					
					accountDAO.updateAccount(source);
					accountDAO.updateAccount(destination);
					
					System.out.println("Amount transferred successfully");
				}else {
					System.out.println("Cannot transfer in same account");
				}
			}
			
			
		}catch(InsufficientBalanceException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public void viewTransactionHistory(int accountNumber) {

	    List<Transaction> transactions =
	            transactionDAO.getTransactionsByAccount(accountNumber);

	    if(transactions.isEmpty()) {

	        System.out.println("No Transactions Found");

	    } else {

	        for(Transaction transaction : transactions) {

	            System.out.println(transaction);
	            System.out.println("----------------------");

	        }

	    }
	}
	
	
	public void viewAllAccounts() {
		if(accounts.isEmpty()) {
			System.out.println("No account exist.");
		}
		else {
			for(Account account : accounts.values()) {
				System.out.println(account);
				System.out.println("---------------");
			}
		}
	}
	
	
	public void deleteAccount(int accountNumber) {
		Account account = findAccount(accountNumber);
		if(account != null) {
			accounts.remove(accountNumber);
			accountDAO.deleteAccount(accountNumber);
			System.out.println("Successfully account deleted.");
		}
	}

}


















