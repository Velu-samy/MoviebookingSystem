package com.mbs.booking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Random;
import java.util.Scanner;
import com.mbs.adminlogin.Pdfgen;
import com.mbs.databseconnection.Databaseconnection.DatabaseConnection;



public class Booking {

             public static void	booking(String username){
            	 
            	 System.out.println("Booking a ticket");
            	 System.out.println("We have several movies that match your mood! :)");
            	 System.out.println("1.Love Failure");
      		     System.out.println("2.Horror");
      		     System.out.println("3.love");
      		     System.out.println("4.action");
      		     
      		     System.out.println("Enter the action you wish to perform:");
      		    Scanner bin = new Scanner (System.in);
      		    int choice = bin.nextInt();
      		    String category = null;
      		    switch(choice) {
      		    case 1:
      		    	category = "love failure";
      		    	break;
      		    case 2:
      		    	category = "horror";
      		    	break;
      		    case 3:
      		    	category  = "love";
      		    	break;
      		    case 4:
      		    	category = "action";
      		    	break;
      		    }
      		    
      		    try {
      		 
      		    String query = "select * from moviebooking where category = ?";
      		    
      		    Connection con = DatabaseConnection.getConnection();
      		    PreparedStatement smt = con.prepareStatement(query);
      		    smt.setString(1,category);
      		    ResultSet rs  = smt.executeQuery();
      		    
      		    if(rs.next()) {
      		    	System.out.println("Here are the movies you selected:" + " "+ category);
      		    	System.out.println("the movies are : ");
      		    	System.out.println("----------------------------------------------------------------------");
      		        System.out.printf("%-15s%-20s%-20s%-20s%n", "movie id", "movie name", "movieticketprice", "movie rating /5");
                    System.out.println("----------------------------------------------------------------------");
      		        do {
                      System.out.printf("%-15d%-20s%-20d%-20s%n",
                                        rs.getInt("id"), rs.getString("moviename"), rs.getInt("cost"), rs.getString("ratings"));
                  } while (rs.next());
      		   System.out.println("Select the movie ID you want:");
      		   int movieselected = bin.nextInt();
      		   
      		   String checkmoviedetails = "select * from moviedetails where id = ?";
               smt = con.prepareStatement(checkmoviedetails);
     		   smt.setInt(1,movieselected);
     		   ResultSet tocheck = smt.executeQuery(); 
     		   
     		   if(tocheck.next())
     		   {
     			  System.out.println("--------------------------------------------------------------------------------------------");
     			  System.out.printf("%-10s %-20s %-20s %-20s %-20s%n", "Movie ID", "Movie Name", "Showtimes", "Ticket Price", "Seats Available");
        	      System.out.println("--------------------------------------------------------------------------------------------");
     			  System.out.printf("%-10d %-20s %-20s %-20d %-20d%n",
     			                  tocheck.getInt("id"), tocheck.getString("moviename"), tocheck.getString("showtimes"), tocheck.getInt("cost"), tocheck.getInt("availabletickets"));

     			 System.out.println("How many tickets do you want:");

     			 
     			  int bookedtickets = bin.nextInt();
     			 System.out.println("-----------------------------");
         		  System.out.println("confirm the Ticket! ");
         		  int tocheckavailabletickets = tocheck.getInt("availabletickets");
        		  int costofticket = tocheck.getInt("cost");
        		  Time showtime = tocheck.getTime("showtimes");
        		  int movieid = tocheck.getInt("id"); 
        		  String moviename = tocheck.getString("moviename");
        		  int totalticketprice = bookedtickets * costofticket;
     			  System.out.println("Ticketprice  " + totalticketprice);
     			  System.out.println("Showtime: " + showtime.toString());
     			  System.out.println("Ticketes you booked" + " " + bookedtickets );
                  System.out.println("Confirm the ticket y : ");
                  String confirm = bin.next();
                  
                  
     			  
         		 if(confirm.equalsIgnoreCase("y"))
         		 {
         			if(tocheckavailabletickets >= bookedtickets) {
            			 
            			 String updatemovieticket = "update moviedetails set  availabletickets = availabletickets  - ? where id = ?" ;
            		   smt = con.prepareStatement(updatemovieticket);
            
            		   smt.setInt(1,bookedtickets);
            		   smt.setInt(2,movieselected);
            		   int ticketupdate = smt.executeUpdate();
            		  if(ticketupdate>0) 
            		  {
            			  Random random = new Random();
          		        int randomCode = 1000 + random.nextInt(9000); // Generates a 4-digit number between 1000 and 9999
            			  String updatetable =  "insert into bookinghistory value (?,?,?,?,?,?)";
            			  smt  = con.prepareStatement(updatetable);
            			  smt.setInt(1,movieid );
            			  smt.setString(2, moviename);
            			  smt.setString(3,username);
            			  smt.setInt(4,bookedtickets);
            			  smt.setInt(5,totalticketprice);
            			  smt.setInt(6,randomCode);
            			  int p = smt.executeUpdate();
            			 
            
            			  if(p>0) {
            			
            				  System.out.println("Ticket booked successfully");
                              System.out.println("\n-------------------------------------");          				  
            				  
            				  System.out.println("            Ticket                    ");
  
            				  System.out.println("-------------------------------------");
            				  System.out.println("Moveiname:" + tocheck.getString("moviename"));
            				  System.out.println("BookedSeats:" + bookedtickets);
            				  System.out.println("Showtime:" + showtime.toString());
            				  System.out.println("Secreatcode:" + randomCode);
            				  System.out.println("--------------------------------- ----");
            				  String mn = tocheck.getString("moviename");
            				  String st = showtime.toString();
            				  Pdfgen.gen(mn,bookedtickets,st,randomCode);
            			  }
            			  else {
            				  System.out.println("Ticket booking canceld");
            			  }
            
            			  
            		  }
            		  else {
            			  System.out.println("server busyy");
            		  }
            		   
            		 }  
            		 else {
            		 
            			 System.out.println("insufficient tickets");
            
            		 }
         			 
         		 }
         		 else {
         			 System.out.println("Ticket cancled");
         		 }
      			 
     			 
     			 
     		   }
     		   else {
     			   System.out.println("No select movies");
     		   }
      		   
    
      		    }
      		
      		    else {
      		    	System.out.println("there is no movies ");
      		    }
      		    
      		    
      		    }
      		    catch(SQLException e) {
      		    	System.out.println(e);
      		    	
      		    }
      		
	}

}
