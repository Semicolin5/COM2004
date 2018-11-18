package src.model;

import java.util.regex.*;

public class CheckInput {

	//Regex strings for input validation
	//TODO discuss what a year in industry code will look like
	private static String regDegree = "[A-Z]{3}[UP][0-9]{2}";
	private static String regModule = "[A-Z]{3}[0-9]{4}";
	private static String regDepartment = "[A-Z]{3}";
	private static String regLoginID = "[0-9]{4}";
	
	private static String regPassword = "[0-9]{1,}&&[a-z]{1,}&&[!#$%&'()*+,-./:;<=>?@^_`{|}~]{1,}";
	
	//Currently just a class to check regex string checking etc.
	
	public static void main(String[] args) {
		/*System.out.println(checkDegreeCode("COMU02"));
		System.out.println(checkDegreeCode("COMU032"));
		System.out.println(checkDegreeCode("BAHP02"));
		
		System.out.println(checkModuleCode("COM1003"));
		System.out.println(checkModuleCode("COM10233"));
		System.out.println(checkModuleCode("BA1023"));*/
		
		System.out.println(checkPassword("9lHo!"));
		
	}
	
	
	public static boolean checkPassword (String password) {
		return Pattern.matches(regPassword, password);
	}
	
	
	//Regex checking functions.  Hopefully this is a start and can come in useful.
	
	public static boolean checkDegreeCode(String degreeCode) {
		return Pattern.matches(regDegree, degreeCode);
	}
	
	public static boolean checkModuleCode(String moduleCode) {
		return Pattern.matches(regModule, moduleCode);
	}
	
	public static boolean checkDeparmentCode(String departmentCode) {
		return Pattern.matches(regDepartment,departmentCode);
	}
	
	public static boolean checkLoginID(String loginID) {
		return Pattern.matches(regLoginID, loginID);
	}
	
	//Other testing methods
	public static String inputModule(String input) {
		//TODO actually write this method!
		int  a = 1;
		//Check the main department exists
		if (a != 1) {
			//Reject and return a string suggesting why
		}
		//Check the additional departments exist
		else if (a != 1) {
			//Reject and return a string suggesting otherwise
		}
		
		return input;
	}
	
	
	public static
	
	
	/**
	 * 
	 */
	public static String checkInputUser(String loginID, String password, String confirmPassword) {
		String returnMessage = "";
		//Lets do our error checks
		if (!checkLoginID(loginID)) {
			returnMessage = "Incorrect LoginID format.";
		}
		else if (/*check for uniqueness*/) {
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
