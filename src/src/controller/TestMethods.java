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
        //testingAdditionQueries();
        testingRetrieveQueries();
        //testingDegreeObject();
    }

    // testing the RetrieveQuery class methods
    private static void testingRetrieveQueries() {

        RetrieveQueries tester = new RetrieveQueries(testdb);

        //System.out.println("retrieving grades list");
        List<Grade> gs = tester.retrieveGradeAtPeriodOfStudy(1001, "B");
        for (Grade g : gs) {
            System.out.println(g.toString());
        }

        //System.out.println("is module repeated");
        //boolean result = tester.isGradeRepeat(1001, "COM1005", "B");
        //System.out.println(result);
        //System.out.println("testing retrieveStudentsModules");
        //List<Module> modulesStudentTakes = tester.retrieveStudentsModules(1001);
        //for (Module m : modulesStudentTakes) {
        //    System.out.println(m.toString());
        //}

        //System.out.println("testing retrieveStudentsModulesGrades");
        //Grade g = tester.retrieveStudentsModuleGrade(1001, "COM1005");
        //System.out.println(g.toString());


    }

    // testing the AdditionQuery class methods
    private static void testingAdditionQueries() {

        AdditionQueries tester = new AdditionQueries(testdb);

        System.out.println("testing a");
        Float init = new Float((float) 7.64);
        Float resit = new Float((float) 49.55);
        //tester.updateGrade(1001, "COM1005", "B", init, resit);

        //System.out.println("testing createStudentModuleAssociation() method");
        //tester.createStudentModuleAssociation(1001, "B", "RSS2109");

        //tester.addDepartment("LIT", "English Literature");
        //tester.addModule("COM2008", "super cool module", 120, 2);
        //tester.addDegree("LITU04", "Nineteenth Century French Poetry", false, false);

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

    // object tests
    private static void testingDegreeObject() {
        // instantiate a Degree
        Degree test = new Degree("COMU01", "Computer Science", false, false, null, null);
        System.out.println(test.toString());
    }
}
