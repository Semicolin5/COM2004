package src.model;

import java.util.regex.*;

public class CheckInput {

	//Regex strings for input validation
	//TODO discuss what a year in industry code will look like
	private static String regDegree = "[A-Z]{3}[UP][0-9]{2}";
	private static String regModule = "[A-Z]{3}[0-9]{4}";
	private static String regDepartment = "[A-Z]{3}";
	
	//Currently just a class to check regex string checking etc.
	
	public static void main(String[] args) {
		System.out.println(checkDegreeCode("COMU02"));
		System.out.println(checkDegreeCode("COMU032"));
		System.out.println(checkDegreeCode("BAHP02"));
		
		System.out.println(checkModuleCode("COM1003"));
		System.out.println(checkModuleCode("COM10233"));
		System.out.println(checkModuleCode("BA1023"));
		
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
	
	

}
