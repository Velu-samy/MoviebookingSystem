package com.mbs.adminlogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mbs.databseconnection.Databaseconnection.DatabaseConnection;

public class Adminmanagebooking {
	static Scanner din = new Scanner (System.in);
	public static void managebooking() {
		 try {
			
			 String query = "select * from Bookinghistory" ;
			 Connection con = DatabaseConnection.getConnection();
			PreparedStatement smt = con.prepareStatement(query);
			ResultSet rs =  smt.executeQuery();
			 
			while(rs.next()) {
				   System.out.println(" movieid : " + rs.getInt(1));
                   System.out.println(" movie name: " + rs.getString(2));
                   System.out.println(" username: " + rs.getString(3));
                   System.out.println(" bookedticket: " + rs.getInt(4));
                   System.out.println(" ticketcost: " + rs.getInt(5));
                   System.out.println(" secretcode: " + rs.getInt(6));
                   System.out.println();
			 }
			 }
			 catch (SQLException e){
				 System.out.println("table does not connect" + e.getMessage());
			 }

	}
	
	public static void managemovie() {
		try {
		    String query = "SELECT mb.id, mb.moviename, mb.category, mb.ratings, mb.cost, md.availabletickets, md.showtimes " +
		                   "FROM moviebooking mb " +
		                   "INNER JOIN moviedetails md ON mb.id = md.id";
		    Connection con = DatabaseConnection.getConnection();
		    PreparedStatement smt = con.prepareStatement(query);
		    ResultSet rs = smt.executeQuery();

		    // Print table header once
		    System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s %-20s%n", "Movie ID", "Movie Name", "Category", "Ratings", "Cost", "Seats Available", "Showtimes");

		    while (rs.next()) {
		        System.out.printf("%-10d %-20s %-20s %-20.2f %-20d %-20d %-20s%n",
		                          rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6), rs.getTime(7) != null ? rs.getTime(7).toString() : "N/A");
		        System.out.println();
		    }
		} catch (SQLException e) {
		    System.out.println("table does not connect: " + e.getMessage());
		}
	}
	
	public static void managemoviedetails() {
		System.out.println("1.Add movie");
		System.out.println("2.Update movie");
		Scanner din = new Scanner (System.in);
	    int ans = din.nextInt();
	    switch(ans){
	    case 1:addmovie();
	    break;
	    case 2:updatemovie();
	    break;
	    default:
	    	System.out.println("invalid please Enter number between 1-2");
	    }
		
		
		
	}
	
	public static void addmovie()
	{
		System.out.println("\nAdding the movies ");
		System.out.println("movie id: ");
		int id = din.nextInt();		
		System.out.println("movie Name:");
		String mn = din.nextLine();
		System.out.println("movie category:");
		String moviecate = din.next();
		System.out.println("movie ratings:");
		double mr = din.nextDouble();
		System.out.println("movie language:");
		String ml = din.next();
		System.out.println("ticket price:");
		int tp = din.nextInt();
		System.out.println("Movie seats:");
		int ms = din.nextInt();
		System.out.println("Enter showtime (HH:MM):");
        String showtimeInput = din.next() + ":00"; 
	
        
        try {
        
              
                PreparedStatement smt1 = null;
                PreparedStatement smt2 = null;
          Connection  con = DatabaseConnection.getConnection();
          // Disable auto-commit for transaction

                String query1 = "INSERT INTO moviebooking (id, moviename, category, ratings, cost, movielanguage) VALUES (?, ?, ?, ?, ?, ?)";
                String query2 = "INSERT INTO moviedetails (id, moviename, showtimes, cost, availabletickets) VALUES (?, ?, ?, ?,?)";

                // Inserting into moviebooking
                smt1 = con.prepareStatement(query1);
                smt1.setInt(1, id);
                smt1.setString(2,mn);
                smt1.setString(3,moviecate);
                smt1.setDouble(4,mr);
                smt1.setInt(5,tp);
                smt1.setString(6,ml);

                // Inserting into moviedetails
                smt2 = con.prepareStatement(query2);
                smt2.setInt(1,id);
                smt2.setString(2,mn);
                smt2.setString(3,showtimeInput);
                smt2.setInt(4,tp);
                smt2.setInt(5,ms);

                // Execute the updates
                int rowsAffected1 = smt1.executeUpdate();
                int rowsAffected2 = smt2.executeUpdate();

               if(rowsAffected1 > 0 && rowsAffected2 >0 ) {
            	   System.out.println("movie added successfully");
               }
               else {
            	   System.out.println("movie does not added");
               }

              
            }
        catch(SQLException e) {
        	System.out.println(e);
  
        	
        }
	}
	
	
	public static void updatemovie() {
		
		
		System.out.println("updating the movie");
		System.out.println("movie id: ");
		int id = din.nextInt();     
		System.out.println("movie Name:");
		String mn = din.next();
		System.out.println("movie category:");
		String mc = din.next();
		System.out.println("movie ratings:");
		double mr = din.nextDouble();
		System.out.println("movie language:");
		String ml = din.next();
		System.out.println("ticket price:");
		int tp = din.nextInt();
		System.out.println("Movie seats:");
		int ms = din.nextInt();
		System.out.println("Enter showtime (HH:MM):");
		String showtimeInput = din.next(); 
		String formattedShowtime = showtimeInput + ":00";
		try {
		
		    PreparedStatement smt1 = null;
		    PreparedStatement smt2 = null;
		    Connection con = DatabaseConnection.getConnection();
		    
		    String query1 = "UPDATE moviebooking SET moviename = ?, category = ?, ratings = ?, cost = ?, movielanguage = ? WHERE id = ?";
		    String query2 = "UPDATE moviedetails SET moviename = ?, showtimes = ?, cost = ?, availabletickets = ? WHERE id = ?";
		    
		    // Updating moviebooking
		    smt1 = con.prepareStatement(query1);
		    smt1.setString(1, mn);
		    smt1.setString(2, mc);
		    smt1.setDouble(3, mr);
		    smt1.setInt(4, tp);
		    smt1.setString(5, ml);
		    smt1.setInt(6, id);
		    
		    // Updating moviedetails
		    smt2 = con.prepareStatement(query2);
		    smt2.setString(1, mn);
		    smt2.setString(2, formattedShowtime);
		    smt2.setInt(3, tp);
		    smt2.setInt(4, ms);
		    smt2.setInt(5, id);
		    
		    // Execute the updates
		    int rows1 = smt1.executeUpdate();
		    int rows2 = smt2.executeUpdate();
		    
		    if (rows1 > 0 && rows2 > 0) {
		        System.out.println("\nMovie updated successfully.");
		    } else {
		        System.out.println("\nMovie update failed.");
		    }
		} catch (SQLException e) {
		    System.out.println(e);
		}
	
	}
	
	
		
	
	
}