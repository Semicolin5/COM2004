package src.objects;

/**
 * Student represents a row of the student the database table,
 * i.e. storing information about one unique student.
 * contains methods to get and set the values of this row.
 * */
public class Student {

    private String loginId;
    private String title;
    private String forename;
    private String surname;
    private String personalTutor;
    private String degreeCode;
    private String levelOfStudy;

    /**
     * Forms the User object given parameters
     * @param loginId String, the Users name code
     * @param title 2 letter string of users title (ms/mr)
     * @param forename String of users forename.
     * @param surname String of users surname
     * @param personalTutor String storing who the student's
     * personal tutor is.
     * @param degreeCode String representing the degree the student is taking
     * @param levelOfStudy String representing the student's level of study.
     * */
    public Student(String loginId, String title, String forename,
                   String surname, String personalTutor,
                   String degreeCode, String levelOfStudy) {
       this.loginId = loginId;
       this.title = title;
       this.forename= forename;
       this.surname = surname;
       this.personalTutor = personalTutor;
       this.degreeCode = degreeCode;
       this.levelOfStudy = levelOfStudy;
    }

    /**
     * Accessor methods
     * */
    public String getLogin() { return loginId; }
    public String getTitle() { return title; }
    public String getForename() { return forename; }
    public String getSurname() { return surname; }
    public String getPersonalTutor() { return personalTutor; }
    public String getDegreeCode() { return degreeCode; }
    public String getLevelOfStudy() { return levelOfStudy; }

    //TODO remove this later, currently just for testing
    public String toString() {
        String details = "loginId: " + loginId + ", title: " + title
                + ", forename: " + forename + ", surname: " + surname
                + ", personalTutor: " + personalTutor + ", degreeCode: "
                + degreeCode + ", level of study: " + levelOfStudy;
        return details;
    }


}







