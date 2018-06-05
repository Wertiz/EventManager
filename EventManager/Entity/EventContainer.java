package EventManager.Entity;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

public class EventContainer {
    private Rectangle rect;
    private Pane pane;

    private Evento evento;
    byte byteImage[];
    private ImageView img;

    private ImageView pencil;
    private ImageView stats;
    private ImageView trashBin;

    private Text nomeEvento;
    private Text luogo;
    private Text dataOra;
    private Text tipoEvento;

    private final int XSIZE = 250;
    private final int YSIZE = 100;

    public EventContainer(Evento e, int layoutX, int layoutY) throws SQLException{
        this.evento = e;
        rect = new Rectangle(XSIZE,YSIZE);
        pane = new Pane();
        pane.setPrefSize(XSIZE, YSIZE);
        pane.setLayoutX(layoutX);
        pane.setLayoutY(layoutY);
        pane.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t){
                    pane.setStyle("-fx-background-color:#efcd7c; -fx-border-color: #f4a850;");
                    pencil.setVisible(true);
                    stats.setVisible(true);
                    trashBin.setVisible(true);
            }
        });
        pane.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t){
                pane.setStyle("-fx-background-color:transparent;");
                pencil.setVisible(false);
                stats.setVisible(false);
                trashBin.setVisible(false);
            }
        });
        pane.setClip(rect);

        loadImages(evento);
        loadText(evento);


        pane.getChildren().add(img);
        pane.getChildren().add(pencil);
        pane.getChildren().add(stats);
        pane.getChildren().add(trashBin);
        pane.getChildren().add(nomeEvento);
        pane.getChildren().add(luogo);
        pane.getChildren().add(dataOra);
        pane.getChildren().add(tipoEvento);


    }
    private void loadImages(Evento e) throws SQLException{
        img = new ImageView();
        pencil = new ImageView();
        stats = new ImageView();
        trashBin = new ImageView();

        try {
            byteImage = e.getImg().getBytes(1, (int) e.getImg().length());
        } catch(Exception ex){

        }
        if(byteImage!=null) {

            img.setImage(new Image(new ByteArrayInputStream(byteImage)));
            img.setFitHeight(75);
            img.setFitWidth(75);
            img.setLayoutX(14);
            img.setLayoutY(15);
        }

        pencil.setImage(new Image(getClass().getResourceAsStream("../pencil.png")));
        pencil.setFitHeight(25);
        pencil.setFitWidth(25);
        pencil.setLayoutX(155);
        pencil.setLayoutY(65);

        pencil.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t){

                pencil.setFitWidth(30);
                pencil.setFitHeight(30);
            }
        });
        pencil.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t){
                pencil.setFitWidth(25);
                pencil.setFitHeight(25);
            }
        });

        stats.setImage(new Image(getClass().getResourceAsStream("../stats.png")));
        stats.setFitHeight(25);
        stats.setFitWidth(25);
        stats.setLayoutX(185);
        stats.setLayoutY(65);

        stats.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t){
                stats.setFitWidth(30);
                stats.setFitHeight(30);
            }
        });
        stats.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t){
                stats.setFitWidth(25);
                stats.setFitHeight(25);
            }
        });

        trashBin.setImage(new Image(getClass().getResourceAsStream("../trash.png")));
        trashBin.setFitHeight(25);
        trashBin.setFitWidth(25);
        trashBin.setLayoutX(215);
        trashBin.setLayoutY(65);
        trashBin.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t){
                trashBin.setFitWidth(30);
                trashBin.setFitHeight(30);

            }
        });
        trashBin.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t){
                trashBin.setFitWidth(25);
                trashBin.setFitHeight(25);

            }
        });

        trashBin.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t){


            }
        });
        pencil.setVisible(false);
        stats.setVisible(false);
        trashBin.setVisible(false);

    }

    private void loadText(Evento e){
        nomeEvento = new Text();

        nomeEvento.setStyle("-fx-font-size: 12pt");
        nomeEvento.setLayoutX(99);
        nomeEvento.setLayoutY(28);
        nomeEvento.setText(e.getNome());

        luogo = new Text();
        luogo.setStyle("-fx-font-size: 8pt");
        luogo.setLayoutX(99);
        luogo.setLayoutY(46);
        luogo.setText(e.getProvincia() + " " + e.getIndirizzo());

        dataOra = new Text();
        dataOra.setStyle("-fx-font-size: 8pt");
        dataOra.setLayoutX(99);
        dataOra.setLayoutY(63);
        dataOra.setText(e.getInizio() + " " + e.getOraInizio());

        tipoEvento = new Text();
        tipoEvento.setStyle("-fx-font-size: 8pt");
        tipoEvento.setLayoutX(99);
        tipoEvento.setLayoutY(80);
        tipoEvento.setText( e.getTipo());
    }

    public void removeChildrens(){
        pane.getChildren().remove(img);
        pane.getChildren().remove(pencil);
        pane.getChildren().remove(trashBin);
        pane.getChildren().remove(stats);
        pane.getChildren().remove(nomeEvento);
        pane.getChildren().remove(luogo);
        pane.getChildren().remove(tipoEvento);
        pane.getChildren().remove(dataOra);
    }
    public Pane getPane() {
        return pane;
    }

    public ImageView getTrashBin() {
        return trashBin;
    }

    public ImageView getPencil() {
        return pencil;
    }

    public ImageView getStats() {
        return stats;
    }
    public String getNomeEvento(){
        return nomeEvento.getText();
    }
    public Evento getEvento() {
        return evento;
    }
}
