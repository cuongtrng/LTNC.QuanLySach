/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connect;
import java.awt.BorderLayout;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class SearchController {
    public void SearchBook(JTable b, String name, String category, String author, String year)
    {
        String[] columns = new String[]{"ID","Name","Category","Author","PublishYear","Amount","Price"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        String sql = "select * From book";
       if (name.length() > 0) 
        {
            sql = sql + " where Name like '%" + name +"%'";
        }
        /*if (category.length()>0) 
        {
            sql = sql + " where Category id like '%" + category +"%'";
        }
        if (author.length()>0) 
        {
            sql = sql + " where Author like '%" + author +"%'";
        }
        if (year.length()>0) 
        {
            sql = sql + " where PublishYear like '%" + year +"%'";
        }*/
        try 
        {
            Statement st = Connect.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            Vector data = null;
            model.setRowCount(0);
            if(rs != null)
            {
                while(rs.next())
                {
                    data = new Vector();
                    data.add(rs.getInt("id"));
                    data.add(rs.getString("Name"));
                    data.add(rs.getString("Category id"));
                    data.add(rs.getString("Author"));
                    data.add(rs.getString("PublishYear"));
                    data.add(rs.getInt("Amount"));
                    data.add(rs.getInt("Price"));
                    model.addRow(data);
                }
            }
            b.setModel(model);
            st.close();
            rs.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Connect.close();
        }
    }
    public void SearchStaff(JTable b, String name)
    {
        String header[] = {"Id","Name","Phone","Email","Address","Shift_count","Start_work_date","UserName","Password"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(header);
        String sql = "select * From staff";
        if (name.length() > 0) 
        {
            sql = sql + " where Name like '%" + name + "%'";
        }
        try 
        {
            Statement st = Connect.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            Vector data = null;
            model.setRowCount(0);
            if(rs != null)
            {
                while(rs.next())
                {
                    data = new Vector();
                    data.add(rs.getInt("id"));
                    data.add(rs.getString("Name"));
                    data.add(rs.getString("Phone"));
                    data.add(rs.getString("Email"));
                    data.add(rs.getString("Address"));
                    data.add(rs.getInt("Shift_count"));
                    data.add(rs.getTimestamp("Start_work_date"));
                    data.add(rs.getString("UserName"));
                    data.add(rs.getString("Password"));
                    model.addRow(data);
                }
            }
            b.setModel(model);
        
            st.close();
            rs.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Connect.close();
        }
    }    
        
}
