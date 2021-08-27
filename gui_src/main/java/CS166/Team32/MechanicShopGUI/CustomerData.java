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
public class CustomerData {
    String row;
    String fname;
    String lname;
    String id;
    String vin;
    public CustomerData(String row, String fname, String lname, String id) {
        this.row=row;
        this.fname=fname;
        this.lname=lname;
        this.id=id;
    }
    public String getRow() {
        return row;
    }
    public void setRow(String row) {
        this.row=row;
    }
    public String getfname() {
        return fname;
    }
    public void setfname(String fname) {
        this.fname=fname;
    }
    public String getlname() {
        return lname;
    }
    public void setlname(String lname) {
        this.lname=lname;
    }
    public String getid() {
        return id;
    }
    public void setid(String id) {
        this.id=id;
    }

    String getvin() {
        return vin;
    }
    public void setvin() {
        this.vin=vin;
    }
}
