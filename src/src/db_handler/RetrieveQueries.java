package src.db_handler;
import java.sql.*;
import src.objects.Department;
import java.util.*;


/**
 * RetrieveQueries.java
 * extension of Queries, used to encapsulate the retrieval methods of SQL.
 * */
public class RetrieveQueries extends Queries {

    // constructor is passed a DatabaseHandler object from which it obtains the Connection object
    public RetrieveQueries (DatabaseHandler db) {
        super(db);
    }

    /**
     * retrieves the department table as a list of Department objects
     * @return list of Department objects.
     * */
    public List<Department> retrieveDepartmentTable() {
        List<Department> table;
        table = null; // table is null pointer if SQL should/cannot be executed
        if (!super.isTableEmpty("department") && super.getPriv() == 4) {
            table = new ArrayList<Department>();
            try {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM department");
                ResultSet res = pstmt.executeQuery();
                while (res.next()) {
                    table.add(new Department(res.getString(1), res.getString(2)));
                }
                super.closeResources(pstmt, res); // close JDBC resources
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        return table;
    }


}
