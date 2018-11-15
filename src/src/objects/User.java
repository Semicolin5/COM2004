package src.objects;

/**
 * User represents a row in the database user table,
 * i.e. storing information about one unique user.
 * contains methods to get and set the values of this row.
 * */
public class User {

    private String login_id;
    private String password;
    private int priv;
    private String salt;

    /**
     * Forms the User object given parameters
     * @param login_id String, the Users name code
     * @param password String that describes department.
     * */
    public User (String login_id, String password, int priv, String salt) {
        this.login_id = login_id;
        this.password = password;
        this.priv = priv;
        this.salt = salt;
    }

    /**
     * Accessor methods
     * */
    public String getLogin() { return login_id; }
    public String getPassword() { return password; }
    public int getPriv() { return priv; }

    //TODO remove this later, currently just for testing
    public String toString() {return (login_id + ", " + priv + ", pw: " + password);}

}
