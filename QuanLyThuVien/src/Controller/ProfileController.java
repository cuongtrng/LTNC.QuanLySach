/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
            JLabel address,JLabel count, JLabel date,JLabel user, JLabel image) throws SQLException{
        idL.setText(""+id);
        Statement sta = dBConnect.getConnect().createStatement();
        ResultSet rs = sta.executeQuery("SELECT * FROM staff WHERE id = '" + id + "'");
        if(rs.next()){
            try {
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
                    date.setText(swd);
                }
                
                user.setText("Hello " + rs.getString("UserName") + " !");
                
                InputStream is = rs.getBinaryStream("Image");
                if (is != null){
                    BufferedImage im = ImageIO.read(is);
                    Image imgs = im.getScaledInstance(image.getWidth(), image.getHeight(),Image.SCALE_SMOOTH);
                    image.setIcon(new ImageIcon(imgs));
                }
                
            } catch (IOException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        dBConnect.close();
    }
    
    public static void getAdminInfoById(int id, JLabel idL,JLabel name, JLabel phone,JLabel email,
            JLabel address,JLabel count, JLabel date,JLabel user, JLabel addressLabel, 
            JLabel countLabel, JLabel dateLabel, JLabel image) throws SQLException{
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
            
            InputStream is = rs.getBinaryStream("Image");
            if (is != null){
                try {
                    BufferedImage im = ImageIO.read(is);
                    Image imgs = im.getScaledInstance(image.getWidth(), image.getHeight(),Image.SCALE_SMOOTH);
                    image.setIcon(new ImageIcon(imgs));
                } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        dBConnect.close();
    }
}
