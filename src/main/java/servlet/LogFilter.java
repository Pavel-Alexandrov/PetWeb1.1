package servlet;

import model.User;
import service.implementation.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "LogFilter", urlPatterns = {"/login"})
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        if (login == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        switch (login) {
            case "admin": {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("requester", "admin");
                request.getRequestDispatcher("/admin").forward(httpServletRequest, response);
                break;
            }
            default: {
                UserService userService = UserService.getInstance();
                User user = userService.getUserByLogin(request.getParameter("login"));
                if (user == null) {
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("requester", user.getLogin());
                request.getRequestDispatcher("/user").forward(httpServletRequest, response);
                break;
            }
        }
    }

    @Override
    public void destroy() {

    }
}
