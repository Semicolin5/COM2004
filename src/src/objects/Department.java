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
     * Accessor methods
     * */
    public String getCode() {return code;}
    public String getName() {return name;}


}
