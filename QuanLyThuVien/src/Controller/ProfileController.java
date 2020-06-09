/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Staff;
import model.dBConnect;

/**
 *
 * @author Cuong
 */
public class ProfileController {
    public static void getStaffInfoById(int id, JLabel idL,JLabel name, JLabel phone,JLabel email,
            JLabel address,JLabel count, JLabel date,JLabel user) throws SQLException{
        idL.setText(""+id);
        Statement sta = dBConnect.getConnect().createStatement();
        ResultSet rs = sta.executeQuery("SELECT * FROM staff WHERE id = '" + id + "'");
        if(rs.next()){
            String sname = rs.getString("Name");
            if(!(sname == null)){
                name.setText(sname);
            }
            
            String sphone = rs.getString("Phone");
            if(!(sphone == null)){
                phone.setText(sphone);
            }
            
            String smail = rs.getString("Email");
            if(!(smail == null)){
                email.setText(smail);
            }
            
            String saddr = rs.getString("Address");
            if(!(saddr == null)){
                address.setText(saddr);
            }

            count.setText(rs.getString("Shift_count"));
            
            String swd = rs.getString("Start_work_date");
            if(!(swd == null)){
                System.out.println("here");
                date.setText(swd);
            }

            user.setText("Hello " + rs.getString("UserName") + " !");
        }
        dBConnect.close();
    }
    
    public static void getAdminInfoById(int id, JLabel idL,JLabel name, JLabel phone,JLabel email,
            JLabel address,JLabel count, JLabel date,JLabel user, JLabel addressLabel, 
            JLabel countLabel, JLabel dateLabel) throws SQLException{
        idL.setText(""+id);
        Statement sta = dBConnect.getConnect().createStatement();
        ResultSet rs = sta.executeQuery("SELECT * FROM admin WHERE id = '" + id + "'");
        if(rs.next()){
            String sname = rs.getString("Name");
            if(!(sname == null)){
                System.out.println(sname);
                name.setText(sname);
            }
            
            String sphone = rs.getString("Phone");
            if(!(sphone == null)){
                phone.setText(sphone);
            }
            
            String smail = rs.getString("Email");
            if(!(smail == null)){
                email.setText(smail);
            }
            
            address.setVisible(false);
            count.setVisible(false);
            date.setVisible(false);
            addressLabel.setVisible(false);
            countLabel.setVisible(false);
            dateLabel.setVisible(false);
            user.setText("Hello " + rs.getString("UserName") + " !");
        }
        dBConnect.close();
    }
}
