package lego.user;

public class UserService {

    UserDAO userDAO;

    public UserService() {
    }

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String login(String email, String password){

        return "";
    }

    public void logout(String email){

    }
    // to be called each time from filter. or exampleAuthenticator
    public boolean validate(String email, String token){
        return false;
    }

    // two passwords ?
    public void register( String email, String password){

    }

}
