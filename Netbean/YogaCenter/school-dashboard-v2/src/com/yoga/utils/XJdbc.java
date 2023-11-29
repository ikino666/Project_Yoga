//Tran Trung Tin - PC07488
package com.yoga.utils;

import java.sql.*;

public class XJdbc {

    public static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String dburl = "jdbc:sqlserver://;serverName=localhost;databaseName=QLTTYoga;trustServerCertificate=true";
    public static String username = "sa";
    public static String password = "system";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static PreparedStatement preparedStatement(String sql, Object... args) throws SQLException {
        Connection conn = DriverManager.getConnection(dburl, username, password);
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt = conn.prepareCall(sql); //proc
        } else {
            pstmt = conn.prepareStatement(sql); //SQL
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement pstmt = preparedStatement(sql, args);
            try {
                return pstmt.executeQuery();
            } finally {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement pstmt = preparedStatement(sql, args);
            try {
                pstmt.executeUpdate();
            } finally {
                pstmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void se(String sql){
        try {
            Connection connection = DriverManager.getConnection(dburl, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String maKHoc = resultSet.getString("MaKhoaHoc");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
