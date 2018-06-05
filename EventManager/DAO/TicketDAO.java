package EventManager.DAO;



import EventManager.Entity.Ticket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;


public class TicketDAO {
    private static final String DEFAULT_URL = "jdbc:oracle:thin:@eventmanager.c4cx3tohvogp.eu-west-3.rds.amazonaws.com:1521:evntmngr";
    private static final String DEFAULT_USER = "Manager";
    private static final String DEFAULT_PASSWORD ="eventManagerPassword";
    private static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private Connection conn = null;

    public TicketDAO(){
        try {
            Class.forName(DEFAULT_DRIVER);
            conn = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            conn=null;
        }
    }

    public Ticket getTicket(String ticket_id){
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT CODICE, DATAACQUISTO, PIATTAFORMA, RIDOTTO, EVENTO FROM TICKETS WHERE CODICE = " + ticket_id);
            ResultSet rs = stmt.executeQuery();
            if(!rs.first())
                return null;
            return new Ticket(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getInt(4),rs.getString(5));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteTicket(int idEvento) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM TICKETS WHERE EVENTO=" + idEvento);
        ResultSet rs = stmt.executeQuery();
    }
    public boolean checkConn() {
        if(conn!=null)
            return true;
        else
            return false;
    }

    public List<Ticket> getTicketsByEvent(int code){
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM TICKETS WHERE EVENTO = " + code + " ORDER BY DATAACQUISTO ASC");
            ResultSet rs = stmt.executeQuery();
            List<Ticket> tickets = new LinkedList<>();
            while(rs.next()){
                tickets.add(new Ticket(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getInt(4), Integer.toString(rs.getInt(5))) );
            }
            return tickets;
        } catch (SQLException e) {
            System.out.println("Errore in getTicketsByEvent");
            e.printStackTrace();
            return null;
        }
    }
}
