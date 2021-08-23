/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject6;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author mela
 */
class ResScreen {
    public Button summaryCloseBtn;
    public Label summaryLabel;

    public void closeButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) summaryCloseBtn.getScene().getWindow();
        stage.close();
    }
    void SetLabel(String text) {
        summaryLabel.setText(text);
    }
    
}
