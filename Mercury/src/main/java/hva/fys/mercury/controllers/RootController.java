package hva.fys.mercury.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class RootController implements Initializable {

    @FXML
    public TextField userTextField;

    @FXML

    public PasswordField passTextField;

    @FXML
    public StackPane rootWorkSpace;

    @FXML
    public BorderPane rootNode;

    @FXML
    private Button logOutBtn;

    @FXML
    private BorderPane parentNode;

    @FXML
    private Button wachtwoordVergetenBtn;

    public StackPane getRootWorkSpace() {
        return rootWorkSpace;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logOutBtn.setVisible(false);
    }

    @FXML
    private void logOut(ActionEvent event) {
        System.out.println("Logging Out");
        Parent pane = loadFXMLFile("/fxml/Root.fxml");

        System.out.println(pane);
        System.out.println(parentNode);
        logOutBtn.setVisible(false);
        rootNode.getChildren().clear();
        rootNode.setCenter(pane);
    }

    @FXML
    public void loginAction(ActionEvent event) {
        System.out.println("Logging In");

        System.out.println("rootWorkspace: " + rootWorkSpace);

        String userNameString = userTextField.getText(), passwordString = passTextField.getText();

        String userAdminString = "admin";
        String userManagerString = "manager";
        String userGebruikerString = "gebruiker";

        String userPass = "password";

        System.out.printf("\nuser: %s\tpass:%s.\n", userNameString, passwordString);

        /*
        TODO
        Function for checking database for all users and check credentials
         */
        //Checks credentials, which is admin!
        //quick and dirty right now, need to clean this up.
        //There should be a function here to handle the logins and which page to load. DRY DRY DRY
        if (userNameString.equalsIgnoreCase(userManagerString) && passwordString.equalsIgnoreCase(userPass)) {
            Parent pane = loadFXMLFile("/fxml/SceneManager.fxml");
            rootWorkSpace.getChildren().clear();
            rootWorkSpace.getChildren().setAll(pane);
            System.out.println("rootrootWorkSpace: " + rootWorkSpace);
            System.out.println("rootNode: " + rootNode);
            logOutBtn.setVisible(true);

        } else if (userNameString.equalsIgnoreCase(userAdminString) && passwordString.equalsIgnoreCase(userPass)) {
            Parent pane = loadFXMLFile("/fxml/SceneAdmin.fxml");
            rootWorkSpace.getChildren().clear();
            rootWorkSpace.getChildren().setAll(pane);
            System.out.println("rootrootWorkSpace: " + rootWorkSpace);
            System.out.println("rootNode: " + rootNode);
            logOutBtn.setVisible(true);
        } else if (userNameString.equalsIgnoreCase(userGebruikerString) && passwordString.equalsIgnoreCase(userPass)) {
            Parent pane = loadFXMLFile("/fxml/SceneGebruiker.fxml");
            rootWorkSpace.getChildren().clear();
            rootWorkSpace.getChildren().setAll(pane);
            System.out.println("rootrootWorkSpace: " + rootWorkSpace);
            System.out.println("rootNode: " + rootNode);
            logOutBtn.setVisible(true);
        } else {
            String content = "Wrong credentials";
            System.out.println(content);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(content);
            alert.showAndWait();
        }

    }

    @FXML
    public void wachtwoordVergetenAction(ActionEvent event) {
        String userNameString = userTextField.getText();

        if (userNameString.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill in your username");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("An email will be sent to the email\naddress associated with this email.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Alert emailConfirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                String emailString = "Email sent to " + userNameString;
                emailConfirmationAlert.setContentText(emailString);
                emailConfirmationAlert.showAndWait();
            }
        }

    }

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
