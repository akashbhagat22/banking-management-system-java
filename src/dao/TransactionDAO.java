package dao;

import java.sql.*;
import java.util.*;
import model.Transaction;
import util.DBConnection;

public class TransactionDAO {
	
	public void saveTransaction(Transaction transaction) {

	    try {

	        Connection con = DBConnection.getConnection();

	        String sql ="INSERT INTO transactions (accountNumber, type, amount, dateTime)VALUES (?, ?, ?, ?)";

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, transaction.getAccountNumber());
	        ps.setString(2, transaction.getTransactionType());
	        ps.setDouble(3, transaction.getAmount());
	        ps.setTimestamp(4, Timestamp.valueOf(transaction.getDateTime()));

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
	
	
	
	public List<Transaction> getTransactionsByAccount(int accountNumber) {

	    List<Transaction> transactions = new ArrayList<>();

	    try {

	        Connection con = DBConnection.getConnection();

	        String sql = "SELECT * FROM transactions WHERE accountNumber = ?";

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, accountNumber);

	        ResultSet rs = ps.executeQuery();

	        while(rs.next()) {

	            Transaction transaction = new Transaction(

	                    rs.getInt("transactionID"),
	                    rs.getInt("accountNumber"),
	                    rs.getString("type"),
	                    rs.getDouble("amount"),
	                    rs.getTimestamp("dateTime").toLocalDateTime()

	            );

	            transactions.add(transaction);

	        }

	        rs.close();
	        ps.close();
	        con.close();

	    } catch(SQLException e) {

	        e.printStackTrace();

	    }

	    return transactions;
	}

}
