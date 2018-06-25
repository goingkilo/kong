package misc.auth;

/**
 * Created by kraghunathan on 7/22/16.
 */
public class SimpleCredential {

    private String username;
    private String password;

    public SimpleCredential(String username,String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
