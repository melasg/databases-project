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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mela
 */
public class ListCustomerOp5Controller {
    public TableView<CustomerQuery5Data> table;
    public TableColumn<CustomerQuery5Data, String> colRow;
    public TableColumn<CustomerQuery5Data, String> colMake;
    public TableColumn<CustomerQuery5Data, String> colModel;
    public TableColumn<CustomerQuery5Data, String> colVIN;
    public TableColumn<CustomerQuery5Data, String> colCount;

    private ObservableList<CustomerQuery5Data> oblist = FXCollections.observableArrayList();

    public Button backButton;
    public Button entButton;

    public TextField txtHowMany;

    private void ListCars(String s) {
        Platform.runLater(() -> backButton.requestFocus());

        int row = 1;
        table.getItems().clear();

        try {
            ConnectionController conn = new ConnectionController();
            Connection connection = conn.getConnection();

            table.setPlaceholder(new Label("No rows to display"));

            ResultSet rs = connection.createStatement().executeQuery("SELECT car.make, car.model, car.vin, COUNT(service_request.rid) AS count FROM car, service_request WHERE car.vin = service_request.car_vin GROUP BY (car.make, car.model, car.vin) ORDER BY COUNT(service_request.rid) DESC LIMIT " + s);

            while(rs.next()) {
                oblist.add(new CustomerQuery5Data(Integer.toString(row), rs.getString("vin"), rs.getString("make"), rs.getString("model"), rs.getString("year"), rs.getString("service_count")));
                row++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        colRow.setCellValueFactory(new PropertyValueFactory<>("row"));
        colMake.setCellValueFactory(new PropertyValueFactory<>("make"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colVIN.setCellValueFactory(new PropertyValueFactory<>("vin"));
        colCount.setCellValueFactory(new PropertyValueFactory<>("count"));

        table.setItems(oblist);
    }

    public void handleCancelAction() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    public void handleGoAction() {
        ListCars(txtHowMany.getText());
    }

}
