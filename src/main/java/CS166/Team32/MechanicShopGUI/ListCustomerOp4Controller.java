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
public class ListCustomerOp4Controller implements Initializable {
    public TableView<CustomerQuery4Data> table;
    public TableColumn<CustomerQuery4Data, String> colRow;
    public TableColumn<CustomerQuery4Data, String> colMake;
    public TableColumn<CustomerQuery4Data, String> colModel;
    public TableColumn<CustomerQuery4Data, String> colYear;
    public TableColumn<CustomerQuery4Data, String> colOdometer;
    private int row;
    private ObservableList<CustomerQuery4Data> oblist = FXCollections.observableArrayList();

    public Button btnReturn;

    public ListCustomerOp4Controller() {
        this.row = 1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> btnReturn.requestFocus());

        try {
            ConnectionController conn = new ConnectionController();
            Connection connection = conn.getConnection();

            table.setPlaceholder(new Label("No rows to display"));

            ResultSet rs = connection.createStatement().executeQuery("SELECT car.make, car.model, car.year, service_request.odometer FROM car, service_request WHERE car.vin = service_request.car_vin AND car.year < 1995 AND service_request.odometer >= 50000 ORDER BY service_request.odometer DESC");

            while(rs.next()) {
                //String row, String vin, String make, String model, String year, String rid, String customer_id, String car_vin, String date, String odometer, String complain) {
                oblist.add(new CustomerQuery4Data(Integer.toString(row), rs.getString("vin"), rs.getString("make"), rs.getString("model"), rs.getString("year"), rs.getString("rid"), rs.getString("customer_id"), rs.getString("car_vin"), rs.getString("date"), rs.getString("odometer"), rs.getString("complain")));
                row++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        colRow.setCellValueFactory(new PropertyValueFactory<>("row"));
        colMake.setCellValueFactory(new PropertyValueFactory<>("make"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colOdometer.setCellValueFactory(new PropertyValueFactory<>("odometer"));

        table.setItems(oblist);
    }

    public void handleCancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        stage.close();
    }

    
}
