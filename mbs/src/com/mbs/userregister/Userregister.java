package com.mbs.userregister;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.mbs.databseconnection.Databaseconnection.DatabaseConnection;

import mbs.main.*;
public class Userregister {

    public	static   void createuser() {
		// TODO Auto-generated method stub
    	Scanner din = new Scanner (System.in);
    	System.out.println("");
    	System.out.println("Creating Useraccount");
    	System.out.println("Enter the Username:");
    	String uname = din.next();
    	System.out.println("Enter the Password:");
    	String upassword = din.next();
    	System.out.println("Enter The Mail");
    	String umail = din.next();
    	System.out.println("Enter The Phonenumber");
    	String unumber = din.next();
        	
    	try {
    	
    	String query = "insert into userregi value(?,?,?,?)";
        Connection con = DatabaseConnection.getConnection();
    	PreparedStatement smt = con.prepareStatement(query);
    	smt.setString(1,uname);
    	smt.setString(2, upassword);
    	smt.setString(3, umail);
    	smt.setString(4,unumber);
    	int rownumber= smt.executeUpdate();
    	
    	if(rownumber>0) {
    		System.out.println("User account successfully created");
    	}
    	else {
    		System.out.println("try again There is problem ");
    	}

    	
    	}
    	catch(SQLException e) {
    		System.out.println("The Error occured in sql: " +e.getMessage() );
    	}
    	
	}

	

}
