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

import com.mycompany.todoapp.datamanagers.UserManager;
import com.mycompany.todoapp.entitys.User;

@WebFilter("/login/page")
public class LoginPageFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        String name = (String) session.getAttribute("userName");
        String pass = (String) session.getAttribute("userPass");

        UserManager manager = new UserManager();

        User user = manager.getByNameAndPass(name, pass);

        if (!user.isEmpty()) { 
            RequestDispatcher dispatcher = req.getRequestDispatcher("/user/page"); 
            dispatcher.forward(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
        
    }

    public void destroy() {

    }

}