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
     * validate determines if the user logs in correctly
     * @param potentialUser User object contains unvalidated user info
     * @return bool true if properly validated
     * */
    public static boolean validate(User potentialUser) {
        Boolean validated = false;
        if (true) { //todo call db.obtainPrivilege() and find out details
            user = potentialUser;
            validated = true; // TODO shouldn't be hardcoded
            System.out.println("TEST IN Main.java " + user.toString());
        } else {
            validated = false;
        }
        db.setPrivLevel(user.getPriv());
        return validated;
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


}






