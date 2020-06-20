/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author littl
 */
public class BillDetail {
    private int amount;
    private String ID;
    public void setbookid(String ID) {
       this.ID = ID;
    }
    private int totalprice;
    public void gettotalprice(int totalprice){
         this.totalprice=totalprice;
    }
   
    public void setamount(int a) {
        this.amount= a;
    }
}
