package misc.auth;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by kraghunathan on 7/26/16.
 */
public class HInterceptor implements  javax.servlet.Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println( ">> WeLcOmE" );
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            String url = ((HttpServletRequest)request).getRequestURL().toString();
            String path = ((HttpServletRequest)request).getPathInfo();

            System.out.println( ">> " + url);

            if( url.equalsIgnoreCase("/login.html")
                    || path.startsWith("/js") || path.startsWith("/css")
                    || path.startsWith("/font/") || path.startsWith("/images")) {
                System.out.println( ">>" + url + " [allow]");
            }
            else if( path.startsWith("/api/login")) { // permit this
                System.out.println( ">>" + url + " [ register]");
                String login_id = request.getParameter("email");
                String password = request.getParameter( "password");
                String register = request.getParameter( "register");
                System.out.println( ">> allow " + login_id + "/" + password + "// " + register );
            }
            else {
                //if tokens exist
                String token  = ((HttpServletRequest) request).getHeader("letoken");
                if( token == null || token.equals("") ) {
                    System.out.println( ">>" + url + " [block no token]");
                    request.getRequestDispatcher("/login.html").forward(request,response);
                }
                else {
                    System.out.println( ">>" + url + " [--allow w token]" + token);
                    chain.doFilter(request, response);
                }
            }
            String queryString = ((HttpServletRequest)request).getQueryString();

        }

    }

    public void destroy() {
        System.out.println( ">> GOoDbYe" );
    }
}
