package src.controller;

import src.model.db_handler.*;
import src.view.*;
import src.objects.*;

/**
 * Main.java
 *
 * This is the main class of the controller,
 * Main method runs the System.
 * */
public class Main {    

    // user stores the privilege level and username for the logged in user
    private static User user;
    // DatabaseHandler maintains the connection with the Database.
    private static DatabaseHandler db;

    /**
     * Main method creates the connection to the database, and instantiates GUIFrame
     * */
    public static void main(String[] args) {
        new GUIFrame();
        db = new DatabaseHandler();
        
    }

    /**
     * Accessor method for the DatabaseHandler object db
     * @return DatabaseHandler object
     * */
    public static DatabaseHandler getDB() {
        return db;
    }

    /**
     * Accessor method to obtain the logged in users privilege
     * @return the users privilege level
     * */
    public static int getPriv() {
        return user.getPriv();
    }
    
    public static int getLoginID() {
    	return user.getLogin();
    }


}






