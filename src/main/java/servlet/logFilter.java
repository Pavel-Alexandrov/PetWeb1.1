package servlet;

import model.User;
import service.implementation.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "logFilter", urlPatterns = {"/login"})
public class logFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        UserService userService = UserService.getInstance();

        User user = userService.getUserByLogin(request.getParameter("login"));
        if (user != null && Objects.equals(request.getParameter("password"), user.getPassword())) {
            switch (user.getRole()) {
                case "admin":
                    {
                        request.getRequestDispatcher("/admin").forward(request, response);
                        break;
                    }
                case "user":
                    {
                        request.setAttribute("user", user);
                        request.getRequestDispatcher("/user").forward(request, response);
                        break;
                    }
            }
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
