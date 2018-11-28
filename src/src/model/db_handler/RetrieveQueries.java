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
        
        return modules;
    }
    
    public List<String> retrieveDepartmentCoreModulesForLOS(String depCode, String pos) {
        List<String> modules = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
            pstmt = conn.prepareStatement("SELECT module_code FROM module_degree WHERE degree_code = ? AND core = true AND  ");
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

    /**
     * retrievePeriodOfStudyForStudent finds all the periods that a targeted student has experienced.
     * @param studentID, int representing the student
     * @return List<PeriodOfStudy>
     * */
    public List<PeriodOfStudy> retrievePeriodOfStudyForStudent(int studentID) {
        List<PeriodOfStudy> table = new ArrayList<PeriodOfStudy>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement("SELECT * FROM period_of_study WHERE login_id=?");
            pstmt.setInt(1, studentID);
            rs = pstmt.executeQuery();
            while(rs.next()){
               table.add(new PeriodOfStudy(rs.getString(1), rs.getString(2),
                       rs.getDate(3), rs.getDate(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, rs);
        }
        return table;
    }

    /**
     * Overloaded version of above function, filters and returns POS specified by label
     * @param studentID student login ID
     * @param label period of study to fetch
     * @return PeriodOfStudy object
     */
    public PeriodOfStudy retrievePeriodOfStudyForStudent(int studentID, String label) {
        PeriodOfStudy periodOfStudy = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement("SELECT * FROM period_of_study WHERE login_id=? AND label=?");
            pstmt.setInt(1, studentID);
            pstmt.setString(2, label);
            rs = pstmt.executeQuery();
            if(rs.next()){
                periodOfStudy = new PeriodOfStudy(rs.getString(1), rs.getString(2),
                        rs.getDate(3), rs.getDate(4), rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, rs);
        }
        return periodOfStudy;
    }

    /**
     * retrievePeriodOfStudyTable obtains all the periods from the database.
     * @return List<PeriodOfStudy> all the period of study objects
     * */
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
    
    public PeriodOfStudy retrieveLatestPeriodOfStudy(int loginID) {
        PeriodOfStudy periodOfStudy = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try {
     	   pstmt = conn.prepareStatement("SELECT * FROM period_of_study WHERE login_id = ? ORDER BY label DESC");
     	   pstmt.setInt(1, loginID);
           res = pstmt.executeQuery();

           if(res.next()) {
               periodOfStudy = new PeriodOfStudy(res.getString(1), res.getString(2),
                       res.getDate(3), res.getDate(4), res.getString(5));
           }
        }
        catch (SQLException e) {
     	   e.printStackTrace();
        }
        finally {
     	   closeResources(pstmt, res);
        }
    	return periodOfStudy;
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

	public Student retrieveStudent(int loginID) {
	    Student student = null;
	    PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement("SELECT * FROM student WHERE login_id = ?");
            pstmt.setInt(1, loginID);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                student = new Student(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeResources(pstmt, rs);
        }
        return student;
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
        Grade grades = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
           pstmt = super.conn.prepareStatement("SELECT * FROM grades WHERE login_id=? AND module_code=? ORDER BY label ASC");
           pstmt.setInt(1, login);
           pstmt.setString(2, module);
           rs = pstmt.executeQuery();

           rs.next();
           grades = new Grade(rs.getString(1), rs.getString(2), rs.getString(3).charAt(0),
                   rs.getFloat(4), rs.getFloat(5), -1);

           if(rs.next()) {
               grades.setRepeatPercent(rs.getFloat(4));
           }

        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
            closeResources(pstmt, rs);
        }
        return grades;
    }

    /**
     * retrieveGradeAtPeriodOfStudy is similar to retrieveStudentsModuleGrade. It allows for a different selection,
     * so that given a student and a label, the grades for that period of study can be retrieved, for all modules.
     * @param login, int representing the students login.
     * @param label, String (of length 1) is the label for a period of study.
     * @return List<Grade> Grade objects, one for each module taken.
     * */
    public List<Grade> retrieveGradeAtPeriodOfStudy(int login, String label) {
        List<Grade> table = new ArrayList<Grade>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
           pstmt = super.conn.prepareStatement("SELECT * FROM grades WHERE login_id=? AND label=?");
           pstmt.setInt(1, login);
           pstmt.setString(2, label);
           rs = pstmt.executeQuery();
           while(rs.next()) { // construct a grade object for each module taken at that period
               table.add(new Grade(rs.getString(1), rs.getString(2), rs.getString(3).charAt(0),
                       rs.getFloat(4), rs.getFloat(5), -1));
           }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, rs);
        }
        return table;
    }

	/**
	* getPassSalt, takes a loginID and returns the associated hashed password and salt
	* @param loginID, int, the users loginID
	* @return String[], returns the hashed password and salt
	*/
	public String[] getPassSalt(int loginID) {
	   String[] passSalt = new String[2];
	   PreparedStatement pstmt = null;
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
    * retrieveEmails, retrieves a list of all stored emails
    * @param List<String>, the list of emails as strings
    */
   public List<String> retrieveEmails() {
       List<String> emails = new ArrayList<String>();
       PreparedStatement pstmt = null;
       ResultSet res = null;
       try {
           // obtain references to the modules that a student takes
           pstmt = conn.prepareStatement("SELECT email FROM student");
           res = pstmt.executeQuery();
           while(res.next()) {
        	   emails.add(res.getString(1));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           closeResources(pstmt, res);
       }
       return emails; 
   }
   
   
   /*
   public List<Module> retrieveTakenModules() {
	   
	   
	   
	   
   }
   */
   
   
   
}
