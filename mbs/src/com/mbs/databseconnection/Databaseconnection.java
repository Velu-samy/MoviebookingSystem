package com.mbs.databseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Databaseconnection {
	

	public class DatabaseConnection {
	    private static final String URL = "jdbc:mysql://localhost:3306/mbs";
	    private static final String USERNAME = "root";
	    private static final String PASSWORD = "Vetri@123";
	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	    }
	}

}
