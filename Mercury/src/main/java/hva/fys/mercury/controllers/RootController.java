package hva.fys.mercury.controllers;

import java.io.IOException;
import java.net.URL; 
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
<<<<<<< HEAD:Mercury/src/main/java/hva/fys/mercury/FXMLController.java
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
=======
import javafx.fxml.Initializable; 
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane; 
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;  
>>>>>>> origin/TempUML:Mercury/src/main/java/hva/fys/mercury/controllers/RootController.java

public class RootController implements Initializable {

    @FXML
    public TextField userTextField;

    @FXML
<<<<<<< HEAD:Mercury/src/main/java/hva/fys/mercury/FXMLController.java
    public PasswordField passTextField;

    @FXML
    public StackPane rootWorkSpace;

    @FXML
    public BorderPane rootNode;
    
    @FXML
    private Button logOutBtn;
=======
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
        Parent pane = loadFXMLFile("/fxml/registreerVermist.fxml");

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
>>>>>>> origin/TempUML:Mercury/src/main/java/hva/fys/mercury/controllers/RootController.java

    public StackPane getRootWorkSpace() {
        return rootWorkSpace;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logOutBtn.setVisible(false);
    }
    
    @FXML
<<<<<<< HEAD:Mercury/src/main/java/hva/fys/mercury/FXMLController.java
    public void logOut(ActionEvent event){
        System.out.println("logging out...");
        Parent pane = loadFXMLFile("/fxml/Root.fxml");
        rootNode.getChildren().clear();
        rootNode.getChildren().setAll(pane);
=======
    private void logOut(ActionEvent event) {
        System.out.println("Logging Out");
        Parent pane = loadFXMLFile("/fxml/Login.fxml");

        System.out.println(pane);
        System.out.println(parentNode);

        parentNode.getChildren().clear();
        parentNode.getChildren().setAll(pane);
>>>>>>> origin/TempUML:Mercury/src/main/java/hva/fys/mercury/controllers/RootController.java
    }

    @FXML
    public void loginAction(ActionEvent event) {
        System.out.println("Logging In");
        Parent pane = loadFXMLFile("/fxml/Scene.fxml");

<<<<<<< HEAD:Mercury/src/main/java/hva/fys/mercury/FXMLController.java
        System.out.println("pane:" + pane);
        System.out.println("rootWorkspace: " + rootWorkSpace);

        String userNameString, passwordString, userPass = "gebruiker";
        String userAdminString = "admin";
        String userManagerString = "manager";

        userNameString = userTextField.getText();
        passwordString = passTextField.getText();

        System.out.printf("\nuser: %s\tpass:%s.\n", userNameString, passwordString);

        /*
        TODO
        Function for checking database for all users and check credentials
         */
        //Checks credentials, which is admin!
        if (userNameString.equalsIgnoreCase(userPass) && passwordString.equalsIgnoreCase(userPass)) {
            rootWorkSpace.getChildren().clear();
            rootWorkSpace.getChildren().setAll(pane);
            System.out.println("rootworkspace: " + rootWorkSpace);
            System.out.println("rootNode: " + rootNode);
            logOutBtn.setVisible(true);

        } else {
            String content = "Wrong credentials";
            System.out.println(content);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(content);
            alert.showAndWait();
        }
=======
        System.out.println(pane);
        System.out.println(parentNode);

        parentNode.getChildren().clear();
        parentNode.getChildren().setAll(pane);
>>>>>>> origin/TempUML:Mercury/src/main/java/hva/fys/mercury/controllers/RootController.java
    }

//    dit is een idee
//    @FXML
//    public void exitLogOut(ActionEvent event) {
//        System.out.println("Logging Out");
//        Parent pane = loadFXMLFile("/fxml/Login.fxml");
//
//        System.out.println("pane: " + pane);
//        System.out.println("rootWorkspace: " + rootWorkSpace);
//
//        rootWorkSpace.getChildren().clear();
//        rootWorkSpace.getChildren().setAll(pane);
//    }

    private Parent loadFXMLFile(String fxmlFileName) {
        try {
            return FXMLLoader.load(getClass().getResource(fxmlFileName));
        } catch (IOException ex) {
            System.out.printf("\n%s: %s\n", ex.getClass().getName(), ex.getMessage());
            return null;
        }
    }

    public void printRoot() {
        System.out.println("ThisrootNode: " + this.rootNode);
    }
}
