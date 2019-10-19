package servlets;

import exception.DBException;
import service.Service;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = UserService.getInstance();

        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        req.getRequestDispatcher("/admin").forward(req, resp);

        try {
            service.addUser(name, Integer.valueOf(age), password, role);
        } catch (DBException e) {
            e.getMessage();
        }
    }
}
