package Services;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import hva.fys.mercury.models.Bagage;
import hva.fys.mercury.models.Reiziger;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

/**
 * creërt een PDF bestand met informatie over de reiziger en de verloren bagage
 *
 * @author Mitchell Wan
 */
public class PdfCreator {

    private final String PDF_FORMAT = "%-80s %30s\n ";
    private final String LOGO_IMAGE = "./src/main/resources/images/corendon_logo.png";
    private Reiziger reiziger;
    private Bagage bagage;
    private final String huidigeIATA;

    public PdfCreator(Reiziger reiziger, Bagage bagage, String huidigeIATA) {
        this.reiziger = reiziger;
        this.bagage = bagage;
        this.huidigeIATA = huidigeIATA;

    }

    /**
     *
     * slaat het pdf bestand op
     *
     * @param bestandsLocatie de locatie van waar het bestand opgeslagen moet
     * worden
     * @param locale stelt de taal van het PDF bestand in
     * @throws FileNotFoundException
     * @throws MalformedURLException
     */
    public void savePdf(String bestandsLocatie, Locale locale) throws FileNotFoundException, MalformedURLException {
        PdfWriter writer = new PdfWriter(bestandsLocatie);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        if (locale.getCountry().equalsIgnoreCase("NL")) {
            document = makePDF(document);
        } else {
            document = makePDFinEng(document);
        }
        document.close();
    }

    /**
     * maakt het PDF bestand aan in het nederlands
     *
     * @param document een leeg pdf document
     * @return het ingevulde pdf document
     * @throws MalformedURLException
     */
    private Document makePDF(Document document) throws MalformedURLException {

        Image img = new Image(ImageDataFactory.create(LOGO_IMAGE));
        document.add(img);

        Text text = new Text("\nVermisteBagage Registratie Formulier\n");
        text.setBold();
        text.setFontSize(24);
        text.setTextAlignment(TextAlignment.CENTER);
        document.add(new Paragraph(text));

        Paragraph etc = setLocDateInfo(LocalDate.now().toString(), LocalTime.now().toString().substring(0, 5));
        document.add(etc);

        Table reizigerHeader = createHeader("Reiziger Informatie:");
        document.add(reizigerHeader);
        Paragraph reizigerInfo = setReizigerInfo();
        document.add(reizigerInfo);

        Table bagageLabelHeader = createHeader("Bagage Label Informatie:");
        document.add(bagageLabelHeader);
        document.add(setBagageLabelInfo());

        Table bagageHeader = createHeader("Bagage Informatie:");
        document.add(bagageHeader);
        document.add(setBagageInfo());

        return document;
    }

    /**
     * maakt het PDF bestand aan in het engels
     *
     * @param document een leeg pdf document
     * @return het ingevulde pdf document
     * @throws MalformedURLException
     */
    private Document makePDFinEng(Document document) throws MalformedURLException {

        Image img = new Image(ImageDataFactory.create(LOGO_IMAGE));
        document.add(img);

        Text text = new Text("\nVermisteBagage Registratie Formulier\n");
        text.setBold();
        text.setFontSize(24);
        text.setTextAlignment(TextAlignment.CENTER);
        document.add(new Paragraph(text));

        Paragraph etc = setLocDateInfoInENG(LocalDate.now().toString(), LocalTime.now().toString().substring(0, 5));
        document.add(etc);

        Table reizigerHeader = createHeader("Traveler Information:");
        document.add(reizigerHeader);
        Paragraph reizigerInfo = setReizigerInfoENG();
        document.add(reizigerInfo);

        Table bagageLabelHeader = createHeader("Luggage Label Information:");
        document.add(bagageLabelHeader);
        document.add(setBagageLabelInfoENG());

        Table bagageHeader = createHeader("Luggage Information:");
        document.add(bagageHeader);
        document.add(setBagageInfoENG());

        return document;
    }

    /**
     * Er wordt een header gemaakt voor het Pdf bestand
     *
     * @param title title van de header
     * @return de gemaakte header
     */
    private Table createHeader(String title) {
        float[] pointColumnWidths = {200, 200, 200};

        Table header = new Table(pointColumnWidths);
        header.setFontColor(Color.WHITE);
        header.setBackgroundColor(Color.RED);

        Cell titelCell = new Cell();
        titelCell.add(title);
        titelCell.setBold();
        titelCell.setBorder(Border.NO_BORDER);

        header.addCell(titelCell);
        header.addCell(new Cell().setBorder(Border.NO_BORDER));
        header.addCell(new Cell().setBorder(Border.NO_BORDER));
        return header;
    }

    private Paragraph setBagageInfo() {
        Paragraph bagageInf = new Paragraph();
        bagageInf.add(String.format(PDF_FORMAT, "Type:", bagage.getBagageType()));
        bagageInf.add(String.format(PDF_FORMAT, "Merk:", bagage.getBagagemerk()));
        bagageInf.add(String.format(PDF_FORMAT, "Kleur 1:", bagage.getPrimaireKleur()));
        bagageInf.add(String.format(PDF_FORMAT, "Kleur 2:", bagage.getSecundaireKleur()));
        bagageInf.add(String.format(PDF_FORMAT, "Bijzondere Kenmerken", bagage.getOverigeEigenschappen() + "\n\n"));
        return bagageInf;
    }

    private Paragraph setBagageInfoENG() {
        Paragraph bagageInf = new Paragraph();
        bagageInf.add(String.format(PDF_FORMAT, "Type:", bagage.getBagageType()));
        bagageInf.add(String.format(PDF_FORMAT, "Brand:", bagage.getBagagemerk()));
        bagageInf.add(String.format(PDF_FORMAT, "Color 1:", bagage.getPrimaireKleur()));
        bagageInf.add(String.format(PDF_FORMAT, "Color 2:", bagage.getSecundaireKleur()));
        bagageInf.add(String.format(PDF_FORMAT, "Special characteristics", bagage.getOverigeEigenschappen() + "\n\n"));
        return bagageInf;
    }

    private Paragraph setBagageLabelInfo() {
        Paragraph bagageLabelInf = new Paragraph();
        bagageLabelInf.add(String.format(PDF_FORMAT, "Label Nummer:", bagage.getBagagelabel()));
        bagageLabelInf.add(String.format(PDF_FORMAT, "VluchtNummer:", bagage.getVluchtNummer() + "\n\n"));
        return bagageLabelInf;
    }

    private Paragraph setBagageLabelInfoENG() {
        Paragraph bagageLabelInf = new Paragraph();
        bagageLabelInf.add(String.format(PDF_FORMAT, "Label Number:", bagage.getBagagelabel()));
        bagageLabelInf.add(String.format(PDF_FORMAT, "Flight Number:", bagage.getVluchtNummer() + "\n\n"));
        return bagageLabelInf;
    }

    private Paragraph setReizigerInfo() {
        Paragraph reizigerInf = new Paragraph();
        reizigerInf.add(String.format(PDF_FORMAT, "Voornaam:", reiziger.getVoornaam()));
        reizigerInf.add(String.format(PDF_FORMAT, "Achternaam:", reiziger.getAchternaam()));
        reizigerInf.add(String.format(PDF_FORMAT, "Adres:", reiziger.getAdres()));
        reizigerInf.add(String.format(PDF_FORMAT, "Woonplaats:", reiziger.getWoonplaats()));
        reizigerInf.add(String.format(PDF_FORMAT, "Postcode:", reiziger.getVoornaam()));
        reizigerInf.add(String.format(PDF_FORMAT, "Land:", reiziger.getLand()));
        reizigerInf.add(String.format(PDF_FORMAT, "Telefoon:", reiziger.getTelefoonnummer()));
        reizigerInf.add(String.format(PDF_FORMAT, "Email:", reiziger.getEmail() + "\n\n"));
        return reizigerInf;
    }

    private Paragraph setReizigerInfoENG() {
        Paragraph reizigerInf = new Paragraph();
        reizigerInf.add(String.format(PDF_FORMAT, "Name:", reiziger.getVoornaam()));
        reizigerInf.add(String.format(PDF_FORMAT, "Surname:", reiziger.getAchternaam()));
        reizigerInf.add(String.format(PDF_FORMAT, "Adress:", reiziger.getAdres()));
        reizigerInf.add(String.format(PDF_FORMAT, "City:", reiziger.getWoonplaats()));
        reizigerInf.add(String.format(PDF_FORMAT, "Postal code:", reiziger.getVoornaam()));
        reizigerInf.add(String.format(PDF_FORMAT, "Country:", reiziger.getLand()));
        reizigerInf.add(String.format(PDF_FORMAT, "Phone:", reiziger.getTelefoonnummer()));
        reizigerInf.add(String.format(PDF_FORMAT, "Email:", reiziger.getEmail() + "\n\n"));
        return reizigerInf;
    }

    private Paragraph setLocDateInfo(String datum, String tijd) {
        Paragraph locDate = new Paragraph();
        locDate.add(String.format(PDF_FORMAT, "Datum:", datum));
        locDate.add(String.format(PDF_FORMAT, "Tijd:", tijd));
        locDate.add(String.format(PDF_FORMAT, "Luchthaven:", huidigeIATA) + "\n\n");
        return locDate;
    }

    private Paragraph setLocDateInfoInENG(String datum, String tijd) {
        Paragraph locDate = new Paragraph();
        locDate.add(String.format(PDF_FORMAT, "Date:", datum));
        locDate.add(String.format(PDF_FORMAT, "Time:", tijd));
        locDate.add(String.format(PDF_FORMAT, "Airport:", huidigeIATA) + "\n\n");
        return locDate;
    }
}
