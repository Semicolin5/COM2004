package src.controller;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import com.sun.org.apache.xpath.internal.operations.Mod;
import src.model.db_handler.*;
import src.objects.*;

import java.sql.Date;
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
	 * @param loginID String, the login ID of the user we want to log in as
	 * @param password String, the password of the user of we want to log in as
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
    
    public static Degree getDegree(String degCode) {
        RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
        return retrieveQ.retrieveDegree(degCode);
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
	
	/*public static List<ModuleDegree> getModulesDegreeLevel(String degCode, String levelOfStudy ) {
		RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
		return retrieveQ.retrieveModuleLinkDegreeTable();
	}*/

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

	/**
     * getStudentModuleGrades is used by the Teacher to obtain the initial, resit, and repeat percents for a
     * module a student took. This information is encapsulated in a List that contains Grade objects for a student
     * and the module.
     * @param loginID, int representing the targeted user
     * @param moduleCode, String representing the module
     * @return List<Grade>, maximum size of two items, repeat Grade and initial Grade, in that order.
     * */
	public static List<Grade> getStudentModuleGrades(int loginID, String moduleCode) {
	    RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
	    return retrieveQ.retrieveStudentsModuleGrade(loginID, moduleCode);
	}

    public static void removeModule(String code) {
        RemovalQueries removalQ = new RemovalQueries(Main.getDB());
        removalQ.removeModule(code);
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

	/**
	 * updateGrades uses additionQueries.java method updateGrade to store new grades.
	 * */
	public static void updateGrades(int loginID, String moduleCode, String label,
									Float initialGrade, Float resitGrade) {
		AdditionQueries additionQ = new AdditionQueries(Main.getDB());
		additionQ.updateGrade(loginID, moduleCode, label, initialGrade, resitGrade);
	}

	/**
     * updatePOS uses additionQueries method updatePeriodOfStudy to insert a weighted mean score for a period.
     * @param loginID,
     * */
	public static void updatePeriodOfStudy(int loginID, String label, float mean) {
	    AdditionQueries additionQ = new AdditionQueries(Main.getDB());
	    additionQ.updatePeriodOfStudy(loginID, label, mean);
    }

    /**
     * addPeriodOfStudy is used to create a new period of study
     * @param loginId, int representing
     * @param label, String representing the period of the new period of study to be added
     * @param startDate, String, in the format
     * @param endDate
     * @param level
     */
	public static void addPeriodOfStudy(int loginId, String label, String startDate, String endDate, String level) {
	    AdditionQueries additionQ = new AdditionQueries(Main.getDB());
		additionQ.addPeriodOfStudy(loginId, label, startDate, endDate, level);
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
	public static void assignCoreModules(int studentID, String studentLevel) {
		PeriodOfStudy pos = getLatestPeriodOfStudy(studentID);
		for (Student student : Controller.getStudents()) {
			if (student.getLogin().equals(String.valueOf(studentID))) {
				//Retrieves a list of all modules the selected student is eligible for and assigns core modules to student.
				for (ModuleDegree m : Controller.getModuleDegrees()) {
					if (m.getDegreeCode().equals(student.getDegreeCode()) && (m.getDegreeLevel().equals(studentLevel))) {
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
    
    //Level 2 inputs

    public static String checkInputGrades(String init, String resit, int priv) {
    	String returnMessage = "";
		if (priv < 2) {
			returnMessage = "Insufficient privilege for this opperation.";
		}
		else if (!RegexTests.checkGrade(init) && !init.equals("empty")) {
			returnMessage = "Incorrect initial grade format, please give a valid percentage to two decimal places.";
		}
		else if (!RegexTests.checkGrade(resit) && !resit.equals("empty")) {
			returnMessage = "Incorrect resit grade format, please give a valid percentage to two decimal places.";
		}
		else {
			return "Accepted";
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

    /**
     * Get the sum of credits of all modules a student is taking during the latest POS
     * @param loginID, int representing the selected student
     * @return int of the sum of all the credits in the modules that they take
     * //TODO what to do with a null user
     */
	public static int getCreditsAssignedToLatestPOS(int loginID) {
	    int creditSum = 0;
	    System.out.println("loginID: " + loginID);
        PeriodOfStudy pos = Controller.getLatestPeriodOfStudy(loginID);
        String periodOfStudyLabel = pos.getLabel();
        System.out.println("label: " + periodOfStudyLabel);
        List<Grade> gs = Controller.getStudentsGradeAtPeriod(loginID, periodOfStudyLabel);
        for (Grade g : gs) {
            System.out.println(g);
            String moduleTaken = g.getModuleCode();
            for (Module m : Controller.getModules()) {
                if (m.getCode().equals(moduleTaken)) {
                    System.out.println("code: " + m.getCode());
                    creditSum = creditSum + m.getCredits(); // if the student is currently taking this module, add this
                }
            }
        }
        System.out.println("total sum is: " + creditSum);
	    return creditSum;
    }

	/**
	 * Find the highest grade achieved in a module, capping if necessary
	 * @param grade grade object containing module grades
	 * @param gradeCap cap on maximum score achievable by a repeat for this student
	 * @return highest module grade
	 */
    public static float getMaximumScore(Grade grade, int gradeCap) {
        float initialScore = grade.getInitialPercent();
        float resitScore = grade.getResitPercent();
        if (resitScore > gradeCap) {
            resitScore = gradeCap; // if a grade is resit, then it is capped
        }
        if (grade.getRepeated() && (initialScore > gradeCap)) {
            initialScore = gradeCap; // if a grade is repeated, then it is capped
        }
        // return the greatest score from resit and initial
        if (initialScore >= resitScore) {
            return initialScore;
        } else {
            return resitScore;
        }
    }

	/**
	 * Calculate a student's degree classification
	 * @param list of student's periods of study
	 * @return String containing degree classification
	 */
	public static String getDegreeClass(List<PeriodOfStudy> periods) {
        float level2 = 0;
        float level3 = 0;
        float level4 = 0;
        for(PeriodOfStudy period : periods) {
            if(period.getLevelOfStudy() != "1") {
            	if(period.getLevelOfStudy().equals("2")) {
            	    level2 = period.getWeightedMean();
				}
				else if(period.getLevelOfStudy().equals("3")) {
					level3 = period.getWeightedMean();
				}
				else if(period.getLevelOfStudy().equals("4")) {
					level4 = period.getWeightedMean();
				}
			}
		}

		if(level2 != 0 && level3 != 0) {
			List<Grade> grades = null;
			float sum = 0;
			if (level4 == 0) {
				sum = level2 * 0.3333333f + level3 * 0.666666f;
			} else {
				sum = level2 * 0.2f + level3 * 0.4f + level4 * 0.4f;
				PeriodOfStudy latestPeriod = periods.get(periods.size());
				grades = Controller.getStudentsGradeAtPeriod(Integer.parseInt(latestPeriod.getLoginID()),
						latestPeriod.getLabel());

				ArrayList<String> passedModuleCodes = new ArrayList<>();
				for (Grade grade : grades) {
					if (grade.getInitialPercent() > 50 || grade.getResitPercent() > 50) {
						passedModuleCodes.add(grade.getModuleCode());
					}
				}

				float creditSum = 0;
				boolean hasPassedDissertation = false;
				for (Module module : Controller.getStudentModules(Integer.parseInt(latestPeriod.getLoginID()))) {
					if (passedModuleCodes.contains(module.getCode())) {
						creditSum += module.getCredits();

						if (module.getCredits() == 60 && passedModuleCodes.contains(module.getCode())) {
							hasPassedDissertation = true;
						}
					}
				}

				if (!hasPassedDissertation && creditSum == 180) {
					return "Postgraduate Diploma";
				}

				if (!hasPassedDissertation && creditSum >= 60) {
					return "Postgraduate Certificate";
				}
			}

			if(sum > 39.5) {
				if (sum < 44.4 && level4 == 0) {
					return "Pass (non-honours)";
				} else if (sum < 49.4 && level4 == 0) {
					return "Third Class";
				} else if (sum < 59.4) {
					return "Lower Second";
				} else if (sum < 69.4) {
					return "Upper Second";
				} else if (sum >= 69.5) {
					return "First Class";
				} else if (sum < 44.4 && level4 != 0) {
					return "Graduate with Bachelor's equivalent";
				}
			} else {
				return "Failed";
			}
		}
		return "Not graduating";
    }

	/**
	 * getGradeWeighting takes a grade and gets the credit grade it is worth
	 * @param g
	 * @return
	 */
	public static int getGradeWeighting (Grade g){
		int cred = 0;
		for (Module mod : Controller.getModules()) {
			if(mod.getCode().equals(g.getModuleCode())){
				cred = mod.getCredits();
			}
		}
		return cred;
	}

	/**
	 * assignRepeatModules
	 */
	public static void assignRepeatModules(int loginID){
	    PeriodOfStudy newPeriodOfStudy = getLatestPeriodOfStudy(loginID);
	    // first create a new period of study which is in the next period of study
        char repeatLabel = newPeriodOfStudy.getLabel().charAt(0);
        int charValue = repeatLabel - 1;
        char firstBeforeLabel = (char) charValue;

        String studentID = newPeriodOfStudy.getLoginID();

        int threshold;
        if (newPeriodOfStudy.getLevelOfStudy().charAt(0) == '4') {
            threshold = 50;
        } else {
            threshold = 40;
        }

		for (Grade g : getStudentsGradeAtPeriod(Integer.parseInt(studentID), String.valueOf(firstBeforeLabel))) {
		    // add the grade as a blank grade
            saveBlankGrades(studentID, g.getModuleCode(), String.valueOf(repeatLabel));
		    // if the student passed the module, then it is carried to the repeat year
            if ( (g.getInitialPercent() > threshold) || (g.getResitPercent() > threshold) ) {
                updateGrades(Integer.parseInt(studentID), g.getModuleCode(), String.valueOf(repeatLabel), g.getInitialPercent(), g.getResitPercent());
            }
        }
	}

	/**
     * checks whether a student is in their final year
     * */
	public static boolean checkFinalYear(int studentID, PeriodOfStudy periodStudyObj){
        //Pull out the students related degree so we can see if they are masters or not
        Student studObj = getStudent(studentID);
        Degree degObj = getDegree(studObj.getDegreeCode());

        //Now we test if it's their final year and control flow appropriatly
        if (degObj.isMasters() && periodStudyObj.getLevelOfStudy().equals("4")) {
            return true;
            //Final year grad masters
        }
        else if (!degObj.isMasters() && periodStudyObj.getLevelOfStudy().equals("3")) {
            //Final year grad under
            return true;
        }
        else{
            return false;
        }
    }

	/**
	 * progressPassedStudent, takes .... and decides how a student should be progressed
	 * @param studentID int, the ID of the student we want to progress
	 */
	public static void progressPassedStudent(int studentID, PeriodOfStudy periodStudyObj) {
		//Pull out the students related degree so we can see if they are masters or not
		Student studObj = getStudent(studentID);
		Degree degObj = getDegree(studObj.getDegreeCode());

        //Progress for all levels here we go
        char newLabel = periodStudyObj.getLabel().charAt(0);
        newLabel++;
        //TODO - update to new dates by 1 year
        String newStartDate = periodStudyObj.getStartDate().toString(); //+1 year
        String newEndDate = periodStudyObj.getStartDate().toString(); //+1 year

        //We check which period of study we should update to
        if (periodStudyObj.getLevelOfStudy().equals("1")) {
            addPeriodOfStudy(studentID, Character.toString(newLabel), newStartDate, newEndDate, "2");
            assignCoreModules(studentID, "2");
        }
        //Level 2, not masters and placement
        else if (periodStudyObj.getLevelOfStudy().equals("2") && !degObj.isMasters() && degObj.hasPlacementYear()) {
            addPeriodOfStudy(studentID, Character.toString(newLabel), newStartDate, newEndDate, "P");
            assignCoreModules(studentID, "P");
        }
        //If not we just progress normally
        else if (periodStudyObj.getLevelOfStudy().equals("2")) {
            addPeriodOfStudy(studentID, Character.toString(newLabel), newStartDate, newEndDate, "3");
            assignCoreModules(studentID, "3");
        }
        //Level 3, is masters and placement
        else if (periodStudyObj.getLevelOfStudy().equals("3") && degObj.isMasters() && degObj.hasPlacementYear()) {
            addPeriodOfStudy(studentID, Character.toString(newLabel), newStartDate, newEndDate, "P");
            assignCoreModules(studentID, "P");
        }
        //If not progress normally
        else if (periodStudyObj.getLevelOfStudy().equals("3")) {
            addPeriodOfStudy(studentID, Character.toString(newLabel), newStartDate, newEndDate, "4");
            assignCoreModules(studentID, "4");
        }
        //Check if we are an undergrad and on placement
        else if (periodStudyObj.getLevelOfStudy().equals("P") && !degObj.isMasters()) {
            addPeriodOfStudy(studentID, Character.toString(newLabel), newStartDate, newEndDate, "3");
            assignCoreModules(studentID, "3");
        }
        //Check if we are a masters and on placement
        else if (periodStudyObj.getLevelOfStudy().equals("P") && degObj.isMasters()) {
            addPeriodOfStudy(studentID, Character.toString(newLabel), newStartDate, newEndDate, "4");
            assignCoreModules(studentID, "4");
        }
		
	}
	
}
