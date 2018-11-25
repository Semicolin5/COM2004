package src.objects;

/**
 * Grade.java
 * Represents a row in the grade class, i.e. the grades that a student achieved for a module that they either
 * currently take, or
 *
 * */
public class Grade {

    private int loginID;
    private String moduleCode;
    private char label;
    private float initialPercent;
    private float resitPercent;

    /**
     * Constructs a Grade object given its parameters.
     * @param loginID
     * @param moduleCode
     * @param label;
     * @param initialPercent
     * @param resitPercent*/
    public Grade(int loginID, String moduleCode, char label, float initialPercent, float resitPercent) {
        this.loginID = loginID;

        this.moduleCode = moduleCode;
        this.initialPercent = initialPercent;
        this.resitPercent = resitPercent;
    }

    public int getLoginID() {
        return loginID;
    }

    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public char getLabel() {
        return label;
    }

    public void setLabel(char label) {
        this.label = label;
    }

    public float getResitPercent() {
        return resitPercent;
    }

    public void setResitPercent(float resitPercent) {
        this.resitPercent = resitPercent;
    }

    public float getInitialPercent() {
        return initialPercent;
    }

    public void setInitialPercent(float initialPercent) {
        this.initialPercent = initialPercent;
    }

    /**
     * toString() method for grades
     * */
    public String toString() {
        return ("Student: " + loginID + ", module: " + moduleCode + ", initial mark: " + initialPercent + ", resit mark: " + resitPercent);
    }
}
