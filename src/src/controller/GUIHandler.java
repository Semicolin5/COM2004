package src.controller;

public class GUIHandler {
    public void saveDegree(String degreeCode, String degreeName){
        System.out.println("saveDegree" + degreeCode + " " + degreeName);
        src.db_handler.DatabaseHandler db = new src.db_handler.DatabaseHandler();
        src.db_handler.AdditionQueries additionQ = new src.db_handler.AdditionQueries(db); //^TODO CHECK OUT THIS ERROR
        additionQ.addDegree(degreeCode, degreeName);
    }
}
