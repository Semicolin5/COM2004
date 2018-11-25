package src.model.db_handler;
import java.sql.*;
import src.objects.*;
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
     * @return degreeTable, list of Degree objects, with all their accessible values set.
     * */
    public List<Degree> retrieveDegreesTable() {
        List<Degree> degreeTable = new ArrayList<Degree>();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            pstmt = conn.prepareStatement("SELECT * FROM degree");
            res = pstmt.executeQuery();
            while (res.next()) {
                String degCode = res.getString(1);
                // for each degree, find the associated departments
                degreeTable.add(new Degree(degCode, res.getString(2),
                        res.getBoolean(3), res.getBoolean(4),
                        retrieveAffiliatedLeadDep(degCode), retrieveAffiliatedNonLeadDep(degCode)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, res);
        }
        return degreeTable;
    }

    /**
     * Helper function used by retrieveDegreeTable to find affiliated non-lead departments for each degree
     * @param code; String of degree whose Departments are to be found
     * @return nonLead; List of department objects representing the non-lead departments for the degree.
     * */
    private List<Department> retrieveAffiliatedNonLeadDep(String code) {

        List<Department> nonLead = new ArrayList<Department>();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            pstmt = conn.prepareStatement("SELECT * FROM department d " +
                    "INNER JOIN degree_department dd WHERE d.department_code = dd.department_code " +
                    "AND dd.degree_code = ? AND dd.lead_department=0");
            pstmt.setString(1, code);
            res = pstmt.executeQuery();
            while(res.next()){
                nonLead.add(new Department(res.getString(1), res.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, res);
        }
        return nonLead;
    }

    /**
     * Helper function used by retrieveDegreeTable to find affiliated lead department for each degree
     * @param code; String of degree whose lead Department is to be found
     * @return lead; Department object representing the lead departmnet
     * */
    private Department retrieveAffiliatedLeadDep(String code) {

        Department lead = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        // obtain the lead department.
        try {
            pstmt = conn.prepareStatement("SELECT * FROM department d " +
                    "INNER JOIN degree_department dd WHERE d.department_code = dd.department_code " +
                    "AND dd.degree_code = ? AND dd.lead_department=1");
            pstmt.setString(1, code);
            res = pstmt.executeQuery();
            while(res.next()){
                lead = new Department(res.getString(1), res.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, res);
        }
        return lead;
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
    
    public List<ModuleDegree> retrieveModuleLinkDegreeTable() {
        List<ModuleDegree> moduleDegreeTable = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
     	   pstmt = conn.prepareStatement("SELECT * FROM module_degree");
            res = pstmt.executeQuery();
            while (res.next()) {
         	   moduleDegreeTable.add(new ModuleDegree(res.getString(1), res.getString(2),
                res.getString(3), res.getBoolean(4)));
            }
        }
        catch (SQLException e) {
     	   e.printStackTrace();
        }
        finally {
     	   closeResources(pstmt, res);
        }
    	return moduleDegreeTable;
    }

    public List<PeriodOfStudy> retrievePeriodOfStudyTable() {
        List<PeriodOfStudy> table = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
     	   pstmt = conn.prepareStatement("SELECT * FROM period_of_study");
           res = pstmt.executeQuery();
           while (res.next()) {
               table.add(new PeriodOfStudy(res.getString(1), res.getString(2),
                       res.getDate(3), res.getDate(4), res.getString(5)));
            }
        }
        catch (SQLException e) {
     	   e.printStackTrace();
        }
        finally {
     	   closeResources(pstmt, res);
        }
    	return table;
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

	public List<Student> retrieveStudentsTable() {
	   List<Student> studentTable = new ArrayList<>();
	   PreparedStatement pstmt = null;
	   ResultSet res = null;
	   try {
		   pstmt = conn.prepareStatement("SELECT * FROM student");
	       res = pstmt.executeQuery();
	       while (res.next()) {
	    	   studentTable.add(new Student(res.getString(1), res.getString(2),
	                   res.getString(3), res.getString(4), res.getString(5),
	                   res.getString(6), res.getString(7)));
	       }
	   }
	   catch (SQLException e) {
		   e.printStackTrace();
	   }
	   finally {
		   closeResources(pstmt, res);
	   }
	   return studentTable;
	}

   /**
    * retrieveUser, given a login ID retrieves the users object including their privilege
    * @param loginID, int, the login ID of the user we want to retrieve
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
    * retrieveStudentsModules, given a student's loginID, this function returns all the modules that that student is
    * taking, and has previously taken as a list of Module objects.
    * @param login int of the Student's login id.
    * @return List<Module>
    * */
    public List<Module> retrieveStudentsModules(int login) {
        List<Module> studentModules = new ArrayList<Module>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // obtain references to the modules that a student takes
            pstmt = conn.prepareStatement("SELECT module_code FROM grades WHERE login_id=? ");
            pstmt.setInt(1, login);
            rs = pstmt.executeQuery();
            ResultSet moduleDetails = null;
            while(rs.next()) {
                pstmt = conn.prepareStatement("SELECT * FROM module WHERE module_code=?");
                pstmt.setString(1, rs.getString(1));
                moduleDetails = pstmt.executeQuery();
                // build a Module object, and add it to the Module list
                if(moduleDetails.next()) {
                    studentModules.add(new Module(moduleDetails.getString(1), moduleDetails.getString(2),
                            moduleDetails.getInt(3), moduleDetails.getInt(4)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, rs);
        }
        return studentModules;
    }

    /**
     * retrieveStudentsModuleGrade, given a student, and a module that they take, this method
     * returns their grades as a Grade object.
     * @param login, int, the users loginID
     * @param module, String, the module that the student takes/has taken
     * @return Grade object
     * */
    public Grade retrieveStudentsModuleGrade(int login, String module){
        Grade studentsGrade = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
           pstmt = super.conn.prepareStatement("SELECT * FROM grades WHERE login_id=? AND module_code=?");
           pstmt.setInt(1, login);
           pstmt.setString(2, module);
           rs = pstmt.executeQuery();
           if(rs.next()) {
               studentsGrade = new Grade(rs.getInt(1), rs.getString(2), rs.getString(3).charAt(0),
                       rs.getFloat(4), rs.getFloat(5));
           }
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
            closeResources(pstmt, rs);
        }
        return studentsGrade;
    }

	/**
	* getPassSalt, takes a loginID and returns the associated hashed password and salt
	* @param loginID, int, the users loginID
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
	
   /**
    * Returns true if the degree is 'allowed' to be deleted (i.e. has no student affiliations, and has no
    * module affiliations).
    * @param degree_code String representing the degree code to be deleted.
    * @return boolean of whether the degree was deleted and allowed to be deleted
    * */
   public boolean allowedToDeleteDegree(String degree_code) {
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
	public boolean allowedToDeleteDepartment(String department_code) {
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
   public boolean allowedToDeleteUser(String login_id) {
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
