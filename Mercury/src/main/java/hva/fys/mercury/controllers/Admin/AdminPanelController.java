package hva.fys.mercury.controllers.Admin;

import hva.fys.mercury.DAO.DatabaseManager;
import hva.fys.mercury.DAO.GebruikerDAO;
import hva.fys.mercury.models.Gebruiker;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class AdminPanelController implements Initializable, ParentControllerContext {

    DatabaseManager dbManager = new DatabaseManager("MercuryTest");

    @FXML
    private TableView gebruikerTableView;

    @FXML
    private AnchorPane gebruikerAanpassenPane;

    @FXML
    private GebruikerAanpassenPaneController gebruikerAanpassenPaneController;

    private ObservableList<Gebruiker> gebruikerList = FXCollections.observableArrayList();

    //Parent controller methods
    private void showTableView() {
        this.gebruikerAanpassenPane.setVisible(false);
        this.gebruikerTableView.setVisible(true);
    }

    @Override
    public void notifyCloseChild() {
        showTableView();
    }

    @Override
    public void notifyChildHasUpdated() {
        gebruikerTableView.refresh();
    }

    @Override
    public void displayStatusMessage(String message) {
//        statusMessage.setText(message);
    }

    @FXML
    public void handleAddItemAction() {
        addItemToFoundLuggageList();
    }

    @FXML
    public void handleDeleteItemAction() {
        System.out.println("Deleting item...");
        Gebruiker selectedItem = (Gebruiker) gebruikerTableView.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Item not found");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to delete this item?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                gebruikerList.removeAll(selectedItem);
            } else {
                System.out.println("Item not deleted.");
            }
        }

    }

    @FXML
    public void handleChangeItemAction() {
        System.out.println("Changed item button pressed.");

        Gebruiker selectedItem = (Gebruiker) gebruikerTableView.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Item not found");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Changing item");

            System.out.println("this: " + this.toString());
            System.out.println("selectedItem: " + selectedItem.toString());
            System.out.println("gebruikerAanpassenPaneController: " + gebruikerAanpassenPaneController.toString());

            gebruikerAanpassenPaneController.setParentContext(this, selectedItem);

            showFoundLuggagePane();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("AdminPanelController.Initialize()");

        fillTable(GebruikerDAO.readAllGebruikerDB());
        gebruikerTableView.refresh();

    }

    public void fillTable(List<Gebruiker> list) {
        gebruikerList.addAll(list);

        gebruikerTableView.setItems(gebruikerList);

        for (int cnr = 0; cnr < gebruikerTableView.getColumns().size(); cnr++) {
            TableColumn tc = (TableColumn) gebruikerTableView.getColumns().get(cnr);
            String propertyName = tc.getId();

            if (propertyName != null && !propertyName.isEmpty()) {
                tc.setCellValueFactory(new PropertyValueFactory<>(propertyName));
                System.out.println("Attached collumn " + propertyName + "in tableview to matching attribute.");
            }

        }
    }

    public void addItemToFoundLuggageList() {
        //add dummy list 
        gebruikerList.add(new Gebruiker());

        // associate the data collection with the table view.
        gebruikerTableView.setItems(this.gebruikerList);

        for (int cnr = 0; cnr < gebruikerTableView.getColumns().size(); cnr++) {
            TableColumn tc = (TableColumn) gebruikerTableView.getColumns().get(cnr);
            String propertyName = tc.getId();

            if (propertyName != null && !propertyName.isEmpty()) {
                tc.setCellValueFactory(new PropertyValueFactory<>(propertyName));
                System.out.println("Attached collumn " + propertyName + "in tableview to matching attribute.");
            }

        }
    }

    public void showFoundLuggagePane() {
        this.gebruikerTableView.setVisible(false);
        this.gebruikerAanpassenPane.setVisible(true);
    }

}
