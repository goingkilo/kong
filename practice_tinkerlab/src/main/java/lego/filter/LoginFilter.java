package lego.filter;

import io.dropwizard.auth.AuthFilter;
import lego.user.UserDAO;
import lego.user.UserService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;


@PreMatching
public class LoginFilter extends AuthFilter {

    @Context private HttpServletRequest     req;
    @Context private HttpServletResponse    res;

    UserDAO userDAO ;
    UserService securityService ;

    public LoginFilter() {
    }

    public LoginFilter(UserDAO userDAO) {

        this.userDAO    = userDAO;
        securityService = new UserService(userDAO);
    }


    @Override
    public void filter(ContainerRequestContext crq) throws IOException {

        UriInfo uriInfo =  crq.getUriInfo();
        String path = uriInfo.getAbsolutePath().getPath();


        if ( path.equalsIgnoreCase( "/api/auth/login") ||  path.equalsIgnoreCase( "/api/user/register") ) {
            return;
        }

        String token = crq.getHeaderString("Authorization");
        String email = crq.getHeaderString("user");
        if( !StringUtils.isBlank(token) && !StringUtils.isBlank(email)) {
            if( securityService.validate( email, token) ) {
                return;
            }
        }

        crq.setRequestUri(UriBuilder.fromPath("/api/auth/dump").build());

    }
}
