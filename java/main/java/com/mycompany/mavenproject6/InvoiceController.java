/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject6;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author mela
 */
public class InvoiceController implements Initializable {
    public Button backButton;
    private int row = 1;
    public TableView<InvoiceData> table;
    public TableColumn<InvoiceData, String> colrow;
    public TableColumn<InvoiceData, String> colfname;
    public TableColumn<InvoiceData, String> collname;
    public TableColumn<InvoiceData, String> colid;
    public TableColumn<InvoiceData, String> colbill;
    private ObservableList<InvoiceData> ol = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->backButton.requestFocus());
        
    }    
    
}
