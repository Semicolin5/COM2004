package src;

import src.db_handler.*;
import src.objects.*;
import java.util.*;

/**
 * Main.java
 *
 * This is the main class of the controller,
 * Main method runs the System.
 * */
public class Main {    

    //TODO implement password && username checking when evaluating priv
    private static String DEMOUSERNAME = "PGreen";
    private static int priv = 0;

    // stores the privilege for the user.
    private static DatabaseHandler db;

    public static void main(String[] args) {

        // DATABASE TESTING
	    System.out.println("Creating the database handler object to connect with db");
	    db = new DatabaseHandler();
        System.out.println("\nTesting the obtain Privilege method...");
	    priv = db.obtainPrivilege(DEMOUSERNAME); // obtaining privilege also sets the privLevel attribute inside db
        System.out.println("priv level " + priv);

        // instantiating Query methods
        RetrieveQueries retrieveQ = new RetrieveQueries(db);
        AdditionQueries additionQ = new AdditionQueries(db);
        RemovalQueries removeQ = new RemovalQueries(db);

        // TEST METHODS WHEN USER LEVEL 4

        System.out.println("\nTesting the add Department method...");
        additionQ.addDepartment("FRE", "French");

        System.out.println("\nTesting the addModule method...");
        additionQ.addModule("FRE3008", "Sex, Subversion and Censorship: Libertine Literature in Seventeenth-Century France", 10);

        System.out.println("\nTesting the selectDepartment method...");
        List<Department> retrievedDepartments = retrieveQ.retrieveDepartmentTable();
        if (retrievedDepartments != null)
            for (Department dep : (retrievedDepartments)) {
                System.out.println("CODE: " + dep.getCode() + ", Name: " + dep.getName());
            }

        System.out.println("\nTesting the deleteDepartment method...");
        removeQ.removeDepartment("RSS");

        System.out.println("\nTesting the removeModule method");
        removeQ.removeModule("RSS2109");


        // TEST METHODS WHEN USER LEVEL 3
        /*System.out.println("Testing the addStudent method... ");
        additionQ.addStudent("EHoggins29", "123456789012345678901234567890", 1,
                "Mr", "Edvard", "Hoggins", "Steve Maddock",
                "hoggins@hotmail.com");

        System.out.println("Testing the addStudentModuleAssociation class");
        additionQ.addStudentModuleAssociation("EHoggins29", "COM1001");
        */

        // TEST METHODS WHEN USER LEVEL 2

        // TEST METHODS WHEN USER LEVEL 1




    }

}






