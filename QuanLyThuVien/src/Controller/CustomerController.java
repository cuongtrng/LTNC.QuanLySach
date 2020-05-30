/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.dBConnect;

/**
 *
 * @author Cuong
 */
public class CustomerController {
    
    public static void AddCustomer(JTextField name, JTextField phone,JTextField email,JTextField address,
             JTextField memship){
        if(name.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please input Name");
            name.requestFocus();
            return;
        }
        if(phone.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please input Phone");
            phone.requestFocus();
            return;
        }
        if(email.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please input Email");
            email.requestFocus();
            return;
        }
        if(address.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please input Address");
            address.requestFocus();
            return;
        }
        if(memship.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please input Membership");
            memship.requestFocus();
            return;
        }
        
        int ret = JOptionPane.showConfirmDialog(null, "Do you want to add data?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.NO_OPTION) 
        {
            return; 
        }
        
        String insert = "INSERT INTO customer (Name,Phone,Email,Address,Registerdate,Expireddate,Membership) "
                + "values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = dBConnect.getConnect().prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1,name.getText());
            ps.setString(2,phone.getText());
            ps.setString(3,email.getText());
            ps.setString(4,address.getText());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            Date d=new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30));
            ps.setTimestamp(6, new Timestamp(d.getTime()));
            ps.setInt(7,Integer.parseInt(memship.getText()));
            
            ResultSet rs = ps.getGeneratedKeys();
            ret = ps.executeUpdate();
            if(ret == 1){
                JOptionPane.showMessageDialog(null, "Adding successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Adding failed");
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            name.setText(null);
            phone.setText(null);
            email.setText(null);
            address.setText(null);
            memship.setText(null);
            dBConnect.close();
        }
    }
    
}
