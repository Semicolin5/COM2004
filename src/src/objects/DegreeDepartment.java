package src.objects;

//A class to link a degree and a department together
public class DegreeDepartment {
		
	private String degreeCode;
	private String departmentCode;
	private boolean leadDepartment;
	
	public DegreeDepartment(String dgC, String dpC, boolean lD) {
		degreeCode = dgC;
		departmentCode = dpC;
		leadDepartment = lD;
	}
	
	public String getDegreeCode() {
		return degreeCode;
	}
	
	public String getDepartmentCode() {
		return departmentCode;
	}
	
	public boolean getLeadDepartment() {
		return leadDepartment;
	}

}
