package com.mycompany.todoapp.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.todoapp.datamanagers.UserManager;
import com.mycompany.todoapp.entitys.User;
import com.mycompany.todoapp.util.HashUtil;

@WebFilter("/login")
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String name = req.getParameter("name");
        String pass = req.getParameter("pass");

        HttpSession session = req.getSession();

        UserManager manager = new UserManager();
        HashUtil hu = new HashUtil(); 
        
        User user = manager.getByNameAndPass(name, hu.md5(pass));
       
        if (user.isEmpty()) {
            resp.setStatus(401);
            resp.setHeader("error", "true");
            session.setAttribute("role", "GUEST");
        } else {
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
            session.setAttribute("userPass", user.getPass());
            session.setAttribute("userRole", user.getRole());

            resp.setStatus(200);
        }
        chain.doFilter(req, resp);
    }

    public void destroy() {
    }



}