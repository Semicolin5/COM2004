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
                PreparedStatement pstmt = super.conn.prepareStatement("INSERT INTO module VALUES (?,?,?)");
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
     * Adds first to the users database table, then to the student table
     * using ACID to ensure that there is no possibility of inconsistency.
     * */
    public void addStudent(String loginId, String password, int priv, String title, String forename, String surname,
                           String personalTutor, String email) {
        if (super.getPriv() == 3) {
            System.out.println("in here");
            try {
                // first create entry in the user table
                PreparedStatement pstmt = super.conn.prepareStatement("INSERT INTO users VALUES (?,?,?)");
                pstmt.setString(1, loginId);
                pstmt.setString(2, password);
                pstmt.setInt(3, priv);
                pstmt.executeUpdate();

                // then create entry in the student table
                PreparedStatement pstmt2 = super.conn.prepareStatement("INSERT INTO student VALUES (?, ?, ?, ?, ?, ?)");
                pstmt2.setString(1, loginId);
                pstmt2.setString(2, title);
                pstmt2.setString(3, forename);
                pstmt2.setString(4,surname);
                pstmt2.setString(5,personalTutor);
                pstmt2.setString(6,email);
                pstmt2.executeUpdate();

                // commit connection, then close resources
                super.conn.commit();
                pstmt.close();
                pstmt.close(); //TODO would this work as just one Prepared Statement object? i.e. reuse pstmt?

            } catch (SQLException e) {
                e.printStackTrace();
                super.db.rollBack();
            }
        }
    }

}
