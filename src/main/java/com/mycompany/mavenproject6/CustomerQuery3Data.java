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
class CustomerQuery3Data {
    private String row;
    private String fname;
    private String lname;
    private String id;
    private String bill;
    
    public CustomerQuery3Data(String row, String fname, String lname, String id, String bill) {
        this.row = row;
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.bill = bill;
    }
    public String getRow() {
        return row;
    }
    public void setRow() {
        this.row = row;
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
    public String getd() {
        return id;
    }
    public void setid() {
        this.id=id;
    }
    public String getbill() {
        return bill;
    }
    public void setbill() {
        this.bill=bill;
    }
    
}
