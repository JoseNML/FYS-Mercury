package hva.fys.mercury;

import hva.fys.mercury.DAO.DataBaseCreator;
import hva.fys.mercury.DAO.PopulateDB;
import hva.fys.mercury.controllers.LoginController;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static Stage stage1;

    public static String DATABASE_PASSWORD;
    public static String DATABASE_NAME = "MercuryTest";

    @Override
    public void start(Stage stage) throws Exception {
        stage1 = stage;
        LoginController.locale = new Locale("en", "US");
        ResourceBundle bundle = ResourceBundle.getBundle("UIResources", LoginController.locale);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"), bundle);
        Parent root = loader.load(); 
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage1.setTitle("Mercury");
        stage1.getIcons().add(new Image("/images/corendon_icon.png"));
        stage1.setMaximized(false);
        //  stage.setResizable(false);
        stage1.setScene(scene);
        stage1.show();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Voer In je Database Password: ");
        DATABASE_PASSWORD = input.nextLine();
//        System.out.print("Voer in je Database Naam: ");
//        DATABASE_NAME = input.nextLine();
        DataBaseCreator.createDatabase(DATABASE_NAME);

        PopulateDB.insertExamplesIntoDB();

        launch(args);
    }

}
