package servlets;

import exception.DBException;
import model.User;
import service.Service;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = UserService.getInstance();

        String name = req.getParameter("name");
        User user = null;

        try {
            user = service.getUserByName(name);
        } catch (DBException e) {
            e.getMessage();
        }

        if (user.getRole().equals("user")) {

        }

        if (user.getRole().equals("admin")) {
            req.getRequestDispatcher("/users").forward(req, resp);
        }
    }
}
