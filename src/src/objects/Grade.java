package src.objects;

/**
 * Grade.java
 * Used to store a students grades for a module. In cases where students need to repeat a year,
 * the variable initialLabel represents the period of study for the initial and resit grades.
 * */
public class Grade {

    private String loginID;
    private String moduleCode;
    private char initialLabel;
    private float initialPercent;
    private float resitPercent;
    private boolean repeated;

    /**
     * A Grade object differs from how grades are stored in the database as it can also to encapsulate a students grades
     * in a module across two years.
     * @param loginID int represents a student login code, identifying who the grade belongs to.
     * @param moduleCode String representing the module of the grade.
     * @param label char refers to the period of study under which the initial and resit grades were taken.
     * @param initialPercent float that stores the percentage of the initial percent scored for a module.
     * @param resitPercent float that stores the resit percentage scored.
     */
    public Grade(String loginID, String moduleCode, char label, float initialPercent, float resitPercent) {
        this.loginID = loginID;
        this.moduleCode = moduleCode;
        this.initialLabel = label;
        this.initialPercent = initialPercent;
        this.resitPercent = resitPercent;
        this.repeated = false;
    }

    /**
     * Overloading the Grade constructor for when this grade is a repeated grade.
     * */
    public Grade(String loginID, String moduleCode, char label, float initialPercent, float resitPercent, boolean repeated) {
        this(loginID, moduleCode, label, initialPercent, resitPercent);
        this.repeated = true;
    }

    /**
     * Accessor methods
     * */

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public char getLabel() {
        return initialLabel;
    }

    public float getResitPercent() {
        return resitPercent;
    }

    public float getInitialPercent() {
        return initialPercent;
    }


    /**
     * toString() method for grades
     * */
    public String toString() {
        return ("Student: " + loginID + ", module: " + ", is repeat: " + repeated + ", +" + moduleCode + "initial mark: " + initialPercent + ", resit mark: " + resitPercent);
    }
}
