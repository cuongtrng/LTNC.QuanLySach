/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cuong
 */
public class dBConnect {
    private static final String db_url = "jdbc:mysql://localhost:3306/library1";
    private static final String user_name = "root";
    private static final String password = "1234";
    private static final String className = "com.mysql.jdbc.Driver";
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
    public static void main(String[] args) {
        //new dBConnect();
        getConnect();
                
    }
}
