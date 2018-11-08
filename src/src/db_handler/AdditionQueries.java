package src.db_handler;

import java.sql.*;
import java.util.*;

public class AdditionQueries extends Queries{

    // Requires conn to execute SQL queries with the database
    // constructor is passed a DatabaseHandler object from which it obtains the Connection object
    public AdditionQueries (DatabaseHandler db) {
        super(db);
    }

    /**
     * Add Department
     * @param code is String of 3 characters representing the degree
     * @param desc is String describing the degree (maxlength 100).
     * */
    public void addDepartments(String code, String desc) {
        try {
            PreparedStatement pstmt = super.conn.prepareStatement("INSERT INTO department VALUES (?,?)");
            pstmt.setString(1,code);
            pstmt.setString(2,desc);
            pstmt.executeUpdate();
            pstmt.close(); // releasing resources
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add Module
     * @param code is a String of 7 characters representing the module
     * @param name is a String describing the module
     * @param credits is an int of the numbers of credits the modules is worth
     * */
    public void addModule(String code, String name, int credits) {
        try {
            PreparedStatement pstmt = super.conn.prepareStatement("INSERT INTO module VALUE (?,?,?)");
            pstmt.setString(1,code);
            pstmt.setString(2, name);
            pstmt.setInt(3, credits);
            pstmt.executeUpdate();
            pstmt.close(); // releasing resources;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
