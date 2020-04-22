package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.dBConnect;

public class LoginController {
     public static boolean isAdminAccountVaild(String user_name, String password) throws IOException, SQLException{
        Statement sta = dBConnect.getConnect().createStatement();
        ResultSet rs = sta.executeQuery("SELECT * FROM admin WHERE UserName = '" + user_name + "' AND Password = '" + password + "'");
        try {
            if (rs.next()){
                return true;
            }
	} finally {
            dBConnect.close();
	}
	return false;
    }
    
    public static boolean isStaffAccountVaild(String user_name, String password) throws IOException, SQLException{
        Statement sta = dBConnect.getConnect().createStatement();
        ResultSet rs = sta.executeQuery("SELECT * FROM staff WHERE UserName = '" + user_name + "' AND Password = '" + password + "'");
        try {
            if (rs.next()){
                return true;
            }
	} finally {
            dBConnect.close();
	}
	return false;
    }
}
