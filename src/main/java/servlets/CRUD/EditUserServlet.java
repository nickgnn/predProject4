package servlets.CRUD;

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

@WebServlet("/edit")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = UserService.getInstance();

        String newName = req.getParameter("newName");
        String newAge = req.getParameter("newAge");
        String newPassword = req.getParameter("newPassword");
        String newRole = req.getParameter("newRole");

        String name = req.getParameter("name");

        req.getRequestDispatcher("/admin").forward(req, resp);

        User user;

        try {
            user = service.getUserByName(name);

            if (!newName.equals("")) {
                service.updateUser(user, newName);
            }

            if (!newAge.equals("")) {
                service.updateUser(user, Integer.valueOf(newAge));
            }

            if (!newPassword.equals("")) {
                service.updateUser(user, user.getName(), user.getAge(), newPassword);
            }

            if (!newRole.equals("")) {
                service.updateUser(user, user.getName(), user.getAge(), user.getPassword(), newRole);
            }
        } catch (DBException e) {
                e.getMessage();
            }
    }
}
