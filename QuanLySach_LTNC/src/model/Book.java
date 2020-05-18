/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author loiph
 */
public class Book  implements Serializable{
    private int ID,amount,price;
    private String name,category,author;

    @Override
    public String toString() {
        return "Book{" + "ID=" + ID + ", amount=" + amount + ", price=" + price + ", name=" + name + ", category=" + category + ", author=" + author + '}';
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

    public void setCategory(String category) {
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public String getAuthor() {
        return author;
    }
    
}
