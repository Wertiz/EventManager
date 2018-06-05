package EventManager.Controller;

import EventManager.DAO.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    private UserDAO login;

    private Stage gestioneEventi;
    @FXML TextField username;
    @FXML TextField password;
    @FXML Button loginButton;
    @FXML Text wrongCredentials;

    @FXML private void enableLogin(){
        if(password.getLength() > 0 && username.getLength() > 0) {
            loginButton.setDisable(false);
        }
        else {
            loginButton.setDisable(true);
        }
    }
    @FXML private void checkCredentials() {
        try{
            Parent root;
            if(login.checkCredentials(username.getText(), password.getText())){
               //imposta il nuovo stage
               gestioneEventi = (Stage) loginButton.getScene().getWindow();
               root = FXMLLoader.load(getClass().getResource("../Boundary/gestioneEventiView.fxml"));
               gestioneEventi.setMinHeight(600);
               gestioneEventi.setMinWidth(800);
               gestioneEventi.getIcons().add(new Image(getClass().getResourceAsStream("../Logo.png")));
               gestioneEventi.setTitle("Event Manager");
               gestioneEventi.setScene(new Scene(root, 800, 600));
               gestioneEventi.show();
            } else {
               wrongCredentials.setVisible(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        login = new UserDAO();
        loginButton.setDisable(true);
        wrongCredentials.setVisible(false);
    }
}
