package com.mycompany.todoapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.json.JsonArrayBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.todoapp.datamanagers.DoingManager;
import com.mycompany.todoapp.entitys.Doing;
import com.mycompany.todoapp.util.JsonUtil;

@WebServlet("/doing/change")
public class DoingChangerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer userIdI = (Integer) session.getAttribute("userId");

        String doingIdStr = req.getParameter("id");
        String doingCmplStr = req.getParameter("completed");

        int userId = userIdI.intValue();
        int doingId = Integer.parseInt(doingIdStr);
        boolean doingCompl = Boolean.parseBoolean(doingCmplStr);

        System.out.println(doingCmplStr);

        DoingManager manager = new DoingManager();
        manager.update(doingId, doingCompl, userId);
    }

}