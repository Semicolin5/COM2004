package src.db_handler;
import java.sql.*;
import src.objects.Department;
import java.util.*;


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
     * Add Department
     * @param code is String of 3 characters representing the degree
     * @param desc is String describing the degree (maxlength 100).
     * */
    public void addDepartments(String code, String desc) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO department VALUES (?,?)");
            pstmt.setString(1,code);
            pstmt.setString(2,desc);
            pstmt.executeUpdate();
            System.out.print("should have added a department");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add Module
     * @param code is a String of 7 characters representing the module
     * @param name is a String describing the module
     * @param credits is an int of the numbers of credits the modules is worth
     * */
    public void addModule(String code, String name, int credits) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO module VALUE (?,?,?)");
            pstmt.setString(1,code);
            pstmt.setString(2, name);
            pstmt.setInt(3, credits);
            pstmt.executeUpdate();
            System.out.println("should have added a module");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * retrieveDepartmentTable
     * @return list of Department objects.
     * */
    public List<Department> retrieveDepartmentTable() {
        //TODO test that this table isn't empty using method from DatabaseHandler (to be created...)
        List<Department> table = new ArrayList<Department>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM department");
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                table.add(new Department(res.getString(1), res.getString(2)));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }
}
