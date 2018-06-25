package misc.auth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kraghunathan on 7/22/16.
 */
public class HUser implements java.security.Principal {

    private String username;

    private List<String> roles;

    public HUser(String username) {
        this.username = username;
    }

    public String getName() {
        return username;
    }

    public void addRole(String role) {
        if( roles == null) {
            roles = new ArrayList<String>();
        }
        this.roles.add(role);
    }

    public List<String> getRoles() {
        return roles;
    }


}
