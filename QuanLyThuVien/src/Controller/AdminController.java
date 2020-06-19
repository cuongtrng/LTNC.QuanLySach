/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.StaffController.CheckEmail;
import Model.Admin;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.dBConnect;

/**
 *
 * @author admin
 */
public class AdminController {
    public void SearchAdmin(JTable b, JComboBox c, JTextField txt)
    {
        String s = (String) c.getSelectedItem();
        String text = txt.getText();
        String header[] = {"Id","Name"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(header);
        String sql = "select * From admin";
        if(s == "ID")
        {
            sql = sql + " where ID like '%" + text + "%'";
        }
        if(s == "Name")
        {
            sql = sql + " where Name like '%" + text + "%'";
        }
        try {
            Statement st = dBConnect.getConnect().createStatement();
            ResultSet rs = st.executeQuery(sql);
            model.setRowCount(0);
            if(rs != null)
            {
                while(rs.next())
                {
                    Admin ad = new Admin();
                    ad.setId(rs.getInt("id"));
                    ad.setName(rs.getString("Name"));
                    Object []data = {ad.getId(),ad.getName()};
                    model.addRow(data);
                }
                
            }
            b.setModel(model);
        
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            dBConnect.close();
        }
    }
    
    public void DisplayAllAdmin(JTable b)
    {
        String header[] = {"Id","Name"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(header);
        String sql = "select * From admin";
        try {
            Statement st = dBConnect.getConnect().createStatement();
            ResultSet rs = st.executeQuery(sql);
            model.setRowCount(0);
            if(rs != null)
            {
                while(rs.next())
                {
                    Admin ad = new Admin();
                    ad.setId(rs.getInt("id"));
                    ad.setName(rs.getString("Name"));
                    Object []data = {ad.getId(),ad.getName()};
                    model.addRow(data);
                }
                
            }
            b.setModel(model);
        
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            dBConnect.close();
        }
    }
    
    public void getAdminByID(JTable b, JTextField name, JTextField phone,JTextField email,
            JTextField user, JTextField pass, JLabel image, JLabel ids)
    {
        int id =  (int) b.getModel().getValueAt(b.getSelectedRow(), 0);
        try {
            Statement st = dBConnect.getConnect().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM admin WHERE id = '" + id + "'");
            while(rs.next())
            {
                name.setText(rs.getString("Name"));
                phone.setText(rs.getString("Phone"));
                email.setText(rs.getString("Email"));
                user.setText(rs.getString("UserName"));
                pass.setText(rs.getString("Password"));
                ids.setText(rs.getString("ID"));
                try {
                    BufferedImage im = ImageIO.read(rs.getBinaryStream("Image"));
                    Image imgs = im.getScaledInstance(image.getWidth(), image.getHeight(),Image.SCALE_SMOOTH);
                    image.setIcon(new ImageIcon(imgs));
                    
                } catch (IOException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dBConnect.close();
        }
    }
    public void AddAdmin(JTextField name, JTextField phone,JTextField email,JTextField user, JTextField pass, 
            JLabel im, String s)
    {
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
        if(user.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please input Usename");
            user.requestFocus();
            return;
        }
        if(pass.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please input Password");
            pass.requestFocus();
            return;
        }
        if(CheckEmail(email.getText()) != true)
        {
            JOptionPane.showMessageDialog(null, "Email invalid");
            email.requestFocus();
            return;
        }
        int ret = JOptionPane.showConfirmDialog(null, "Do you want to add data?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (ret != JOptionPane.YES_OPTION) 
        {
            return; 
        }
        String insert = "INSERT INTO admin (Name,Phone,Email,UserName,Password,Image) "
                + "values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = dBConnect.getConnect().prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, name.getText());
            ps.setString(2,phone.getText());
            ps.setString(3,email.getText());
            ps.setString(4,user.getText());
            ps.setString(5,pass.getText());
            try {
                FileInputStream fis=new FileInputStream(new File(s));
                ps.setBinaryStream(6, fis);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultSet rs = ps.getGeneratedKeys();
            ret = ps.executeUpdate();
            if(ret>0){
                JOptionPane.showMessageDialog(null, "Create successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Creat fail");
            }
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
    
     public static String getImageAdmin(JLabel b, String s)
    {
        JFileChooser fc=new JFileChooser();
        fc.showOpenDialog(null);
        if(fc.getSelectedFile()!=null)
        {
            s=fc.getSelectedFile().getAbsolutePath();
            BufferedImage img;
            try {
                img=ImageIO.read(new File(s));
                Image imgs = img.getScaledInstance(b.getWidth(), b.getHeight(),Image.SCALE_SMOOTH);
                b.setIcon(new ImageIcon(imgs));
            } catch (IOException ex) {
                Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return s;
    }
     
     public void UpdateAdmin(JTextField name, JTextField phone,JTextField email,JTextField user, JTextField pass, 
             JLabel im, JLabel txtid, String s)
    {
        String edit = "UPDATE admin SET Name = ?,Phone=?,Email=?,UserName=?,Password=?,Image=?"
                + "WHERE id = ?";
        if(CheckEmail(email.getText()) != true)
        {
            JOptionPane.showMessageDialog(null, "Email invalid");
            email.requestFocus();
            return;
        }
        try {
            PreparedStatement ps = dBConnect.getConnect().prepareStatement(edit);
            
            ps.setInt(7, Integer.parseInt(txtid.getText()));
            ps.setString(1, name.getText());
            ps.setString(2,phone.getText());
            ps.setString(3,email.getText());
            ps.setString(4,user.getText());
            ps.setString(5,pass.getText());
            try {
                FileInputStream fis=new FileInputStream(new File(s));
                ps.setBinaryStream(6, fis);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
            }
            int n = ps.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Updating successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Updating fail");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            dBConnect.close();
        }
    }
     
     public void DeleteAdmin(JTable b)
    {
        int row = b.getSelectedRow();
        int id = (int) b.getModel().getValueAt(row, 0);
        int ret = JOptionPane.showConfirmDialog(null, "Do you want to delete?", "Confirm", JOptionPane.YES_NO_OPTION);
        if(ret != JOptionPane.YES_OPTION) {
        return;
        }
        String sql = "DELETE From admin where id = ?";
        try {
            PreparedStatement ps = dBConnect.getConnect().prepareStatement(sql);
            ps.setInt(1, id);
            ret = ps.executeUpdate();
            if(ret>0){
                JOptionPane.showMessageDialog(null, "Delete successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Delete fail");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            dBConnect.close();
        }
    }
     
     public void New(JTextField name, JTextField phone,JTextField email,JTextField user, JTextField pass, 
             JLabel image, JLabel id)
    {
        name.setText("");
        phone.setText("");
        email.setText("");
        user.setText("");
        pass.setText("");
        image.setIcon(null);
        id.setText("");
    }
}
