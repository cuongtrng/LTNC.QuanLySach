package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.dBConnect;

public class LoginController {
     public static boolean isAccountVaild(String user_name, String password) throws IOException, SQLException{
        Statement sta = dBConnect.getConnect().createStatement();
        ResultSet rs = sta.executeQuery("SELECT * FROM taikhoan WHERE tendangnhap = '" + user_name + "' AND matkhau = '" + password + "'");
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
