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
     * // TODO do this one last this is impossible to do without removing like everything
     * Remove Department Query - only accessible for Administrators (privilege level 4)
     * @param code String that represents the departments code, used to identify what row to delete.
     * */
    public void removeDepartment(String code) {
        if (super.getPriv() == 4) {
            PreparedStatement pstmt = null;
            try {
                db.enableACID();
                // TODO remove students related to the department


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
     * @param code String that represents the modules code, used to identify the row to delete
     * */
    public void removeModule(String code) {
        if (super.getPriv() == 4) {
            PreparedStatement pstmt = null;
            try {
                db.enableACID();
                pstmt = super.conn.prepareStatement("DELETE FROM module WHERE code=?");
                pstmt.setString(1, code);
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
    }

    /**
     * Removes rows from table where condition. Shouldn't be called directly.
     * Removes grade row completely from the database given a column and variable
     * @param table String describing the table containing the row(s) to delete
     * @param column String describing column in WHERE column=item
     * @param whereEquals String describing the item to be delete
     **/
    public void removeRowWhere(String table, String column, String whereEquals) {
        PreparedStatement pstmt = null;
        String query = "DELETE FROM $tableName WHERE $columnName = ?";
        String addedTable = query.replace("$tableName",table);
        String addedColumn = addedTable.replace("$columnName", column);
        try {
            pstmt = conn.prepareStatement(addedColumn); //WHERE module_code=\"COM1005\"");
            System.out.println("BINDING");
            pstmt.setString(1, whereEquals);
            pstmt.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
            closePreparedStatement(pstmt);
        }

    }

}
