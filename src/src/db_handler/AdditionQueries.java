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
     * Add Department Query - only accessible for Administrators (privilege level 4)
     * @param code is String of 3 characters representing the degree
     * @param desc is String describing the degree (maxlength 100).
     * */
    public void addDepartments(String code, String desc) {
        if (super.getPriv() == 4) {
            try {
                PreparedStatement pstmt = super.conn.prepareStatement("INSERT INTO department VALUES (?,?)");
                pstmt.setString(1, code);
                pstmt.setString(2, desc);
                pstmt.executeUpdate();
                super.conn.commit();
                pstmt.close(); // releasing resources
            } catch (SQLException e) {
                e.printStackTrace();
                super.db.rollBack(); // ensure ACID
            }
        }
    }

    /**
     * Add Module Query - only accessible for Administrators (privilege level 4)
     * @param code is a String of 7 characters representing the module
     * @param name is a String describing the module
     * @param credits is an int of the numbers of credits the modules is worth
     * */
    public void addModule(String code, String name, int credits) {
        if (super.getPriv() == 4) {
            try {
                PreparedStatement pstmt = super.conn.prepareStatement("INSERT INTO module VALUE (?,?,?)");
                pstmt.setString(1, code);
                pstmt.setString(2, name);
                pstmt.setInt(3, credits);
                pstmt.executeUpdate();
                super.conn.commit();
                pstmt.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
                super.db.rollBack();
            }
        }
    }

    /**
     * Add Students Query - only accessible for Registrars (privilege level 3)
     * */
    //public void addStudent(String )

}
