package src.model.db_handler;
import java.sql.*;

import src.objects.Degree;
import src.objects.Department;
import src.objects.Module;
import src.objects.User;
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
        List<Department> table = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet res = null;
            try {
                pstmt = conn.prepareStatement("SELECT * FROM department");
                res = pstmt.executeQuery();
                while (res.next()) {
                    table.add(new Department(res.getString(1), res.getString(2)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeResources(pstmt, res);
            }
        return table;
    }


    /**
     * retrieve the degree table, linked with the departments that teach that course
     * */
    public List<Degree> retrieveDegreesTable() {
        List<Degree> degreeTable = new ArrayList<Degree>();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        //TODO: Add privilege check to controller function.
        try {
            pstmt = conn.prepareStatement("SELECT * FROM degree");
            res = pstmt.executeQuery();
            while (res.next()) {
                degreeTable.add(new Degree(res.getString(1), res.getString(2), res.getBoolean(3), res.getBoolean(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, res);
        }
        return degreeTable;
    }

    /**
     * retrieves the module table as a list of Module objects
     * @return list of Module objects.
     * */
    public List<Module> retrieveModuleTable() {
        List<Module> table = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        if (!super.isTableEmpty("module")) {
            try {
                pstmt = conn.prepareStatement("SELECT * FROM module");
                res = pstmt.executeQuery();
                while (res.next()) {
                    table.add(new Module(res.getString(1), res.getString(2), res.getInt(3), res.getInt(4)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeResources(pstmt, res);
            }
        }
        return table;
    }
    
    public List<String> retrieveDepartmentsModules(String depCode) {
        List<String> modules = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        if (!super.isTableEmpty("module")) {
            try {
                pstmt = conn.prepareStatement("SELECT module_code FROM module_degree WHERE degree_code = ?");
                pstmt.setString(1, depCode);
                res = pstmt.executeQuery();
                while (res.next()) {
                    modules.add(res.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeResources(pstmt, res);
            }
        }
        return modules;
    }
    
    
    /**
     * retrieve the users table
     * @return List<User>, returns the list of the users table
     */
   public List<User> retrieveUsersTable() {
       List<User> userTable = new ArrayList<>();
       PreparedStatement pstmt = null;
       ResultSet res = null;
       try {
    	   pstmt = conn.prepareStatement("SELECT * FROM users");
           res = pstmt.executeQuery();
           while (res.next()) {
        	   userTable.add(new User(res.getInt(1), res.getString(2),
               res.getString(3), res.getInt(4)));
           }
       }
       catch (SQLException e) {
    	   e.printStackTrace();
       }
       finally {
    	   closeResources(pstmt, res);
       }
       return userTable;
   }
   
   
   /**
    * retrieveUser, given a login ID retrieves the users object including their privilege
    * @param int loginID, the login ID of the user we want to retrieve
    * @return User user, the User object of the user we want to retrieve
    */
  public User retrieveUser(int loginID) {
      User ourUser = null;
      PreparedStatement pstmt = null;
      ResultSet res = null;
      try {
    	  pstmt = conn.prepareStatement("SELECT * FROM users where login_id = ?");
          pstmt.setInt(1, loginID);
          res = pstmt.executeQuery();
          if (res.next()) {
        	  ourUser = new User(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4));
         }
      } 
      catch (SQLException e) {
    	  e.printStackTrace();
      } 
      finally {
    	  closeResources(pstmt, res);
      }
      return ourUser;
  }
   
   
   
   
   
   
   
   /**
    * getPassSalt, takes a loginID and returns the associated hashed password and salt
    * @param int loginID, the users loginID
    * @return String[], returns the hashed password and salt
    */
   public String[] getPassSalt(int loginID) {
	   String[] passSalt = new String[2];
	   PreparedStatement pstmt = null;
	   System.out.println(loginID);
	   ResultSet res = null;
       try {
    	   pstmt = conn.prepareStatement("SELECT hashpass, salt FROM users WHERE login_id = ?");
           pstmt.setInt(1, loginID);
           res = pstmt.executeQuery();
           if (res.next()) {
        	   passSalt[0] = res.getString(1);
        	   passSalt[1] = res.getString(2);
           }
       }
       catch (SQLException e) {
           e.printStackTrace();
       } 
       finally {
    	   closeResources(pstmt, res);
       }
	   return passSalt;
   }
   
   
   
   
   
   
   
   
   
   
   
}
