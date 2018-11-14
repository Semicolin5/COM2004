package src.controller;

import src.model.db_handler.AdditionQueries;
import src.model.db_handler.DatabaseHandler;

/**
 * Creating an incredibly bulky class that will do all the tests
 *
 * */
public class TestMethods {


    private static DatabaseHandler testdb;

    public static void main(String[] args) {

        testdb = new DatabaseHandler();
        // enter the tests you want to carry out in here
        System.out.println("testing additionQueries. All tests run?: " + testingAdditionQueriesLVL4());//testingAdditionQueries());

    }

    // testing the AdditionQuery class methods
    private static boolean testingAdditionQueriesLVL4() {

        boolean noFails = true;
        AdditionQueries tester = new AdditionQueries(testdb);

        // testing admin sql queries
        testdb.setPrivLevel(4);

        System.out.println("testing addDepartment");
        // positive test case
        tester.addDepartment("LIT", "English Literature");
        // negative test case
        tester.addDepartment(null, null);
        testdb.setPrivLevel(0);
        // negative test case
        tester.addDepartment("GEO", "Geography Dept");



        System.out.println("testing addModule");
        tester.addModule("COM2008", "super cool module", 120);



        System.out.println("testing a");





        return noFails;

    }

}
