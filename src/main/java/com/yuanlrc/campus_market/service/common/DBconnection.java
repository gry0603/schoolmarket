package com.yuanlrc.campus_market.service.common;

import java.sql.*;
public class DBconnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/school?allowPublicKeyRetrieval=true&serverTimezone=GMT%2b8&useUnicode=true&characterEncoding=utf8&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String DRIVER = "com.mysql.jdbc.Driver"; // 更新为新的驱动名

    static {
        try {
            Class.forName(DRIVER); // 加载驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库驱动加载失败");
        }
    }
    public Connection getCon() throws Exception {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // 添加查询方法
    public ResultSet executeQuery(String sql) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = getCon();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}
