package src.controller;

import src.model.db_handler.*;
import src.objects.Degree;
import src.objects.Department;

import java.util.*;

/**
 * Creating an incredibly bulky class that will do all the tests
 * */
public class TestMethods {


    private static DatabaseHandler testdb;

    public static void main(String[] args) {

        testdb = new DatabaseHandler();

        testingRemovalQueries();
        //testingAdditionQueries();
        //testingRetrieveQueries();
        //testingDegreeObject();
    }

    // testing the RetrieveQuery class methods
    private static void testingRetrieveQueries() {

        RetrieveQueries tester = new RetrieveQueries(testdb);
        testdb.setPrivLevel(4);

        // testing the retrieval method for a list of departments
        System.out.println("\n1) Retrieving list of departments");
        List<Department> lsDep = tester.retrieveDepartmentTable();
        for (Department d : lsDep) {
            System.out.println(d.toString());
        }

        // testing the retrieval method for a list of degrees
        //System.out.println("\n2)Retrieving list of degrees");
        //System.out.println("2.1)Testing retrieveAffiliatedNonLeadDep method");
        //List<Department> nonLead = tester.retrieveAffiliatedNonLeadDep("COMU49"); //
        //for (Department d : nonLead) {
        //    System.out.println(d.toString());
        //}

        //System.out.println("2.2)Testing retrieveAffiliatedLeadDep method");
        //Department lead = tester.retrieveAffiliatedLeadDep("COMU49");
        //System.out.println(lead.toString());

        System.out.println("2.3)Testing retrieveDegreesTable() method");
        List<Degree> lsDeg = tester.retrieveDegreesTable();
        for (Degree d : lsDeg) {
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
        tester.addModule("COM2008", "super cool module", 120, 2);

        System.out.println("testing addDegree");
        //positive test case
        tester.addDegree("LITU04", "Nineteenth Century French Poetry", false, false);

        System.out.println("testing addStudent");

    }

    // testing the RemovalQueries class methods
    public static void testingRemovalQueries() {

        RemovalQueries tester = new RemovalQueries(testdb);
        testdb.setPrivLevel(4);

        // testing the removeStudent function
        //System.out.println("Testing removeStudent");
        //tester.removeStudent(1001);

        // testing the removeStudentsModuleChoice function
        System.out.println("Testing removeStudentsModuleChoice");
        tester.removeStudentsModuleChoice(1001, "COM1005");

        // testing the removeModule method, removing the COM1005 module
        //System.out.println("Testing removeModule");
        //tester.removeModule("COM1005");

    }

    // object tests
    private static void testingDegreeObject() {
        // instantiate a Degree
        Degree test = new Degree("COMU01", "Computer Science", false, false, null, null);
        System.out.println(test.toString());
    }
}
