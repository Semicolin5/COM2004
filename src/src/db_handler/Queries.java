package src.db_handler;
import java.sql.*;

/**
 * Queries.java
 * Superclass for AdditionQueries.java, UpdateQueries.java,
 * RemovalQueries.java and RetrieveQueries.java.
 * Contains methods used across these classes.
 * */
abstract class Queries {

    // Requires conn to execute SQL queries with the database
    protected Connection conn;
    protected DatabaseHandler db;


    // constructor is passed a DatabaseHandler object from which it obtains the Connection object
    public Queries (DatabaseHandler db) {
        conn = db.getConn();
        this.db = db;
    }

    /**
     * Obtains users privilege level form database object
     * */
    public int getPriv() {
        return db.getPrivLevel();
    }

    /**
     * Finds out if a table is empty, returning boolean of the result
     * @param name String of the table name to search
     * @return bool of whether empty  or not
     * */
    public boolean isTableEmpty(String name) {
        // TODO make this method
        return false; // for now assume no empty tables
    }

    /**
     * Utility method that releases JDBC resources
     * Closes resources in order of pstmt first, followed by result set.
     * @param pstmt is a PreparedStatement object that is to be closed
     * @param rs is a ResultSet object that is closed
     * */
    public void closeResources(PreparedStatement pstmt, ResultSet rs) {
        closePreparedStatement(pstmt); //closing PreparedStatement object
        try {
            if (rs != null) { rs.close(); } // closing ResultSet object
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes given PreparedStatement objects.
     * @param pstmt is a PreparedStatement object that is to be closed
     * */
    public void closePreparedStatement(PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
