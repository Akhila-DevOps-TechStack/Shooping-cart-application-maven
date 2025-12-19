package com.shashi.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {

    private static Connection conn = null;

    private DBUtil() {
    }

    public static Connection provideConnection() {

        try {
            if (conn == null || conn.isClosed()) {

                ResourceBundle rb = ResourceBundle.getBundle("application");

                String driverName = rb.getString("db.driverName");
                String connectionString = rb.getString("db.connectionString");
                String username = rb.getString("db.username");
                String password = rb.getString("db.password");

                Class.forName(driverName);

                conn = DriverManager.getConnection(
                        connectionString,
                        username,
                        password
                );

                if (conn == null) {
                    throw new RuntimeException("❌ Database connection is NULL");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to establish database connection");
        }

        return conn;
    }

    public static void closeConnection(ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(PreparedStatement ps) {
        try {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
