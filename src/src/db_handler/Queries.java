package src.db_handler;
import java.sql.*;

/**
 * Queries.java
 * Superclass for AdditionQueries.java, UpdateQueries.java,
 * DeleteQueries.java and RetrieveQueries.java.
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
     * @param rs is a ResultSet object that is closed
     * */
    public void closeResultSet(java.sql.ResultSet rs) {
        // try to close the ResultSet object
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Utility method that releases JDBC resources by closing a Statement object.
     * @param stmt is a Statement object (regular Statement, or the
     * PreparedStatement object), which is closed.
     * TODO check that you can pass PreparedStatment objects into this method
     * */
    public void closeStatement(java.sql.Statement stmt) {
        // try to close the Statement object
        if (stmt !=null) {
            try {
                stmt.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
