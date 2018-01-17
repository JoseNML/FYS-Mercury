package hva.fys.mercury.controllers;

import Services.PdfCreator;
import hva.fys.mercury.DAO.BagageDAO;
import hva.fys.mercury.models.Bagage;
import hva.fys.mercury.models.Reiziger;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class die de vermiste bagage registreert
 *
 * @author Mitchell Wan
 */
public class RegistreerVermistController implements Initializable {

    Bagage bagage = new Bagage();
    Reiziger reiziger = new Reiziger();
    @FXML
    public TextField voornaam;
    @FXML
    public TextField achternaam;
    @FXML
    public TextField adres;
    @FXML
    public TextField woonplaats;
    @FXML
    public TextField postcode;
    @FXML
    public TextField land;
    @FXML
    public TextField telefoonnummer;
    @FXML
    public TextField email;
    @FXML
    public TextField bagageLabel;
    @FXML
    public TextField vluchtNummer;
    @FXML
    public DatePicker datumGevonden;
    @FXML
    public TextField tijdGevonden;
    @FXML
    public TextField locatieGevonden;
    @FXML
    public ComboBox bagageType;
    @FXML
    public ComboBox status;
    @FXML
    public TextField merk;
    @FXML
    public TextField primaireKleur;
    @FXML
    public TextField secundaireKleur;
    @FXML
    public TextField formaat;
    @FXML
    public TextField gewicht;
    @FXML
    public TextField IATA;
    @FXML
    public TextField overigeEigenschappen;

    @FXML
    private GridPane BagageOpslaan;

    @FXML
    private Button opslaanBTN;

    @FXML
    private Label opgeslagenLabel;

    @FXML
    private Label denyLabel;
    @FXML
    private Button pdfBTN;
    
    ResourceBundle RsBundle = ResourceBundle.getBundle("UIResources", LoginController.locale);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        status.getItems().addAll(
                RsBundle.getString("gevonden.5"),
                RsBundle.getString("gevonden.6"),
                RsBundle.getString("gevonden.7")
        );
        bagageType.getItems().addAll(
                RsBundle.getString("gevonden.8"),
                RsBundle.getString("gevonden.9"),
                RsBundle.getString("gevonden.10"),
                RsBundle.getString("gevonden.11"),
                RsBundle.getString("gevonden.12"),
                RsBundle.getString("gevonden.13"),
                RsBundle.getString("gevonden.14"),
                RsBundle.getString("gevonden.15")
        ); 
    }

    /**
     * controleert of alle TextFields zijn ingevuld en geeft een boolean waarde
     * terug
     *
     * @return een boolean met waarde 'true' als ze zijn ingevuld en de waarde
     * 'false' als ze niet zijn ingevuld
     */
    public boolean checkText() {
        TextField[] text = {
            voornaam,
            achternaam,
            adres,
            woonplaats,
            postcode,
            land,
            telefoonnummer,
            email,
            bagageLabel,
            vluchtNummer,
            tijdGevonden,
            locatieGevonden,
            IATA,
            merk,
            primaireKleur,
            secundaireKleur,
            formaat,
            gewicht,
            overigeEigenschappen
        };

        for (int i = 0; i < text.length; i++) {
            if (text[i].getText().trim().length() == 0) {
                return false;
            }

        }
        return true;
    }

    /**
     * maakt alle TextFields onbruikbaar
     */
    public void disableFields() {
        TextField[] text = {
            voornaam,
            achternaam,
            adres,
            woonplaats,
            postcode,
            land,
            telefoonnummer,
            email,
            bagageLabel,
            vluchtNummer,
            tijdGevonden,
            locatieGevonden,
            IATA,
            merk,
            primaireKleur,
            secundaireKleur,
            formaat,
            gewicht,
            overigeEigenschappen
        };
        for (int i = 0; i < text.length; i++) {
            text[i].setDisable(true);
        }
        datumGevonden.getEditor().setDisable(true);
    }

    /**
     * maakt alle TextFields bruikbaar
     */
    public void enableFields() {
        TextField[] text = {
            voornaam,
            achternaam,
            adres,
            woonplaats,
            postcode,
            land,
            telefoonnummer,
            email,
            bagageLabel,
            vluchtNummer,
            tijdGevonden,
            locatieGevonden,
            IATA,
            merk,
            primaireKleur,
            secundaireKleur,
            formaat,
            gewicht,
            overigeEigenschappen
        };
        for (int i = 0; i < text.length; i++) {
            text[i].setDisable(false);
        }
        datumGevonden.getEditor().setDisable(false);
        datumGevonden.getEditor().setText("");
    }

    /**
     * Slaat alle informatie die in de textfields is opgeslagen op en verstuurt
     * de informatie naar de database
     *
     * @param event
     */
    public void opslaanBagage(ActionEvent event) {
        BagageDAO dbBagage = new BagageDAO();
        if (checkText() == false) {
            denyLabel.setText("You did not fill in all textfields!");
        } else {
            disableFields();
            reiziger.setVoornaam(voornaam.getText());
            reiziger.setAchternaam(achternaam.getText());
            reiziger.setAdres(adres.getText());
            reiziger.setWoonplaats(woonplaats.getText());
            reiziger.setPostcode(postcode.getText());
            reiziger.setLand(land.getText());
            reiziger.setTelefoonnummer(telefoonnummer.getText());
            reiziger.setEmail(email.getText());
            bagage.setBagagelabel(bagageLabel.getText());
            bagage.setVluchtNummer(vluchtNummer.getText());
            bagage.setDatumGevonden(datumGevonden.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            bagage.setTijdGevonden(tijdGevonden.getText());
            bagage.setGevondenLocatie(locatieGevonden.getText());
            bagage.setBagageType(bagageType.getValue().toString());
            bagage.setBagagemerk(merk.getText());
            bagage.setPrimaireKleur(primaireKleur.getText());
            bagage.setSecundaireKleur(secundaireKleur.getText());
            bagage.setFormaat(formaat.getText());
            bagage.setGewichtInKG(gewicht.getText());
            bagage.setIATA_Code(IATA.getText());
            bagage.setStatus(status.getValue().toString());
            bagage.setOverigeEigenschappen(overigeEigenschappen.getText());
            denyLabel.setText("");
            dbBagage.registreerBagage(bagage);
            opgeslagenLabel.setText("Information succesfully saved!");
            System.out.println("Gegevens zijn opgeslagen!");  
        pdfBTN.setVisible(true);
        }
    }

    /**
     * maak alle TextFields leeg
     */
    public void annuleerText() {
        TextField[] annuleer = {
            voornaam,
            achternaam,
            adres,
            woonplaats,
            postcode,
            land,
            telefoonnummer,
            email,
            bagageLabel,
            vluchtNummer,
            tijdGevonden,
            locatieGevonden,
            IATA,
            merk,
            primaireKleur,
            secundaireKleur,
            formaat,
            gewicht,
            overigeEigenschappen
        };

        for (int i = 0; i < annuleer.length; i++) {
            annuleer[i].setText("");
        }
    }

    /**
     * maakt alle TextFields, DatePickers en Labels leeg en onbruikbaar
     *
     * @param event
     */
    @FXML
    private void annuleerBagage(ActionEvent event) {
        denyLabel.setText("");
        opgeslagenLabel.setText("");
        datumGevonden.getEditor().setDisable(false);
        datumGevonden.getEditor().setText("");
        annuleerText();
        pdfBTN.setVisible(false);
    }

    /**
     * zorgt ervoor dat alle velden en labels gereset worden.
     *
     * @param event
     */
    @FXML
    private void nieuwFormulier(ActionEvent event) {
        opgeslagenLabel.setText("");
        annuleerText();
        enableFields();
        denyLabel.setText("");
        opgeslagenLabel.setText("");

    }

    /**
     * Dit slaat het formulier op als een PDF bestand
     *
     * @param event
     */
    @FXML
    private void saveAsPDF(ActionEvent event) {
        try {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sla formulier op.");
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(filter);
            File file = fileChooser.showSaveDialog(BagageOpslaan.getScene().getWindow());
            PdfCreator creator = new PdfCreator(reiziger, bagage, bagage.getIATA_Code());
            creator.savePdf(file.getAbsolutePath(), LoginController.locale);

        } catch (FileNotFoundException | MalformedURLException ex) {
            Logger.getLogger(RegistreerVermistController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
