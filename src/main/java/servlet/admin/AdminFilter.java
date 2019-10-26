package servlet.admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {

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
        if (requester == null || !requester.equals("admin")) {
            session.invalidate();
            httpServletRequest.getRequestDispatcher("/index.jsp").forward(httpServletRequest, response);
        }
        chain.doFilter(httpServletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
