package source.db_handler;

import java.sql.*;
import java.util.*;

/**
 *  DatabaseHandler.java
 *
 *  Class stores methods to retrieve or change information stored in the database.
 * */
public  class DatabaseHandler{

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/db";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = ""; // default password for mysql is empty
    Connection conn; // TODO make this private

    /**
     * constructor creates a connection to the database.
     * */
    public DatabaseHandler() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests database methods.
     * */
    public static void main(String[] args) {

        System.out.println("entered main");
        // instantiates this class to test its functions
        DatabaseHandler test = new DatabaseHandler();

        System.out.println(" \n Check driver exists");
        test.checkDriverExists();

        // testing to see if I can obtain the priviledge of a user:
        int priv = test.obtainPrivilege("MBlack");
        System.out.println("priviledge level is: " + priv);

        // testing the closeConnectionn() method;
        closeConnection(test.conn);
        System.out.println("test end");
    }

    /**
     * obtainPriviledge, given a login name, returns that users priviledge level.
     * @param login is the String of the users login
     * @return Integer ranging from 1 to 4, where 4 is highest priviledge level.
     * */
    private Integer obtainPrivilege(String login) {
        Statement stmt = null;
        ResultSet res = null;
        int privLevel = 0;

        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery("SELECT privilege FROM users WHERE login_id=\'JSmith24\'");
            ResultSetMetaData rsmd = res.getMetaData();
            System.out.println(rsmd.getColumnName(1));
            while (res.next()) {
                System.out.println("Result is: " + res.getString("privilege"));
                privLevel = res.getInt("privilege");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        closeStatement(stmt);
        closeResultSet(res);
        return privLevel;
    }

    /**
     * findDrivers method from lecture 9
     * All JDBC drivers are automatically loaded at startup if on classpath.buildpath
     * This function checks that the driver is loaded.
     * */
    private void checkDriverExists(){
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
    
    private static void testCommit() {
    	System.out.println("Hello World");
    }

}





