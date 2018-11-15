package src.objects;
import java.util.*;

/**
 * Degree.java
 * Represents a row in the Degree table, as well as storing the associated
 * Departments
 **/
public class Degree {

    private static String degreeCode;
    private static String degreeName;
    private static Department lead;
    private static List<Department> nonLead;

    /**
     * @param degreeCode String of 7 letter degree code
     * @param degreeName String of the name of the degree
     * */
    public Degree (String degreeCode, String degreeName){//, Department lead, List<Department> nonLead) {
        this.degreeCode = degreeCode;
        this.degreeName = degreeName;
        this.lead = null;
        this.nonLead = null;
    }

   /**
    * Accessor methods
    * */
   public String getDegreeCode() { return degreeCode; }
   public String getDegreeName() { return degreeName; }
   // TODO make ones for department too

    /**
     * toString method //TODO remove aafter testing
     * */
    public String toString() {
        String s = "Code: " + degreeCode + ", Name: " + degreeName;
        return s;
    }
}
