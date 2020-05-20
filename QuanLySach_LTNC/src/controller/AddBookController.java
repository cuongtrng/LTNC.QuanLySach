/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import model.Book;
import model.dBConnect;
import view.AddBook;

/**
 *
 * @author loiph
 */
public class AddBookController {
    dBConnect ad= new dBConnect();
    
    public boolean AddBookToDB(Book b){
        String sql= " INSERT INTO book (Name,Category_id,Author,PublishYear,Amount,Price)"
                + "VALUES(?,?,?,?,?,?)";
        try {
            
            PreparedStatement ps= dBConnect.getConnect().prepareStatement(sql);
                    
            //ps.setInt(1, b.getID());
            ps.setString(1, b.getName());
            ps.setString(2, b.getCategoryID());
            ps.setString(3, b.getAuthor());
            ps.setInt(4, b.getPublisYear());
            ps.setInt(5, b.getAmount());
            ps.setInt(6, b.getPrice());
            
            return ps.executeUpdate() >0;
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally
        {
            dBConnect.close();
        }
    }
    public int getLastID(){
        int id = 0;
        String sql= "SELECT id FROM book";
        try {
            PreparedStatement ps= dBConnect.getConnect().prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                id=rs.getInt("id");
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }
       return id;
       
    }
    public boolean deleteBook(int id){
        String sql="DELETE FROM book WHERE id="+id;
        try {
            PreparedStatement ps= dBConnect.getConnect().prepareStatement(sql);
            return ps.executeUpdate(sql) >0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateBook(int id, String newname, String newcategory, 
            String newauthor, int newpublishyear, int newamout, int newprice){
        String sql="UPDATE book SET Name=?, Category_id=?, Author=?, PublishYear=?, Amount=?, Price=?"
                + "WHERE id="+id;
        String sql1= " replace INTO book (id,Name,Category_id,Author,PublishYear,Amount,Price)"
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps= dBConnect.getConnect().prepareStatement(sql1);
            ps.setInt(1, id);
            ps.setString(2, newname);
            ps.setString(3, newcategory);
            ps.setString(4, newauthor);
            ps.setInt(5, newpublishyear);
            ps.setInt(6, newamout);
            ps.setInt(7, newprice);
           
            return ps.executeUpdate() >0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Book> getListBook(){
        ArrayList<Book> list= new ArrayList<>();
        String sql= "SELECT * FROM book";
        try {
            PreparedStatement ps= dBConnect.getConnect().prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                Book b= new Book();
                b.setID(rs.getInt("id"));
                b.setName(rs.getString("Name"));
                b.setCategoryID(rs.getString("Category_id"));
                b.setAuthor(rs.getString("Author"));
                b.setPublisYear(rs.getInt("PublishYear"));
                b.setAmount(rs.getInt("Amount"));
                b.setPrice(rs.getInt("Price"));
                list.add(b);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        
    }
    public static void main(String[] args) {
        AddBookController ab= new AddBookController();
        ab.getLastID();
                
    }
}
