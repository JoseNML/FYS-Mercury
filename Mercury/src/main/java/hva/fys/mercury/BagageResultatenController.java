/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hva.fys.mercury;

import com.sun.corba.se.spi.activation._ActivatorImplBase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Eigenaar
 */
public class BagageResultatenController implements Initializable {
        
        
    @FXML
    private TableView table;
    
    private ObservableList<TableHandler> tableList;
   
    private int registratieNrCounter = 0;
    private int bagageNrCounter;
    //TableHandler handler = (TableHandler)table.getSelectionModel().getSelectedItem();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Begin table");
        tableList = FXCollections.observableArrayList();
        
        tableList.add(new TableHandler(registratieNrCounter++, "Koffer", "Samsonite", "CD212", bagageNrCounter++, "Amsterdam", "Rood", "Zwart", "80cmx80cmx120cm", "14kg", "Jose Niemel, Amsterdam", "N/A"));
        tableList.add(new TableHandler(registratieNrCounter++, "Koffer", "American Tourister", "CD523", bagageNrCounter++, "Brussels", "Geel", "Zilver", "100cmx100cmx140cm", "20kg", "Yassine Idrissi, Amsterdam", "N/A"));
        tableList.add(new TableHandler(registratieNrCounter++, "Handbagage", "Samsonite", "CD142", bagageNrCounter++, "Vienna", "Blauw", "Zwart", "50cmx50cmx80cm", "5kg", "David Britt, Amsterdam", "N/A"));
        tableList.add(new TableHandler(registratieNrCounter++, "Koffer", "Babymel", "CD953", bagageNrCounter++, "Hannover", "Zilver", "Grijs", "90cmx90cmx110cm", "15kg", "Daniel den Toom, Amsterdam", "N/A"));
        tableList.add(new TableHandler(registratieNrCounter++, "Handbagage", "HXTN", "CD582", bagageNrCounter++, "Frankfurt", "Paars", "Geel", "40cmx40cmx70cm", "7kg", "Devlin Zwanenburg, Baarn", "N/A"));
        tableList.add(new TableHandler(registratieNrCounter++, "Koffer", "Samsonite", "CD192", bagageNrCounter++, "Rotterdam", "Bruin", "Zwart", "80cmx80cmx100cm", "17kg", "Mitchell Wan, Amsterdam", "N/A"));
        table.setItems(this.tableList);
        System.out.println("Table end");
        
        for (int cnr = 0; cnr < table.getColumns().size(); cnr++) {
            TableColumn tc = (TableColumn)table.getColumns().get(cnr);
            String propertyName = tc.getId();
            if (propertyName != null && !propertyName.isEmpty()) {
                
            }
            tc.setCellValueFactory(new PropertyValueFactory<>(propertyName));
            System.out.println("Attached column '" + propertyName + "' in tableview to matching attribute");
            
        }
    }
}
