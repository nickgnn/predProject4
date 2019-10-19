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
import java.util.List;

@WebServlet("/admin")
public class AdminPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = UserService.getInstance();

        try {
            List<User> allUsers = service.getAllUsers();
            req.setAttribute("usersList", allUsers);
            req.getRequestDispatcher("adminPage.jsp").forward(req, resp);
        } catch (DBException e) {
            e.getMessage();
        }
    }
}
