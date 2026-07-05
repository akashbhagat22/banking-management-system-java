package main;

import java.util.*;

import model.Account;
import model.CurrentAccount;
import model.SavingsAccount;
import service.BankService;

public class Main {

	public static void main(String[] args) {
		
		BankService bankservice = new BankService();
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println();
			System.out.println("===== BANKING SYSTEM =====");
			System.out.println("1. Create Account");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Transfer Money");
			System.out.println("5. View Transaction History");
			System.out.println("6. View All Accounts");
			System.out.println("7. Delete Account");
			System.out.println("8. Exit");

			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();
			
			switch(choice) {

		    case 1:
		        System.out.println("Select Account Type");
		        System.out.println("1. Saving Account\n2. Current Account");
		        int accountType = sc.nextInt();
		        
		        System.out.println("Enter account number");
				int accountNumber = sc.nextInt();
				
				System.out.println("Enter account holder name");
				sc.nextLine();
				String accountHolderName = sc.nextLine();
				
				System.out.println("Enter Balance");
				double balance = sc.nextDouble();
				
				System.out.println("Enter Branch");
				sc.nextLine();
				String branch = sc.nextLine();
				
				Account account = null;
		        
		        if(accountType == 1) {
		        	account = new SavingsAccount(accountNumber,accountHolderName,balance,branch);
		        }
		        else if(accountType == 2) {
		        	account = new CurrentAccount(accountNumber,accountHolderName,balance,branch);
		        }
		        else {
		        	System.out.println("Invalid account type");
		        	break;
		        }
		        
		        bankservice.createAccount(account);
		        
		        break;

		    case 2:
		        System.out.println("Enter account Number");
		        int accountNumber1 = sc.nextInt();
		        System.out.println("Enter amount");
		        double amount = sc.nextDouble();
		        bankservice.deposit(accountNumber1, amount);
		        
		        break;

		    case 3:
		    	System.out.println("Enter account Number");
		        int accountNumber2 = sc.nextInt();
		        System.out.println("Enter amount");
		        double amount2 = sc.nextDouble();
		        bankservice.withdraw(accountNumber2, amount2);
		        
		        break;

		    case 4:
		        System.out.println("Enter Source Account Number");
		        int fromAccountNumber = sc.nextInt();
		        
		        System.out.println("Enter destination Account Number");
		        int toAccountNumber = sc.nextInt();
		        
		        System.out.println("Enter amount to transfer");
		        double amount4 = sc.nextDouble();
		        
		        bankservice.transferMoney(fromAccountNumber, toAccountNumber, amount4);
		        
		        break;

		    case 5:
		        System.out.println("Enter Account Number");
		        int accountNumber5 = sc.nextInt();
		        
		        bankservice.viewTransactionHistory(accountNumber5);
		        
		        break;

		    case 6:
		        bankservice.viewAllAccounts();
		        break;

		    case 7:
		        System.out.println("Enter Account Number");
		        int accountNumber7 = sc.nextInt();
		        
		        bankservice.deleteAccount(accountNumber7);
		        
		        break;

		    case 8:
		        System.out.println("Thank you for using the Banking System");
		        sc.close();
		        return;

		    default:
		        System.out.println("Invalid Choice");
		}
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
