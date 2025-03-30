package com.mbs.userlogin;
import com.mbs.booking.Moviereviews;
import com.mbs.booking.*;
import com.mbs.booking.Writereview;
import com.mbs.databseconnection.Databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Userlogin {
    public static String username;
    public static String upassword;
    static Scanner din = new Scanner(System.in);
    public static void userlogin() {
     
        System.out.println("User login");
        System.out.println("");
        System.out.println("Enter the username:");
        username = din.next();
        System.out.println("Enter the password");
        String password = din.next();

        try {
           
            String query = "SELECT count(*) FROM userregi WHERE uname=? AND upassword=?";
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement smt = con.prepareStatement(query);
            smt.setString(1, username);
            smt.setString(2, password);

            ResultSet resultset = smt.executeQuery();

            if (resultset.next() && resultset.getInt(1) > 0) {
                System.out.println("Welcomeback " + username);

                int choice;
                do {
                	System.out.println(" ");
                    System.out.println("1. Book a Ticket");
                    System.out.println("2. See the Booking History");
                    System.out.println("3. write Review");
                    System.out.println("4. view Reviews");
                    System.out.println("5. Reset the password");
                    System.out.println("6. Logout");
                    System.out.println("Enter the action you wish to perform: ");
                    choice = din.nextInt();

                    switch (choice) {
                        case 1:
                            Booking.booking(username);
                            break;
                        case 2:
                            Bookinghistory.bookinghistory(username, upassword);
                            break;
                        case 3:
                           Writereview.moviereviews();
                            break;
                        case 4:
                        	Moviereviews.moviereviews();
                        	break;
                        case 5:
                        	Userlogin.resetpass(username);
                        	break;
                        case 6:
                        	System.out.println("logouting....");
                        	break;
                        default:
                            System.out.println("Invalid choice, please try again.");
                            break;
                    }
                } while (choice != 6);
            } else {
                System.out.println("Invalid username or password");
            }
        } catch (SQLException e) {
            System.out.println("The error occurred in SQL: " + e);
        }
    }
    
    
    public static void resetpass(String un) {
    	 
    	  System.out.println("Changeing the password");
          System.out.println("");
          System.out.println("Enter the Preveious password :");
          String prevpassword = din.next();
          System.out.println("Enter the New  password");
          String newpassword = din.next();
          
          try {
	           
	            String query = "Update userregi set upassword = ? where uname= ? And upassword = ? ";
	            Connection con = DatabaseConnection.getConnection();
	            PreparedStatement smt = con.prepareStatement(query);
	            
	            smt.setString(1, newpassword);
	            smt.setString(2, un);
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
