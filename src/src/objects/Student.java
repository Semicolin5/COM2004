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
    private String email;

    /**
     * Forms the User object given parameters
     * @param loginId String, the Users name code
     * @param title 2 letter string of users title (ms/mr)
     * @param forename String of users forename.
     * @param surname String of users surname
     * @param personalTutor String storing who the student's
     * personal tutor is.
     * @param degreeCode String representing the degree the student is taking
     * @param email String representing the student's email address.
     * */
    public Student(String loginId, String title, String forename,
                   String surname, String personalTutor, String email,
                   String degreeCode) {
       this.loginId = loginId;
       this.title = title;
       this.forename= forename;
       this.surname = surname;
       this.personalTutor = personalTutor;
       this.email = email;
       this.degreeCode = degreeCode;
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
    public String getEmail() { return email; }

    /**
     * toString returns a String that neatly represents an instance of the Student object. 
     * */
    public String toString() {
        String details = "loginId: " + loginId + ", title: " + title
                + ", forename: " + forename + ", surname: " + surname
                + ", personalTutor: " + personalTutor + ", degreeCode: "
                + degreeCode + ", level of study: " + email;
        return details;
    }


}







