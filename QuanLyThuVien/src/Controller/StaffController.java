/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.dBConnect;

/**
 *
 * @author admin
 */
public class StaffController {
    public void AddStaff(JTextField name, JTextField phone,JTextField email,JTextField address,JTextField count,
            JTextField user, JTextField pass,JPanel p)
    {
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
        if(user.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(p, "Please input Usename");
            user.requestFocus();
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
        String insert = "INSERT INTO staff (Name,Phone,Email,Address,Shift_count,Start_work_date,UserName,Password) "
                + "values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = dBConnect.getConnect().prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, name.getText());
            ps.setString(2,phone.getText());
            ps.setString(3,email.getText());
            ps.setString(4,address.getText());
            ps.setInt(5, Integer.parseInt(count.getText()));
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(7,user.getText());
            ps.setString(8,pass.getText());
            
            ResultSet rs = ps.getGeneratedKeys();
            ret = ps.executeUpdate();
            if(ret == 1){System.out.println("Thêm nhân viên thành công");}
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            dBConnect.close();
        }
    }
     public void IdStaff(JComboBox id)
    {
        id.removeAllItems();
        String sql = "select * From staff";
        try {
            Statement st = dBConnect.getConnect().createStatement();
            ResultSet rs = st.executeQuery(sql);
           
            while(rs.next())
            {
                id.addItem(rs.getString("id"));
            }
            
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dBConnect.close();
        }
    }
    public void Showdata(JComboBox id, JTextField name, JTextField phone,JTextField email,JTextField address,JTextField count,
            JTextField date,JTextField user, JTextField pass)
    {
        String txtid = (String) id.getSelectedItem();
        try {
            PreparedStatement ps = dBConnect.getConnect().prepareStatement("select * From staff where id = ?");
            ps.setString(1, txtid);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                name.setText(rs.getString("Name"));
                phone.setText(rs.getString("Phone"));
                email.setText(rs.getString("Email"));
                address.setText(rs.getString("Address"));
                count.setText(rs.getString("Shift_count"));
                date.setText(rs.getString("Start_work_date"));
                user.setText(rs.getString("UserName"));
                pass.setText(rs.getString("Password"));
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dBConnect.close();
        }
    }
    public void EditStaff(JTextField name, JTextField phone,JTextField email,JTextField address,JTextField count,
            JTextField date,JTextField user, JTextField pass, JComboBox txtid)
    {
        String edit = "UPDATE staff SET Name = ?,Phone=?,Email=?,Address=?,Shift_count=?,Start_work_date=?,UserName=?,Password=?"
                + "WHERE id = ?";
        String id = (String) txtid.getSelectedItem();
        try {
            PreparedStatement ps = dBConnect.getConnect().prepareStatement(edit);
            
            ps.setString(1, name.getText());
            ps.setString(2,phone.getText());
            ps.setString(3,email.getText());
            ps.setString(4,address.getText());
            ps.setInt(5, Integer.parseInt(count.getText()));
            ps.setString(6, date.getText());
            ps.setString(7,user.getText());
            ps.setString(8,pass.getText());
            ps.setString(9, id);
            
            int n = ps.executeUpdate();
            if(n>0)
                System.out.println("Chỉnh sửa thông tin nhân viên thành công!");
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            dBConnect.close();
        }
    }
    public void DeleteStaff(JTable b, JPanel p)
    {
        int row = b.getSelectedRow();
        int id = (int) b.getModel().getValueAt(row, 0);
        int ret = JOptionPane.showConfirmDialog(p, "Do you want to delete?", "Confirm", JOptionPane.YES_NO_OPTION);
        if(ret != JOptionPane.YES_OPTION) {
        return;
        }
        String sql = "DELETE From staff where id = ?";
        try {
            PreparedStatement ps = dBConnect.getConnect().prepareStatement(sql);
            ps.setInt(1, id);
            ret = ps.executeUpdate();
            if(ret>0)
                System.out.println("Xóa nhân viên thành công!");
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            dBConnect.close();
        }
    }
    public void Clear_IfStaff(JTextField name, JTextField phone,JTextField email,JTextField address,JTextField count,
            JTextField date,JTextField user, JTextField pass)
    {
        name.setText("");
        phone.setText("");
        email.setText("");
        address.setText("");
        count.setText("");
        date.setText("");
        user.setText("");
        pass.setText("");
    }
}