package misc.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by kraghunathan on 7/26/16.
 */
public class TicketChecker implements  Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println( ">> WeLcOmE:tIcKeTsPlEaSe !!" );
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            Cookie[] cookies = ((HttpServletRequest) request).getCookies();
            for( Cookie cookie : cookies) {
                if( cookie.getName().equals("letoken") ) {
                    String token = cookie.getValue();
                    if( validate(token) ) {
                        chain.doFilter(request,response);
                    }
                    else {
                        //save work here :(
                    }
                }

            }

        }
        String redirect = ((HttpServletRequest)request).getPathInfo();
        ((HttpServletRequest)request).setAttribute( "redirect", redirect);
        ((HttpServletRequest)request).getRequestDispatcher("/login.html").forward(request,response);

    }

    private boolean validate(String token) {
        String[] pieces = token.split(":");
        return true;
    }

    public void destroy() {
        System.out.println( ">> GOoDbYe:tIcKeTsPlEaSe" );
    }
}
