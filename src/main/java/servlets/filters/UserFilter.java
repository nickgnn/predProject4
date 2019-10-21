package servlets.filters;

import util.AuthManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "UserFilter", urlPatterns = "/user/*")
public class UserFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        AuthManager authManager = AuthManager.getInstance();

        if (!authManager.isLogged()) {
            req.getRequestDispatcher("userIsNotAuthenticated.jsp").forward(req, resp);
            return;
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}
