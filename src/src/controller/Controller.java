package src.controller;

public class Controller {

    public static void saveDegree(String degreeCode, String degreeName, src.db_handler.DatabaseHandler db) {
        src.db_handler.AdditionQueries additionQ = new src.db_handler.AdditionQueries(db);
        additionQ.addDepartment(degreeCode, degreeName);
    }

    public static void saveDepartment(String departmentCode, String departmentName, src.db_handler.DatabaseHandler db) {
        src.db_handler.AdditionQueries additionQ = new src.db_handler.AdditionQueries(db);
        additionQ.addDepartment(departmentCode, departmentName);
    }
}
