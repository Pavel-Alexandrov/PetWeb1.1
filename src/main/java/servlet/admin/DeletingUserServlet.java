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

@WebServlet("/admin/delete")
public class DeletingUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            UserService userService = UserService.getInstance();

            Integer id = Integer.valueOf(request.getParameter("id"));
            userService.deleteUser(id);

            ServletContext servletContext = this.getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/admin");
            requestDispatcher.forward(request, response);

        } catch (IOException | ServletException ioe) {
            throw new IOException("ошибка при удалении пользователя");
        }
    }
}
