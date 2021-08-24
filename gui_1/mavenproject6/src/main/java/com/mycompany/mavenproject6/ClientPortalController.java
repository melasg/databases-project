/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject6;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mela
 */
public class ClientPortalController {
//        public void AddCustomerController() throws IOException {
//        FXMLLoader fxmll = new FXMLLoader(getClass().getResource("AddCustomerController.java"));
//        
//    }
//    @FXML
//    private void switchToSecondary() throws IOException {
//        App.setRoot("secondary");
//    }
    public void AddCarController() throws IOException  {
        FXMLLoader fml = new FXMLLoader(getClass().getResource("AddCar.fxml"));
        Parent window = fml.load();
        Stage stage = new Stage();
        stage.setTitle("Add Car");
        stage.setScene(new Scene(window));
        stage.show();
    }
    public void AddCustomerController() throws IOException  {
        FXMLLoader fml = new FXMLLoader(getClass().getResource("AddCustomer.fxml"));
        Parent window = fml.load();
        Stage stage = new Stage();
        stage.setTitle("Add Customer");
        stage.setScene(new Scene(window));
        stage.show();
    }
    public void AddMechanicController() throws IOException  {
        FXMLLoader fml = new FXMLLoader(getClass().getResource("AddMechanic.fxml"));
        Parent window = fml.load();
        Stage stage = new Stage();
        stage.setTitle("Add Mechanic");
        stage.setScene(new Scene(window));
        stage.show();
    }
    public void InsertServiceRequestController() throws IOException  {
        FXMLLoader fml = new FXMLLoader(getClass().getResource("ListCustomerOp5.fxml"));
        Parent window = fml.load();
        Stage stage = new Stage();
        stage.setTitle("Open New Service Request");
        stage.setScene(new Scene(window));
        stage.show();
    }
    public void CloseServiceRequestController() throws IOException  {
        FXMLLoader fml = new FXMLLoader(getClass().getResource("CloseServiceRequest.fxml"));
        Parent window = fml.load();
        Stage stage = new Stage();
        stage.setTitle("Close Service Request");
        stage.setScene(new Scene(window));
        stage.show();
    }
    public void ListCustomerOp1Controller() throws IOException  {
        FXMLLoader fml = new FXMLLoader(getClass().getResource("ListCustomerOp1.fxml"));
        Parent window = fml.load();
        Stage stage = new Stage();
        stage.setTitle("Invoice for Customers with standing bills over $100");
        stage.setScene(new Scene(window));
        stage.show();
    }
    public void ListCustomerOp2Controller() throws IOException  {
        FXMLLoader fml = new FXMLLoader(getClass().getResource("ListCustomerOp2.fxml"));
        Parent window = fml.load();
        Stage stage = new Stage();
        stage.setTitle("Invoice for Customers with More Than 20 Cars");
        stage.setScene(new Scene(window));
        stage.show();
    }
    public void ListCustomerOp3Controller() throws IOException {
        FXMLLoader fml = new FXMLLoader(getClass().getResource("ListCustomerOp3.fxml"));
        Parent window = fml.load();
        Stage stage = new Stage();
        stage.setTitle("Customer Invoices (Descending)");
        stage.setScene(new Scene(window));
        stage.show();
    }
    public void ListCustomerOp4Controller() throws IOException  {
        FXMLLoader fml = new FXMLLoader(getClass().getResource("ListCustomerOp4.fxml"));
        Parent window = fml.load();
        Stage stage = new Stage();
        stage.setTitle("Invoice for Customers with Cars Made Before 1995 and over 50,000 Miles");
        stage.setScene(new Scene(window));
        stage.show();
    }
    public void ListCustomerOp5Controller() throws IOException  {
        FXMLLoader fml = new FXMLLoader(getClass().getResource("ListCustomerOp5.fxml"));
        Parent window = fml.load();
        Stage stage = new Stage();
        stage.setTitle("Invoice for Customers with the Most Services");
        stage.setScene(new Scene(window));
        stage.show();
    }
    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
    
}
