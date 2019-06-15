package com.mycompany.todoapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {

    private static final String USER = "root";
    private static final String PASS = "pass";
    private static final String URL = "jdbc:mysql://localhost:3306/ToDoDB?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private Connection conn = null;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("ERROR!C");
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet doQuery(String sql) {
        try {
            conn = ConnectionUtil.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeQuery("SET NAMES 'UTF8'");
            ResultSet result = null;
            if (sql.contains("insert") || sql.contains("delete") || sql.contains("update")) {
                stmt.executeUpdate(sql);
            } else {
                result = stmt.executeQuery(sql);
            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}