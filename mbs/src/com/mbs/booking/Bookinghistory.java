package com.mbs.booking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mbs.databseconnection.Databaseconnection.DatabaseConnection;

public class Bookinghistory {

	 public static void bookinghistory(String username, String upassword){
		 System.out.println("bookign history");
		 
		 try {
		
		 String query = "select * from Bookinghistory where username = ?";
		 Connection con = DatabaseConnection.getConnection();
		PreparedStatement smt = con.prepareStatement(query);
		smt.setString(1, username);
		ResultSet rs =  smt.executeQuery();
		 
		while(rs.next()) {
			 System.out.println("");
			 System.out.println("movieid : " + rs.getInt(1));
		     System.out.println("movie name: " + rs.getString(2));
		     System.out.println("username: " + rs.getString(3));
		     System.out.println("bookedticket: " + rs.getInt(4));
		     System.out.println("ticketcost: " +rs.getInt(5));
		 }
		 }
		 catch (SQLException e){
			 System.out.println("table does not connect" + e.getMessage());
		 }
		 
	 }
}
