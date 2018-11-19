package src.controller;

import src.model.db_handler.*;
import src.objects.Degree;
import src.objects.Department;
import src.objects.Module;
import src.objects.User;
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
	
	public static boolean passwordMatch(int loginID, String password) {
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
	
    public static void saveDegree(String degreeCode, String degreeName, boolean masters) {
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addDegree(degreeCode, degreeName, masters);
    }

    public static void saveDepartment(String departmentCode, String departmentName) {
        // TODO maybe have logic to check that the parameters are the right length?
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addDepartment(departmentCode, departmentName);
    }

    public static void saveModule(String moduleCode, String moduleName, int credits) {
        // TODO maybe have logic to check that the parameters are the right length?
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addModule(moduleCode, moduleName, credits);
    }

    public static void saveModuleAssociation(String moduleCode, String moduleName, int level, boolean core) {
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

    public static void saveUser(int login, String pass, int priv) {
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        String salt = CryptoModule.generateSalt();
        String hashedPassword = CryptoModule.hashPassword(pass, salt);
        additionQ.addUser(login, hashedPassword, priv, salt);
    }

    public static void saveStudent(int login, String title, String forename, String surname, String tutor, String email, String degree) {
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addStudent(login, title, forename, tutor, email, degree);
    }

    public static String checkInputUser(int loginID, String password, String confirmPassword) {
    	RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
    	String returnMessage = "";
		if (password != confirmPassword) {
			returnMessage = "Passwords do not match.";
		}
		else if (!RegexTests.checkPassword(password)) {
			returnMessage = "Incorrect password format."; //must contain 1 uppercase, 1 lowercase, 1 symbol
		}
		else if (!RegexTests.checkLoginID(Integer.toString(loginID))) {
			returnMessage = "Incorrect LoginID format.";
		}
		else if (retrieveQ.checkDuplicateLoginID(loginID)) {
			returnMessage = "LoginID already in use.";
		}
		else if (password != confirmPassword) {
			returnMessage = "Passwords do not match.";
		}
		else {
			returnMessage = "Accepted";
		}
		return returnMessage;
	}
}
