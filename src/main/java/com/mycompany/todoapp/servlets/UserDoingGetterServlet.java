package com.mycompany.todoapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.todoapp.datamanagers.DoingManager;
import com.mycompany.todoapp.datamanagers.UserManager;
import com.mycompany.todoapp.entitys.Doing;
import com.mycompany.todoapp.util.JsonUtil;

@WebServlet("/doing/get/user")
public class UserDoingGetterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        DoingManager doingManager = new DoingManager();

        JsonUtil jsonUtil = new JsonUtil();

        Integer userIdI = (Integer) session.getAttribute("userId");
        System.out.println(userIdI);
        int userId = userIdI.intValue();

        List<Doing> doings = doingManager.getByUserId(userId);
        List<Integer> doingCounters = new ArrayList<Integer>();

        for (int i = 0; i < 3; i++) {
            doingCounters.add(doingManager.getDoingsNumberByUserId(userId, i));
        }

        JsonArrayBuilder mainJson = jsonUtil.getJsonArray(doings);
        JsonObjectBuilder countersJson = jsonUtil.getJsonObject(doingCounters);

        mainJson.add(countersJson);

        PrintWriter out = resp.getWriter();
        out.println(mainJson.build());
        out.close();
    }

}