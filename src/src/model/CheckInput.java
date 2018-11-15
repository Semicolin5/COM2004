package src.model;

import java.util.regex.*;

public class CheckInput {

	//Regex strings for input validation
	private static String regDegree = "[A-Z]{3}[UP][0-9]{2}";
	
	//Currently just a class to check regex string checking etc.
	
	public static void main(String[] args) {
		System.out.println(checkDegreeCode("COMU02"));
		System.out.println(checkDegreeCode("COMU032"));
		System.out.println(checkDegreeCode("BAHP02"));

	}
	
	
	public static boolean checkDegreeCode(String degreeCode) {
		return Pattern.matches(regDegree, degreeCode);
	}
	
	

}
