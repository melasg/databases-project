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
public class ListCustomerOp1Controller implements Initializable {
    public TableView<CustomerQuery1Data> table;
    public TableColumn<CustomerQuery1Data, String> colRow;
    public TableColumn<CustomerQuery1Data, String> colFName;
    public TableColumn<CustomerQuery1Data, String> colLName;
    public TableColumn<CustomerQuery1Data, String> colID;
    public TableColumn<CustomerQuery1Data, String> colBill;
    private int row;
    private ObservableList<CustomerQuery1Data> oblist = FXCollections.observableArrayList();

    public Button btnReturn;

    public ListCustomerOp1Controller() {
        this.row = 1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> btnReturn.requestFocus());

        try {
            ConnectionController conn = new ConnectionController();
            Connection connection = conn.getConnection();

            table.setPlaceholder(new Label("No rows to display"));

            ResultSet rs = connection.createStatement().executeQuery("SELECT customer.fname, customer.lname, customer.id, closed_request.bill FROM customer, closed_request, service_request WHERE closed_request.bill < 100 AND closed_request.rid = service_request.rid AND service_request.customer_id = customer.id ORDER BY closed_request.bill DESC");

            while(rs.next()) {
                //String row, String id, String fname, String lname, String phone, String address, String sum_bill
                oblist.add(new CustomerQuery1Data(Integer.toString(row), rs.getString("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("phone"), rs.getString("address"), rs.getString("sum_bill")));
                row++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        colRow.setCellValueFactory(new PropertyValueFactory<>("row"));
        colFName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colLName.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBill.setCellValueFactory(new PropertyValueFactory<>("bill"));

        table.setItems(oblist);
    }

    public void handleCancelAction() {
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        stage.close();
    }
}