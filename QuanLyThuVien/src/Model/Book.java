/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.time.Year;
import java.util.Date;

/**
 *
 * @author loiph
 */
public class Book {
    private int ID, amount, price;
    private String name, categoryID, author;
    private int publisYear;

    public void setPublisYear(int publisYear) {
        this.publisYear = publisYear;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

   

    public int getID() {
        return ID;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublisYear() {
        return publisYear;
    }         
    
}
