package src.controller;

public class GUIHandler {

    public void saveDegree(String degreeCode, String degreeName){
        src.db_handler.DatabaseHandler db = new src.db_handler.DatabaseHandler();
        src.db_handler.AdditionQueries additionQ = new src.db_handler.AdditionQueries(db); //^TODO CHECK OUT THIS ERROR
        additionQ.addDegree(degreeCode, degreeName);
    }

    public void saveDepartment(String departmentCode, String departmentName){
        src.db_handler.DatabaseHandler db = new src.db_handler.DatabaseHandler();
        src.db_handler.AdditionQueries additionQ = new src.db_handler.AdditionQueries(db); //^TODO CHECK OUT THIS ERROR
        additionQ.addDepartment(departmentCode, departmentName);
    }
}
