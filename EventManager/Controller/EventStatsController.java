package EventManager.Controller;

import EventManager.DAO.TicketDAO;
import EventManager.Entity.SingletonEvento;
import EventManager.Entity.Statistiche;
import EventManager.Entity.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EventStatsController implements Initializable {

    @FXML private BarChart<String, Number> barStats;
    @FXML private PieChart pieStats;
    @FXML private Text ticketSold;
    @FXML private Text ticketWhole;
    @FXML private Text ticketReduced;
    @FXML private Pane backPane;
    @FXML private Text back;
    private SingletonEvento sEvento;
    private TicketDAO ticketDAO;
    private List<Ticket> tickets=null;
    private Statistiche stats;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        sEvento = SingletonEvento.getInstance();
        ticketDAO = new TicketDAO();
        if(ticketDAO.checkConn())
            tickets = ticketDAO.getTicketsByEvent(sEvento.ottieniEvento().getIdEvento());
        //populate statistics
        stats = new Statistiche();
        int i = 0;
        for(Ticket t : tickets){
            stats.incrementSold();
            if(t.isRidotto())
                stats.incrementRidotti();
            if(!t.getPiattaforma().equals("PC"))
                stats.incrementMobile();
            stats.addPairDateBuy(t.getDataAcquisto());
            i++;
        }
        //make bar chart
        barStats.setTitle("Numero di Acquisti per data");
        XYChart.Series series = new XYChart.Series();
        for(Pair<String, Integer> s : stats.getVendutiInData()) {
            series.getData().add(new XYChart.Data(s.getKey(), s.getValue()));
        }
        barStats.getData().add(series);
        //make piechart
        Double mobile;
        if(stats.getBigliettiVenduti()!=0)
            mobile = ((double)stats.getBigliettiMobile() / (double)stats.getBigliettiVenduti()) * 100.0;
        else
            mobile = 0.0;

        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(new PieChart.Data("Acquisti da Mobile: "+ mobile +"%", mobile ), new PieChart.Data("Acquisti da PC: "+(100.0-mobile)+"%", 100.0-mobile) );
        pieStats.setData(pieData);
        pieStats.setTitle("Percentuale acquisti");
        //Texts

        ticketSold.setText(ticketSold.getText() + " " + Integer.toString(stats.getBigliettiVenduti()));
        ticketWhole.setText(ticketWhole.getText() + " " + Integer.toString(stats.getBigliettiVenduti() - stats.getRidotti() ) );
        ticketReduced.setText(ticketReduced.getText() + " " + Integer.toString(stats.getRidotti()));
    }
}