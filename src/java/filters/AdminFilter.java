package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;

/**
 *
 * @author awarsyle
 */
public class AdminFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        // code that is executed before the servlet
        
        HttpServletRequest hsr = (HttpServletRequest)request;
        HttpSession session = hsr.getSession();
        
        Role role = null;
        
        // Get the role session attribute
        if (session.getAttribute("role") != null) {
            role = (Role) session.getAttribute("role");
        }
        
        // If the role returned isn't an admin role, send them to the home screen.
        if (role.getRoleID() == 2) {
            HttpServletResponse resp = (HttpServletResponse)response;
            resp.sendRedirect("home");
            return;
        }
        
         // allow the user to access the servlet
         chain.doFilter(request, response);
         
         // code that is executed after the servlet
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       
    }

    @Override
    public void destroy() {
        
    }

}
