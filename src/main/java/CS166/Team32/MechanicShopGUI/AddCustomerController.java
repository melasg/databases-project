/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject6;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mela
 */
public class AddCustomerController {
    public TextField txtAddCustomerFName;
    public TextField txtAddCustomerLName;
    public TextField txtAddCustomerPhone;
    public TextField txtAddCustomerAddress;

    public Button btnAddCustomerCreate;
    public Button btnAddCustomerCancel;

    public Label lblError;

    @FXML
    protected void initialize() {
        Platform.runLater(() -> btnAddCustomerCancel.requestFocus());
    }

    public void handleCreateAction() {
        lblError.setOpacity(0);
        PreparedStatement stmt;

        try {
            ConnectionController con = new ConnectionController();
            Connection connection = con.getConnection();

            List<List<String>> data = con.executeQueryAndReturnResult("SELECT MAX(id) FROM customer");
            int id = Integer.parseInt(data.get(0).get(0)) + 1;

            if (txtAddCustomerFName.getText().length() < 1 || txtAddCustomerLName.getText().length() < 1 || txtAddCustomerAddress.getText().length() < 1 || txtAddCustomerPhone.getText().length() < 1) {
                lblError.setText("All fields are required.");
                lblError.setOpacity(1);
                return;
            }

            stmt = connection.prepareStatement("INSERT INTO customer (id, fname, lname, phone, address) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, id);
            stmt.setString(2, txtAddCustomerFName.getText());
            stmt.setString(3, txtAddCustomerLName.getText());
            stmt.setString(4, txtAddCustomerPhone.getText());
            stmt.setString(5, txtAddCustomerAddress.getText());
            stmt.executeUpdate();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Summary.fxml"));
                Parent summary = fxmlLoader.load();

                ResScreen results = fxmlLoader.<ResScreen>getController();
                results.SetLabel("Customer " + txtAddCustomerFName.getText() + " " + txtAddCustomerLName.getText() + "\nID " + id + "\nadded successfully!");

                Stage stage = new Stage();
                stage.setTitle("Success!!");
                stage.setScene(new Scene(summary));
                stage.showAndWait();
                Stage thisStage = (Stage) btnAddCustomerCreate.getScene().getWindow();
                thisStage.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void handleCancelAction() {
        Stage stage = (Stage) btnAddCustomerCancel.getScene().getWindow();
        stage.close();
    }

    public void handleKeyPressed() {
        lblError.setOpacity(0);
    }
    
}
