package EventManager.Entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {

    private String codice;
    private String dataAcquisto;
    private String piattaforma;
    private boolean ridotto;
    private String codiceEvento;

    public Ticket(String c, Date da, String p, int r, String ce){
        codice = c;
        dataAcquisto = new SimpleDateFormat("dd/MM/yyyy").format(da);
        piattaforma = p;
        if(r==1)
            ridotto = true;
        else
            ridotto=false;
        codiceEvento = ce;
    }

    public Ticket(){
        codice = null;
        dataAcquisto = null;
        piattaforma = null;
        ridotto = false;
        codiceEvento = null;
    }

    public String getCodice() {
        return codice;
    }

    public String getCodiceEvento() {
        return codiceEvento;
    }

    public boolean isRidotto() {
        return ridotto;
    }

    public String getDataAcquisto() {
        return dataAcquisto;
    }

    public String getPiattaforma() {
        return piattaforma;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public void setCodiceEvento(String codiceEvento) {
        this.codiceEvento = codiceEvento;
    }

    public void setDataAcquisto(String dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public void setPiattaforma(String piattaforma) {
        this.piattaforma = piattaforma;
    }

    public void setRidotto(boolean ridotto) {
        this.ridotto = ridotto;
    }
}
