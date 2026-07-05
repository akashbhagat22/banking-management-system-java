package dao;

import java.sql.*;
import java.util.*;

import model.Account;
import model.CurrentAccount;
import model.SavingsAccount;
import util.DBConnection;

public class AccountDAO {
	
	public void saveAccount(Account account) {
		
		try {

	        Connection con = DBConnection.getConnection();
	        
//	         "INSERT INTO accounts VALUES(?,?,?,?,?)";
	        
	         String sql = "INSERT INTO accounts (accountNumber, accountHolderName, balance, branch, accountType)VALUES (?, ?, ?, ?, ?)";

	        PreparedStatement ps = con.prepareStatement(sql);
	        
	        ps.setInt(1, account.getAccountNumber());
	        ps.setString(2, account.getAccountHolderName());
	        ps.setDouble(3, account.getBalance());
	        ps.setString(4, account.getBranch());
	        
	        String accountType;
	        
	        if(account instanceof SavingsAccount) {
	            accountType = "SAVINGS";
	        } else {
	            accountType = "CURRENT";
	        }
	        
	        ps.setString(5, accountType);
	        
	        int rows = ps.executeUpdate();
	        
	        if (rows > 0) {
			    System.out.println("Value Inserted Successfully!");
			} else {
			    System.out.println("Insertion Failed!");
			}
	        
	        ps.close();
	        con.close();
	        
	    } catch (SQLException e) {

	        e.printStackTrace();
	    }

	}
	
	
	
	public Account findAccount(int accountNumber) {

	    Account account = null;

	    try {

	        Connection con = DBConnection.getConnection();

	        String sql = "SELECT * FROM accounts WHERE accountNumber = ?";

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, accountNumber);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next()) {

	            String accountType = rs.getString("accountType");
	            String accountHolderName = rs.getString("accountHolderName");
	            double balance = rs.getDouble("balance");
	            String branch = rs.getString("branch");

	            if(accountType.equals("SAVINGS")) {

	                account = new SavingsAccount(
	                        accountNumber,
	                        accountHolderName,
	                        balance,
	                        branch);

	            } else {

	                account = new CurrentAccount(
	                        accountNumber,
	                        accountHolderName,
	                        balance,
	                        branch);
	            }
	        }

	        rs.close();
	        ps.close();
	        con.close();

	    } catch(SQLException e) {

	        e.printStackTrace();
	    }

	    return account;
	}
	
	
	
	
	
	public void updateAccount(Account account) {

	    try {

	        Connection con = DBConnection.getConnection();

	        String sql = "UPDATE accounts SET balance = ? WHERE accountNumber = ?";

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setDouble(1, account.getBalance());
	        ps.setInt(2, account.getAccountNumber());

	        int rows = ps.executeUpdate();

	        if(rows > 0) {
	            System.out.println("Account Updated Successfully!");
	        } else {
	            System.out.println("Account Not Found!");
	        }

	        ps.close();
	        con.close();

	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	
	
	public void deleteAccount(int accountNumber) {

	    try {

	        Connection con = DBConnection.getConnection();

	        String sql = "DELETE FROM accounts WHERE accountNumber = ?";

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, accountNumber);

	        int rows = ps.executeUpdate();

	        if(rows > 0) {
	            System.out.println("Account Deleted Successfully!");
	        } else {
	            System.out.println("Account Not Found!");
	        }

	        ps.close();
	        con.close();

	    } catch(SQLException e) {

	        e.printStackTrace();
	    }
	}
	
	
	
	public List<Account> getAllAccounts() {

	    List<Account> accounts = new ArrayList<>();

	    try {

	        Connection con = DBConnection.getConnection();

	        String sql = "SELECT * FROM accounts";

	        PreparedStatement ps = con.prepareStatement(sql);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            int accountNumber = rs.getInt("accountNumber");
	            String accountHolderName = rs.getString("accountHolderName");
	            double balance = rs.getDouble("balance");
	            String branch = rs.getString("branch");
	            String accountType = rs.getString("accountType");

	            Account account;

	            if (accountType.equals("SAVINGS")) {

	                account = new SavingsAccount(
	                        accountNumber,
	                        accountHolderName,
	                        balance,
	                        branch);

	            } else {

	                account = new CurrentAccount(
	                        accountNumber,
	                        accountHolderName,
	                        balance,
	                        branch);
	            }

	            accounts.add(account);
	        }

	        rs.close();
	        ps.close();
	        con.close();

	    } catch (SQLException e) {

	        e.printStackTrace();
	    }

	    return accounts;
	}

}
