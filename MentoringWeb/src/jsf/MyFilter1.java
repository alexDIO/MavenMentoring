package jsf;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by olomakovskyi on 9/26/2014.
 */
public class MyFilter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("First filter inited");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        System.out.println("Started at " + start);
        filterChain.doFilter(servletRequest,servletResponse);
        long end = System.currentTimeMillis();
        System.out.println("Ended at " + end);
        servletResponse.getWriter().println("Performance " + (end - start));
    }

    @Override
    public void destroy() {
        System.out.println("First filter destroyed");
    }
}
