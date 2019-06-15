package com.mycompany.todoapp.datamanagers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.todoapp.entitys.User;
import com.mycompany.todoapp.util.ConnectionUtil;

public class UserManager {

    private User setDataToUser(ResultSet rs) {
        User user = new User();
        try{
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPass(rs.getString("password"));
                user.setRole(rs.getString("role"));
            } 
            return user;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getById(int id) {
        ConnectionUtil cu = new ConnectionUtil();

        String sql = "select * from Users where id=" + id;
        ResultSet rs = cu.doQuery(sql);
        
        User user = setDataToUser(rs);
        cu.close();
        return user;
    }

    public User getByNameAndPass(String name, String pass) {
        ConnectionUtil cu = new ConnectionUtil();

        String sql = "select * from Users where name=\"" + name + "\" and password=\"" + pass + "\""; 
        ResultSet rs = cu.doQuery(sql);

        User user = setDataToUser(rs);
        cu.close();
        return user;
    }

    public void insert(String name, String pass) {
        ConnectionUtil cu = new ConnectionUtil();
        String sql = "insert into Users(name, password) values ( \"" + name + "\",\"" + pass + "\")"; 
        cu.doQuery(sql);
        cu.close();
    }

    public void deleteById(int id) {
        ConnectionUtil cu = new ConnectionUtil();
        String sql = "delete from Users where id=" + id;
        cu.doQuery(sql);
        cu.close();
    }

    public List<User> getAll() {
        ConnectionUtil cu = new ConnectionUtil();
        List<User> users = new ArrayList<User>();

        try{
            String sql = "select * from Users";
            ResultSet rs = cu.doQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPass(rs.getString("password"));
                user.setRole(rs.getString("role"));
                users.add(user);
            } 
            cu.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

}