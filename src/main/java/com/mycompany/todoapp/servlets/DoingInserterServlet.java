package com.mycompany.todoapp.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.todoapp.datamanagers.DoingManager;

@WebServlet("/doing/insert")
public class DoingInserterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        HttpSession session = req.getSession();

        DoingManager manager = new DoingManager();

        String text = req.getParameter("text");

        Integer userIdI = (Integer) session.getAttribute("userId");
        int userId = userIdI.intValue();

        manager.insert(text, userId);

    }

}