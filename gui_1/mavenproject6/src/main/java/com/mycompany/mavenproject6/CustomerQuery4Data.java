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
public class CustomerQuery4Data {
    private String row;
    private String vin;
    private String make;
    private String model;
    private String year;
    private String rid;
    private String customer_id;
    private String car_vin;
    private String date;
    private String odometer;
    private String complain;
    public CustomerQuery4Data(String row, String vin, String make, String model, String year, String rid, String customer_id, String car_vin, String date, String odometer, String complain) {
    	this.row = row;
    	this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.rid = rid;
        this.customer_id = customer_id;
        this.car_vin = car_vin;
        this.date = date;
        this.odometer = odometer;
        this.complain = complain;
    }
    
    public String getRow() {
        return row;
    }
    public void setRow() {
        this.row = row;
    }
    public String getvin() {
        return vin;
    }
    public void setvin() {
        this.vin = vin;
    }
    public String getmake() {
        return make;
    }
    public void setmake() {
        this.make = make;
    }
    public String getmodel() {
        return model;
    }
    public void setmodel() {
        this.model=model;
    }
    public String getyear() {
        return year;
    }
    public void setyear() {
        this.year=year;
    }
    public String getrid() {
        return rid;
    }
    public void setrid() {
        this.rid=rid;
    }
    public String getcustomer_id() {
        return customer_id;
    }
    public void setcustomer_id() {
        this.customer_id=customer_id;
    }
    public String getcar_vin() {
        return car_vin;
    }
    public void setcar_vin() {
        this.car_vin=car_vin;
    }
    public String getdate() {
        return date;
    }
    public void setdate() {
        this.date=date;
    }
    public String getodometer() {
        return odometer;
    }
    public void setodometer() {
        this.odometer=odometer;
    }
    
    public String getcomplain() {
        return complain;
    }
    public void setcomplain() {
        this.complain=complain;
    }
}
