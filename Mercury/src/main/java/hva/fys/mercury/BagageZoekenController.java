package hva.fys.mercury;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class BagageZoekenController implements Initializable {

    @FXML
    private Button zoekenBTN;
    
    @FXML
    private BorderPane rootBorderPane;
    
    @FXML
    private void zoekAction(ActionEvent event) {
        System.out.println("Opening search results");
        Parent pane = loadFXMLFile("/fxml/bagageResultaten.fxml");
        rootBorderPane.getChildren().clear();
        rootBorderPane.setCenter(pane);
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }
    
        private Parent loadFXMLFile(String fxmlFileName) {
        try {
            return FXMLLoader.load(getClass().getResource(fxmlFileName));
        } catch (IOException ex) {
            System.out.printf("\n%s: %s\n", ex.getClass().getName(), ex.getMessage());
            return null;
        }
    }
}
