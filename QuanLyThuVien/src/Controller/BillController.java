/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.PrintBill;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.dBConnect;

/**
 *
 * @author littl
 */
public class BillController {

//    public static void addBill(JTextField staff, JTextField Id, JTextField date, JTextField expiredDate  ){
//        try {
//            String insert = "INSERT INTO bill (StaffId,CustomerId,IssuesDate,ExpireDate) "
//                    + "values(?,?,?,?)";
//            PreparedStatement ps = dBConnect.getConnect().prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
//            
//            ps.setString(1,staff.getText());
//            ps.setString(2,Id.getText());
//            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
//            Date d=new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30));
//            ps.setTimestamp(4, new Timestamp(d.getTime()));
//        } catch (SQLException ex) {
//            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void deleteBill(String id){
        
    }
    public static void addBill(JTextField CustomerId, String staffid, int totalprice){
        
        if(CustomerId.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please input ID");
            CustomerId.requestFocus();
            

        }
        else if(totalprice==0){
            JOptionPane.showMessageDialog(null, "please input detail");

        }
        else{
        String a;
        a = getCustomerId(CustomerId.getText());
        if(a==null) {                        
            JOptionPane.showMessageDialog(null, "invalid CustomerId"); 
            CustomerId.requestFocus();

        }
        else{
            try {
                String insert = "INSERT INTO bill (StaffId,CustomerId,IssuesDate,ExpireDate,Price) "
                        + "values(?,?,?,?,?)";
                PreparedStatement ps = dBConnect.getConnect().prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);

                ps.setString(1,staffid);
                ps.setString(2,CustomerId.getText());
                ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                Date d=new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30));
                ps.setTimestamp(4, new Timestamp(d.getTime()));
                ps.setString(5,""+totalprice);           
                ps.executeUpdate();
                JOptionPane.showConfirmDialog(null, "Do you want to add data?", "Confirm", JOptionPane.YES_NO_OPTION);
                JOptionPane.showMessageDialog(null, "Adding successfully");                               
                
            } catch (SQLException ex) {
                Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    }
    public static void addBilldetail(JTextField idBill, JTextField idBook, JTextField amount){
        
        String insert = "INSERT INTO billdetail (BillId,BookId,Amount) "
                    + "values(?,?,?)";
        try {
            PreparedStatement ps = dBConnect.getConnect().prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1,idBill.getText());
            ps.setString(2,idBook.getText());
            ps.setString(3,amount.getText());
            
        } catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void getDate(JTextField Date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date.setText(dateFormat.format(Date));
        
    }
    public static String getCustomerId(String Customerid){
        try {
            Statement sta = dBConnect.getConnect().createStatement();
            ResultSet rs = sta.executeQuery("SELECT id FROM customer where customer.id=" +Customerid);       
            if(rs.next()){
            return rs.getString("id");
            }else{
            return null;
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
    public static void displayAllBill(JTable table){
        try {
            Statement sta = dBConnect.getConnect().createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM bill,staff where staff.id = bill.StaffID");
//            System.out.println(rs);
            Vector data= null;
            String header[] = {"id", "CustomerID", "StaffName","IssueDate","ExpireDate","Price"};
            DefaultTableModel tblmodel = new DefaultTableModel(header, 0);
            tblmodel.setRowCount(0);
            while (rs.next()){
                data = new Vector();
                data.add(rs.getInt(1));
                data.add(rs.getInt(2));
                data.add(rs.getString(9));
                data.add(rs.getTimestamp(4));
                data.add(rs.getTimestamp(5));
                data.add(rs.getInt(6));
//                System.out.println(data);
                tblmodel.addRow(data);
            }
            table.setModel(tblmodel);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dBConnect.close();
        }
    }
    
    public static String getPrice(String bookId){
        Statement sta;
        try {           
            sta = dBConnect.getConnect().createStatement();     
            ResultSet rs = sta.executeQuery("SELECT Price FROM book where book.id = "+bookId);
            if(rs.next()){
                return rs.getString(1);

            }
            else return null;
        }    
        catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    public static int getBillId(){
        try {
            Statement sta = dBConnect.getConnect().createStatement();
            ResultSet rs = sta.executeQuery("select max(`id`) from `bill`");
            rs.next();
            return rs.getInt(1);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        
    }
    
//    public static void main (String[] args) throws SQLException{
//        getCustomerId();
//        
//        
//    }
//        addBill("1", "2");
//        Statement sta = dBConnect.getConnect().createStatement();
//        ResultSet rs = sta.executeQuery("SELECT * FROM bill,staff where staff.id = bill.StaffID");
//        Vector data= null;
//        while (rs.next()){
//            data = new Vector();
//            data.add(rs.getInt(1));
//            data.add(rs.getInt(2));
//            data.add(rs.getString(9));
//            data.add(rs.getTimestamp(4));
//            data.add(rs.getTimestamp(5));
//            data.add(rs.getInt(6));
//            System.out.println(data);
//        }
//        
        
//    }
}
