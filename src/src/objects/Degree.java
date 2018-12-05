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
     * @param masters boolean representing whether or not the degree will be taken to master
     * @param yearIndustry boolean representing whether or not the
     * @param lead Department object representing the lead Department for the degree
     * @param nonLead list of department objects representing the non-lead departments for the degree.
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
    public  boolean isMasters() {
        return masters; }
    public  boolean hasPlacementYear() {
        return yearIndustry; }
    public List <Department> getNonLeadDepartments(){
       return nonLead;
    }
    public Department getLeadDepartment(){
       return lead;
    }
}
