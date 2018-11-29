package src.controller;

import src.model.db_handler.*;
import src.objects.*;

import java.util.*;

/**
 * Creating an incredibly bulky class that will do all the tests
 * */
public class TestMethods {


    private static DatabaseHandler testdb;

    public static void main(String[] args) {

        testdb = new DatabaseHandler();

        //testingRemovalQueries();
        testingAdditionQueries();
        //testingRetrieveQueries();
        //testingDegreeObject();
    }

    // testing the RetrieveQuery class methods
    private static void testingRetrieveQueries() {

        RetrieveQueries tester = new RetrieveQueries(testdb);

        System.out.println("testing retrieveStudentsModuleGrade");

        Grade g = tester.retrieveStudentsModuleGrade(1001, "COM1005");
        System.out.println(g.toString());
    }

    // testing the AdditionQuery class methods
    private static void testingAdditionQueries() {

        AdditionQueries tester = new AdditionQueries(testdb);

        System.out.println("testing addPeriodOfStudy");
        tester.addPeriodOfStudy(2001, "C", "2018-09-03", "2019-07-01", "2", false);

    }

    // testing the RemovalQueries class methods
    public static void testingRemovalQueries() {

        RemovalQueries tester = new RemovalQueries(testdb);


        // testing the removeStudent function
        //System.out.println("Testing removeStudent");
        //tester.removeStudent(1001);

        // testing the removeStudentsModuleChoice function
        //System.out.println("Testing removeStudentsModuleChoice");
        //tester.removeStudentsModuleChoice(1001, "COM1005");

        // testing the removeModule method, removing the COM1005 module
        //System.out.println("Testing removeModule");
        //tester.removeModule("COM1005");

        //testing the removeDepartment method on a Department with no affiliated degrees
        //System.out.println("trying out removeDepartment");
        //tester.removeDepartment("RSS");

        // testing the removeDegree method on a Degree with no affilaited modules
        //System.out.println("trying out removeDegree");
        //tester.removeDegree("COMU03");

    }
}
