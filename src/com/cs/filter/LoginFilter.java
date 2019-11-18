package com.cs.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends BaseFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String action = request.getParameter("action");
        String uri = request.getRequestURI();
        Object id = request.getServletContext().getAttribute("uId");
        if (id != null || uri.endsWith("login.jsp") || uri.endsWith("register.jsp") || uri.endsWith(".css") || uri.endsWith(".js") || "login".equals(action) || "register".equals(action)) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}