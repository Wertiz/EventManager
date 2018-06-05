package EventManager.DAO;


import EventManager.Entity.Statistiche;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class StatsDAO {
    private static final String DEFAULT_URL = "jdbc:oracle:thin:@eventmanager.c4cx3tohvogp.eu-west-3.rds.amazonaws.com:1521:evntmngr";
    private static final String DEFAULT_USER = "Manager";
    private static final String DEFAULT_PASSWORD ="eventManagerPassword";
    private static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private Connection conn = null;

    public StatsDAO(){
        try {
            Class.forName(DEFAULT_DRIVER);
            conn = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR STATS DAO CLASSNOTFOUND");
            e.printStackTrace();
            conn=null;
        } catch (SQLException e) {
            System.out.println("ERROR STATS DAO SQL");
            e.printStackTrace();
            conn=null;
        }
    }

    public boolean checkConn(){
        if (conn == null)
            return false;
        else
            return true;
    }

    public Statistiche getStats(String code){
        Statistiche stats = new Statistiche();
        try {
            PreparedStatement stmt = conn.prepareCall("SELECT COUNT(CODICE) FROM TICKETS WHERE EVENTO = " + code);
        } catch (SQLException e) {
            System.out.println("ERRORE IN getStats");
            e.printStackTrace();
        }

        return stats;
    }

}
