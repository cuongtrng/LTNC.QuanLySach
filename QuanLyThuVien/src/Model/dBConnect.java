package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dBConnect {
    private static String db_url = "jdbc:mysql://localhost/library1?serverTimezone=UTC";
    private static String user_name = "root";
    private static String password = "12345";
    private static String className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection conn = null;
    
    public static Connection getConnect(){
        try {
            // Khai báo tên driver để kết nối với hệ quản trị cơ sở dữ liệu MYSQL
            Class.forName(className);
            conn = DriverManager.getConnection(db_url, user_name, password);
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
            return conn;
        } catch (Exception ex) {
            System.out.println("Kết nối cơ sở dữ liệu thất bại!");
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void close() {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(dBConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
