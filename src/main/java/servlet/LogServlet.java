package servlet;

import model.User;
import service.implementation.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/login")
public class LogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        UserService userService = UserService.getInstance();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userService.getUserByLogin(login);

        if (user == null || !password.equals(user.getPassword())) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        if (login.equals("admin")) {
            HttpSession session = request.getSession();
            session.setAttribute("requester", "admin");
            response.sendRedirect("/admin");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("requester", login);
            request.getRequestDispatcher("/user").forward(request, response);
        }

    }
}
