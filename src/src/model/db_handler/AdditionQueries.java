package src.model.db_handler;

import java.sql.*;

/**
 * AdditionQueries class stores methods that make addition queries with the SQL server.
 * */
public class AdditionQueries extends Queries{

    // Requires conn to execute SQL queries with the database
    // constructor is passed a DatabaseHandler object from which it obtains the Connection object
    public AdditionQueries (src.model.db_handler.DatabaseHandler db) {
        super(db);
    }

    /* *
     * Add Department Query - only accessible for Administrators (privilege level 4)
     * @param code is String of 3 characters representing the degree
     * @param desc is String describing the degree (maxlength 100).
     * */
    public void addDepartment(String code, String desc) {
        System.out.println("yes");
        PreparedStatement pstmt = null;
        try {
            db.enableACID();
            pstmt = super.conn.prepareStatement("INSERT INTO department VALUES (?,?)");
            pstmt.setString(1, code);
            pstmt.setString(2, desc);
            pstmt.executeUpdate();
            super.conn.commit();
            db.disableACID();
        } catch (SQLException e) {
            super.db.rollBack(); // ensure ACID
            e.printStackTrace();
        } finally {
            closePreparedStatement(pstmt);
        }
    }

    /**
     * Add Module Query - only accessible for Administrators (privilege level 4)
     * @param code is a String of 7 characters representing the module
     * @param name is a String describing the module
     * @param credits is an int of the numbers of credits the modules is worth
     * */
    public void addModule(String code, String name, int credits, int semester) {
        PreparedStatement pstmt = null;
        try {
            db.enableACID();
            pstmt = super.conn.prepareStatement("INSERT INTO module VALUES (?,?,?,?)");
            pstmt.setString(1, code);
            pstmt.setString(2, name);
            pstmt.setInt(3, credits);
            pstmt.setInt(4, semester);
            pstmt.executeUpdate();
            super.conn.commit();
            db.disableACID();
        } catch (SQLException e) {
            super.db.rollBack();
            e.printStackTrace();
        } finally {
            closePreparedStatement(pstmt);
        }
    }

    /**
     * Add Degree Query - only accessible for Administrators (privilege level 4)
     * Creates a new degree as an entry in the degree table.
     * @param code String that is 6 char code representing the degree
     * @param name String describing the degree, i.e. "Computer Science"
     * */
    public void addDegree(String code, String name, boolean masters, boolean yearIndustry) {
        PreparedStatement pstmt = null;
        try {
            System.out.println(name + masters + code + yearIndustry);
            db.enableACID();
            pstmt = super.conn.prepareStatement("INSERT INTO degree VALUES (?,?,?,?)");
            pstmt.setString(1, code);
            pstmt.setString(2, name);
            pstmt.setBoolean(3, masters);
            pstmt.setBoolean(4, yearIndustry);
            pstmt.executeUpdate();
            super.conn.commit();
            db.disableACID();
        } catch (SQLException e) {
            super.db.rollBack();
            e.printStackTrace();
        } finally {
            closePreparedStatement(pstmt);
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
        PreparedStatement pstmt = null;
        try {
            db.enableACID();
            pstmt = super.conn.prepareStatement("INSERT INTO degree_department VALUES (?,?,?)");
            pstmt.setString(1, degreeCode);
            pstmt.setString(2, departmentCode);
            pstmt.setBoolean(3, lead);
            pstmt.executeUpdate();
            super.conn.commit();
            db.disableACID();
        } catch (SQLException e) {
            super.db.rollBack();
            e.printStackTrace();
        } finally {
            closePreparedStatement(pstmt);
        }
    }

    /**
     * Add Students Query - only accessible for Registrars (privilege level 3)
     * Adds first to the users database table, then to the student table
     * using ACID to ensure that there is no possibility of inconsistency.
     * */
    public void addStudent(int loginId, String password, String salt, String title, String forename, String surname,
                           String personalTutor, String email, String degreeCode) {
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        try {
            db.enableACID();
            // first create entry in the user table
            pstmt = super.conn.prepareStatement("INSERT INTO users VALUES (?,?,?,?)");
            pstmt.setInt(1, loginId);
            pstmt.setString(2, salt);
            pstmt.setString(3, password);
            pstmt.setInt(4, 1);
            pstmt.executeUpdate();

            // then create entry in the student table
            pstmt2 = super.conn.prepareStatement("INSERT INTO student VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstmt2.setInt(1, loginId);
            pstmt2.setString(2, title);
            pstmt2.setString(3, forename);
            pstmt2.setString(4,surname);
            pstmt2.setString(5,personalTutor);
            pstmt2.setString(6,email);
            pstmt2.setString(7, degreeCode);
            pstmt2.executeUpdate();

            // commit connection, then close resources
            super.conn.commit();
            db.disableACID();
        } catch (SQLException e) {
            e.printStackTrace();
            super.db.rollBack();
        } finally {
            closePreparedStatement(pstmt);
            closePreparedStatement(pstmt2); //TODO would this work as just one pstmt?
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
    public void addStudentModuleAssociation(String studentId, String moduleCode, String label) {
        try {
            db.enableACID();
            PreparedStatement pstmt = super.conn.prepareStatement("INSERT INTO grades VALUES (?,?,?, NULL, NULL)");
            pstmt.setString(1, studentId);
            pstmt.setString(2, moduleCode);
            pstmt.setString(3, label);
            pstmt.executeUpdate();
            super.conn.commit();
            db.disableACID();
        } catch (SQLException e) {
            e.printStackTrace();
            super.db.rollBack();
        }
    }

    /**
     * Add period of study  Query - only accessible for Registrars (privilege level 3)
     * This table tracks when the student studied each level of a degree.
     * Will allow staff to see if a student redid a year, for example
     * This SQL query takes a student and adds a row in the period of study with their ID as the foreign key.
     * @param loginId: int representing the student.
     * @param label: Part of a composite primary key, along with loginID.
     * @param startDate: Date tells us when the period of study began.
     * @param endDate: Date tells us when the period of study ended.
     * @param level: Char representing the level of the degree the student took in this period of study.
     */
    public void addPeriodOfStudy(int loginId, String label, String startDate, String endDate, String level) {
        PreparedStatement pstmt = null;
        try {
            db.enableACID();

            pstmt = super.conn.prepareStatement("INSERT INTO period_of_study VALUES (?,?,?,?,?)");
            pstmt.setInt(1, loginId);
            pstmt.setString(2, label);
            pstmt.setDate(3, java.sql.Date.valueOf(startDate));
            pstmt.setDate(4, java.sql.Date.valueOf(endDate));
            pstmt.setString(5, level);
            pstmt.executeUpdate();

            // commit connection, then close resources
            super.conn.commit();
            db.disableACID();
        } catch (SQLException e) {
            e.printStackTrace();
            super.db.rollBack();
        } finally {
            closePreparedStatement(pstmt);
        }
    }

    /**
     * Add UserAssociation Query - only accessible for Administrators (privilege level 4)
     * This SQL query adds a row to the user table.
     * @param loginId String of the user's identification code.
     * @param password Hashed/salted password for user.
     * @param priv  Privilege level of staff member.
     * @param salt Salt used for user's password (prevents Rainbow table attacks).
     * */
    public void addUser(int loginId, String salt, String password, int priv) {
        PreparedStatement pstmt = null;
        try {
            db.enableACID();
            // first create entry in the user table
            pstmt = super.conn.prepareStatement("INSERT INTO users VALUES (?,?,?,?)");
            pstmt.setInt(1, loginId);
            pstmt.setString(2, salt);
            pstmt.setString(3, password);
            pstmt.setInt(4, priv);
            pstmt.executeUpdate();

            // commit connection, then close resources
            super.conn.commit();
            db.disableACID();
        } catch (SQLException e) {
            e.printStackTrace();
            super.db.rollBack();
        } finally {
            closePreparedStatement(pstmt);
        }
    }

    /**
     * Add Module Degree Association Query - only accessible for Administrators (privilege level 4)
     * TODO:
     * */
    public void addModuleDegreeAssociation(String moduleCode, String degreeCode,String level, boolean core) {
        PreparedStatement pstmt = null;
        try {
            db.enableACID();
            pstmt = super.conn.prepareStatement("INSERT INTO module_degree VALUES (?,?,?,?)");
            pstmt.setString(1, moduleCode);
            pstmt.setString(2, degreeCode);
            pstmt.setString(3, level);
            pstmt.setBoolean(4,core);
            pstmt.executeUpdate();
            super.conn.commit();
            db.disableACID();
        } catch (SQLException e) {
            e.printStackTrace();
            super.db.rollBack();
        } finally {
            closePreparedStatement(pstmt);
        }
    }

    /**
     * createStudentModuleAssociation is given a student, a label and a module code. It creates the students association
     * with this module by creating a row in the grades table.
     * @param login, int representing the students login code,
     * @param label, String of length one representing the period of study label,
     * @param moduleCode String representing the module the student is to take.
     * */
    public void createStudentModuleAssociation(int login, String label, String moduleCode) {
        PreparedStatement pstmt = null;
        try {
            pstmt = super.conn.prepareStatement("INSERT INTO grades VALUES (?,?,?,NULL,NULL)");
            db.enableACID();
            pstmt.setInt(1, login);
            pstmt.setString(2, moduleCode); // label must be length one
            pstmt.setString(3, label);
            pstmt.executeUpdate();
            db.disableACID();
        } catch (SQLException e) {
            super.db.rollBack();
            e.printStackTrace();
        } finally {
           closePreparedStatement(pstmt);
        }
    }

    /**
     * updateGrade enables the teacher to add new scores to a grade.
     * @param login, int representing the targeted student (1/3 of the primary key)
     * @param label, String of length 1 representing the period of study of the grade to change (1/3 of the primary key)
     * @param moduleCode String reprsenting the module of the grade to change (1/3 of the primary key)
     * @param initialGrade Float representing the initial student grade
     * @param resitGrade Float representing the initial student grade
     * */
    public void updateGrade(int login, String moduleCode, String label, Float initialGrade, Float resitGrade, Float repeatGrade) {
        PreparedStatement pstmt = null;
        boolean initialNotNull = (initialGrade > 0) && (initialGrade != null);
        boolean resitNotNull = (resitGrade > 0) && (resitGrade != null);
        boolean repeatNotNull = (repeatGrade > 0) && (repeatGrade != null);
        try {
            db.enableACID();
            pstmt = super.conn.prepareStatement("UPDATE grades SET initial_percent=?, resit_percent=? WHERE "
                    + "login_id=? AND module_code=? AND label=?");
            pstmt.setInt(3, login);
            pstmt.setString(4, moduleCode);
            pstmt.setString(5, label);
            // check that the floats aren't null, if they are null set using .setNull()
            if (initialNotNull) { // allows for negative one to act as sentinel
                pstmt.setFloat(1, initialGrade);
            } else {
                pstmt.setNull(1, Types.FLOAT);
            }
            if (resitNotNull) { // allows for negative one to act as sentinel
                pstmt.setFloat(2, resitGrade);
            } else {
                pstmt.setNull(2, Types.DECIMAL);
            }
            pstmt.executeUpdate();

            if(repeatNotNull) {
                //Increment label to next character of the alphabet
                int nextLabel = label.charAt(0) + 1;
                pstmt.setString(5, String.valueOf((char) nextLabel));

                //Set initial grade of new record to repeat grade
                pstmt.setFloat(1, repeatGrade);
                //Set resit grade to null
                pstmt.setNull(2, Types.DECIMAL);

                pstmt.executeUpdate();
            }

            super.conn.commit();
            db.disableACID();
        } catch (SQLException e) {
            db.rollBack();
            e.printStackTrace();
        } finally {
            closePreparedStatement(pstmt);
        }
    }
}
