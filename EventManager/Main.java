package EventManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage login) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Boundary/loginView.fxml"));
        login.setResizable(false);
        login.getIcons().add(new Image(getClass().getResourceAsStream("Logo.png")));
        login.setTitle("Event Manager");
        login.setScene(new Scene(root, 800, 600));
        login.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
