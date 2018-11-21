package src.model.db_handler;
import java.sql.*;
import java.util.*;


/**
 * RetrieveQueries.java
 * extension of Queries, used to encapsulate the retrieval methods of SQL.
 * */
public class CheckQueries extends Queries {

	// constructor is passed a DatabaseHandler object from which it obtains the Connection object
    public CheckQueries (DatabaseHandler db) {
        super(db);
    }
    
   /**
    * checkDuplicateLoginID, takes a login ID and returns true if we already have one
    * @param int loginID, the loginID we are checking we don't have duplicate of
    * @return boolean, true if we already have the user ID stored
    */
   public boolean checkDuplicateLoginID(int loginID) {
	   List<Integer> lIDS = new ArrayList<Integer>();
	   PreparedStatement pstmt = null;
	   ResultSet res = null;
       if (super.getPriv() == 0) {
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
               lIDS.add(1);  //This just stops some funky stuff from happening maybe will TODO later
           } finally {
               closeResources(pstmt, res);
           }
       }
       if (lIDS.size() > 0) {
    	   return true;
       }
       else {
    	   return false;
       }
   }
   
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
           lIDS.add(1);  //This just stops some funky stuff from happening maybe will TODO later
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
