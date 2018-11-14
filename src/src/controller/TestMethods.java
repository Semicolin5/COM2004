package src.controller;

import src.db_handler.AdditionQueries;
import src.db_handler.DatabaseHandler;

/**
 * Creating an incredibly bulky class that will do all the tests
 *
 * */
public class TestMethods {


    private static DatabaseHandler testdb;

    public static void main(String[] args) {

        testdb = new DatabaseHandler();
        // enter the tests you want to carry out in here
        System.out.println("testing additionQueries. All tests passed?: " + testingAdditionQueries());//testingAdditionQueries());

    }

    // testing the AdditionQuery class methods
    private static boolean testingAdditionQueries() {

        boolean noFails = true;

        // testing admin sql queries
        testdb.setPrivLevel(4);
        AdditionQueries tester = new AdditionQueries(testdb);
        tester.addDepartment("LIT", "English Literature");


        return noFails;

    }

}
