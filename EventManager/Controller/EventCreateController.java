package EventManager.Controller;

import EventManager.DAO.EventDAO;
import EventManager.Entity.Evento;
import EventManager.Entity.SingletonEvento;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.net.URL;

import java.sql.Blob;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class EventCreateController extends GestioneEventiController implements Initializable {
    private EventDAO eventDAO;
    private SingletonEvento sEvento;
    private Evento evento;
    @FXML private AnchorPane anchorPane;
    @FXML private ComboBox tipo;
    @FXML private ComboBox regione;
    @FXML private CheckBox checkBox;
    @FXML private DatePicker dataFine;
    @FXML private DatePicker dataInizio;

    @FXML private Pane backPane;
    @FXML private Text back;

    @FXML private TextField nomeEvento;
    @FXML private TextField org;
    @FXML private ComboBox nazione;
    @FXML private TextField provincia;
    @FXML private TextField indirizzo;
    @FXML private TextField numeroCivico;
    @FXML private TextField nomeStrutt;
    @FXML private Spinner capienza;
    @FXML private TextField costo;

    @FXML private ComboBox ora1;
    @FXML private ComboBox minuti1;
    @FXML private ComboBox ora2;
    @FXML private ComboBox minuti2;

    @FXML private ImageView imageView;
    @FXML private Button imageChooser;
    @FXML private Button addButton;
    private boolean checked = false;

    public void initialize(URL url, ResourceBundle rb) {
        eventDAO = new EventDAO();

        setComboBoxes();
        setEvents();

        addTextLimiter(numeroCivico,costo, 5);
    }

    private void setComboBoxes() {
        int i = 1;
        ora1.getItems().add("00");
        ora2.getItems().add("00");
        for(;i<24; i++){
            if(i<10) {
                ora1.getItems().add("0"+i);
                ora2.getItems().add("0"+i);
            } else {
                ora1.getItems().add(i);
                ora2.getItems().add(i);
            }
        }
        minuti1.getItems().add("00");
        minuti2.getItems().add("00");
        for(i=1;i<60; i++){
            if(i<10) {
                minuti1.getItems().add("0"+i);
                minuti2.getItems().add("0"+i);
            } else {
                minuti1.getItems().add(i);
                minuti2.getItems().add(i);
            }
        }

        ora1.getSelectionModel().select("00");
        ora2.getSelectionModel().select("00");
        minuti1.getSelectionModel().select("00");
        minuti2.getSelectionModel().select("00");
        regione.getItems().addAll("Abruzzo", "Basilicata", "Calabria", "Campania", "Emilia-Romagna", "Friuli-Venezia Giulia", "Lazio", "Liguria", "Lombardia", "Marche", "Molise", "Piemonte", "Puglia", "Sardegna", "Sicilia", "Toscana", "Trentino-Alto Adige", "Umbria", "Valle d'Aosta", "Veneto");
        regione.getSelectionModel().select("Abruzzo");

        nazione.getItems().add("Italia");
        nazione.getSelectionModel().select("Italia");
        tipo.getItems().addAll("Concerto", "Meeting", "Cinema", "Teatro");
        tipo.getSelectionModel().select("Concerto");
    }

    private void setEvents() {

        checkBox.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t){
                if(!checked) {
                    dataFine.setDisable(false);
                    ora2.setDisable(false);
                    minuti2.setDisable(false);
                    checked = true;
                } else {
                    dataFine.setDisable(true);
                    ora2.setDisable(true);
                    minuti2.setDisable(true);
                    checked = false;
                }

            }
        });

        backPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) backPane.getScene().getWindow();
                sEvento = SingletonEvento.getInstance();
                sEvento.inizializza(null);
                stage.close();
            }
        });
        backPane.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                back.setFill(Color.WHITE);
                backPane.setStyle("-fx-background-color: #edce84;");
            }
        });
        backPane.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                back.setFill(Color.BLACK);
                backPane.setStyle("-fx-background-color: #efcd7c;");
            }
        });

        imageChooser.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                try{
                    FileChooser fileChooser = new FileChooser();
                    FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                    FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                    fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
                    File file = fileChooser.showOpenDialog(null);
                    if(file != null) {
                        Image image = new Image(file.toURI().toURL().toExternalForm());
                        imageView.setImage(image);
                        imageView.setFitHeight(100);
                        imageView.setFitWidth(100);
                    }
                } catch (Exception ex) {
                    System.out.println("Impossibile caricare l'immagine");
                }
            }
        });

    }

    public void addButtonAction(){
        if(checkFields() == 1 || dataInizio.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attenzione");
            alert.setHeaderText(null);
            alert.setContentText("Riempire tutti i campi obbligatori");
            alert.showAndWait();
        } else {
            int idEvento = ThreadLocalRandom.current().nextInt(1000, 10000);

            String nome = nomeEvento.getText();
            String organizzatore = org.getText();
            String naz = nazione.getValue().toString();
            String reg = regione.getValue().toString();
            String prov = provincia.getText();
            String ind = indirizzo.getText();
            String nc = numeroCivico.getText();
            String strutt = nomeStrutt.getText();
            int cap = Integer.parseInt(capienza.getValue().toString());
            Date inizio = java.sql.Date.valueOf(dataInizio.getValue());
            Date fine;
            if(checked) {
                fine = java.sql.Date.valueOf(dataFine.getValue());
            } else {
                fine = null;
            }
            String oraInizio = ora1.getValue().toString()+":"+minuti1.getValue().toString();
            String oraFine = ora2.getValue().toString()+":"+minuti2.getValue().toString();

            float cos = Float.parseFloat(costo.getText());
            Blob img = null;
            String tp = tipo.getValue().toString();
            try {
                BufferedImage bImage = SwingFXUtils.fromFXImage(imageView.getImage(), null);
                ByteArrayOutputStream s = new ByteArrayOutputStream();
                ImageIO.write(bImage, "png", s);
                byte byteImage[] = s.toByteArray();
                img = new javax.sql.rowset.serial.SerialBlob(byteImage);
                s.close();
            } catch (Exception e){
                e.printStackTrace();
            }
            evento = new Evento(idEvento,nome,organizzatore,naz,reg,prov,ind,nc,strutt,cap,inizio,oraInizio,fine,oraFine,cos,img,tp);
            sEvento = SingletonEvento.getInstance();
            sEvento.inizializza(evento);
            try{
                eventDAO.addEvent(evento);
            }catch(Exception e){
                e.printStackTrace();
            }
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        }
    }
    private int checkFields() {
        for (Node node : anchorPane.getChildren()) {
            if (node instanceof TextField) {
                TextField text = (TextField)node;
                if(text.getText().trim().isEmpty()){
                    return 1;
                }
            }
        }

        return 0;
    }

    private static void addTextLimiter(final TextField tf, final TextField costo, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
        costo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    costo.setText(oldValue);
                }
            }
        });
    }
}