/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject6;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mela
 */
public class InsertServiceRequestController {
    private int rowCar = 1;

    public TableView<CustomerData> tblCustomer;
    public TableColumn<CustomerData, String> colRowCustomer;
    public TableColumn<CustomerData, String> colFName;
    public TableColumn<CustomerData, String> colLName;
    public TableColumn<CustomerData, String> colID;

    private ObservableList<CustomerData> customerOblist = FXCollections.observableArrayList();

    public TableView<CustomerData> tblCar;
    public TableColumn<CustomerData, String> colRowCar;
    public TableColumn<CustomerData, String> colMake;
    public TableColumn<CustomerData, String> colModel;
    public TableColumn<CustomerData, String> colVIN;

    private ObservableList<CustomerData> carOblist = FXCollections.observableArrayList();

    public TextField txtLName;
    public TextField txtOdometer;
    public TextArea taComplaints;

    public Button btnSubmit;
    public Button btnCancel;
    public Button btnGo;

    public Label lblError;

    private String customerID = "";
    private String carVIN = "";

    public void handleSubmitAction() {
        lblError.setOpacity(0);
        tblCustomer.setPlaceholder(new Label("Nothing to see here..."));
        tblCar.setPlaceholder(new Label("Nothing to see here, really..."));

        if (txtLName.getText().length() < 1) {
            lblError.setText("Last name is required!");
            lblError.setOpacity(1);
            return;
        }

        if (customerID.equals("")) {
            lblError.setText("Please select a customer and car!");
            lblError.setOpacity(1);
            return;
        }

        if (txtOdometer.getText().length() < 1) {
            lblError.setText("Odometer is required!");
            lblError.setOpacity(1);
            return;
        }

        try {
            Integer.parseInt(txtOdometer.getText());
        } catch (NumberFormatException e) {
            lblError.setText("Odometer must be a number!");
            lblError.setOpacity(1);
            return;
        }

        if (taComplaints.getText().length() < 1) {
            lblError.setText("Complaint is required!");
            lblError.setOpacity(1);
            return;
        }

        try {
            ConnectionController conn = new ConnectionController();
            Connection connection = conn.getConnection();

            List<List<String>> last_id = conn.executeQueryAndReturnResult("SELECT MAX(rid) FROM service_request");
            int rid = Integer.parseInt(last_id.get(0).get(0)) + 1;

            Date sqlDate = new Date(System.currentTimeMillis());

            conn.executeUpdate("INSERT INTO service_request (rid, customer_id, car_vin, date, odometer, complain) VALUES (" + rid + ", '" + customerID + "', '" + carVIN + "', '" + sqlDate + "', " + txtOdometer.getText() + ", '" + taComplaints.getText() + "')");

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Summary.fxml"));
                Parent summary = fxmlLoader.load();

                ResScreen results = fxmlLoader.getController();
                results.SetLabel("Service request " + rid + " added for customer " + txtLName.getText() + " with ID " + customerID + ". Car VIN is " + carVIN + ".");

                Stage stage = new Stage();
                stage.setTitle("Success!!");
                stage.setScene(new Scene(summary));
                stage.showAndWait();
                Stage thisStage = (Stage) btnSubmit.getScene().getWindow();
                thisStage.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void handleGoAction(ActionEvent actionEvent) {
        lblError.setOpacity(0);
        int rowCustomer = 1;
        rowCar = 1;
        tblCustomer.getItems().clear();
        tblCar.getItems().clear();

        try {
            ConnectionController conn = new ConnectionController();
            Connection connection = conn.getConnection();

            if (txtLName.getText().length() < 1) {
                lblError.setText("Last name is required!");
                lblError.setOpacity(1);
                return;
            }

            if (conn.executeQuery("SELECT lname FROM customer WHERE lname = '" + txtLName.getText() + "'") < 1) {
                lblError.setText("Whoops. No customer with last name " + txtLName.getText() + " exists.");
                lblError.setOpacity(1);
                return;
            }

            ResultSet rs = connection.createStatement().executeQuery("SELECT C.fname, C.lname, C.id FROM customer C WHERE lname = '" + txtLName.getText() + "'");

            while(rs.next()) {
                customerOblist.add(new CustomerData(Integer.toString(rowCustomer), rs.getString("fname"), rs.getString("lname"), rs.getString("id")));
                rowCustomer++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        colRowCustomer.setCellValueFactory(new PropertyValueFactory<>("row"));
        colFName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colLName.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));

        tblCustomer.setItems(customerOblist);

        tblCustomer.setRowFactory(tv -> {
            TableRow<CustomerData> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {

                    CustomerData clickedRow = tblCustomer.getSelectionModel().getSelectedItem();
                    ListCars(clickedRow.getid());
                }
            });

            return row;
        });
    }

    private void ListCars(String id) {
        rowCar = 1;
        tblCar.getItems().clear();

        try {
            ConnectionController conn = new ConnectionController();
            Connection connection = conn.getConnection();

            if (conn.executeQuery("SELECT lname FROM customer WHERE lname = '" + txtLName.getText() + "'") < 1) {
                lblError.setText("Whoops. No customer with last name " + txtLName.getText() + " exists.");
                lblError.setOpacity(1);
                return;
            }

            if (conn.executeQuery("SELECT car.vin, car.make, car.model, car.year FROM car, owns WHERE owns.customer_id = " + id + " AND owns.car_vin = car.vin") == 0) {
                lblError.setText("No cars were found for customer " + txtLName.getText() + ". Try adding a car to their account first.");
                lblError.setOpacity(1);
                return;
            }

            ResultSet rs = connection.createStatement().executeQuery("SELECT car.make, car.model, car.vin FROM car, owns WHERE owns.customer_id = " + id + " AND owns.car_vin = car.vin");

            while(rs.next()) {
                carOblist.add(new CustomerData(Integer.toString(rowCar), rs.getString("make"), rs.getString("model"), rs.getString("vin")));
                rowCar++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        colRowCar.setCellValueFactory(new PropertyValueFactory<>("row"));
        colMake.setCellValueFactory(new PropertyValueFactory<>("make"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colVIN.setCellValueFactory(new PropertyValueFactory<>("vin"));

        tblCar.setItems(carOblist);

        tblCar.setRowFactory(tv -> {
            TableRow<CustomerData> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {

                    CustomerData clickedRow = tblCar.getSelectionModel().getSelectedItem();
                    customerID = id;
                    carVIN = clickedRow.getvin();
                }
            });

            return row;
        });
    }

    public void handleCancelAction() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void handleKeyboardPressedAction() {
        lblError.setOpacity(0);
    }
    
}
