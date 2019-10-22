package servlet;

import service.implementation.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdatingUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            UserService userService = UserService.getInstance();

            String login = request.getParameter("login");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            userService.updateUser(name, login, password);
            response.sendRedirect("/start");

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ioe) {
            throw new IOException("ошибка при изменении пользователя");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        request.setAttribute("login", request.getParameter("login"));
        request.setAttribute("id", request.getParameter("id"));

        ServletContext servletContext = this.getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/update.jsp");
        requestDispatcher.forward(request, response);
    }
}
