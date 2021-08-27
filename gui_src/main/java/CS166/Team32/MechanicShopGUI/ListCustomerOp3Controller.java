/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject6;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mela
 */
public class ListCustomerOp3Controller implements Initializable {
    public Button backButton;
    private int row = 1;
    public TableView<CustomerQuery3Data> table;
    public TableColumn<CustomerQuery3Data, String> crow;
    public TableColumn<CustomerQuery3Data, String> cfname;
    public TableColumn<CustomerQuery3Data, String> clname;
    public TableColumn<CustomerQuery3Data, String> cid;
    public TableColumn<CustomerQuery3Data, String> cbill;
    private ObservableList<CustomerQuery3Data> ol = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      Platform.runLater(() -> backButton.requestFocus());

        try {
            ConnectionController cccc = new ConnectionController();
            Connection connection = cccc.getConnection();

            table.setPlaceholder(new Label("No data "));

            ResultSet rs = connection.createStatement().executeQuery("SELECT C.fname, C.lname, C.id, SUM(CR.bill) AS bill FROM closed_request CR, customer C, service_request SR WHERE CR.rid = SR.rid AND SR.customer_id = C.id GROUP BY (C.fname, C.lname, C.id) ORDER BY SUM(CR.bill) DESC");

            while(rs.next()) {
                //tring row, String fname, String lname, String id, String bill
                ol.add(new CustomerQuery3Data(Integer.toString(row), rs.getString("fname"), rs.getString("lname"), rs.getString("id"), rs.getString("bill")));
                row++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        crow.setCellValueFactory(new PropertyValueFactory<>("row"));
        cfname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        clname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cbill.setCellValueFactory(new PropertyValueFactory<>("bill"));

        table.setItems(ol);
    }

    public void handleCancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
    
}
