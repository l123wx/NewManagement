package db;
import newsManagement.NewsInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Db {
    public static Connection getConnection() throws SQLException,ClassNotFoundException {

        String driverName = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/newsManagement";
        String userName = "root";
        String userPwd = "123456";

        Class.forName(driverName);
        return DriverManager.getConnection(dbURL, userName, userPwd);
    }
    //关闭数据库连接，释放资源
    public static void release(PreparedStatement prestmt, Connection conn,
                               ResultSet rs) {
        if (prestmt != null) {
            try {
                prestmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            prestmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
    }
}
