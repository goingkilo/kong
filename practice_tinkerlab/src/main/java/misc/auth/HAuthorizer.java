package misc.auth;

import io.dropwizard.auth.Authorizer;

/**
 * Created by kraghunathan on 7/23/16.
 */
public class HAuthorizer implements Authorizer<HUser> {

    public boolean authorize(HUser hUser, String role) {
        return hUser.getRoles().contains(role);
    }
}
