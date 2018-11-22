package src.objects;
import java.util.*;

/**
 * Degree.java
 * Represents a row in the Degree table, as well as storing the associated
 * Departments
 **/
public class Degree {

    private String degreeCode;
    private String degreeName;
    private boolean masters;
    private boolean yearIndustry;
    private Department lead;
    private List<Department> nonLead;

    /**
     * @param degreeCode String of 7 letter degree code
     * @param degreeName String of the name of the degree
     * */
    public Degree (String degreeCode, String degreeName, boolean masters, boolean yearIndustry, Department lead, List<Department> nonLead) {
        this.degreeCode = degreeCode;
        this.degreeName = degreeName;
        this.masters = masters;
        this.yearIndustry = yearIndustry;
        this.lead = lead;
        this.nonLead = nonLead;
    }

   /**
    * Accessor methods
    * */
   public  String getDegreeCode() {
       return degreeCode; }
   public String getDegreeName() { return degreeName; }
    public  boolean getDegreeType() {
        return masters; }
    public  boolean getDegreePlacement() {
        return yearIndustry; }
   // TODO make ones for department too

    /**
     * toString method //TODO remove aafter testing
     * */
    public String toString() {
        String s = "Code: " + degreeCode + ", Name: " + degreeName + ", Lead Dep: " + lead.toString();
        return s;
    }
}
