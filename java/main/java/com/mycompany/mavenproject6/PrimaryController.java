package com.mycompany.mavenproject6;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class PrimaryController {
    public void AddCustomerController() throws IOException {
        FXMLLoader fxmll = new FXMLLoader(getClass().getResource("AddCustomerController.java"));
        
    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
