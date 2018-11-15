package src.controller;

import src.model.db_handler.*;
import src.objects.Department;

import java.util.List;

/**
 * Controller.java
 * Contains methods called from GUI classes that handle dataflow to and from the database.
 * Methods call the appropriate methods from db_handler classes, and also is responsible
 * for system logic, like checking for SQL injection, or ensuring that appropriate parameters
 * are being passed.
 * */
public class Controller {

    public static void saveDegree(String degreeCode, String degreeName) {
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addDepartment(degreeCode, degreeName);
    }

    public static void saveDepartment(String departmentCode, String departmentName) {
        // TODO maybe have logic to check that the parameters are the right length?
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addDepartment(departmentCode, departmentName);
    }

    public static List<Department> getDepartments() {
        RetrieveQueries retrieveQ = new RetrieveQueries(Main.getDB());
        return retrieveQ.retrieveDepartmentTable();
    }

    public static void removeDepartment(String code) {
        RemovalQueries removalQ = new RemovalQueries(Main.getDB());
        removalQ.removeDepartment(code);
    }

    public static void saveUser(String login, String pass, int priv, String salt) {
        AdditionQueries additionQ = new AdditionQueries(Main.getDB());
        additionQ.addUser(login, pass, priv, salt);
    }
}
