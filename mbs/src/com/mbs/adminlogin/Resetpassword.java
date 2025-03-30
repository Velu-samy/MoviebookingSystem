package com.mbs.adminlogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.mbs.databseconnection.Databaseconnection.DatabaseConnection;

public class Resetpassword {
	public static void resetpassword(String adminname) {
		Scanner din = new Scanner(System.in);
		System.out.println("reseting password");
		System.out.println("Enter the previous Password");
		String prevpassword=din.next();	
		System.out.println("Enter the  new password");
		String newpassword=din.next();		
		
			 try {
		            
		            String query = "Update adminlogin set password = ? where aname= ? And password = ? ";
		            Connection con = DatabaseConnection.getConnection();
		            PreparedStatement smt = con.prepareStatement(query);
		            
		            smt.setString(1, newpassword);
		            smt.setString(2, adminname);
		            smt.setString(3, prevpassword);
		            
		           int rowaff = smt.executeUpdate();
		           if(rowaff>0) {
		        	   System.out.println("password reset successfully");
		           }
		           else {
		        	   System.out.println("password reset faild");
		           }
		            
		}
			 catch(SQLException e) {
				 System.out.println(e);
				 
			 }
		
	}

}
