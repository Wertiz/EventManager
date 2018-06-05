package EventManager.Controller;

import EventManager.DAO.EventDAO;
import EventManager.DAO.TicketDAO;
import EventManager.Entity.Evento;
import EventManager.Entity.EventContainer;

import EventManager.Entity.SingletonEvento;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.*;

public class GestioneEventiController implements Initializable {

    private ArrayList<Evento> eventi;
    private ArrayList<EventContainer> container;
    private EventDAO eventDAO;
    private TicketDAO ticketDAO;


    private Button addButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField searchBar;

    private Stage eventCreationStage;
    private Stage eventModifyStage;
    private Stage eventStatsStage;

    //SELEZIONA TUTTI GLI EVENTI PRESENTI NEL DATABASE
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eventi = new ArrayList<Evento>();
        container = new ArrayList<EventContainer>();
        eventDAO = new EventDAO();
        ticketDAO = new TicketDAO();
        addButton = new Button();
        addButton.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: 75px; " +
                        "-fx-min-height: 75px; " +
                        "-fx-max-width: 75px; " +
                        "-fx-max-height: 75px;" +
                        "-fx-font-size: 20pt"
        );
        addButton.setTooltip(new Tooltip("Aggiungi Evento"));
        addButton.setText("+");
        addButton.setLayoutX(706);
        addButton.setLayoutY(505);
        //CARICA TUTTI GLI EVENTI
        try {
            eventi = eventDAO.getAllEvents();
            fillWithEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        anchorPane.getChildren().add(addButton);
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t){
                Parent root;
                try {
                    SingletonEvento sEvento = SingletonEvento.getInstance();
                    eventCreationStage = new Stage();
                    root = FXMLLoader.load(getClass().getResource("../Boundary/addEventView.fxml"));
                    eventCreationStage.setMinHeight(600);
                    eventCreationStage.setMinWidth(800);
                    eventCreationStage.getIcons().add(new Image(getClass().getResourceAsStream("../Logo.png")));
                    eventCreationStage.setTitle("Aggiungi evento");
                    eventCreationStage.setScene(new Scene(root, 800, 600));
                    eventCreationStage.setOnCloseRequest(event -> {
                        sEvento.inizializza(null);
                    });
                    eventCreationStage.initModality(Modality.APPLICATION_MODAL);
                    eventCreationStage.showAndWait();

                    if(sEvento.ottieniEvento() != null){
                        eventi.add(sEvento.ottieniEvento());
                        refresh();
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    @FXML
    private void alphabeticalOrdering(boolean b) {

    }

    @FXML
    private void chronologicalOrder(boolean b) {

    }

    @FXML
    private void searchEvent() {
        for (EventContainer c : container) {
            if (c.getNomeEvento().toLowerCase().indexOf(searchBar.getText()) < 0) {
                c.getPane().setVisible(false);
            } else {
                c.getPane().setVisible(true);
            }
        }
    }

    //RIEMPIE LA FINESTRA CON GLI EVENTI
    private void fillWithEvents() throws SQLException {
        int filled = 1;
        int layoutX = 10;
        int layoutY = 45;
        for (Evento e : eventi) {
            container.add(createEventContainer(e, layoutX, layoutY));
            if (filled != 3) {
                layoutX += 250 + 10;
                filled++;
            } else {
                layoutX = 10;
                layoutY += 100 + 10;
                filled = 1;
            }
        }
    }

    private void delete(int id, Evento e, EventContainer c) {
        try {
            ticketDAO.deleteTicket(id);
            eventDAO.deleteEvent(id);
            eventi.remove(e);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void refresh() {
        for (EventContainer c : container) {
            c.removeChildrens();
            anchorPane.getChildren().remove(c.getPane());
        }
        try {
            fillWithEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private EventContainer createEventContainer(Evento e, int layoutX, int layoutY) throws SQLException {
        EventContainer c = new EventContainer(e, layoutX, layoutY);
        anchorPane.getChildren().add(c.getPane());

        //Modifica evento
        Tooltip.install(c.getPencil(), new Tooltip("Modifica evento"));
        c.getPencil().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Parent root;
                try {
                    SingletonEvento sEvento = SingletonEvento.getInstance();
                    sEvento.inizializza(c.getEvento());
                    eventModifyStage = new Stage();
                    root = FXMLLoader.load(getClass().getResource("../Boundary/modifyEventView.fxml"));
                    eventModifyStage.getIcons().add(new Image(getClass().getResourceAsStream("../Logo.png")));
                    eventModifyStage.setTitle("Modifica evento");
                    eventModifyStage.setScene(new Scene(root, 800, 600));
                    eventModifyStage.setOnCloseRequest(event -> {
                        sEvento.inizializza(null);
                    });
                    eventModifyStage.initModality(Modality.APPLICATION_MODAL);
                    eventModifyStage.showAndWait();
                    refresh();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //Visualizza statistiche
        Tooltip.install(c.getStats(), new Tooltip("Visualizza le statistiche"));
        c.getStats().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Parent root;
                try {
                    SingletonEvento sEvento = SingletonEvento.getInstance();
                    sEvento.inizializza(c.getEvento());
                    eventStatsStage = new Stage();
                    root = FXMLLoader.load(getClass().getResource("../Boundary/eventStatsView.fxml"));
                    eventStatsStage.getIcons().add(new Image(getClass().getResourceAsStream("../Logo.png")));
                    eventStatsStage.setTitle("Statistiche");
                    eventStatsStage.setScene(new Scene(root, 800, 600));
                    eventStatsStage.setOnCloseRequest(event -> {
                        sEvento.inizializza(null);
                    });
                    eventStatsStage.initModality(Modality.APPLICATION_MODAL);
                    eventStatsStage.showAndWait();
                    refresh();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //Elimina evento
        Tooltip.install(c.getTrashBin(), new Tooltip("Elimina evento"));
        c.getTrashBin().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Sicuro di voler eliminare l'evento  " + e.getNome() + "?", ButtonType.YES, ButtonType.CANCEL);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    delete(e.getIdEvento(), e, c);
                    refresh();
                }
            }
        });

        return c;

    }
}