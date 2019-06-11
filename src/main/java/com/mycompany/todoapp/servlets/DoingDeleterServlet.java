package com.mycompany.todoapp.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.todoapp.datamanagers.DoingManager;

@WebServlet("/doing/delete")
public class DoingDeleterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        
        HttpSession session = req.getSession();

        Integer userIdI = (Integer) session.getAttribute("userId");
        int userId = userIdI.intValue();


        String doingIdS = req.getParameter("id");
        int doingId = Integer.parseInt(doingIdS);

        DoingManager manager = new DoingManager();
        manager.deleteByIdAndUserId(doingId, userId);
    }

}