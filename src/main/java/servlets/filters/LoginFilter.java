package servlets.filters;

import exception.DBException;
import model.User;
import service.Service;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/login/*")
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Service service = UserService.getInstance();

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String name = request.getParameter("name");
        User userByName = null;

        try {
            userByName = service.getUserByName(name);
        } catch (DBException e) {
            e.getMessage();
        }

        String role = userByName.getRole();

        if (role.equals("user")) {
//            req.setAttribute("userName", name);
//            req.getRequestDispatcher("/user").forward(req, resp);
            response.sendRedirect("/user");
        }

        if (role.equals("admin")) {
            response.sendRedirect("/admin");
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}