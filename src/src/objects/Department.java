package src.objects;

/**
 * object represents a row in the database department table.
 * contains methods to get and set the values of this row.
 * */
public class Department {

    private String code;
    private String name;

    /**
     * Forms the Department object given
     * @param code String representing the departments code
     * @param name String that describes department.
     * */
    public Department (String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Accessor and mutator methods
     * */
    public void setCode(String c) {this.code = c;}
    public void setName(String n) {this.name = n;}
    public String getCode() {return code;}
    public String getName() {return name;}
}
