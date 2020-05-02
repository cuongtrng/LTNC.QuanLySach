/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.SearchController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class Search extends JFrame implements ActionListener{
    private JFrame search;
    private JTabbedPane searchby;
    
    private JTable book;
    private JLabel book_name;
    private JLabel author;
    private JLabel year;
    private JTextField txtbook;
    private JTextField txtauthor;
    private JTextField txtyear;
    private JButton display;
    private JLabel category;
    private JTextField txtcategory;
    private JPanel searchbook;
    
    private JPanel searchbill;
    private JPanel searchcustomer;
    public Search()
    {
        search = new JFrame("Search");
        search.setSize(1000, 700);
        //search.setLayout(null);
        search.setVisible(true);
        search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        search.getContentPane().setBackground(new Color(255,255,255));
        //search.setLocationByPlatform(true);
        
        searchby = new JTabbedPane();
        searchbook = new JPanel(null);
        searchbill = new JPanel(null);
        searchcustomer = new JPanel(null);
        search.add(searchby);
        searchby.addTab("Search Book", searchbook);
        searchby.addTab("Search Bill", searchbill);
        searchby.addTab("Search Customer", searchcustomer);
        
        //search book
        book_name = new JLabel("Search by name"); book_name.setBounds(0, 0, 200, 40);
        book_name.setFont(new Font("Courier New", Font.ITALIC, 18));
        searchbook.add(book_name);
        
        txtbook = new JTextField(); txtbook.setBounds(0, 42, 400, 30);
        txtbook.setFont(new Font("Courier New", Font.CENTER_BASELINE, 17));
        searchbook.add(txtbook);
        
        author = new JLabel("Search by author"); author.setBounds(450, 0, 200, 40);
        author.setFont(new Font("Courier New", Font.ITALIC, 18));
        searchbook.add(author);
        
        txtauthor = new JTextField(); txtauthor.setBounds(450, 42, 250, 30);
        txtauthor.setFont(new Font("Courier New", Font.CENTER_BASELINE, 17));
        searchbook.add(txtauthor);
        
        category = new JLabel("Search by category"); category.setBounds(0, 80, 200, 40);
        category.setFont(new Font("Courier New", Font.ITALIC, 18));
        searchbook.add(category);
        
        txtcategory = new JTextField(); txtcategory.setBounds(0, 122, 250, 30);
        txtcategory.setFont(new Font("Courier New", Font.CENTER_BASELINE, 17));
        searchbook.add(txtcategory);
        
        year = new JLabel("Search by publication year"); year.setBounds(450, 80, 350, 40);year.setFont(new Font("Courier New", Font.ITALIC, 18));
        searchbook.add(year);
        
        txtyear = new JTextField(); txtyear.setBounds(450, 122, 100, 30);
        txtyear.setFont(new Font("Courier New", Font.CENTER_BASELINE, 17));
        searchbook.add(txtyear);
        
        display = new JButton("Display"); display.setBounds(0, 170, 80, 30);display.setBackground(Color.getHSBColor(94, 150, 250));
        display.setForeground(Color.WHITE);searchbook.add(display);
        
        String[] columns = new String[]{"ID","Name","Category","Author","PublishYear","Amount","Price"};
        Object[][] data = new Object[][]{{null,null,null,null,null,null,null},{null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null},{null,null,null,null,null,null,null}};
        
        book = new JTable(data,columns);
        JScrollPane scrollPane = new JScrollPane(book);scrollPane.setBounds(40, 210, 900, 400);
        searchbook.add(scrollPane);
        
        display.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==display)
        {
            SearchController s = new SearchController();
            s.SearchBook(book,txtbook.getText(),txtcategory.getText(),txtauthor.getText(),txtyear.getText());
        }
    }
    
}
