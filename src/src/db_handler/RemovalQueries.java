package src.db_handler;
import java.sql.*;

public class RemovalQueries extends Queries {

    public RemovalQueries (DatabaseHandler db) {
        super(db);
    }

    /**
     * Remove Department Query - only accessible for Administrators (privilege level 4)
     * @param code String that represents the departments code, used to identify what row to delete.
     * */
    public void removeDepartment(String code) {
        if (super.getPriv() == 4) {
            PreparedStatement pstmt = null;
            try {
                pstmt = super.conn.prepareStatement("DELETE FROM department WHERE code=?");
                pstmt.setString(1, code);
                pstmt.executeUpdate();
                super.conn.commit();
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
                pstmt = super.conn.prepareStatement("DELETE FROM module WHERE code=?");
                pstmt.setString(1, code);
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

}
