import java.sql.*;

/**
 *  DatabaseHandler.java
 *
 *  Class stores methods to retrieve or change information stored in the database.
 * */
public class DatabaseHandler{
    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = ""; // default password for mysql is empty

    /**
     * Tests database methods.
     * */
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        System.out.println("entered main");

        try {
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            System.out.println("test end");
            try {
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("Goodbye!");
    }

}