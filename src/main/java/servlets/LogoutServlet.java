package servlets;

import util.AuthManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthManager authManager = AuthManager.getInstance();
        String name = authManager.getName();

        authManager.setLogged(false);
        authManager.setName("");
        authManager.setPassword("");

        req.setAttribute("name", name);
        req.getRequestDispatcher("logoutPage.jsp").forward(req, resp);
    }
}
