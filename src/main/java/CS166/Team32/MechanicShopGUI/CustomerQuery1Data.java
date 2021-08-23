/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject6;

/**
 *
 * @author mela
 */
class CustomerQuery1Data {
    private String row;
    private String id;
    private String fname;
    private String lname;
    private String phone;
    private String address;
    private String sum_bill;
    
    public CustomerQuery1Data(String row, String id, String fname, String lname, String phone, String address, String sum_bill) {
    	this.row = row;
    	this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.address = address;
        this.sum_bill = sum_bill;
    }
    
    public String getRow() {
        return row;
    }
    public void setRow() {
        this.row = row;
    }
    public String getId() {
        return id;
    }
    public void setId() {
        this.id = id;
    }
    public String getfname() {
        return fname;
    }
    public void setfname() {
        this.fname = fname;
    }
    public String getlname() {
        return lname;
    }
    public void setlname() {
        this.lname=lname;
    }
    public String getphone() {
        return phone;
    }
    public void setphone() {
        this.phone=phone;
    }
    public String getaddress() {
        return address;
    }
    public void setaddress() {
        this.address=address;
    }
    
    public String getsum_bill() {
        return sum_bill;
    }
    public void setsum_bill() {
        this.sum_bill=sum_bill;
    }
    
    
}
