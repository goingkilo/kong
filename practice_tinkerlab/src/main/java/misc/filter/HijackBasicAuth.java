package misc.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kraghunathan on 7/26/16.
 */
public class HijackBasicAuth implements  Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if( response instanceof HttpServletResponse) {
            HttpServletResponse httpResponse = ((HttpServletResponse) response);
            for( String x : httpResponse.getHeaderNames()) {
                System.out.println("[] >> >> >> " + httpResponse.getHeader(x));
            }
            if (httpResponse.getHeader("WWW-Authenticate") != null ) {
                httpResponse.sendRedirect("/login.html");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    public void destroy() {
    }
}
