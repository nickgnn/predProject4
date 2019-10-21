package servlets.filters;

import exception.DBException;
import model.User;
import service.Service;
import service.UserService;
import util.AuthManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/login/*")
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Service service = UserService.getInstance();
        AuthManager authManager = AuthManager.getInstance();
        User userByName;
        String role = "";

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String name = request.getParameter("name");
        String password = request.getParameter("pass");
        authManager.setName(name);
        authManager.setPassword(password);

        try {
            if (AuthManager.isLogin(name, password)) {
                userByName = service.getUserByName(name);
                role = userByName.getRole();
            } else {
                req.getRequestDispatcher("wrongLoginOrPassword.jsp").forward(req, resp);
                return;
            }
        } catch (DBException e) {
            e.getMessage();
        }

        if (role.equals("user")) {
            authManager.setLogged(true);
            response.sendRedirect("/user");
        }

        if (role.equals("admin")) {
            authManager.setLogged(true);
            response.sendRedirect("/admin");
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}