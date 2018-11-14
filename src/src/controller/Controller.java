package src.controller;

/**
 * Controller.java
 * */
public class Controller {

    public static void saveDegree(String degreeCode, String degreeName) {
        src.db_handler.AdditionQueries additionQ = new src.db_handler.AdditionQueries(Main.getDB());
        additionQ.addDepartment(degreeCode, degreeName);
    }

    public static void saveDepartment(String departmentCode, String departmentName) {
        src.db_handler.AdditionQueries additionQ = new src.db_handler.AdditionQueries(Main.getDB());
        additionQ.addDepartment(departmentCode, departmentName);
    }
}
