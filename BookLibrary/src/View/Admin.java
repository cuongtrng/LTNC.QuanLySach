/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.SearchController;
import Controller.StaffController;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author admin
 */
public class Admin extends JFrame implements ActionListener{
    private JTabbedPane admin;
    private JPanel staff;
    private JPanel account;
    private Color c;
    
    private JLabel id;          private JTextField txtid;
    private JLabel name;        private JTextField txtname;
    private JLabel phone;       private JTextField txtphone;
    private JLabel email;       private JTextField txtemail;
    private JLabel address;     private JTextField txtaddress;
    private JLabel count;       private JTextField txtcount;
    private JLabel date;        private JTextField txtdate;
    private JLabel usename;     private JTextField txtusename;
    private JLabel password;    private JTextField txtpassword;
    private JTable table_staff;
    private JButton addstaff;
    private JButton editstaff;
    private JButton deletestaff;
    private JButton searchstaff;    private JTextField txtsearch;   private JLabel searchname;
    private JScrollPane scrollPane_staff;
    
    public Admin()
    {
        this.setSize(1000, 700);
        //this.setLayout(null);
        this.setTitle("Admin");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(255,255,255));
        
        c = Color.getHSBColor(94, 150, 250);
        
        admin = new JTabbedPane();
        staff = new JPanel(null);
        account = new JPanel(null);
        this.add(admin);
        admin.add("Staff", staff);
        admin.add("Account",account);
        
        //thong tin nhan vien
        id = new JLabel("ID");id.setBounds(540,10,100,30);staff.add(id);
        
        txtid=new JTextField();txtid.setBounds(650,10,300,30);
        txtid.setFont(new Font("Courier New", Font.CENTER_BASELINE, 17));staff.add(txtid);
        
        name = new JLabel("Name");name.setBounds(540,45,100,30);staff.add(name);
        
        txtname=new JTextField();txtname.setBounds(650,45,300,30);
        txtname.setFont(new Font("Courier New", Font.CENTER_BASELINE, 17));staff.add(txtname);
        
        phone = new JLabel("Phone number");phone.setBounds(540,80,100,30);staff.add(phone);
        
        txtphone=new JTextField();txtphone.setBounds(650,80,300,30);
        txtphone.setFont(new Font("Courier New", Font.CENTER_BASELINE, 17));staff.add(txtphone);
        
        email = new JLabel("Email");email.setBounds(540,115,100,30);staff.add(email);
        
        txtemail=new JTextField();txtemail.setBounds(650,115,300,30);
        txtemail.setFont(new Font("Courier New", Font.CENTER_BASELINE, 17));staff.add(txtemail);
        
        address = new JLabel("Address");address.setBounds(540,150,100,30);staff.add(address);
        
        txtaddress=new JTextField();txtaddress.setBounds(650,150,300,30);
        txtaddress.setFont(new Font("Courier New", Font.CENTER_BASELINE, 17));staff.add(txtaddress);
        
        count = new JLabel("Shift_count");count.setBounds(540,185,100,30);staff.add(count);
        
        txtcount=new JTextField();txtcount.setBounds(650,185,300,30);
        txtcount.setFont(new Font("Courier New", Font.CENTER_BASELINE, 17));staff.add(txtcount);
        
        date = new JLabel("Start_work_date");date.setBounds(540,220,100,30);staff.add(date);
        
        txtdate=new JTextField();txtdate.setBounds(650,220,300,30);
        txtdate.setFont(new Font("Courier New", Font.CENTER_BASELINE, 17));staff.add(txtdate);
        
        usename = new JLabel("Usename");usename.setBounds(540,255,100,30);staff.add(usename);
        
        txtusename=new JTextField();txtusename.setBounds(650,255,300,30);
        txtusename.setFont(new Font("Courier New", Font.CENTER_BASELINE, 17));staff.add(txtusename);
        
        password = new JLabel("Password");password.setBounds(540,290,100,30);staff.add(password);
        
        txtpassword=new JTextField();txtpassword.setBounds(650,290,300,30);
        txtpassword.setFont(new Font("Courier New", Font.CENTER_BASELINE, 17));staff.add(txtpassword);
        
        //tim kiem, them, sua, xoa nhan vien
        String[] columns = new String[]{"ID","Name","Phone","Email","Address",
            "Shift_count","Start_work_date","UserName","Password"};
        Object[][] data = new Object[][]{{null,null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null},{null,null,null,null,null,null,null,null,null}};
        
        table_staff = new JTable(data,columns); 
        scrollPane_staff = new JScrollPane(table_staff); scrollPane_staff.setBounds(10, 330, 960, 250); 
        staff.add(scrollPane_staff);
        
        addstaff=new JButton("Add");addstaff.setBounds(420,50,80,35);addstaff.setBackground(c);staff.add(addstaff);
        
        editstaff=new JButton("Edit");editstaff.setBounds(420,140,80,35);editstaff.setBackground(c);staff.add(editstaff);
        
        deletestaff=new JButton("Delete");deletestaff.setBounds(850,590,80,35);deletestaff.setBackground(c);
        staff.add(deletestaff);
        
        searchstaff = new JButton("Search");searchstaff.setBounds(100,100,80,35);searchstaff.setBackground(c);
        staff.add(searchstaff);
        
        searchname = new JLabel("Enter name");searchname.setBounds(20,50,70,30);staff.add(searchname);
        txtsearch = new JTextField();txtsearch.setBounds(100,50,250,35);
        txtsearch.setFont(new Font("Courier New", Font.CENTER_BASELINE, 17));staff.add(txtsearch);
        
        searchstaff.addActionListener(this);
        addstaff.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()== searchstaff)
        {
            SearchController s = new SearchController();
            s.SearchStaff(table_staff, txtsearch.getText());
        }
        if(e.getSource()==addstaff)
        {
            StaffController stf = new StaffController();
            stf.AddStaff(txtid, txtname, txtphone, txtemail, txtaddress, txtcount, txtdate, txtusename, txtpassword, staff);
        }
    }
    
}
