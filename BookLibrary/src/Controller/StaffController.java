/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connect;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class StaffController {
    public void AddStaff(JTextField id,JTextField name, JTextField phone,JTextField email,JTextField address,JTextField count,
            JTextField date,JTextField use, JTextField pass,JPanel p)
    {
        if(id.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(p, "Please input ID");
            id.requestFocus();
            return;
        }
        if(name.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(p, "Please input Name");
            name.requestFocus();
            return;
        }
        if(phone.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(p, "Please input Phone");
            phone.requestFocus();
            return;
        }
        if(email.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(p, "Please input Email");
            email.requestFocus();
            return;
        }
        if(address.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(p, "Please input Address");
            address.requestFocus();
            return;
        }
        if(count.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(p, "Please input Shift_count");
            count.requestFocus();
            return;
        }
        /*if(date.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(p, "Please input Start_work_date");
            date.requestFocus();
            return;
        }*/
        if(use.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(p, "Please input Usename");
            use.requestFocus();
            return;
        }
        if(pass.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(p, "Please input Password");
            pass.requestFocus();
            return;
        }
        int ret = JOptionPane.showConfirmDialog(p, "Do you want to add data?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (ret != JOptionPane.YES_OPTION) 
        {
            return; 
        }
        String insert = "INSERT INTO staff (id,Name,Phone,Email,Address,Shift_count,Start_work_date,UserName,Password) "
                + "values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(insert);
            ps.setInt(1, Integer.parseInt(id.getText()));
            ps.setString(2, name.getText());
            ps.setString(3,phone.getText());
            ps.setString(4,email.getText());
            ps.setString(5,address.getText());
            ps.setInt(6, Integer.parseInt(count.getText()));
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8,use.getText());
            ps.setString(9,pass.getText());
            
            ret = ps.executeUpdate();
            System.out.println(ret);
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Connect.close();
        }
    }
}
