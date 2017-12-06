/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hva.fys.mercury.controllers;

import hva.fys.mercury.DAO.DatabaseManager;
import hva.fys.mercury.DAO.GebruikerDAO;
import hva.fys.mercury.MainApp;
import hva.fys.mercury.controllers.ParentControllerContext;
import hva.fys.mercury.models.Gebruiker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author djmbritt
 */
public class GebruikerAanpassenPaneController implements Initializable {

    /*
    Zet je textfield voor je gebruikeraanpassen.fxml text fields hier beneden
     */
    @FXML
    private Label EmployeeID;
    @FXML
    private TextField Initials;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField MiddleName;
    @FXML
    private TextField SurName;
    @FXML
    private DatePicker BirthDate;
    @FXML
    private DatePicker StartEmploymentDate;
    @FXML
    private TextField WorkEmail;
    @FXML
    private ChoiceBox<String> WorkingLocation;
    @FXML
    private ChoiceBox<String> StatusEmployment;
    @FXML
    private DatePicker EndDateEmployment;
    @FXML
    private TextField PersonalEmail;
    @FXML
    private TextField MobilePhoneNumber;
    @FXML
    private TextField HomePhoneNumber;
    @FXML
    private TextField DepartmentEmployment;
    @FXML
    private TextField HomeAdress;
    @FXML
    private TextField PostalCode;
    @FXML
    private PasswordField WachtWoord;
    @FXML
    private PasswordField WachtWoordVerificatie;

    GebruikerDAO gebruikerDAO = new GebruikerDAO();

    ObservableList<String> statusList = FXCollections.<String>observableArrayList("Werkenzaam", "Ontslagen", "Met Verlof", "Vakantie", "Afgewezen", "Gesoliciteerd");
    
    //Fornow.
    ObservableList<String> werkLocatieList = FXCollections.<String>observableArrayList("Amsterdam", "Istanbul", "Malaga", "Ankara");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        WorkingLocation.getItems().addAll(werkLocatieList);
//        StatusEmployment.getItems().addAll(statusList);

    }

    private ParentControllerContext parentController;
    private Gebruiker gebruiker;

    public void setParentContext(ParentControllerContext pC, Gebruiker gbrkr) {
        System.out.println("this.parentcontroller: " + pC.toString());
        System.out.println("this.founLuggage: " + gbrkr.toString());
        this.parentController = pC;
        this.gebruiker = gbrkr;
        this.initFields(gbrkr);
        pC.displayStatusMessage("Editing found luggage");
    }

    private void initFields(Gebruiker gbrkr) {

        EmployeeID.setText(Integer.toString(gbrkr.getEmployeeID()));
        Initials.setText(gbrkr.getInitials());
        FirstName.setText(gbrkr.getFirstName());
        MiddleName.setText(gbrkr.getMiddleName());
        SurName.setText(gbrkr.getSurName());
        WorkEmail.setText(gbrkr.getWorkEmail());

//        BirthDate.setValue(gbrkr.getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy").toString()));
//        StartEmploymentDate.setText(gbrkr.getStartEmploymentDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy").toString()));
//        EndDateEmployment.setValue(gbrkr.getEndDateEmployment().format(DateTimeFormatter.ofPattern("dd-MM-yyyy").toString()));

        WorkingLocation.setValue(gbrkr.getWorkingLocation());
        StatusEmployment.setValue(gbrkr.getStatusEmployment());

        PersonalEmail.setText(gbrkr.getPersonalEmail());
        MobilePhoneNumber.setText(gbrkr.getMobilePhoneNumber());
//        HomePhoneNumber.setText(gbrkr.getHomePhoneNumber());
        DepartmentEmployment.setText(gbrkr.getDepartmentEmployment());
        HomeAdress.setText(gbrkr.getHomeAdress());
        PostalCode.setText(gbrkr.getPostalCode());
    }

    @FXML
    public void handleCancelAction(ActionEvent event) {
        System.out.println("Canceling");

        this.parentController.displayStatusMessage("Cancelled editing found luggage...");
        this.parentController.notifyCloseChild();
        this.gebruiker = null;

    }

    @FXML
    public void handleSaveAction(ActionEvent event) {
        System.out.println("Saving....");

//                    bagage.setDatumGevonden(datumGevondenG.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        this.gebruiker.setEmployeeID(Integer.parseInt(EmployeeID.getText()));
        this.gebruiker.setInitials(Initials.getText());
        this.gebruiker.setFirstName(FirstName.getText());
        this.gebruiker.setMiddleName(MiddleName.getText());
        this.gebruiker.setSurName(SurName.getText());
        this.gebruiker.setBirthDate(BirthDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        this.gebruiker.setStartEmploymentDate(StartEmploymentDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        this.gebruiker.setWorkEmail(WorkEmail.getText());
        this.gebruiker.setWorkingLocation(WorkingLocation.getValue().toString());
        this.gebruiker.setStatusEmployment(StatusEmployment.getValue().toString());
        this.gebruiker.setEndDateEmployment(EndDateEmployment.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        this.gebruiker.setPersonalEmail(PersonalEmail.getText());
        this.gebruiker.setMobilePhoneNumber(MobilePhoneNumber.getText());
//        this.gebruiker.setHomePhoneNumber(HomePhoneNumber.getText());
        this.gebruiker.setDepartmentEmployment(DepartmentEmployment.getText());
        this.gebruiker.setHomeAdress(HomeAdress.getText());
        this.gebruiker.setPostalCode(PostalCode.getText());

        DatabaseManager db = new DatabaseManager(MainApp.DATABASE_NAME);
        String reUpGebruikerQuery = String.format("SELECT EmployeeID FROM Gebruikers WHERE EmployeeID='%d'", this.gebruiker.getEmployeeID());
        String registreerOfUpdateGebruiker = db.executeStringQuery(reUpGebruikerQuery);

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        if (registreerOfUpdateGebruiker != null) {
            gebruikerDAO.updateGebruiker(this.gebruiker);
            confirmation.setContentText("Updated Gebruiker: " + this.gebruiker.getFirstName() + " to Database 😁");
            confirmation.showAndWait();

        } else {
            gebruikerDAO.registreerGebruiker(this.gebruiker);
            confirmation.setContentText("Saved new Gebruiker: " + this.gebruiker.getFirstName() + " to Database 😁");
            confirmation.showAndWait();
        }

        this.parentController.displayStatusMessage("Saving new information");
        this.parentController.notifyChildHasUpdated();
        this.parentController.notifyCloseChild();

    }

    @FXML
    public void handleResetAction(ActionEvent event) {

//                    bagage.setDatumGevonden(datumGevondenG.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        EmployeeID.setText(Integer.toString(this.gebruiker.getEmployeeID()));
        Initials.setText(this.gebruiker.getInitials());
        FirstName.setText(this.gebruiker.getFirstName());
        MiddleName.setText(this.gebruiker.getMiddleName());
        SurName.setText(this.gebruiker.getSurName());
//        BirthDate.setValue(this.gebruiker.getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"). toString()));
//        BirthDate.setValue(new SimpleDateFormat("dd-MM-yyyy"));
//        StartEmploymentDate.setText(this.gebruiker.getStartEmploymentDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"). toString()));
        WorkEmail.setText(this.gebruiker.getWorkEmail());
        WorkingLocation.setValue(this.gebruiker.getWorkingLocation());
        StatusEmployment.setValue(this.gebruiker.getStatusEmployment());
//        EndDateEmployment.setText(this.gebruiker.getEndDateEmployment().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"). toString()));
        PersonalEmail.setText(this.gebruiker.getPersonalEmail());
        MobilePhoneNumber.setText(this.gebruiker.getMobilePhoneNumber());
//        HomePhoneNumber.setText(this.gebruiker.getHomePhoneNumber());
        DepartmentEmployment.setText(this.gebruiker.getDepartmentEmployment());
        HomeAdress.setText(this.gebruiker.getHomeAdress());
        PostalCode.setText(this.gebruiker.getPostalCode());

        this.parentController.displayStatusMessage("resseting information");
    }

    @FXML
    public void handleClearAction(ActionEvent event) {

        EmployeeID.setText(null);
        Initials.setText(null);
        FirstName.setText(null);
        MiddleName.setText(null);
        SurName.setText(null);
        BirthDate.setValue(null);
        StartEmploymentDate.setValue(null);
        WorkEmail.setText(null);
        WorkingLocation.setValue(null);
        StatusEmployment.setValue(null);
        EndDateEmployment.setValue(null);
        PersonalEmail.setText(null);
        MobilePhoneNumber.setText(null);
        HomePhoneNumber.setText(null);
        DepartmentEmployment.setText(null);
        HomeAdress.setText(null);
        PostalCode.setText(null);

        this.parentController.displayStatusMessage("Clearing everything");
    }
}
