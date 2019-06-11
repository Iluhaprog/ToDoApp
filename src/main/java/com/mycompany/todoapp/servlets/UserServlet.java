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
import com.mycompany.todoapp.entitys.User;
import com.mycompany.todoapp.util.HashUtil;
import com.mycompany.todoapp.util.JsonUtil;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        resp.setCharacterEncoding("UTF-8");

        if (resp.getStatus() == 200) {
            resp.setContentType("application/json");

            UserManager userManager = new UserManager();
            DoingManager doingManager = new DoingManager();
            HashUtil hu = new HashUtil();

            JsonUtil jsonUtil = new JsonUtil();

            String userName = (String) session.getAttribute("userName");
            String userPass = (String) session.getAttribute("userPass");

            System.out.println(userPass);

            User user = userManager.getByNameAndPass(userName, userPass);

            System.out.println(user.toString());

            List<Doing> doings = doingManager.getByUserId(user.getId());
            List<Integer> doingCounters = new ArrayList<Integer>();

            for (int i = 0; i < 3; i++) {
                doingCounters.add(doingManager.getDoingsNumberByUserId(user.getId(), i));
            }

            JsonObjectBuilder userJson = jsonUtil.getJsonObject(user);
            JsonObjectBuilder countersJson = jsonUtil.getJsonObject(doingCounters);
            JsonArrayBuilder mainJson = jsonUtil.getJsonArray(doings);
            mainJson.add(userJson);
            mainJson.add(countersJson);

            PrintWriter out = resp.getWriter();
            out.println(mainJson.build());
            out.close();

        } else {
            PrintWriter out = resp.getWriter();
            out.println("none");
            out.close();
        }
    }

}