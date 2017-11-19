/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hva.fys.mercury.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Eigenaar
 */
public class RegistreerVermistController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private Button opslaanBTN;

    @FXML
    private TextField dateFound;

    @FXML
    private TextField timeFound;

    @FXML
    private TextField LugType;

    @FXML
    private TextField brand;

        @FXML
    private TextField     arrWiFi;
            @FXML
    private TextField lugTag;
            
                @FXML
    private TextField     LocationFound;
                
                    @FXML
    private TextField mainColor;
                    
                        @FXML
    private TextField     SecColor;
                        
                            @FXML
    private TextField size;
                            
                                @FXML
    private TextField     weight;
                                
                                    @FXML
    private TextField passengerName;
                                    
                                        @FXML
    private TextField     city;
                                        
                                            @FXML
    private TextField otherCar;

    @FXML
    private void opslaanAction(ActionEvent event) {
        String content = "Save?";
        System.out.println(content);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void annuleerAction(ActionEvent event) {
        String content = "Cancelled";
        System.out.println(content);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void opslaanBagage(ActionEvent event) {
        System.out.println("Bagage is opgeslagen");
    }

}
