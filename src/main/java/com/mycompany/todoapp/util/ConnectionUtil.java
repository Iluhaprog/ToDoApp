package com.mycompany.todoapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionUtil {

    private static final String USER = "root";
    private static final String PASS = "pass";
    private static final String URL = "jdbc:mysql://localhost:3306/ToDoDB?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private Connection conn = null;
    private ResultSet result = null;
    private Statement stmt = null;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            System.out.println("OK!C");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch(Exception e) {
            System.out.println("ERROR!C");
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet doQuery(String sql) {
        try{
            this.conn = ConnectionUtil.getConnection();        
            this.stmt = conn.createStatement();
            this.stmt.executeQuery("SET NAMES 'UTF8'");
            if (sql.contains("insert") || sql.contains("delete") || sql.contains("update")) {
                stmt.executeUpdate(sql);
                return null;
            } else {
                this.result = stmt.executeQuery(sql);
                System.out.println("OK!");
                return this.result;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}