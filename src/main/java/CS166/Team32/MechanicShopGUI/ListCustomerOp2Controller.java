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
public class ListCustomerOp2Controller implements Initializable {
   private int row = 1;

    public TableView<CustomerQuery2Data> table;
    public TableColumn<CustomerQuery2Data, String> colRow;
    public TableColumn<CustomerQuery2Data, String> colFName;
    public TableColumn<CustomerQuery2Data, String> colLName;
    public TableColumn<CustomerQuery2Data, String> colID;
    public TableColumn<CustomerQuery2Data, String> colCars;

    private ObservableList<CustomerQuery2Data> oblist = FXCollections.observableArrayList();

    public Button btnReturn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> btnReturn.requestFocus());

        try {
            ConnectionController conn = new ConnectionController();
            Connection connection = conn.getConnection();

            table.setPlaceholder(new Label("No rows to display"));

            ResultSet rs = connection.createStatement().executeQuery("SELECT customer.fname, customer.lname, customer.id, COUNT(owns.car_vin) AS cars FROM owns, customer WHERE owns.customer_id = customer.id GROUP BY customer.fname, customer.lname, customer.id HAVING COUNT(owns.car_vin) > 20 ORDER BY COUNT(owns.car_vin) DESC");

            while (rs.next()) {
                //String row, String id, String fname, String lname, String phone, String address
                oblist.add(new CustomerQuery2Data(Integer.toString(row), rs.getString("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("phone"), rs.getString("address")));
                row++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        colRow.setCellValueFactory(new PropertyValueFactory<>("row"));
        colFName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colLName.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCars.setCellValueFactory(new PropertyValueFactory<>("cars"));

        table.setItems(oblist);
    }

    public void handleCancelAction() {
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        stage.close();
    }
    
}
