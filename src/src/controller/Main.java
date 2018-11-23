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
    private static User user = null;
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
    	if (user == null) {
    		return 0;
    	}
    	else {
    		return user.getPriv();
    	}
    }
    
    public static int getLoginID() {
    	if (user == null) {
    		return 0;//TODO maybe fix this so it throws an Exception
    	}
    	else {
    		return user.getLogin();
    	}
    }
    
    public static void setUser(User ourUser) {
    	user = ourUser;
    }


}






