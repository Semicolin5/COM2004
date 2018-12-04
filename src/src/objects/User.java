package src.objects;

/**
 * User represents a row in the database user table,
 * i.e. storing information about one unique user.
 * contains methods to get and set the values of this row.
 * */
public class User {

    private int login_id;
    private String salt;
    private String password;
    private int priv;

    /**
     * Forms the User object given parameters
     * @param login_id String, the Users name code
     * @param password String that describes department.
     * */
    public User (int login_id, String salt, String password, int priv) {
        this.login_id = login_id;
        this.password = password;
        this.priv = priv;
        this.salt = salt;
    }

    /**
     * Accessor methods
     * */
    public int getLogin() { return login_id; }
    public int getPriv() { return priv; }

}
