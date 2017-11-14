package hva.fys.mercury;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class FXMLController implements Initializable {

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

    public StackPane getRootWorkSpace() {
        return rootWorkSpace;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logOutBtn.setVisible(false);
    }
    
    @FXML
    public void logOut(ActionEvent event){
        System.out.println("logging out...");
        Parent pane = loadFXMLFile("/fxml/Root.fxml");
        rootNode.getChildren().clear();
        rootNode.getChildren().setAll(pane);
    }

    @FXML
    public void loginAction(ActionEvent event) {
        System.out.println("Logging In");
        Parent pane = loadFXMLFile("/fxml/MenuBar.fxml");

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
