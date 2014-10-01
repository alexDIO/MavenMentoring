package jsf;

import javax.servlet.*;
import java.io.IOException;
import java.util.Random;

/**
 * Created by olomakovskyi on 9/26/2014.
 */
public class MyFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Second filter inited");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Response and request were got");
        Random rnd = new Random();
//        if (rnd.nextInt(100) < 50){
//            System.out.println("epic fail");
//            return;
//        }
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Response and request were thrown");
    }

    @Override
    public void destroy() {
        System.out.println("Second filter destroyed");
    }
}
