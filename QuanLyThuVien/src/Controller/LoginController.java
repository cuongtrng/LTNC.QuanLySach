package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.dBConnect;

public class LoginController {
     public static int isAdminAccountVaild(String user_name, String password) throws IOException, SQLException{
        Statement sta = dBConnect.getConnect().createStatement();
        ResultSet rs = sta.executeQuery("SELECT * FROM admin WHERE UserName = '" + user_name + "' AND Password = '" + password + "'");
        try {
            if (rs.next()){
                return rs.getInt("id");
            }
	} finally {
            dBConnect.close();
	}
	return -1;
    }
    
    public static int isStaffAccountVaild(String user_name, String password) throws IOException, SQLException{
        Statement sta = dBConnect.getConnect().createStatement();
        ResultSet rs = sta.executeQuery("SELECT * FROM staff WHERE UserName = '" + user_name + "' AND Password = '" + password + "'");
        try {
            if (rs.next()){
                return rs.getInt("id");
            }
	} finally {
            dBConnect.close();
	}
	return -1;
    }
}
