package com.mycompany.todoapp.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/user")
public class UserFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        HttpSession session = req.getSession();

        String role = (String) session.getAttribute("userRole");

        if (role == null || role.equals("GUEST")) {
            resp.setStatus(401);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/view/login/login.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.setStatus(200);
            chain.doFilter(req, resp);
        }
    }

    public void destroy() {

    }
    
}