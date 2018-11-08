package src.db_handler;
import java.sql.*;


/**
 * AdminitrationQueries.java
 * Class contains methods that carry out queries relating to the Administrators tasks,
 * i.e. removing and adding degree courses.
 * */
public class AdministrationQueries {

    private Connection conn;

    public AdministrationQueries (src.db_handler.DatabaseHandler db) {
        conn = db.getConn();
    }

    /**
     *  Add Department
     * @param code is String of 3 characters representing degree
     * @param desc is String describing the degree (maxlength 100).
     * */
    public void addModule(String code, String desc) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO department VALUES (?,?)");
            pstmt.setString(1,code);
            pstmt.setString(2,desc);
            pstmt.executeUpdate();
            System.out.print("should have added department");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
