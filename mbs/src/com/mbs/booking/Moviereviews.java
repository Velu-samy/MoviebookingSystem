package com.mbs.booking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mbs.databseconnection.Databaseconnection.DatabaseConnection;

public class Moviereviews {

	 public static void moviereviews(){
		 System.out.println("Movie Feedback");
		 try {
		
		 String query = "select * from reviews";
		 Connection con = DatabaseConnection.getConnection();
	     PreparedStatement smt = con.prepareStatement(query);
		 ResultSet rs =  smt.executeQuery();
		 
		 while(rs.next()) {
			 System.out.println();
		     System.out.println("movie name: " + rs.getString("moviename"));
		     System.out.println("username: " + rs.getString("username"));
		     System.out.println("showtime: " + rs.getTime("showtime").toString());
		     System.out.println("feedback: " +rs.getString("moviefeedback"));
		 }
		 }
		 catch (SQLException e){
			 System.out.println("table does not connect" + e.getMessage());
		 }
		 
	 }
}
