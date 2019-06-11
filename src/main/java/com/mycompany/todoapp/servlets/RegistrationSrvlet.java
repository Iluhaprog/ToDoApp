package com.mycompany.todoapp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.todoapp.datamanagers.UserManager;
import com.mycompany.todoapp.entitys.User;
import com.mycompany.todoapp.util.HashUtil;

@WebServlet("/registration")
public class RegistrationSrvlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String pass = req.getParameter("pass");

        UserManager manager = new UserManager();
        
        List<User> users = manager.getAll();
        boolean isSingle = true;
        
        for (User user : users) {
            if (user.getName().equals(name)) {
                isSingle = false;
                break;
            }
        }
        if (isSingle) {
            HashUtil hu = new HashUtil();
            manager.insert(name, hu.md5(pass));
            resp.setHeader("exist", "false");
        } else {
            resp.setHeader("exist", "true");
            resp.setStatus(409);
        }        
    }
}