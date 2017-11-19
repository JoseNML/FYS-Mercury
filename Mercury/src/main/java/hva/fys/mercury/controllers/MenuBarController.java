package hva.fys.mercury.controllers;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author djmbritt
 */
public class MenuBarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private StackPane rootWorkSpace;

    @FXML
    private StackPane workspace;

    @FXML
    private BorderPane rootNode;

    @FXML
    private AnchorPane anchorWorkSpace;

    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.printf("\nStarting Menu Bar: %s\n", "initing...");
        System.out.printf("\nresources: %s\n", resources);
        System.out.printf("\nlocation: %s\n", location);
    }


    @FXML
    private void openRegistreerGevondenbagage(ActionEvent event) {
        System.out.println("registreer bagage geopend ");
        Parent pane = loadFXMLFile("/fxml/bagageFormulier.fxml");

        System.out.println("rootWorkspace: " + rootWorkSpace);

        System.out.println(pane);
        System.out.println(workspace);
        workspace.getChildren().clear();
        workspace.getChildren().setAll(pane);
    }

    @FXML
    private void openRegistreerVerlorenbagage(ActionEvent event) {
        System.out.println("verloren bagage geopend ");
        Parent pane = loadFXMLFile("/fxml/registreerVermist.fxml");
        System.out.println("rootWorkspace: " + rootWorkSpace);

        System.out.println(pane);
        System.out.println(workspace);

        workspace.getChildren().clear();
        workspace.getChildren().setAll(pane);
    }

    @FXML
    private void openDashboard(ActionEvent event) {
        System.out.println("dashboard geopend ");
        Parent pane;
        pane = loadFXMLFile("/fxml/Dashboard.fxml");
        System.out.println("rootWorkspace: " + rootWorkSpace);

        System.out.println(pane);
        System.out.println(workspace);
        workspace.getChildren().clear();
        workspace.getChildren().setAll(pane);

    }

    @FXML
    private void openBagageZoeken(ActionEvent event) {
        System.out.println("bagage zoeken geopend ");
        Parent pane = loadFXMLFile("/fxml/bagageFormulier.fxml");

        System.out.println(pane);
        System.out.println(workspace);
        workspace.getChildren().clear();
        workspace.getChildren().setAll(pane);
    }

    @FXML
    private void openManagerOverzicht(ActionEvent event) {
        System.out.println("manager  geopend ");
        Parent pane = loadFXMLFile("/fxml/managerOverzicht.fxml");

        System.out.println(pane);
        System.out.println(workspace);
        workspace.getChildren().clear();
        workspace.getChildren().setAll(pane);
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
