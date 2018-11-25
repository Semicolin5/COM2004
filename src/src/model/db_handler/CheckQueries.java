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
   
   /**
    * checkDuplicateStudent, takes a login ID and returns true if we already have that student stored
    * @param String loginID, the login ID we are checking we don't have a duplicate
    * @return boolean, true if we already have the student stored
    */
   public boolean checkDuplicateStudent(int loginID) {
	   List<Integer> lIDS = new ArrayList<Integer>();
	   PreparedStatement pstmt = null;
	   ResultSet res = null;
       try {
           pstmt = conn.prepareStatement("SELECT login_id FROM student WHERE login_id = ?");
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
    * checkDuplicatePeriodOfStudy, takes a login ID, label and returns true if we already have that period of study stored
    * @param String loginID, the login ID we are pairing with label to check we don't have a duplicate
    * @param String label, the label we are pairing with login ID to check we don't have a duplicate
    * @return boolean, true if we already have the period of study stored
    */
   public boolean checkDuplicatePeriodOfStudy(int loginID, String perStudy) {
	   List<Integer> lIDS = new ArrayList<Integer>();
	   PreparedStatement pstmt = null;
	   ResultSet res = null;
       try {
           pstmt = conn.prepareStatement("SELECT login_id FROM student WHERE login_id = ? and label = ?");
           pstmt.setInt(1, loginID);
           pstmt.setString(2, perStudy);
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
    * checkDuplicateDegreeDepartment, takes a degree code, department code and returns true if we already have that degree department link stored
    * @param String degCode, the degree code we are pairing with department code to check we don't have a duplicate
    * @param String depCode, the department code we are pairing with degree code to check we don't have a duplicate
    * @return boolean, true if we already have the degree department link stored
    */
   public boolean checkDuplicateDegreeDepartment(String degCode, String depCode) {
	   List<String> dCS = new ArrayList<String>();
	   PreparedStatement pstmt = null;
	   ResultSet res = null;
       try {
           pstmt = conn.prepareStatement("SELECT degreeCode FROM degree_department WHERE degree_code = ? and department_code = ?");
           pstmt.setString(1, degCode);
           pstmt.setString(2, depCode);
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
    * checkDuplicateModuleDegree, takes a module code, degree code and returns true if we already have that module degree link stored
    * @param String modCode, the module code we are pairing with degree code to check we don't have a duplicate
    * @param String degCode, the degree code we are pairing with degree code to check we don't have a duplicate
    * @return boolean, true if we already have the module degree link stored
    */
   public boolean checkDuplicateModuleDegree(String modCode, String degCode) {
	   List<String> mCS = new ArrayList<String>();
	   PreparedStatement pstmt = null;
	   ResultSet res = null;
       try {
           pstmt = conn.prepareStatement("SELECT module_code FROM module_degree WHERE module_code = ? and degree_code = ?");
           pstmt.setString(1, modCode);
           pstmt.setString(2, degCode);
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
   
   /**
    * checkDuplicateModuleDegree, takes a module code, degree code and returns true if we already have that module degree link stored
    * @param String modCode, the module code we are pairing with degree code to check we don't have a duplicate
    * @param String degCode, the degree code we are pairing with degree code to check we don't have a duplicate
    * @return boolean, true if we already have the module degree link stored
    */
   public boolean checkDuplicateGrades(int studID, String modCode, String label) {
	   List<Integer> sID = new ArrayList<Integer>();
	   PreparedStatement pstmt = null;
	   ResultSet res = null;
       try {
           pstmt = conn.prepareStatement("SELECT student_id FROM grades WHERE student_id = ? and module_code = ? and label = ?");
           pstmt.setInt(1, studID);
           pstmt.setString(2, modCode);
           pstmt.setString(3, label);
           res = pstmt.executeQuery();
           while (res.next()) {
               sID.add(res.getInt(1));
           }
       }
       catch (SQLException e) {
           e.printStackTrace();
           sID.add(1);
       } 
       finally {
           closeResources(pstmt, res);
       }
       if (sID.size() > 0) {
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
}
