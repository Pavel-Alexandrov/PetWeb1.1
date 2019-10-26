package servlet.user;

import service.implementation.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserFilter", urlPatterns = {"/user/*"})
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            httpServletRequest.getRequestDispatcher("/index.jsp").forward(httpServletRequest, response);
        }
        String requester = (String) session.getAttribute("requester");
        UserService userService = UserService.getInstance();
        if (requester == null || userService.getUserByLogin(requester) == null) {
            session.invalidate();
            httpServletRequest.getRequestDispatcher("/index.jsp").forward(httpServletRequest, response);
        }
        chain.doFilter(httpServletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
