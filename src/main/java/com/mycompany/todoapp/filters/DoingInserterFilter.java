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

@WebFilter("/doing/insert")
public class DoingInserterFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        String role = (String) session.getAttribute("userRole");
        
        System.out.println(role);

        if (role == null || role.equals("GUEST")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login/page");
            dispatcher.forward(req, resp);
        } 
        if (role.equals("USER")) {
            chain.doFilter(req, resp);
        }
        
    }

    public void destroy() {

    }

}