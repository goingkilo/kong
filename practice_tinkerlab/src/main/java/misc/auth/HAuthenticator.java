//package misc.auth;
//
//import com.google.common.base.Optional;
//import io.dropwizard.auth.AuthenticationException;
//import io.dropwizard.auth.Authenticator;
//import io.dropwizard.auth.basic.BasicCredentials;
//
///**
// * Created by kraghunathan on 7/22/16.
// */
//public class HAuthenticator implements Authenticator<BasicCredentials, HUser> {
//
//    public Optional<HUser> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
//        if (  "password".equals(basicCredentials.getPassword())  &&  "user".equals(basicCredentials.getUsername())) {
//            return Optional.of(new HUser(basicCredentials.getUsername()));
//        }
//        throw new io.dropwizard.auth.AuthenticationException("Sign in failed.");
//
//    }
//}
