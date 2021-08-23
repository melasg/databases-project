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
class CustomerQuery5Data {
    private String row;
    private String vin;
    private String make;
    private String model;
    private String year;
    private String service_count;
    
    public CustomerQuery5Data(String row, String vin, String make, String model, String year, String service_count) {
    	this.row = row;
    	this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.service_count = service_count;
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
    public String getservice_count() {
        return service_count;
    }
    public void setservice_count() {
        this.service_count=service_count;
    }
    
}
