package lego.user;


import io.dropwizard.auth.Authorizer;

public class ExampleAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String role) {
        return user.getRole() != null && user.getRole().equalsIgnoreCase(role);
    }
}
