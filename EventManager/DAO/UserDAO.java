package EventManager.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {
    private static final String DEFAULT_URL = "jdbc:oracle:thin:@eventmanager.c4cx3tohvogp.eu-west-3.rds.amazonaws.com:1521:evntmngr";
    private static final String DEFAULT_USER = "Manager";
    private static final String DEFAULT_PASSWORD = "eventManagerPassword";
    private static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private Connection conn = null;


    public UserDAO() {
        try {
            Class.forName(DEFAULT_DRIVER);
            conn = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            conn = null;
        }

    }

    public boolean checkCredentials(String username, String password) {
        Statement stmt = null;
        String query = "SELECT USERNAME FROM UTENTI WHERE USERNAME='" + username + "' AND PASSWORD='" + password + "'";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
