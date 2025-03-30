package com.mbs.adminlogin;
import com.mbs.booking.Moviereviews;
import com.mbs.databseconnection.Databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Adminlogin {
    public static void checkadmin() {
        Scanner din = new Scanner(System.in);
        System.out.println("Enter the AdminName:");
        String name = din.next();
        System.out.print("Enter the Password:");
        String pass = din.next();

        try {
          
            String query = "SELECT count(*) FROM adminlogin WHERE aname=? AND password=?";
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement smt = con.prepareStatement(query);

            smt.setString(1, name);
            smt.setString(2, pass);

            ResultSet resultSet = smt.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.out.println("Welcome Admin: " + name);

                int choice;
                do {
                	System.out.println(" ");
                    System.out.println("1. Booking Details");
                    System.out.println("2. Show  Movies");
                    System.out.println("3. Manage Movie Details");
                    System.out.println("4. view reviews");
                    System.out.println("5. Reset password");
                    System.out.println("6. Logout");
                    System.out.println("Choose what you want:");

                    choice = din.nextInt();

                    switch (choice) {
                        case 1:
                            Adminmanagebooking.managebooking();
                            break;
                        case 2:
                        	Adminmanagebooking.managemovie();
                            // Add functionality for managing movies
                            break;
                        case 3:
                        	Adminmanagebooking.managemoviedetails();
                            // Add functionality for managing movie details
                            break;
                        case 4:
                           Moviereviews.moviereviews();
                            break;
                            
                        case 5:
                        	Resetpassword.resetpassword(name);
                            break;
                        case 6:
                            System.out.println("Logging out...");
                            break;    
                        default:
                            System.out.println("Invalid choice, please try again.");
                            break;
                    }
                } while (choice !=6);
            } else {
                System.out.println("invalid adminname Or password");
            }
        } catch (SQLException e) {
            System.out.println(e  + "please Enter number");
        }
    }
}
