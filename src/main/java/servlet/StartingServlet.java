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

@WebServlet("/start")
public class StartingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            UserService userService = UserService.getInstance();
            request.setAttribute("userList", userService.getAllUsers());

            ServletContext servletContext = this.getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(request, response);
        }catch (IOException ioe) {
            throw new IOException("ошибка при получении списка пользователей");
        }
    }
}
