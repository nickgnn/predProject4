package servlets.filters;

import exception.DBException;
import model.User;
import service.Service;
import service.UserService;
import util.AuthManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = "/admin/*")
public class AdminFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Service service = UserService.getInstance();
        AuthManager authManager = AuthManager.getInstance();

        if (!authManager.isLogged()) {
            req.getRequestDispatcher("userIsNotAuthenticated.jsp").forward(req, resp);
            return;
        }

        String name = authManager.getName();
        User userByName = null;

        try {
            userByName = service.getUserByName(name);
        } catch (DBException e) {
            e.getMessage();
        }

        String role = userByName.getRole();

        if (role.equals("admin")) {
            authManager.setLogged(true);
            chain.doFilter(req, resp);
        }

        if (role.equals("user")) {
            req.getRequestDispatcher("noPermission.jsp").forward(req, resp);
            return;
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}
