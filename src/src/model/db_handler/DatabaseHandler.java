package src.model.db_handler;

import java.sql.*;
import java.util.*;

/**
 *  DatabaseHandler.java
 *
 *  Class is used to create the Connection object, and is a associated
 *  with a user, and their respective privilege level.
 * */
public class DatabaseHandler{

    // JDBC driver name and database URL
    final String DB_URL = "jdbc:mysql://localhost:3306/db";

    //  Database credentials
    final String USER = "root";
    final String PASS = "frank"; // default password for mysql is empty
    private int privLevel = 0;

    private Connection conn;

    /**
     * constructor creates a connection to the database.
     * */
    public DatabaseHandler() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            disableACID();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * turns autocommit off
     * */
    public void enableACID() throws SQLException {
        conn.setAutoCommit(false);
    }

    /**
     * turns autocommit on
     * */
    public void disableACID() throws SQLException {
        conn.setAutoCommit(true);
    }

    /**
     * Accessor method for Connection object
     * */
    public Connection getConn() {
        return conn;
    }

    /**
     * Accessor method for priviledge level
     * */
    public int getPrivLevel() { return privLevel;}

    public void setPrivLevel(int priv) { privLevel = priv; }
    /**
     * Method is called when a transaction may have failed. First checks connection, then rolls back
     * if database must revert.
     * */
    public void rollBack() {
        try {
            if (conn != null)
                conn.rollback();
        }
        catch  (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * findDrivers method from lecture 9
     * All JDBC drivers are automatically loaded at startup if on classpath.buildpath
     * This function checks that the driver is loaded.
     * */
    public void checkDriverExists(){
        Enumeration<Driver> list = DriverManager.getDrivers();
        while (list.hasMoreElements())
            System.out.println(list.nextElement());
    }

    /**
     * Utility method that releases JDBC resource by closing a Connection
     * object.
     * */
    public void closeConnection() {
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





