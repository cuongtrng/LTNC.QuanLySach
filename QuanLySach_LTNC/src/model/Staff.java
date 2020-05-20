/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author admin
 */
public class Staff {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private int shift_count;
    private Timestamp start_work_date;
    private String username;
    private String password;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getShift_count() {
        return shift_count;
    }

    public void setShift_count(int shift_count) {
        this.shift_count = shift_count;
    }

    public Timestamp getStart_work_date() {
        return start_work_date;
    }

    public void setStart_work_date(Timestamp start_work_date) {
        this.start_work_date = start_work_date;
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
    public Staff(int sid, String sname, String sphone, String semail, String saddress, int count, Timestamp date,
            String user, String pass)
    {
        id = sid;
        name = sname;
        phone = sphone;
        email = semail;
        address = saddress;
        shift_count = count;
        start_work_date = date;
        username = user;
        password = pass;
    }
    public Staff()
    {
        id = 0;
        name = null;
        phone = null;
        email = null;
        address = null;
        shift_count = 0;
        start_work_date = null;
        username = null;
        password = null;
    }
}
