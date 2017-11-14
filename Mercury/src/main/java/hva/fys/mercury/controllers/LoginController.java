package hva.fys.mercury.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author davidbritt
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passTextField;

    @FXML
    private StackPane rootWorkSpace;
    
    @FXML
    private BorderPane rootNode;
    
    @FXML
    private void loginAction(ActionEvent event) {
        System.out.println("Logging In");
        Parent pane = loadFXMLFile("/fxml/MenuBar.fxml");

        System.out.println("pane:" + pane);
        System.out.println("rootWorkspace: " + rootWorkSpace);

        String userNameString, passwordString, userPass = "admin";

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
            System.out.println(rootWorkSpace);
        } else {
            String content = "Wrong credentials";
            System.out.println(content);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(content);
            alert.showAndWait();
        }
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
