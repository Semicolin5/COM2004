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
    private boolean degreeType;
    private Department lead;
    private List<Department> nonLead;

    /**
     * @param degreeCode String of 7 letter degree code
     * @param degreeName String of the name of the degree
     * */
    public Degree (String degreeCode, String degreeName, boolean degreeType){//, Department lead, List<Department> nonLead) {
        this.degreeCode = degreeCode;
        this.degreeName = degreeName;
        this.degreeType = degreeType;
        this.lead = null;
        this.nonLead = null;
    }

   /**
    * Accessor methods
    * */
   public  String getDegreeCode() {
       return degreeCode; }
   public String getDegreeName() { return degreeName; }
    public  boolean getDegreeType() {
        return degreeType; }
   // TODO make ones for department too

    /**
     * toString method //TODO remove aafter testing
     * */
    public String toString() {
        String s = "Code: " + degreeCode + ", Name: " + degreeName;
        return s;
    }
}
