/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Connect {
    private static String url = "jdbc:mysql://localhost/thuvien";    
    //private static String driverName = "com.mysql.jdbc.Driver";   
    private static String username = "root";   
    private static String password = "1998";
    private static Connection conn;

    public static Connection getConnection() {
        
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");
        } catch (SQLException ex) {
            System.out.println("Failed connection!");
        }
        
        return conn;

    }
    public static void close()
    {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
