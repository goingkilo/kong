package lego.user;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.Optional;

public class ExampleAuthenticator implements Authenticator<BasicCredentials, User> {

    //see for UnitOfWorkAwareProxy authenticatr : https://spin.atomicobject.com/2016/07/26/dropwizard-dive-part-1/
    UserDAO dao;

    public ExampleAuthenticator(){}

    public ExampleAuthenticator( UserDAO dao){
        this.dao = dao;
    }

    @Override
    @UnitOfWork
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {

        User user = dao.getByEmail( credentials.getUsername());
        if( user != null ) {
            return Optional.of(user);
        }
        return Optional.empty();
    }
}

/* from the net :https://groups.google.com/forum/#!topic/dropwizard-user/VqlYB9JKC9o
 public class UserAuthenticator implements Authenticator<BasicCredentials, User> {
        private final UserDAO userDAO;

        public UserAuthenticator(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
            Optional<User> optionalUser = userDAO.find(basicCredentials.getUsername());
            if (!optionalUser.isPresent()) {
                return optionalUser;
            }

            User user = optionalUser.get();
            byte[] hash = hashPassword(user.getSalt(), basicCredentials.getPassword());
            if (Arrays.equals(hash, user.getHash())) {
                return optionalUser;
            } else {
                return Optional.absent();
            }
        }
    }

 */