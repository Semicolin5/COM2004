package src.objects;

/**
 * Grade.java
 * Represents a row in the grade class, i.e. the grades that a student achieved for a module that they either
 * currently take, or
 *
 * */
public class Grade {

    private String loginID;
    private String moduleCode;
    private char label;
    private float initialPercent;
    private float resitPercent;
    private float repeatPercent;

    /**
     * Builds a grade object
     * @param loginID
     * @param moduleCode
     * @param label
     * @param initialPercent
     * @param resitPercent
     * @param repeatPercent
     */
    public Grade(String loginID, String moduleCode, char label, float initialPercent, float resitPercent, float repeatPercent) {
        this.loginID = loginID;
        this.moduleCode = moduleCode;
        this.label = label;
        this.initialPercent = initialPercent;
        this.resitPercent = resitPercent;
        this.repeatPercent = repeatPercent;
    }


    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
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

    public float getRepeatPercent() {
        return repeatPercent;
    }

    public void setRepeatPercent(float repeatPercent) {
        this.repeatPercent = repeatPercent;
    }

    /**
     * toString() method for grades
     * */
    public String toString() {
        return ("Student: " + loginID + ", module: " + moduleCode + ", initial mark: " + initialPercent + ", resit mark: " + resitPercent);
    }
}
