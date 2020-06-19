/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Blob;

/**
 *
 * @author admin
 */
public class Admin {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String username;
    private String password;
    private Blob image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
    public Admin(int sid, String sname, String sphone, String semail, String user, String pass, Blob simage)
    {
        id = sid;
        name = sname;
        phone = sphone;
        email = semail;
        username = user;
        password = pass;
        image = simage;
    }
    public Admin()
    {
        id = 0;
        name = null;
        phone = null;
        email = null;
        username = null;
        password = null;
        image = null;
    }
}
