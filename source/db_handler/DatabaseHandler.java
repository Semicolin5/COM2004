package source.db_handler;

import java.sql.*;
import java.util.*;

/**
 *  DatabaseHandler.java
 *
 *  Class stores methods to retrieve or change information stored in the database.
 * */
public class DatabaseHandler{

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = ""; // default password for mysql is empty

    /**
     * Tests database methods.
     * */
    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;

        System.out.println("entered main");
        DatabaseHandler test = new DatabaseHandler();

        try {
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            test.checkDriverExists();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            System.out.println("test end");
	    closeConnection(conn); 
        }

        System.out.println("Goodbye!");
    }

    /**
     * findDrivers method from lecture 9
     * All JDBC drivers are automatically loaded at startup if on classpath.buildpath
     * This function checks that the driver is loaded.
     * */
    public void checkDriverExists(){
        System.out.println("\n Drivers loaded as properties: ");
        System.out.println(System.getProperty("jdbc.drivers"));
        System.out.println("\n Drivers loaded by DriverManager: ");
        Enumeration<Driver> list = DriverManager.getDrivers();
        while (list.hasMoreElements())
            System.out.println(list.nextElement());
    }
    
    /**
     * Utility method that releases JDBC resources
     * @param rs is a ResultSet object that is closed
     * */
    private static void closeResultSet(java.sql.ResultSet rs) {
      // try to close the ResultSet object
      if (rs != null) {
	  try {
	      rs.close();
	  }
	  catch (Exception ex) {
	      ex.printStackTrace();
	  }
      }
    }

    /**
     * Utility method that releases JDBC resources by closing a Statement object.
     * @param stmt is a Statement object (regular Statement, or the
     * PreparedStatement object), which is closed.
     * TODO check that you can pass PreparedStatment objects into this method
     * */
    private static void closeStatement(java.sql.Statement stmt) {
	// try to close the Statement object
	if (stmt !=null) {
	    try {
		stmt.close(); 
	    }
	    catch (Exception ex) {
		ex.printStackTrace();
	    }
	}
    }

    /**
     * Utility method that releases JDBC resource by closing a Connection
     * object.
     * */
    private static void closeConnection(java.sql.Connection conn) {
	if (conn != null) {
	    try {
		conn.close(); 
	    }
	    catch (Exception ex) {
		ex.printStackTrace();
	    }
	}
    }

}





