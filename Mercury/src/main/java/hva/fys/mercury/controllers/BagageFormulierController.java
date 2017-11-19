package hva.fys.mercury.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author davidbritt
 */
public class BagageFormulierController implements Initializable {

    /**
     * Initializes the controller class.
     */
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
    private TextField arrWiFi;

    @FXML
    private TextField lugTag;

    @FXML
    private TextField LocationFound;

    @FXML
    private TextField mainColor;

    @FXML
    private TextField SecColor;

    @FXML
    private TextField size;

    @FXML
    private TextField weight;

    @FXML
    private TextField passengerName;

    @FXML
    private TextField city;

    @FXML
    private TextField otherCar;

    @FXML
    private void opslaanAction(ActionEvent event) {
        String content = "Save?";
        System.out.println(content);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(content);
        alert.showAndWait();

        String[] textFieldsArrayStrings = new String[14];
        textFieldsArrayStrings[1] = dateFound.getText();
        textFieldsArrayStrings[2] = timeFound.getText();
        textFieldsArrayStrings[3] = LugType.getText();
        textFieldsArrayStrings[4] = brand.getText();
        textFieldsArrayStrings[5] = arrWiFi.getText();
        textFieldsArrayStrings[6] = lugTag.getText();
        textFieldsArrayStrings[7] = LocationFound.getText();
        textFieldsArrayStrings[8] = mainColor.getText();
        textFieldsArrayStrings[9] = SecColor.getText();
        textFieldsArrayStrings[10] = size.getText();
        textFieldsArrayStrings[11] = weight.getText();
        textFieldsArrayStrings[12] = passengerName.getText();
        textFieldsArrayStrings[13] = city.getText();
        textFieldsArrayStrings[14] = otherCar.getText();
        
        //dosomething with textField array.

    }

    @FXML
    private void annuleerAction(ActionEvent event) {
        String content = "Cancelled";
        System.out.println(content);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
