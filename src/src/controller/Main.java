package src.controller;

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
    private static String DEMOUSERNAME = "HWhite";
    private static int priv = 0;

    // stores the privilege for the user.
    private static DatabaseHandler db;

    public static void main(String[] args) {

    }

    public void saveDegree(String degreeCode, String degreeName){
        db = new src.db_handler.DatabaseHandler();
        src.db_handler.AdditionQueries additionQ = new src.db_handler.AdditionQueries(db); //^TODO CHECK OUT THIS ERROR
        additionQ.addDegree(degreeCode, degreeName);
    }

    public void saveDepartment(String departmentCode, String departmentName){
        db = new src.db_handler.DatabaseHandler();
        src.db_handler.AdditionQueries additionQ = new src.db_handler.AdditionQueries(db); //^TODO CHECK OUT THIS ERROR
        additionQ.addDepartment(departmentCode, departmentName);
    }


}






