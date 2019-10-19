package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String s1 = (String) session.getAttribute("password");
        String s2 = (String) session.getAttribute("login");
        String s3 = (String) session.getAttribute("role");


        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");



        resp.sendRedirect("logoutPage.jsp");
    }
}
