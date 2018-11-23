package src.controller;

import src.model.db_handler.*;
import src.objects.*;
import java.util.List;
import src.model.*;

/**
 * Controller.java
 * Contains methods called from GUI classes that handle dataflow to and from the database.
 * Methods call the appropriate methods from db_handler classes, and also is responsible
 * for system logic, like checking for SQL injection, or ensuring that appropriate parameters
 * are being passed.
 * */
public class Controller {
	//*******************************************
	//Private Controller methods (may be put into a different file at a later date)
	//*******************************************
	private static boolean passwordMatch(int loginID, String password) {
    	RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB()); //TODO put in own file
    	String[] passSalt = retrieveQ.getPassSalt(loginID);
		String hashedPass = CryptoModule.hashPassword(password, passSalt[1]);
		if (passSalt[0].equals(hashedPass)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	//TODO - auto generate module codes to minimise attack surface
	private static String generateModuleCode(String depCode) {
    	RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
		List<String> listModulesCodes = retrieveQ.retrieveDepartmentsModules(depCode);		
		//Now we must check all values	
		
		String moduleCode = "";
		
		
		return moduleCode;
	}
	
	
	
	
	
	
	
	//*******************************************
	//Public Controller methods
	//*******************************************
	
    /**
     * setCurrentUser, takes a login ID and sets the current user to the one linked to the login ID
     * @param loginID int, the login ID of the user we want to set as current user
     */
	public static void setCurrentUser(int loginID) {
		RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());	
		User user = retrieveQ.retrieveUser(loginID);
		Main.setUser(user);
	}
    
    public static String checkLogin(String loginID, String password) {
    	CheckQueries checkQ = new CheckQueries(Main.getDB());
    	//Check that we can turn login ID to an int
    	int loginInt;
    	String returnMessage = "";
    	if (!RegexTests.checkLoginID(loginID)) {
    		return "Incorrect Login ID format.";
    	}
    	else {
    		loginInt = Integer.parseInt(loginID);		
    	}
    	//Run the checks
    	if (!checkQ.checkLoginIDExists(loginInt)) {
    		returnMessage = "Incorrect User ID and Password combination.";
    	}
    	else if (!Controller.passwordMatch(loginInt, password)) {
    		returnMessage = "Incorrect User ID and Password combination.";
    	}
    	else {
    		returnMessage = "Accepted";
    	}
    	return returnMessage;
    }
    
    
    
    
    

	
    public static void saveDegree(String degreeCode, String degreeName, boolean masters, boolean yearIndustry) {
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addDegree(degreeCode, degreeName, masters, yearIndustry);
    }

    public static void saveDepartment(String departmentCode, String departmentName) {
        // TODO maybe have logic to check that the parameters are the right length?
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addDepartment(departmentCode, departmentName);
    }

    
    
    public static String saveModule(String moduleCode, String moduleName, int credits, int semester, List<ModuleDegree> moduleDegreeList, int priv) {
        //Get our databases initialised
    	
    	String returnMessage = "";
        
        //Lets start our if statements
        if (priv != 4) {
        	returnMessage = "Denied - Insufficient privelige.";
        }
        else if (!RegexTests.checkModuleCode(moduleCode)) {
        	returnMessage = "Incorrect module code format.";
        }
        else if (returnMessage.equals("q")/* check unique module code*/) {
        	returnMessage = "Module Code already exists.";
        }
        else if (moduleName.length() > 100) { 
        	returnMessage = "Module name too long.";
        }
        else if (returnMessage.equals("q")/*Check credits are an int in range 0 -  120*/) {
        	
        }
        else if (returnMessage.equals("q")/* not sure I need to check this?*/) {
        	
        }
        else {
        	//Store in the database
        }
            
        
        
        
        //Module needs to be added to the module table and the module_degree table
        
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addModule(moduleCode, moduleName, credits, semester);
        return returnMessage;
    }

    public static void saveDepartmentAssociation(String degreeCode, String departmentCode, boolean lead) {
        // TODO maybe have logic to check that the parameters are the right length?
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addDepartmentAssociation(degreeCode, departmentCode, lead);
    }

    public static void saveModuleAssociation(String moduleCode, String moduleName, String level, boolean core) {
        // TODO maybe have logic to check that the parameters are the right length?
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addModuleDegreeAssociation(moduleCode, moduleName, level, core);
    }

    public static List<Degree> getDegrees() {
        RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
        return retrieveQ.retrieveDegreesTable();
    }

    public static List<Department> getDepartments() {
        RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
        return retrieveQ.retrieveDepartmentTable();
    }

    public static List<Module> getModules() {
        RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
        return retrieveQ.retrieveModuleTable();
    }

    public static List<User> getUsers() {
        RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
        return retrieveQ.retrieveUsersTable();
    }

    public static List<Student> getStudents() {
	    RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
	    return retrieveQ.retrieveStudentsTable();
    }

    public static void removeDepartment(String code) {
        RemovalQueries removalQ = new RemovalQueries(Main.getDB());
        removalQ.removeDepartment(code);
    }

    public static void removeModule(String code) {
        RemovalQueries removalQ = new RemovalQueries(Main.getDB());
        removalQ.removeModule(code);
    }

    public static void removeUser(String login) {
        RemovalQueries removalQ = new RemovalQueries(Main.getDB());
        removalQ.removeUser(login);
    }

    public static void removeStudent(int login) {
	    RemovalQueries removalQ = new RemovalQueries(Main.getDB());
	    removalQ.removeStudent(login);
    }

    /**
	 * removeDegree first ensures that there are no students currently taking that degree, if not, then it deletes
	 * the degree.
	 * @param degree_code String representing the degree that should be deleted.
	 * @return boolean that returns false if the degree is not allowed to be deleted and hasn't been, true if it
	 * is allowed to be deleted and has been.
	 * */
    public static boolean removeDegree(String degree_code) {
        RemovalQueries removeQ = new RemovalQueries(Main.getDB());
        RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
        boolean deletionAllowed = retrieveQ.allowedToDeleteDegree(degree_code);
        // if there aren't any associated users, delete the degree
        if(deletionAllowed){
            System.out.println("deleting the degree");
            removeQ.removeDegree(degree_code);
        }
		return deletionAllowed; // returns true if the degree was deleted, false otherwise
    }



    public static void saveUser(int login, String pass, int priv) {
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        String salt = CryptoModule.generateSalt();
        String hashedPassword = CryptoModule.hashPassword(pass, salt);
        additionQ.addUser(login, salt, hashedPassword, priv);
    }

    /**public static void saveStudent(int login, String title, String forename, String surname, String tutor,
                                   String email, String degree, String label, String level, String startDate, String endDate) {
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        DateFormat df = DateFormat.getDateInstance();
        start = df.parse(df);
        additionQ.addStudent(login, title, forename, surname, tutor, email, degree);
    }*/
    
    
    
    //********************************************************
    //Methods to check user input
    //********************************************************
    
    //Level 4 inputs
    
    public static String checkInputUser(int loginID, String password, String confirmPassword, int priv) {
    	CheckQueries retrieveQ = new CheckQueries(Main.getDB());
    	String returnMessage = "";
		if (priv != 4) {
			returnMessage = "Insufficient privilege for this opperation.";
		}		
		else if (password != confirmPassword) {
			returnMessage = "Passwords do not match.";
		}
		else if (!RegexTests.checkPassword(password)) {
			returnMessage = "Incorrect password format."; //must contain 1 uppercase, 1 lowercase, 1 symbol
		}
		else if (!RegexTests.checkLoginID(Integer.toString(loginID))) {
			returnMessage = "Incorrect LoginID format.";
		}
		else if (retrieveQ.checkDuplicateUser(loginID)) {
			returnMessage = "LoginID already in use.";
		}
		else {
			returnMessage = "Accepted";
		}
		return returnMessage;
	}
    
    //TODO maybe add check to see if a department name is duplicated (very QOL)
    public static String checkInputDepartment(String depCode, String depName, int priv) {
    	CheckQueries retrieveQ = new CheckQueries(Main.getDB());
    	String returnMessage = "";
    	if (priv != 4) {
			returnMessage = "Insufficient privilege for this opperation.";
    	}
		else if (!RegexTests.checkDepartmentCode(depCode)) {
			returnMessage = "Incorrect department code format.";
		}
		else if (retrieveQ.checkDuplicateDepartment(depCode)) {
			returnMessage = "Department code already in use";
		}
		else if (depName.length() > 100) {
			returnMessage = "Department name is too long.";
		}
		else {
			returnMessage = "Accepted";
		}
    	return returnMessage;
    }
    
    //TODO add checks for the list of linked departments
    public static String checkInputDegree(String degCode, String degName, List<DegreeDepartment> degDep, int priv) {
    	CheckQueries retrieveQ = new CheckQueries(Main.getDB());
    	String returnMessage = "";
    	if (priv != 4) {
			returnMessage = "Insufficient privilege for this opperation.";
    	}
    	else if (!RegexTests.checkDegreeCode(degCode)) {
    		returnMessage = "Incorrect degree code format.";
    	}
    	else if (retrieveQ.checkDuplicateDegree(degCode)) {
    		returnMessage = "Degree code already in use";
    	}
    	else if (degName.length() > 100) {
    		returnMessage = "Degree name is too long.";
    	}
    	else if (returnMessage.equals("A")) {
    		//TODO check for each linked department, the linkage is unique, the departments exist
    	}
    	else {
    		returnMessage = "Accepted";
    	}
    	return returnMessage;
    	
    }
    
    
    
    
}
