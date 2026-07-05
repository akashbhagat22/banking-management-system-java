package main;

import java.sql.Connection;

import util.DBConnection;

public class TestConnection {

    public static void main(String[] args) {

        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	
            Connection con =
                    DBConnection.getConnection();

            System.out.println(
                    "Connected Successfully!"
            );

            con.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}