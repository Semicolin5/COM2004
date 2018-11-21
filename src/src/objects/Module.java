package src.objects;

/**
 * Module represents a row in the database module table,
 * i.e. storing information about one unique module.
 * contains methods to get and set the values of this row.
 * */
public class Module {

    private String code;
    private String name;
    private int credits;
    private int semester;

    /**
     * Forms the Module object given parameters
     * @param  code, the Module's code
     * @param name, String that describes module.
     * @param credits, int that sets how many credits the module is worth.
     * */
    public Module (String code, String name, int credits, int semester) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.semester = semester;
    }

    /**
     * Accessor methods
     * */
    public String getCode() { return code; }
    public String getName() { return name; }
    public int getCredits() { return credits; }

    //TODO remove this later, currently just for testing
    public String toString() {return (code + ", " + name + "," + credits);}

}