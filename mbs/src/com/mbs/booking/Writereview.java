package com.mbs.booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.mbs.databseconnection.Databaseconnection.DatabaseConnection;

public class Writereview {

    static Scanner din = new Scanner(System.in);

    public static void moviereviews() {

        System.out.println("moviename:" );
        String mname = din.nextLine(); // Use nextLine() to read the entire line
        System.out.println("username:");
        String uname = din.nextLine(); // Use nextLine() to read the entire line
        System.out.println("Enter showtime (HH:MM):");
        String showtimeInput = din.nextLine(); // Use nextLine() to read the entire line
        String formattedShowtime = showtimeInput + ":00";
        System.out.println("feedback:" );
        String fb = din.nextLine(); // Use nextLine() to read the entire line
        try {

            String query = "insert into reviews values(?,?,?,?)";
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement smt = con.prepareStatement(query);
            smt.setString(1, mname);
            smt.setString(2, uname);
            smt.setString(3, formattedShowtime);
            smt.setString(4, fb);
            int rows = smt.executeUpdate();

            if (rows > 0) {
                System.out.println("Review updated successfully.");
            } else {
                System.out.println("Failed to update the review.");
            }

        } catch (SQLException e) {
            System.out.println("table does not connect: " + e.getMessage());
        }
    }
}
