/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklibrary;

import View.Admin;
import View.Home;

/**
 *
 * @author admin
 */
public class BookLibrary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Home w = new Home();
        w.show();
        Admin ad = new Admin();
        ad.show();
        
    }
    
}
