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

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = UserService.getInstance();

        try {
            List<User> allUsers = service.getAllUsers();
            req.setAttribute("usersList", allUsers);
            req.getRequestDispatcher("listOfUsers.jsp").forward(req, resp);

//            allUsers.forEach(System.out::println);
        } catch (DBException e) {
            e.getMessage();
        }
    }
}
