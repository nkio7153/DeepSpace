package com.depthspace.utils;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {
    private DBUtil(){}
    //取得資料庫連接屬性
    private static ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
    private static String driver=bundle.getString("driver");
    private static String url=bundle.getString("url");
    private static String user=bundle.getString("user");
    private static String password= bundle.getString("password");
    //載入驅動
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //建立連接
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
    //關閉資源
    public static void close(Connection conn, Statement ps, ResultSet rs)  {
        if(rs!=null) {
            try {
                 rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
