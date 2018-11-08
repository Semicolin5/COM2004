package source;

import source.db_handler.AdministrationQueries;
import source.db_handler.DatabaseHandler;

/**
 * Main.java
 *
 * This is the main class of the controller,
 * Main method runs the System.
 * */
public class Main {    

    //
    private static String DEMOUSERNAME = "PGreen";
    private static int priv = 0;

    // stores the privilege for the user.
    private static DatabaseHandler db;

    public static void main(String[] args) {
	    System.out.println("Creating the database handler object to connect with db");
	    db = new DatabaseHandler();

        System.out.println("Testing the obtainPrivilege method...");
	    priv = db.obtainPrivilege(DEMOUSERNAME); // obtaining priviledge also sets the privLevel attribute inside db
        System.out.println("priv level " + priv);

        System.out.println("Testing the addDepartment method");
        AdministrationQueries runAdminQ = new AdministrationQueries(db);
        runAdminQ.addModule("FRE", "French");

    }
}






