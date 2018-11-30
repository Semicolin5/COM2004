package src.controller;

import src.model.db_handler.*;
import src.objects.*;

import java.util.ArrayList;
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
    
	/**
	 * checkLogin, takes a login ID and a password and returns a String saying Accepted if they can login and an error message else
	 * @param String loginID, the login ID of the user we want to log in as 
	 * @param String password, the password of the user of we want to log in as
	 * @return String, Accepted if correct username and password combo else error message 
	 */
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

    public  static  void saveBlankGrades(String studentId, String moduleCode, String label){
		AdditionQueries additionQ = new AdditionQueries(Main.getDB());
		additionQ.addStudentModuleAssociation(studentId, moduleCode, label);
	}

    public static void saveDegree(String degreeCode, String degreeName, boolean masters, boolean yearIndustry) {
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addDegree(degreeCode, degreeName, masters, yearIndustry);
    }

    public static void saveDepartment(String departmentCode, String departmentName) {
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addDepartment(departmentCode, departmentName);
    }

    public static void saveModule(String moduleCode, String moduleName, int credits, int semester) {
    	AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addModule(moduleCode, moduleName, credits, semester);
    }

    public static void saveDepartmentAssociation(String degreeCode, String departmentCode, boolean lead) {
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addDepartmentAssociation(degreeCode, departmentCode, lead);
    }

    public static void saveModuleAssociation(String moduleCode, String moduleName, String level, boolean core) {
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

	public static List<ModuleDegree> getModuleDegrees() {
		RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
		return retrieveQ.retrieveModuleLinkDegreeTable();
	}
	
	public static List<ModuleDegree> getModulesDegreeLevel(String degCode, String levelOfStudy ) {
		RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
		return retrieveQ.retrieveModuleLinkDegreeTable();
	}

    public static List<User> getUsers() {
        RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
        return retrieveQ.retrieveUsersTable();
    }

    public static List<Student> getStudents() {
	    RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
	    return retrieveQ.retrieveStudentsTable();
    }

    public static Student getStudent(int loginID) {
		RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
		return retrieveQ.retrieveStudent(loginID);
	}

    public static List<PeriodOfStudy> getPeriodsOfStudy() {
		RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
		return retrieveQ.retrievePeriodOfStudyTable();
	}

	public static PeriodOfStudy getLatestPeriodOfStudy(int loginID) {
		RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
		return retrieveQ.retrieveLatestPeriodOfStudy(loginID);
	}

	public static List<Module> getStudentModules(int loginID) {
		RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
		return retrieveQ.retrieveStudentsModules(loginID);
	}

	public static Grade getStudentModuleGrades(int loginID, String moduleCode) {
		RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
		return retrieveQ.retrieveStudentsModuleGrade(loginID, moduleCode);
	}

    public static void removeModule(String code) {
        RemovalQueries removalQ = new RemovalQueries(Main.getDB());
        removalQ.removeModule(code);
    }

    
    /**
     * getModuleOptions, takes a students user ID and returns a list of modules which they can take
     * @param int studentID, the student ID we want to search on 
     * 
     * @return List<Module>, the list of modules the selected student can take
     */
    public static List<Module> getModuleOptions(int studentID) {
    	String levelOfStudy = getLatestPeriodOfStudy(studentID).getLevelOfStudy();
    	
    	
    	
    	
    	
    	
    	return null;
    }
    
    
    
    /**
     * getPeriodsOfStudent
     * @param studentID int, the login id for a student
     * @return List<PeriodOfStudy> is a list of all the periods of study that the student has experienced.
     * */
    public static List<PeriodOfStudy> getPeriodsOfStudyForStudent(int studentID) {
        RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
        return retrieveQ.retrievePeriodOfStudyForStudent(studentID);
    }

	/**
	 * Overloaded version of above function, filters and only returns POS with specified label
	 * @param studentID student login ID
	 * @param label period of study to fetch
	 * @return PeriodOfStudy object
	 */
    public static PeriodOfStudy getPeriodsOfStudyForStudent(int studentID, String label) {
    	RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
    	return retrieveQ.retrievePeriodOfStudyForStudent(studentID, label);
	}

    /**
     * getStudentsGradeAtPeriod given a period of study, and a student, returns their grades in an ArrayList.
     * @param studentID int, the login id for the student
     * @param label, String of length 1, representing hte
     * */
    public static List<Grade> getStudentsGradeAtPeriod(int studentID, String label) {
       RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
       return retrieveQ.retrieveGradeAtPeriodOfStudy(studentID, label);
    }

    /**
     * Only the Administrator (privilege level 4) should be able to access this method.
     * removeUser removes users. First it is ensured that the user to be deleted isn't also a student. Since only Administrators
     * are able to delete users, and only, registrars are allowed to delete students, the two cases are separated.
     * @param login String for the users login id.
     * @boolean returns true if the user was allowed to be removed, and hence was removed. However, returns false
     * if the user was a student, and the user remains in the table.
     * */
    public static boolean removeUser(String login) {
        RemovalQueries removalQ = new RemovalQueries(Main.getDB());
        CheckQueries checkQ = new CheckQueries(Main.getDB());
        // if there aren't any associate
        boolean deletionAllowed = checkQ.checkDeleteUser(login);
        if (deletionAllowed) {
           removalQ.removeUser(login);
        }
        return deletionAllowed;
    }

    /**
     * Only the Registrar (privilege level 3) should be able to access this method.
     * @param login String representing the user to remove.
     * For the selected student, this method deletes:
     *      1) the students grades/module association from the grades table
     *      2) the students period of study information from the period_of_study table
     *      3) the students reference in the student table
     *      4) the students user details in the user table
     * */
    public static void removeStudent(int login) {
	    RemovalQueries removalQ = new RemovalQueries(Main.getDB());
	    removalQ.removeStudent(login);
    }

    /**
     * Only the Registrar (privilege level 3) should be able to access this method.
     * @param login String representing the user to remove.
     * For the selected student, this method deletes:
     *      1) the students grades/module association from the grades table
     *      2) the students period of study information from the period_of_study table
     *      3) the students reference in the student table
     *      4) the students user details in the user table
     * */
    public static void removeGrades(int login, String module_code, String label) {
        RemovalQueries removalQ = new RemovalQueries(Main.getDB());
        removalQ.removeGrades(login, module_code, label);
    }

    /**
	 * removeDegree first ensures that there are no students currently taking that degree, if not, then it deletes
	 * the degree.
	 * @param degree_code String representing the degree that should be deleted.
	 * @return boolean that returns false if the degree is not allowed to be deleted and hence has not been, true if it
	 * is allowed to be deleted and has been.
	 * */
    public static boolean removeDegree(String degree_code) {
        RemovalQueries removeQ = new RemovalQueries(Main.getDB());
        CheckQueries checkQ = new CheckQueries(Main.getDB());
        boolean deletionAllowed = checkQ.checkDeleteDegree(degree_code);
        // if there aren't any associated users, delete the degree
        if(deletionAllowed){
            removeQ.removeDegree(degree_code);
        }
		return deletionAllowed; // returns true if the degree was deleted, false otherwise
    }

    /**
	 * removeDepartment first ensures that there are no degrees currently associated with the department, if not,
	 * the department is deleted.
	 * @param code String represents the department that should be deleted.
     * @return boolean that returns false if the department is not allowed to be delted and hence has not been, true if it is
     * allowed to be deleted and has been.
	 * */
	public static boolean removeDepartment(String code) {
        RemovalQueries removalQ = new RemovalQueries(Main.getDB());
        CheckQueries checkQ = new CheckQueries(Main.getDB());
        boolean deletionAllowed = checkQ.checkDeleteDepartment(code);
        // if there aren't any associated degrees, delete the department
        if(deletionAllowed){
           removalQ.removeDepartment(code);
        }
        return deletionAllowed;
    }

    public static void saveUser(int login, String pass, int priv) {
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        String salt = CryptoModule.generateSalt();
        String hashedPassword = CryptoModule.hashPassword(pass, salt);
        additionQ.addUser(login, salt, hashedPassword, priv);
    }

    public static void saveStudent(int loginID, String password, String title,
								   String forename, String surname, String tutor,
								   String email, String degreeCode, String degreeLevel,
								   String posLabel, String startDate, String endDate) {
		AdditionQueries additionQ = new AdditionQueries(Main.getDB());

		String salt = CryptoModule.generateSalt();
		String hashedPassword = CryptoModule.hashPassword(password, salt);

		additionQ.addStudent(loginID, hashedPassword, salt, title, forename, surname,
				tutor, email, degreeCode);
		additionQ.addPeriodOfStudy(loginID, posLabel, startDate, endDate, degreeLevel);
	}

	public static void updateGrades(int loginID, String moduleCode, String label,
									Float initialGrade, Float resitGrade, Float repeatGrade) {
		AdditionQueries additionQ = new AdditionQueries(Main.getDB());
		additionQ.updateGrade(loginID, moduleCode, label, initialGrade, resitGrade, repeatGrade);
	}
    
    //********************************************************
    //Methods to auto generate input
    //********************************************************
	//TODO - auto generate module codes to minimise attack surface
	public static String generateModuleCode(String depCode) {
    	RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
		List<String> listModulesCodes = retrieveQ.retrieveDepartmentsModules(depCode);		
		//Now we must check all values	
		
		String moduleCode = "";
		
		
		return moduleCode;
	}
	
	/**
	 * generateEmail takes a forename and a surname and generates a unique email
	 * @param forename String, the forename of the email we are generating
	 * @param surname String, the surname of the email we are generating
	 * @return String, the unique email that we have generated
	 */
	public static String generateEmail(String forename, String surname) {
		RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
		String email = "";
		String emailStart = forename.substring(0, 1) + surname;
		List<String> storedEM = retrieveQ.retrieveEmails();		
		
		boolean loopInv = true;
		int i = 1;
		while (loopInv) {
			email = (emailStart + String.valueOf(i) + "@sheffieldringroad.ac.uk").toLowerCase();
			
			if (!storedEM.contains(email)) {
				//We do not have that email stored => we can use it!
				loopInv = false;
			}
			
			i ++;
		}

		return email;
	}
	
	
	/**
	 * assignCoreModules, automatically assigns the core modules to the student when they are signed up to a new degree
	 */
	public static void assignCoreModules(int studentID, int studentLevel) {
		PeriodOfStudy pos = getLatestPeriodOfStudy(studentID);
		for (Student student : Controller.getStudents()) {
			if (student.getLogin().equals(String.valueOf(studentID))) {
				//Retrieves a list of all modules the selected student is eligible for and assigns core modules to student.
				for (ModuleDegree m : Controller.getModuleDegrees()) {
					if (m.getDegreeCode().equals(student.getDegreeCode()) && (m.getDegreeLevel().equals(String.valueOf(studentLevel)))) {
						for (Module mod : Controller.getModules()) {
							if (mod.getCode().equals(m.getModuleCode())) {
								if (m.isCore())
									Controller.saveBlankGrades(String.valueOf(studentID), mod.getCode(), pos.getLabel());
							}
						}
					}
				}
			}
		}
		
		//Then assign those core modules to the student (maybe wipe the table of module associations first? or check)
		
		
		
		
		
	}
    
    
    
    
    //********************************************************
    //Methods to check user input
    //********************************************************
    
    //Level 4 inputs
    
    public static String checkInputUser(int loginID, String password, String confirmPassword, int priv) {
    	CheckQueries checkQ = new CheckQueries(Main.getDB());
    	String returnMessage = "";
		if (priv != 4) {
			returnMessage = "Insufficient privilege for this opperation.";
		}		
		else if (!password.equals(confirmPassword)) {
			returnMessage = "Passwords do not match.";
		}
		else if (!RegexTests.checkPassword(password)) {
			returnMessage = "Incorrect password format."; //must contain at least 1 uppercase, 1 lowercase, 1 symbol, 1 number and between 8-16 chars long
		}
		else if (!RegexTests.checkLoginID(Integer.toString(loginID))) {
			returnMessage = "Incorrect LoginID format.";
		}
		else if (checkQ.checkDuplicateUser(loginID)) {
			returnMessage = "LoginID already in use.";
		}
		else {
			returnMessage = "Accepted";
		}
		return returnMessage;
	}
    
    public static String checkInputDepartment(String depCode, String depName, int priv) {
    	CheckQueries checkQ = new CheckQueries(Main.getDB());
    	String returnMessage = "";
    	if (priv != 4) {
			returnMessage = "Insufficient privilege for this opperation.";
    	}
		else if (!RegexTests.checkDepartmentCode(depCode)) {
			returnMessage = "Incorrect department code format.";
		}
		else if (checkQ.checkDuplicateDepartment(depCode)) {
			returnMessage = "Department code already in use";
		}
		else if (depName.length() > 100) {
			returnMessage = "Department name is too long.";
		}
		else if (!RegexTests.checkTitle(depName)) {
			returnMessage = "Please only use special characters . ) ( & for department name.";
		}
		else {
			returnMessage = "Accepted";
		}
    	return returnMessage;
    }
    
    public static String checkInputDegree(String degCode, String degName, boolean masters, int priv) {
    	CheckQueries checkQ = new CheckQueries(Main.getDB());
    	String returnMessage = "";
    	if (priv != 4) {
			returnMessage = "Insufficient privilege for this opperation.";
    	}
    	else if (!RegexTests.checkDegreeCode(degCode)) {
    		returnMessage = "Incorrect degree code format.";
    	}
    	else if (checkQ.checkDuplicateDegree(degCode)) {
    		returnMessage = "Degree code already in use";
    	}
    	else if (degName.length() > 100) {
    		returnMessage = "Degree name is too long.";
    	}
		else if (!RegexTests.checkTitle(degName)) {
			returnMessage = "Please only use special characters . ) ( & for degree name.";
		}
    	else if (masters && degCode.substring(3, 4).equals("U")) {
    		returnMessage = "Masters degrees should have a P in the middle of their code.";
    	}
    	else if (!masters && degCode.substring(3, 4).equals("P")) {
    		returnMessage = "Undergraduate degrees should have a U in the middle of their code.";
    	}
    	else {
    		returnMessage = "Accepted";
    	}
    	return returnMessage;
    	
    }
    
    public static String checkInputModule(String modCode, String modName, String credits, int priv) {
    	CheckQueries checkQ = new CheckQueries(Main.getDB());
    	String returnMessage = "";
    	if (priv != 4) {
			returnMessage = "Insufficient privilege for this opperation.";
    	}
    	else if (!RegexTests.checkModuleCode(modCode)) {
			returnMessage = "Incorrect module code format.";
    	}
    	else if (checkQ.checkDuplicateModule(modCode)) {
			returnMessage = "Module code already exists.";
    	}
    	else if (modName.length() > 100) {
			returnMessage = "Module name is too long.";
    	}
		else if (!RegexTests.checkTitle(modName)) {
			returnMessage = "Please only use special characters . ) ( & for module name.";
		}
    	else if(!RegexTests.checkModuleCredits(credits)) {
    		returnMessage = "Credits should be a valid number.";
    	}
    	else if (Integer.parseInt(credits) < 0 || Integer.parseInt(credits) > 120) {
    		returnMessage = "Credits value shoul be between 0 and 120";
    	}
    	else {
    		returnMessage = "Accepted";
    	}
    	return returnMessage;
    }
    
    //Level 3 inputs
    
    public static String checkInputStudent(String studNo, String forename, String surname, String personalT, String password,  String confirmPassword, int priv) {
    	CheckQueries checkQ = new CheckQueries(Main.getDB());
    	String returnMessage = "";
    	
    	if (priv < 3) {
			returnMessage = "Insufficient privilege for this opperation.";
    	}
		else if (!RegexTests.checkLoginID(studNo)) {
			returnMessage = "Incorrect Student number format.";
		}
		else if (checkQ.checkDuplicateUser(Integer.parseInt(studNo))) {
			returnMessage = "Student number is already in use.";
		}
		else if (forename.length() > 50) {
			returnMessage = "Forename is too long.";
		}
		else if (!RegexTests.checkName(forename)) {
			returnMessage = "Forename cannot contain special characters or spaces.";
		}
		else if (surname.length() > 50) {
			returnMessage = "Surname is too long.";
		}
		else if (!RegexTests.checkName(surname)) {
			returnMessage = "Surname cannot contain special characters or spaces.";
		}
		else if (personalT.length() > 50) {
			returnMessage = "Personal tutor name is too long.";
		}
		else if (!RegexTests.checkFullName(personalT)) {
			returnMessage = "Personal tutor cannot contain special characters";
		}
		else if (!password.equals(confirmPassword)) {
			returnMessage = "Passwords do not match.";
		}
		else if (!RegexTests.checkPassword(password)) {
			returnMessage = "Incorrect password format."; //must contain at least 1 uppercase, 1 lowercase, 1 symbol, 1 number and between 8-16 chars long
		}
		else {
			returnMessage = "Accepted";
		}   	
    	return returnMessage;
    }
    
      
    //A private checking method
	private static boolean passwordMatch(int loginID, String password) {
    	RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
    	String[] passSalt = retrieveQ.getPassSalt(loginID);
		String hashedPass = CryptoModule.hashPassword(password, passSalt[1]);
		if (passSalt[0].equals(hashedPass)) {
			return true;
		}
		else {
			return false;
		}
	}

    
}
