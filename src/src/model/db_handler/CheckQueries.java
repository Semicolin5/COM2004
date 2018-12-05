package src.model.db_handler;
import java.sql.*;
import java.util.*;


public class CheckQueries extends Queries {

	// constructor is passed a DatabaseHandler object from which it obtains the Connection object
    public CheckQueries (DatabaseHandler db) {
        super(db);
    }
    
    //*******************************************
    //Check duplicates - methods to check if there are duplicates of the primary keys
    //*******************************************
    
    
   /**
    * checkDuplicateUser, takes a login ID and returns true if we already have the stored user
    * @param int loginID, the login ID of the user we are checking we don't have a duplicate
    * @return boolean, true if we already have the user stored
    */
   public boolean checkDuplicateUser(int loginID) {
	   List<Integer> lIDS = new ArrayList<Integer>();
	   PreparedStatement pstmt = null;
	   ResultSet res = null;
       try {
           pstmt = conn.prepareStatement("SELECT login_id FROM users WHERE login_id = ?");
           pstmt.setInt(1, loginID);
           res = pstmt.executeQuery();
           while (res.next()) {
               lIDS.add(res.getInt(1));
           }
       }
       catch (SQLException e) {
           e.printStackTrace();
           lIDS.add(1);
       }
       finally {
           closeResources(pstmt, res);
       }
       if (lIDS.size() > 0) {
    	   return true;
       }
       else {
    	   return false;
       }
   }
   
   
   /**
    * checkDuplicateDepartment, takes a department code and returns true if we already have that department stored
    * @param String depCode, the department code we are checking we don't have a duplicate
    * @return boolean, true if we already have the department stored
    */
   public boolean checkDuplicateDepartment(String depCode) {
	   List<String> dCS = new ArrayList<String>();
	   PreparedStatement pstmt = null;
	   ResultSet res = null;
       try {
    	   pstmt = conn.prepareStatement("SELECT department_code FROM department WHERE department_code = ?");
           pstmt.setString(1, depCode);
           res = pstmt.executeQuery();
           while (res.next()) {
        	   dCS.add(res.getString(1));
           }
       }
       catch (SQLException e) {
    	   e.printStackTrace();
           dCS.add("");
       }
       finally {
    	   closeResources(pstmt, res);
       }
       
       if (dCS.size() > 0) {
    	   return true;
       }
       else {
    	   return false;
       }
   }
   
   /**
    * checkDuplicateDegree, takes a degree code and returns true if we already have that degree stored
    * @param String degCode, the degree code we are checking we don't have a duplicate
    * @return boolean, true if we already have the degree stored
    */
   public boolean checkDuplicateDegree(String degCode) {
	   List<String> dCS = new ArrayList<String>();
	   PreparedStatement pstmt = null;
	   ResultSet res = null;
       try {
    	   pstmt = conn.prepareStatement("SELECT degree_code FROM degree WHERE degree_code = ?");
           pstmt.setString(1, degCode);
           res = pstmt.executeQuery();
           while (res.next()) {
        	   dCS.add(res.getString(1));
           }
       }
       catch (SQLException e) {
    	   e.printStackTrace();
           dCS.add("");
       }
       finally {
    	   closeResources(pstmt, res);
       }
       
       if (dCS.size() > 0) {
    	   return true;
       }
       else {
    	   return false;
       }
   }   
   
   /**
    * checkDuplicateModule, takes a module code and returns true if we already have that module stored
    * @param String modCode, the module code we are checking we don't have a duplicate
    * @return boolean, true if we already have the module stored
    */
   public boolean checkDuplicateModule(String modCode) {
	   List<String> mCS = new ArrayList<String>();
	   PreparedStatement pstmt = null;
	   ResultSet res = null;
       try {
    	   pstmt = conn.prepareStatement("SELECT module_code FROM module WHERE module_code = ?");
           pstmt.setString(1, modCode);
           res = pstmt.executeQuery();
           while (res.next()) {
        	   mCS.add(res.getString(1));
           }
       }
       catch (SQLException e) {
    	   e.printStackTrace();
           mCS.add("");
       }
       finally {
    	   closeResources(pstmt, res);
       }
       
       if (mCS.size() > 0) {
    	   return true;
       }
       else {
    	   return false;
       }
   }     
   
   //*******************************************
   //Check existence - checks a primary key value exists
   //*******************************************

   /**
    * checkLoginIDExists, takes a login ID and returns true if it exists
    * @param int loginID, the login ID we search on
    * @return boolean exists, the boolean we return depending on whether it exists
    */
   public boolean checkLoginIDExists(int loginID) {
	   List<Integer> lIDS = new ArrayList<Integer>();
	   PreparedStatement pstmt = null;
	   ResultSet res = null;
       try {
    	   pstmt = conn.prepareStatement("SELECT login_id FROM users WHERE login_id = ?");
           pstmt.setInt(1, loginID);
           res = pstmt.executeQuery();
           while (res.next()) {
        	   lIDS.add(res.getInt(1));
           }
       }
       catch (SQLException e) {
    	   e.printStackTrace();
           lIDS.add(1);
       } finally {
               closeResources(pstmt, res);
       }
       if (lIDS.size() == 1) {
    	   return true;
       }
       else {
    	   return false;
       }
   }
   
   
   
   //**********************************************************
   //Check to see if we can delete things
   //**********************************************************
   
   
   
   /**
    * Returns true if the degree is 'allowed' to be deleted (i.e. has no student affiliations, and has no
    * module affiliations).
    * @param degree_code String representing the degree code to be deleted.
    * @return boolean of whether the degree was deleted and allowed to be deleted
    * */
   public boolean checkDeleteDegree(String degree_code) {
        PreparedStatement pstmt = null;
        ResultSet res = null;
        boolean hasAffiliatedStudents = true; // assume it has students, and check
        boolean hasAffiliatedModules = true; // assume it has affiliated modules, and check
        try {
            pstmt = super.conn.prepareStatement("SELECT * FROM student WHERE degree_code=?");
            pstmt.setString(1, degree_code);
            res = pstmt.executeQuery();
            // checks to see if res is empty
            if (!res.next()) {
                hasAffiliatedStudents = false; // allowed is true if no student takes the degree
            }
            pstmt = super.conn.prepareStatement("SELECT * FROM module_degree WHERE degree_code=?");
            pstmt.setString(1, degree_code);
            res = pstmt.executeQuery();
            if (!res.next()) {
                hasAffiliatedModules = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, res);
        }
        return (!hasAffiliatedModules && !hasAffiliatedStudents);
   }


	/**
	* Returns true if the Department is allowed to be deleted.
	* Departments are allowed to be deleted if there are no degrees affiliated with the department.
	* @param department_code String represents the department to check.
	* @return boolean of whether the department was allowed to be deleted
	* */
	public boolean checkDeleteDepartment(String department_code) {
	   PreparedStatement pstmt = null;
	   ResultSet res = null;
	   boolean affiliatedDegrees = true; // assume that there are affiliated degrees, and code proves otherwise
	   try {
	       pstmt = super.conn.prepareStatement("SELECT * FROM degree_department WHERE department_code=?");
	       pstmt.setString(1, department_code);
	       res = pstmt.executeQuery();
	       // checks to see if res is empty
	       if(!res.next()) {
	           affiliatedDegrees = false;
	       }
	
	   } catch (SQLException e) {
	        e.printStackTrace();
	   } finally {
	       closeResources(pstmt, res);
	   }
	   return (!affiliatedDegrees);
	}

   /**
    * Return true if the user is allowed to be deleted. A user is allowed to be deleted if the user isn't a student
    * and has an associated row in the student table.
    * @param login_id String representing the user under question
    * */
   public boolean checkDeleteUser(String login_id) {
       boolean allowedToDeleteUser = false;
       // checks the user isn't a student
       try {
           PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM student WHERE login_id = ? ");
           pstmt.setString(1, login_id);
           ResultSet rs = pstmt.executeQuery();
           if(!rs.next()){
               allowedToDeleteUser = true; // no affiliated entry in the student table
           }
       } catch (SQLException e)  {
           e.printStackTrace();
       }
       return allowedToDeleteUser;
   }
   
   
}
