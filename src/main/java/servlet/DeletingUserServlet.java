package servlet;

import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeletingUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            UserService userService = UserService.getInstance();

            Integer id = Integer.valueOf(request.getParameter("id"));
            userService.deleteUser(id);
            response.sendRedirect("/start");

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ioe) {
            throw new IOException("ошибка при удалении пользователя");
        }
    }
}
