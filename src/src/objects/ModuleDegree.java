package src.objects;

//A class to link a module and a degree together
public class ModuleDegree {
	
	private String moduleCode;
	private String degreeCode;
	private String degreeLevel;
	private boolean core;
	

	public ModuleDegree(String mC, String dC, String dL, boolean c) {
		moduleCode = mC;
		degreeCode = dC;
		degreeLevel = dL;
		core = c;
	}
	
	public String getModuleCode() {
		return moduleCode;
	}
	
	public String getDegreeCode() {
		return degreeCode;
	}
	
	public String getDegreeLevel() {
		return degreeLevel;
	}
	
	public boolean getCore() {
		return core;
	}

}
