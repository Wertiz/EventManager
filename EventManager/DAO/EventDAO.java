package EventManager.DAO;



import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import EventManager.Entity.Evento;


public class EventDAO {
    private static final String DEFAULT_URL = "jdbc:oracle:thin:@eventmanager.c4cx3tohvogp.eu-west-3.rds.amazonaws.com:1521:evntmngr";
    private static final String DEFAULT_USER = "Manager";
    private static final String DEFAULT_PASSWORD ="eventManagerPassword";
    private static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private Connection conn = null;


    public EventDAO(){
        try {
            Class.forName(DEFAULT_DRIVER);
            conn = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            conn=null;
        }

    }
    public Evento getEvent(String code) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EVENTI WHERE ID_EVENTO=" + code);
        ResultSet rs = stmt.executeQuery();
        if(!rs.first())
            return null;
        Evento e = new Evento(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),
                rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9), rs.getInt(10),
                rs.getDate(11), rs.getString(12), rs.getDate(13), rs.getString(14), rs.getInt(15), rs.getBlob(16), rs.getString(17));
        return e;
    }
    public Evento getEvent(ResultSet rs) throws SQLException{
        Evento e = new Evento(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),
                rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9), rs.getInt(10),
                rs.getDate(11), rs.getString(12), rs.getDate(13), rs.getString(14), rs.getInt(15), rs.getBlob(16), rs.getString(17));
        return e;
    }
    public ArrayList<Evento> getAllEvents(){
        ArrayList<Evento> eventi = new ArrayList<Evento>();
        final String query = "SELECT * FROM EVENTI";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                eventi.add(new EventDAO().getEvent(rs));
            }
            return eventi;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void addEvent(Evento e) throws SQLException {
        String dataInizio = new SimpleDateFormat("yyyy-MM-dd").format(e.getInizio());
        dataInizio = "TO_DATE('"+ dataInizio +" 00:00:00', 'YYYY-MM-DD HH24:MI:SS')";
        String dataFine;
        if(e.getFine() != null) {
            dataFine = new SimpleDateFormat("yyyy-MM-dd").format(e.getFine());
            dataFine =  "TO_DATE('"+ dataFine +" 00:00:00', 'YYYY-MM-DD HH24:MI:SS')";
        } else {
            dataFine = "NULL";
        }
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO EVENTI (ID_EVENTO, NOMEEVENTO, ORGANIZZATORE, NAZIONE, REGIONE, PROVINCIA, INDIRIZZO, NUMEROCIVICO, STRUTTURA, CAPIENZA, DATAINIZIO, ORAINIZIO, DATAFINE, ORAFINE, COSTO, IMMAGINE, TIPOEVENTO) "+
                        "VALUES('"+e.getIdEvento()+"', '"+e.getNome()+"', '"+e.getOrganizzatore()+"', '"+e.getNazione()+"', '"+e.getRegione()+"', '"+e.getProvincia()+
                        "', '"+ e.getIndirizzo()+"', '"+ e.getCivico() +"', '"+ e.getStruttura() +"', '"+ e.getCapienza() +"', "+dataInizio+", '"+e.getOraInizio() +
                        "', "+ dataFine +", '"+e.getOraFine() +"', '"+ e.getCosto()+"', ?, '"+ e.getTipo()+"')");
        stmt.setBytes(1, e.getImg().getBytes(1, (int)e.getImg().length()));
        ResultSet rs = stmt.executeQuery();
    }

    public void updateEvent(Evento e) throws SQLException{
        String dataInizio = new SimpleDateFormat("yyyy-MM-dd").format(e.getInizio());
        dataInizio = "TO_DATE('"+ dataInizio +" 00:00:00', 'YYYY-MM-DD HH24:MI:SS')";
        String dataFine;
        if(e.getFine() != null) {
            dataFine = new SimpleDateFormat("yyyy-MM-dd").format(e.getFine());
            dataFine =  "TO_DATE('"+ dataFine +" 00:00:00', 'YYYY-MM-DD HH24:MI:SS')";
        } else {
            dataFine = "NULL";
        }
        PreparedStatement stmt = conn.prepareStatement("UPDATE EVENTI SET NOMEEVENTO = '"+e.getNome()+"', ORGANIZZATORE = '"+e.getOrganizzatore()+"', REGIONE = '"+e.getRegione()+"', PROVINCIA = '"+e.getProvincia()+
                        "', INDIRIZZO = '"+e.getIndirizzo()+"', NUMEROCIVICO = '"+e.getCivico()+"', STRUTTURA = '"+e.getStruttura()+"', CAPIENZA = '"+e.getCapienza()+
                        "', DATAINIZIO = "+dataInizio+", ORAINIZIO = '"+e.getOraInizio()+
                        "', DATAFINE = "+dataFine+", ORAFINE = '"+e.getOraFine()+"', COSTO = '"+e.getCosto()+"', IMMAGINE = ?,"+
                        " TIPOEVENTO = '"+e.getTipo()+"' WHERE ID_EVENTO = '"+e.getIdEvento()+"'");
        stmt.setBytes(1, e.getImg().getBytes(1, (int)e.getImg().length()));
        ResultSet rs = stmt.executeQuery();
    }
    public void deleteEvent(int idEvento) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM EVENTI WHERE ID_EVENTO=" + idEvento);
        ResultSet rs = stmt.executeQuery();
    }
}


