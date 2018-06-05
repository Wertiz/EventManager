package EventManager.Entity;

import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.Date;

public class Evento {

    private int idEvento;
    private String nome;
    private String organizzatore;
    private String nazione;
    private String regione;
    private String provincia;
    private String indirizzo;
    private String civico;
    private String struttura;
    private int capienza;
    private Date inizio;
    private Date fine;
    private String oraInizio;
    private String oraFine;
    private float costo;
    private Blob img;
    private String tipo;

    @Override
    public String toString() {
        return "Evento{" +
                "idEvento=" + idEvento +
                ", nome='" + nome + '\'' +
                ", organizzatore='" + organizzatore + '\'' +
                ", nazione='" + nazione + '\'' +
                ", regione='" + regione + '\'' +
                ", provincia='" + provincia + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", civico='" + civico + '\'' +
                ", struttura='" + struttura + '\'' +
                ", capienza=" + capienza +
                ", inizio=" + inizio +
                ", fine=" + fine +
                ", oraInizio='" + oraInizio + '\'' +
                ", oraFine='" + oraFine + '\'' +
                ", costo=" + costo +
                ", img=" + img +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    public Evento(){
        nome = null;
        struttura = null;
        inizio = null;
        tipo=null;
        img=null;
        idEvento=0;
        organizzatore=null;
        nazione=null;
        regione=null;
        indirizzo=null;
        civico=null;
        capienza=0;
        fine =null;
        costo=0;
    }

    public Evento(int idEvento, String nome, String organizzatore, String nazione, String regione, String provincia, String indirizzo, String civico, String struttura, int capienza, Date inizio, String oraInizio, Date fine,  String oraFine, float costo, Blob img,String tipo){
        this.idEvento = idEvento;
        this.nome = nome;
        this.organizzatore = organizzatore;
        this.nazione = nazione;
        this.regione = regione;
        this.provincia = provincia;
        this.indirizzo = indirizzo;
        this.civico = civico;
        this.struttura = struttura;
        this.capienza = capienza;
        this.inizio = inizio;
        this.fine = fine;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.costo = costo;
        this.tipo = tipo;
        this.img = img;

    }
    public Evento updateEvento(String nome, String organizzatore, String nazione, String regione, String provincia, String indirizzo, String civico, String struttura, int capienza, Date inizio, String oraInizio, Date fine,  String oraFine, float costo, Blob img,String tipo){
        this.nome = nome;
        this.organizzatore = organizzatore;
        this.nazione = nazione;
        this.regione = regione;
        this.provincia = provincia;
        this.indirizzo = indirizzo;
        this.civico = civico;
        this.struttura = struttura;
        this.capienza = capienza;
        this.inizio = inizio;
        this.fine = fine;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.costo = costo;
        this.tipo = tipo;
        this.img = img;
        return this;
    }
    public String getProvincia(){ return provincia; }
    public String getNome(){
        return nome;
    }

    public String getStruttura() {
        return struttura;
    }

    public Blob getImg(){
        return img;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapienza() {
        return capienza;
    }

    public float getCosto() {
        return costo;
    }

    public String getCivico() {
        return civico;
    }

    public Date getInizio() {
        return inizio;
    }

    public Date getFine() {
        return fine;
    }

    public String getOraInizio(){ return oraInizio; }

    public String getOraFine(){ return oraFine; }

    public int getIdEvento() {
        return idEvento;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getNazione() {
        return nazione;
    }

    public String getOrganizzatore() {
        return organizzatore;
    }

    public String getRegione() {
        return regione;
    }

    public void setProvincia(String provincia){ this.provincia = provincia;
    }
    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public void setInizio(Date inizio){ this.inizio = inizio; }

    public void setFine(Date fine) {
        this.fine = fine;
    }

    public void setOraInizio(String oraInizio){ this.oraInizio = oraInizio; }

    public void setOraFine(String oraFine) { this.oraFine = oraFine; }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public void setOrganizzatore(String organizzatore) {
        this.organizzatore = organizzatore;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public void setStruttura(String struttura) {
        this.struttura = struttura;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setImg(Blob img) { this.img = img; }
}
