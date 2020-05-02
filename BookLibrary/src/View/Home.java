/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author admin
 */
public class Home extends JFrame implements ActionListener{
    private Color c;
    private JTextField person;
    private JPanel option;
    private JButton search; private JButton bill; private JButton stock;
    private JButton customer; private JButton report;
    private JButton signout;
    private JButton b1; private JButton b2;private JButton b3;private JButton b4;
    private JButton b5; private JButton b6;private JButton b7;private JButton b8;
    private JLabel hi;
    public Home()
    {
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(1000, 700);
        this.setLayout(null);
        this.setTitle("Home");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(255,255,255));//(204, 166, 166)
       
        
        c = Color.getHSBColor(94, 150, 250);
        person = new JTextField("Staff");person.setBounds(0,0,200,40);person.setForeground(Color.WHITE);
        person.setBackground(Color.getHSBColor(94, 100, 220));person.setHorizontalAlignment(JTextField.CENTER);this.add(person);
        
        /*option = new JPanel();option.setBounds(0, 240, 200, 700);option.setBackground(c); 
        option.setLayout(new BorderLayout());
        this.add(option);*/
        
        search = new JButton("Search"); search.setForeground(Color.WHITE);search.setBounds(0, 40, 200, 110);
        search.setBackground(c);this.add(search);
        
        bill = new JButton("Bill");bill.setBackground(c);bill.setForeground(Color.WHITE);
        bill.setBounds(0, 152, 200, 110);this.add(bill);
        
        stock = new JButton("Stock");stock.setBackground(c);stock.setForeground(Color.WHITE);
        stock.setBounds(0, 264, 200, 110); this.add(stock);
    
        customer = new JButton("Customer");customer.setBackground(c);customer.setForeground(Color.WHITE);
        customer.setBounds(0, 376, 200, 110); this.add(customer);
        
        report = new JButton("Report");report.setBackground(c);report.setForeground(Color.WHITE);
        report.setBounds(0, 488, 200, 110); this.add(report);
        
        signout = new JButton("Sign out"); signout.setBounds(0, 600, 200, 70); signout.setBackground(Color.getHSBColor(94, 100, 220));
        signout.setForeground(Color.WHITE); this.add(signout);
        
        hi = new JLabel("Welcome back"); hi.setBounds(200, 0, 800, 40); hi.setHorizontalAlignment(SwingConstants.CENTER);
        hi.setFont(new Font("Courier New", Font.CENTER_BASELINE, 25));this.add(hi);
        
        Icon icon1 = new ImageIcon("D:\\image\\b11.jpg");Icon icon2 = new ImageIcon("D:\\image\\b22.jpg");
        Icon icon3 = new ImageIcon("D:\\image\\b33.png");Icon icon4 = new ImageIcon("D:\\image\\b44.jpg");
        b1 = new JButton(icon1);b1.setBounds(200, 40, 200, 320);this.add(b1);
        b2 = new JButton(icon2);b2.setBounds(400, 40, 200, 320);this.add(b2);
        b3 = new JButton(icon3);b3.setBounds(600, 40, 200, 320);this.add(b3);
        b4 = new JButton(icon4);b4.setBounds(800, 40, 200, 320);this.add(b4);
        
        Icon icon5 = new ImageIcon("D:\\image\\b5.jpg");Icon icon6 = new ImageIcon("D:\\image\\b66.jpg");
        Icon icon7 = new ImageIcon("D:\\image\\b77.jpg");Icon icon8 = new ImageIcon("D:\\image\\b88.jpg");
        b5 = new JButton(icon5);b5.setBounds(200, 360, 200, 320);this.add(b5);
        b6 = new JButton(icon6);b6.setBounds(400, 360, 200, 320);this.add(b6);
        b7 = new JButton(icon7);b7.setBounds(600, 360, 200, 320);this.add(b7);
        b8 = new JButton(icon8);b8.setBounds(800, 360, 200, 320);this.add(b8);
        
        search.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search)
        {
            //this.dispose();
            Search nl = new Search();
        }
    }
    
}
