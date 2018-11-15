package src.controller;

import src.model.db_handler.*;
import src.objects.Department;

import java.util.*;

/**
 * Creating an incredibly bulky class that will do all the tests
 * */
public class TestMethods {


    private static DatabaseHandler testdb;

    public static void main(String[] args) {

        testdb = new DatabaseHandler();

        // enter the tests you want to carry out in here
        // System.out.println("testing additionQueries.";
        testingAdditionQueries();

        //System.out.println("testing RetrieveQueries");
        //testingRetrieveQueries();
    }

    // testing the RetrieveQuery class methods
    private static void testingRetrieveQueries() {

        RetrieveQueries tester = new RetrieveQueries(testdb);
        testdb.setPrivLevel(4);

        // testing the retrieval method for a list of departments
        System.out.println("Retrieving list of departments");
        List<Department> lsDep = tester.retrieveDepartmentTable();
        for (Department d : lsDep) {
            System.out.println(d.toString());
        }
    }

    // testing the AdditionQuery class methods
    private static void testingAdditionQueries() {

        AdditionQueries tester = new AdditionQueries(testdb);

        // testing admin sql queries
        testdb.setPrivLevel(4);

        System.out.println("testing addDepartment");
        // positive test case
        tester.addDepartment("LIT", "English Literature");

        System.out.println("testing addModule");
        // positive test case
        tester.addModule("COM2008", "super cool module", 120);

        System.out.println("testing addDegree");
        //positive test case
        tester.addDegree("LITU04", "Nineteenth Century French Poetry");

        System.out.println("testing addStudent");

    }

}
