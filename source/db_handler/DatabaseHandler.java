package source.db_handler;

import java.sql.*;
import java.util.*; //TODO is this actually needed?

/**
 *  DatabaseHandler.java
 *
 *  Class is used to create the Connection object, and
 *  methods to retrieve this object when a query is to be made.
 *
 * */
public class DatabaseHandler{

    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/db";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = ""; // default password for mysql is empty
    private int privLevel = 0;


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
     * obtainPriviledge, given a login name, returns that users priviledge level.
     * @param login is the String of the users login
     * @return Integer ranging from 1 to 4, where 4 is highest priviledge level.
     * */
    public Integer obtainPrivilege(String login) {
        Statement stmt = null;
        ResultSet res = null;

        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery("SELECT privilege FROM users WHERE login_id=\'JSmith24\'");
            ResultSetMetaData rsmd = res.getMetaData();
            while (res.next()) {
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
     * get connection. Getter method for
     * */
    public Connection getConn() {
        return conn;
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
    public static void closeResultSet(java.sql.ResultSet rs) {
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
    public static void closeStatement(java.sql.Statement stmt) {
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





