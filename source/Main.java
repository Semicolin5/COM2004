package source;

import source.db_handler.DatabaseHandler;

/**
 * Main.java
 *
 * This is the main class of the controller,
 * Main method runs the System.
 * */
public class Main {    

    //
    private static String DEMOUSERNAME = "JSmith24";
    private static int priv = 0;

    // stores the privilege for the user.
    private static DatabaseHandler db;

    public static void main(String[] args) {
	    System.out.println("Creating the database handler object to connect with db");
	    db = new DatabaseHandler();

        System.out.println("Testing the obtainPrivilege method...");
	    priv = db.obtainPrivilege(DEMOUSERNAME);
        System.out.println("priv level " + priv);
    }
}






