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
	    System.out.println("Creating the database handler object to connect with db");
	    db = new DatabaseHandler();

        System.out.println("Testing the obtain Privilege method...");
	    priv = db.obtainPrivilege(DEMOUSERNAME); // obtaining privilege also sets the privLevel attribute inside db
        System.out.println("priv level " + priv);

        System.out.println("\n Testing the add Department method");
        RetrieveQueries runAdminQ = new RetrieveQueries(db);
        AdditionQueries additionQ = new AdditionQueries(db);
        additionQ.addDepartments("FRE", "French");

        System.out.println("Testing the addModule method");
        additionQ.addModule("FRE3008", "Sex, Subversion and Censorship: Libertine Literature in Seventeenth-Century France", 10);

        System.out.println("Testing the selectDepartment method");
        List<Department> retrievedDepartments = runAdminQ.retrieveDepartmentTable();
        for (Department dep : (retrievedDepartments)) {
            System.out.println("CODE: " + dep.getCode() + ", Name: " + dep.getName());
        }

    }


}






