package filters;

import exception.DBException;
import service.Service;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = "/")
public class MyNewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("INIT!!!!!INIT!!!!!INIT!!!!!INIT!!!!!INIT!!!!!INIT!!!!!INIT!!!!!INIT!!!!!INIT!!!!!INIT!!!!!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Service service = UserService.getInstance();

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");

        try {
            //Logged user.
            if (nonNull(session) &&
                    nonNull(session.getAttribute("login")) &&
                    nonNull(session.getAttribute("password"))) {

                String role = (String) session.getAttribute("role");

                moveToMenu(req, res, role);


            } else if (service.isExistsUser(login)) {
                String role = service.getUserByName(login).getRole();

                req.getSession().setAttribute("password", password);
                req.getSession().setAttribute("login", login);
                req.getSession().setAttribute("role", role);

                moveToMenu(req, res, role);

            } else {
                moveToMenu(req, res, "undefined");
            }
        } catch (DBException e) {
            e.getMessage();
        }
    }

    private void moveToMenu(HttpServletRequest req,
                            HttpServletResponse res,
                            String role)
            throws ServletException, IOException {


        if (role.equals("admin")) {
            req.getRequestDispatcher("adminPage.jsp").forward(req, res);
        } else if (role.equals("user")) {
            req.getRequestDispatcher("userPage.jsp").forward(req, res);
        } else {
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {
        System.out.println("DESTROY!!!DESTROY!!!DESTROY!!!DESTROY!!!DESTROY!!!DESTROY!!!DESTROY!!!DESTROY!!!DESTROY!!!DESTROY!!!");
    }
}
