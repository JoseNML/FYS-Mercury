package hva.fys.mercury.controllers;

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

public class RootController implements Initializable {

    @FXML
    private StackPane workspace;

    @FXML
    private BorderPane parentNode;

    @FXML
    private void openRegistreerGevondenbagage(ActionEvent event) {
        System.out.println("registreer bagage geopend ");
        Parent pane = loadFXMLFile("/fxml/bagageFormulier.fxml");

        System.out.println(pane);
        System.out.println(workspace);
        workspace.getChildren().clear();
        workspace.getChildren().setAll(pane);
    }

    @FXML
    private void openRegistreerVerlorenbagage(ActionEvent event) {
        System.out.println("verloren bagage geopend ");
        Parent pane = loadFXMLFile("/fxml/VerlorenBagageFormulier.fxml");

        System.out.println(pane);
        System.out.println(workspace);

        workspace.getChildren().clear();
        workspace.getChildren().setAll(pane);
    }

    @FXML
    private void openDashboard(ActionEvent event) {
        System.out.println("dashboard geopend ");
        AnchorPane pane = (AnchorPane) loadFXMLFile("/fxml/Dashboard.fxml");
        workspace.getChildren().clear();
        workspace.getChildren().setAll(pane);
        pane.setPrefHeight(workspace.getHeight());
        pane.setPrefWidth(workspace.getWidth());

        System.out.println(workspace.getHeight());
        System.out.println(workspace.getWidth());

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

    @FXML
    private void logOut(ActionEvent event) {
        System.out.println("Logging Out");
        Parent pane = loadFXMLFile("/fxml/Login.fxml");

        System.out.println(pane);
        System.out.println(parentNode);

        parentNode.getChildren().clear();
        parentNode.getChildren().setAll(pane);
    }

    @FXML
    private void loginAction(ActionEvent event) {
        System.out.println("Logging In");
        Parent pane = loadFXMLFile("/fxml/Scene.fxml");

        System.out.println(pane);
        System.out.println(parentNode);

        parentNode.getChildren().clear();
        parentNode.getChildren().setAll(pane);
    }

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
