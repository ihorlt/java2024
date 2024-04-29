package ua.edu.nung.pz.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.edu.nung.pz.dao.entity.User;

import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = "/cart/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute(User.USER_SESSION_NAME) != null) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("/");
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
