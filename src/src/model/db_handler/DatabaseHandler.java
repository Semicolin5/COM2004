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
    final String PASS = ""; // default password for mysql is empty
    private int privLevel = 0;


    private Connection conn;

    /**
     * constructor creates a connection to the database.
     * */
    public DatabaseHandler() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false); // enables ACID
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
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT privilege FROM users WHERE login_id=?");
            pstmt.setString(1,login);
            ResultSet res = pstmt.executeQuery();
            ResultSetMetaData rsmd = res.getMetaData();
            while (res.next()) {
                privLevel = res.getInt("privilege");
            }
            // close resources
            pstmt.close();
            res.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return privLevel;
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
        System.out.println("\n Drivers loaded as properties: ");
        System.out.println(System.getProperty("jdbc.drivers"));
        System.out.println("\n Drivers loaded by DriverManager: ");
        Enumeration<Driver> list = DriverManager.getDrivers();
        while (list.hasMoreElements())
            System.out.println(list.nextElement());
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





