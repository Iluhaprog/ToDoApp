package com.mycompany.todoapp.datamanagers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.todoapp.entitys.Doing;
import com.mycompany.todoapp.util.ConnectionUtil;

public class DoingManager {

    private Doing setDataToDoing(ResultSet rs) {
        Doing doing = new Doing();

        try {
            while(rs.next()) {
                doing.setId(rs.getInt("id"));
                doing.setText(rs.getString("doingText"));
                doing.setDate(rs.getString("doingDate"));
                doing.setComleted(rs.getBoolean("isCompleted"));
                doing.setUserId(rs.getInt("userId"));
            }
            return doing;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*  
        Если mod = 0 , то мы получаем количество задач/дел пользователя;
        Если mod = 1 , то мы получаем количество невыполненных задач/дел пользователя;
        Если mod = 2 , то ма получаем количество выполненных задач/дел пользователя.
    */
    public int getDoingsNumberByUserId(int userId, int mod) {
        int number = 0;
        String sql = "";

        ConnectionUtil cu = new ConnectionUtil();
        if (mod == 0) {
            sql = "select count(1) from Doings where userId=" + userId;
        } else if (mod == 1) {
            sql = "select count(1) from Doings where userId=" + userId + " and isCompleted=false";
        } else if (mod == 2) {
            sql = "select count(1) from Doings where userId=" + userId + " and isCompleted=true";
        }

        ResultSet rs = cu.doQuery(sql);

        try {
            while(rs.next()) {
                number = rs.getInt("count(1)");
            }
            return number;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<Doing> getByUserId(int userId) {
        List<Doing> doings = new ArrayList<Doing>();

        ConnectionUtil cu = new ConnectionUtil();
        String sql = "select Doings.id, Doings.doingText, Doings.doingDate, Doings.isCompleted, " + 
                     "Doings.userId from Doings inner join Users on Doings.userId=Users.id where Users.id=" + userId;
        ResultSet rs = cu.doQuery(sql);

        try {
            while(rs.next()) {
                Doing doing = new Doing();
                doing.setId(rs.getInt("id"));
                doing.setText(rs.getString("doingText"));
                doing.setDate(rs.getString("doingDate"));
                doing.setComleted(rs.getBoolean("isCompleted"));
                doing.setUserId(rs.getInt("userId"));
                doings.add(doing);
            }
            return doings;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Doing getById(int id) {
        ConnectionUtil cu = new ConnectionUtil();
        String sql = "select * from Doings where id=" + id;
        ResultSet rs = cu.doQuery(sql);

        Doing doing = setDataToDoing(rs);

        return doing;
    }

    public void insert(String text, int userId) {
        ConnectionUtil cu = new ConnectionUtil();
        String sql = "insert into Doings(doingText, userId) values(\"" + text + "\"," + userId + ")";
        cu.doQuery(sql);
    } 

    public void deleteByIdAndUserId(int id, int userId) {
        ConnectionUtil cu = new ConnectionUtil();
        String sql = "delete from Doings where id=" + id + " and userId=" + userId;
        cu.doQuery(sql);
    }

    public void deleteById(int id) {
        ConnectionUtil cu = new ConnectionUtil();
        String sql = "delete from Doings where id=" + id;
        cu.doQuery(sql);
    }

    public void update(int id, String text, int userId) {
        ConnectionUtil cu = new ConnectionUtil();
        String sql = "update Doings set doingText=" + text + " where id=" + id + " and userId=" + userId;
        cu.doQuery(sql);
    }

    public void update(int id, boolean isCompleted, int userId) {
        ConnectionUtil cu = new ConnectionUtil();
        String sql = "update Doings set isCompleted=" + isCompleted + " where id=" + id + " and userId=" + userId;
        cu.doQuery(sql);
    }

    public List<Doing> getAll() {
        List<Doing> doings = new ArrayList<Doing>();

        ConnectionUtil cu = new ConnectionUtil();
        String sql = "select * from Doings";
        ResultSet rs = cu.doQuery(sql);

        try {
            while(rs.next()) {
                Doing doing = new Doing();
                doing.setId(rs.getInt("id"));
                doing.setText(rs.getString("doingText"));
                doing.setDate(rs.getString("doingDate"));
                doing.setComleted(rs.getBoolean("isCompleted"));
                doing.setUserId(rs.getInt("userId"));
                doings.add(doing);
            }
            return doings;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}