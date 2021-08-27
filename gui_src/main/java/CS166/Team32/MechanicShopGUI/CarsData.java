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
public class CarsData {
    private String row;
    private String make;
    private String model;
    private String vin;
    
    public CarsData(String row, String make, String model, String vin) {
        this.row=row;
        this.make=make;
        this.model=model;
        this.vin=vin;
    }
    public String getrow() {
        return row;
    }
    public void setrow(String row) {
        this.row=row;
    }
    public String getmake() {
        return make;
    }
    public void setmake(String make) {
        this.make=make;
    }
    public String getmodel() {
        return model;
    }
    public void setmodel(String model) {
        this.model=model;
    }
    public String getvin() {
        return vin;
    }
    public void setvin(String vin) {
        this.vin=vin;
    }
    
}
