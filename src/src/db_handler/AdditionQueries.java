package src.db_handler;

import java.sql.*;

/**
 * AdditionQueries class stores methods that make addition queries with the SQL server.
 * */
public class AdditionQueries extends Queries{

    // Requires conn to execute SQL queries with the database
    // constructor is passed a DatabaseHandler object from which it obtains the Connection object
    public AdditionQueries (src.db_handler.DatabaseHandler db) {
        super(db);
    }

    /* *
     * Add Department Query - only accessible for Administrators (privilege level 4)
     * @param code is String of 3 characters representing the degree
     * @param desc is String describing the degree (maxlength 100).
     * */
    public void addDepartment(String code, String desc) {
        System.out.println("In addDepartment");
        System.out.println(super.getPriv());
        if (true ) { //TODO fix
            System.out.println("yes");
            PreparedStatement pstmt = null;
            try {
                pstmt = super.conn.prepareStatement("INSERT INTO department VALUES (?,?)");
                pstmt.setString(1, code);
                pstmt.setString(2, desc);
                pstmt.executeUpdate();
                super.conn.commit();
            } catch (SQLException e) {
                super.db.rollBack(); // ensure ACID
                e.printStackTrace();
            } finally {
                closePreparedStatement(pstmt);
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
            PreparedStatement pstmt = null;
            try {
                pstmt = super.conn.prepareStatement("INSERT INTO module VALUES (?,?,?)");
                pstmt.setString(1, code);
                pstmt.setString(2, name);
                pstmt.setInt(3, credits);
                pstmt.executeUpdate();
                super.conn.commit();
            } catch (SQLException e) {
                super.db.rollBack();
                e.printStackTrace();
            } finally {
                closePreparedStatement(pstmt);
            }
        }
    }

    /**
     * Add Degree Query - only accessible for Administrators (privilege level 4)
     * Creates a new degree as an entry in the degree table.
     * @param code String that is 6 char code representing the degree
     * @param name String describing the degree, i.e. "Computer Science"
     * */
    public void addDegree(String code, String name) {
        if (super.getPriv() == 4) {
            PreparedStatement pstmt = null;
            try {
                pstmt = super.conn.prepareStatement("INSERT INTO degree VALUES (?,?)");
                pstmt.setString(1, code);
                pstmt.setString(2, name);
                pstmt.executeUpdate();
                super.conn.commit();
            } catch (SQLException e) {
                super.db.rollBack();
                e.printStackTrace();
            } finally {
                closePreparedStatement(pstmt);
            }
        }
    }

    /**
     * Add Department Association Query - only accessible for Administrators (privilege level 4)
     * Creates associations
     * @param degreeCode String that is 6 char code representing the degree
     * @param departmentCode String that is 3 char code representing the department, i.e. "Computer Science"
     * @param lead boolean expressing whether the associated department is the lead department
     * */
    public void addDepartmentAssociation (String degreeCode, String departmentCode, boolean lead) {
        if (super.getPriv() == 4) {
            PreparedStatement pstmt = null;
            try {
                pstmt = super.conn.prepareStatement("INSERT INTO associated_department VALUES (?,?,?)");
                pstmt.setString(1, degreeCode);
                pstmt.setString(2, departmentCode);
                pstmt.setBoolean(3, lead);
                pstmt.executeUpdate();
                super.conn.commit();
            } catch (SQLException e) {
                super.db.rollBack();
                e.printStackTrace();
            } finally {
                closePreparedStatement(pstmt);
            }
        }
    }


    /**
     * TODO add approval
     * */

    /**
     * Add Students Query - only accessible for Registrars (privilege level 3)
     * Adds first to the users database table, then to the student table
     * using ACID to ensure that there is no possibility of inconsistency.
     * */
    public void addStudent(String loginId, String password, String salt, int priv, String title, String forename, String surname,
                           String personalTutor, String email, String degreeCode) {
        if (super.getPriv() == 3) {
            PreparedStatement pstmt = null;
            PreparedStatement pstmt2 = null;
            try {
                // first create entry in the user table
                pstmt = super.conn.prepareStatement("INSERT INTO users VALUES (?,?,?,?)");
                pstmt.setString(1, loginId);
                pstmt.setString(2, password);
                pstmt.setInt(3, priv);
                pstmt.setString(4, salt);
                pstmt.executeUpdate();

                // then create entry in the student table
                pstmt2 = super.conn.prepareStatement("INSERT INTO student VALUES (?, ?, ?, ?, ?, ?, ?)");
                pstmt2.setString(1, loginId);
                pstmt2.setString(2, title);
                pstmt2.setString(3, forename);
                pstmt2.setString(4,surname);
                pstmt2.setString(5,personalTutor);
                pstmt2.setString(6,email);
                pstmt2.setString(7, degreeCode);
                pstmt2.executeUpdate();

                // commit connection, then close resources
                super.conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                super.db.rollBack();
            } finally {
                closePreparedStatement(pstmt);
                closePreparedStatement(pstmt2); //TODO would this work as just one pstmt?
            }
        }
    }

    /**
     * Add Module Association Query - only accessible for Registrars (privilege level 3)
     * The presence of a grade row in the grades table represents the fact that the student is taking
     * that module (or resitting). This SQL query takes a student and module names and creates a row in the
     * grades table, with null values in marks columns.
     * @param studentId String of the students identification code.
     * @param moduleCode String representing the module the student takes.
     * */
    public void addStudentModuleAssociation(String studentId, String moduleCode) {
        if (super.getPriv() == 3) {
            try {
                PreparedStatement pstmt = super.conn.prepareStatement("INSERT INTO grades VALUES (?,?,NULL, NULL)");
                pstmt.setString(1, studentId);
                pstmt.setString(2, moduleCode);
                pstmt.executeUpdate();
                super.conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                super.db.rollBack();
            }
        }
    }

    //TODO add grades is actually performed through an INSERT statement which is why it isn't here



}
