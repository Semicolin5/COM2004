package src.model.db_handler;
import java.sql.*;

/**
 * RemovalQueries.java class methods stores all SQL queries related to deletion
 * */
public class RemovalQueries extends Queries {

    public RemovalQueries (DatabaseHandler db) {
        super(db);
    }

    /**
     * Remove Department Query - only accessible for Administrators (privilege level 4)
     * This method is only run after it is checked that there are no associated degrees to the department.
     * If there are degrees associated to a department the Administrator wants to delete, that Admin should
     * be prompted to delete the degrees first.
     * @param code String that represents the departments code, used to identify what row to delete.
     * */
    public void removeDepartment(String code) {
        if (super.getPriv() == 4) {
            PreparedStatement pstmt = null;
            try {

                db.enableACID();
                pstmt = super.conn.prepareStatement("DELETE FROM department WHERE code=?");
                pstmt.setString(1, code);
                pstmt.executeUpdate();
                super.conn.commit();
                db.disableACID();

            } catch (SQLException e) {
                super.db.rollBack(); // maintains ACID if failure in query
                e.printStackTrace();
            } finally {
                closePreparedStatement(pstmt);
            }
        }
    }



    /**
     * Remove Module Query - only accessible for Administrators (privilege level 4)
     * Given a module_code of a module, this method will delete this module, and all its references.
     * Firstly all of the module's affiliations with degrees are delete from module_degree,
     * then the module is deleted from the module table.
     * @param code String that represents the modules code, used to identify the row to delete
     * */
    public void removeModule(String code) {
        if (true) { // TODO implement privilege check
            try {

                db.enableACID();
                // first delete all rows from grades table that refers to the module
                removeRowWhere("grades", "module_code", code);
                // secondly, delete all rows from the core table that refers to the module
                removeRowWhere("module_degree", "module_code", code);
                // then delete the row from the module table
                removeRowWhere("module", "module_code", code);
                super.conn.commit();
                db.disableACID();

            } catch (SQLException e) {
                super.db.rollBack();
                e.printStackTrace();
            }
        }
    }

    /**
     * Removes rows from table where condition. Shouldn't be called directly.
     * Removes grade row completely from the database given a column and variable
     * @param table String describing the table containing the row(s) to delete
     * @param column String describing column in WHERE column=item
     * @param whereEquals String describing the item to be delete
     **/
    private void removeRowWhere(String table, String column, String whereEquals) {
        PreparedStatement pstmt = null;
        String query = "DELETE FROM $tableName WHERE $columnName = ?";
        String addedTable = query.replace("$tableName",table);
        String addedColumn = addedTable.replace("$columnName", column);
        try {
            pstmt = conn.prepareStatement(addedColumn); //WHERE module_code=\"COM1005\"");
            pstmt.setString(1, whereEquals);
            pstmt.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
            closePreparedStatement(pstmt);
        }
    }

    /**
     * Remove User. Assumption that this method wouldn't be able to be run a user who is also a student.
     * @param loginID String that represents the user's loginID, used to identify the row to delete
     * */
    public void removeUser(String loginID) {
        PreparedStatement pstmt = null;
        try {
            db.enableACID();
            pstmt = super.conn.prepareStatement("DELETE FROM users WHERE login_id=?");
            pstmt.setString(1, loginID);
            pstmt.executeUpdate();
            super.conn.commit();
            db.disableACID();
        } catch (SQLException e) {
            super.db.rollBack(); // maintains ACID if failure in query
            e.printStackTrace();
        } finally {
            closePreparedStatement(pstmt);
        }

    }


    /**
     * Method removeStudent is used by the registrar to remove students, or admin to remove users - only accessible to
     * privilege level 3/4
     * @param loginID int representing the primary key of a row in the student table, the student login id,
     * The method queues, then executes for deletions from the database:
     *      1) deletes the student's associated row(s) in grades
     *      2) deletes the student's associated row in period of study
     *      3) deletes the student's row in the student table
     *      4) deletes the student's row in the user table.
     * */
    public void removeStudent(int loginID) {
        if (true) { // TODO implement the privilege check so only privilege 3/4 can access this method
            PreparedStatement pstmtRemoveGrades = null;
            PreparedStatement pstmtRemovePoS = null;
            PreparedStatement pstmtRemoveStudent = null;
            PreparedStatement pstmtRemoveUser =null;
            try {

                db.enableACID();
                pstmtRemoveGrades = super.conn.prepareStatement("DELETE FROM grades WHERE login_id = ?");
                pstmtRemoveGrades.setInt(1, loginID);
                pstmtRemoveGrades.executeUpdate();
                pstmtRemovePoS = super.conn.prepareStatement("DELETE FROM period_of_study WHERE login_id = ?");
                pstmtRemovePoS.setInt(1, loginID);
                pstmtRemovePoS.executeUpdate();
                pstmtRemoveStudent = super.conn.prepareStatement("DELETE FROM student WHERE login_id = ?");
                pstmtRemoveStudent.setInt(1, loginID);
                pstmtRemoveStudent.executeUpdate();
                pstmtRemoveUser = super.conn.prepareStatement("DELETE FROM users WHERE login_id = ?");
                pstmtRemoveUser.setInt(1, loginID);
                pstmtRemoveUser.executeUpdate();
                super.conn.commit();
                db.disableACID();

                pstmtRemoveGrades.close(); // releasing resource
                pstmtRemovePoS.close();
            } catch (SQLException e) {
                super.db.rollBack(); // maintains ACID if failure in query
                e.printStackTrace();
            } finally {
                closePreparedStatement(pstmtRemoveGrades);
                closePreparedStatement(pstmtRemovePoS);
                closePreparedStatement(pstmtRemoveStudent);
                closePreparedStatement(pstmtRemoveUser);
            }
        }
    }

    /**
     * removeStudentsModuleChoice takes two parameters, a student, and the module they need to drop.
     * This is to be used by registrars so that they can drop modules on behalf of the student. Is also accessible
     * for the student whose grade will be dropped.
     * @param login_id int representing the student whose module choice is being dropped
     * @param module_code String representing the module that the student will drop.
     * */
    public void removeStudentsModuleChoice(int login_id, String module_code) {
        if (true) { //TODO sort out the privilege check for this (bearing in mind that students can also do this if it is their module)

            PreparedStatement pstmt = null;
            try{

                db.enableACID();
                pstmt = super.conn.prepareStatement("DELETE FROM grades WHERE login_id=? AND module_code=?");
                pstmt.setInt(1, login_id);
                pstmt.setString(2, module_code);
                pstmt.executeUpdate();
                db.disableACID();

            } catch (SQLException e) {
               db.rollBack(); // rolls back if there is a problem
               e.printStackTrace();
            } finally {
                closePreparedStatement(pstmt); // release resources
            }

        }
    }

}
