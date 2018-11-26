package src.model;

import java.util.regex.*;

public class RegexTests {

	//Regex strings for input validation
	//TODO discuss what a year in industry code will look like
	
	
	
	private static String regLoginID = "[0-9]{4}";
	private static String regDepartment = "[A-Z]{3}";
	private static String regDegree = "[A-Z]{3}[UP][0-9]{2}";
	private static String regModule = "[A-Z]{3}[0-9]{4}";
	private static String regCredits = "[0-9]{1,3}";
	private static String regTitle = "[a-zA-Z .,()&]*";
	private static String regName = "[a-zA-Z]";
	private static String regFullName = "[a-zA-Z ]";
	

	//These are the special chars we allow !#$%&'()*+,-./:;<=>?@^_`{|}~+
	private static String regPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!#$%&'()*+,-./:;<=>?@^_`{|}~+]).{8,16}$";
	
	//Currently just a class to check regex string checking etc.
	public static void main(String[] args) {
		//This is just used for testing		
		System.out.println(checkName("aaaaa  &&&&ASFaaaa"));
	}
	
	
	//Regex checking functions.  Hopefully this is a start and can come in useful.
	
	public static boolean checkPassword (String password) {
		return Pattern.matches(regPassword, password);
	}
	
	public static boolean checkFullName(String fullN) {
		return Pattern.matches(regFullName, fullN);
	}
	
	public static boolean checkName(String name) {
		return Pattern.matches(regName, name);
	}
	
	public static boolean checkTitle(String title) {
		return Pattern.matches(regTitle, title);
	}
	
	public static boolean checkDepartmentCode(String departmentCode) {
		return Pattern.matches(regDepartment,departmentCode);
	}
	
	public static boolean checkModuleCredits(String credits) {
		return Pattern.matches(regCredits, credits);
	}
	
	public static boolean checkDegreeCode(String degreeCode) {
		return Pattern.matches(regDegree, degreeCode);
	}
	
	public static boolean checkModuleCode(String moduleCode) {
		return Pattern.matches(regModule, moduleCode);
	}
	

	
	public static boolean checkLoginID(String loginID) {
		return Pattern.matches(regLoginID, loginID);
	}

}
