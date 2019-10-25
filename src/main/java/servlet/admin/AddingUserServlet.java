package servlet.admin;

import service.implementation.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/add")
public class AddingUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            UserService userService = UserService.getInstance();

            String login = request.getParameter("login");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String role = "user";
            userService.addUser(name, login, password, role);

            ServletContext servletContext = this.getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/admin");
            requestDispatcher.forward(request, response);

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException | ServletException ioe) {
            throw new IOException("ошибка при добавлении пользователя");
        }
    }
}
