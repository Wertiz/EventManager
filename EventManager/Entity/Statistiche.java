package EventManager.Entity;

import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Statistiche {
    private int bigliettiVenduti;
    private int ridotti;
    private int bigliettiMobile;
    private Set<Pair<String, Integer>> vendutiInData;
    private Set<String> dates;
    private Pair<String, Integer> pair;
    //private ArrayList<String> dates;
    //private ArrayList<Integer> bought;

    public Statistiche (){
        bigliettiMobile=0;
        bigliettiVenduti=0;
        ridotti=0;
        vendutiInData = new HashSet<Pair<String, Integer>>();
        dates = new TreeSet<String>();
        /*dates = new ArrayList<>();
        bought = new ArrayList<>();*/
    };

    public void addPairDateBuy(String date){
        pair = new Pair<String, Integer>(date,1);
        if(!dates.contains(date)){
            dates.add(date);
            vendutiInData.add(pair);
        } else {
            vendutiInData.remove(pair);
                    pair = new Pair<String, Integer>(date, pair.getValue()+1);
            vendutiInData.add(pair);
        }
    }

    public int getBigliettiMobile() {
        return bigliettiMobile;
    }

    public int getBigliettiVenduti() {
        return bigliettiVenduti;
    }

    public int getRidotti() {
        return ridotti;
    }

    public void setBigliettiMobile(int bigliettiMobile) {
        this.bigliettiMobile = bigliettiMobile;
    }

    public void setBigliettiVenduti(int bigliettiVenduti) {
        this.bigliettiVenduti = bigliettiVenduti;
    }

    public void setRidotti(int ridotti) {
        this.ridotti = ridotti;
    }
    public Set<Pair<String, Integer>> getVendutiInData(){
        return vendutiInData;
    }
    /*public ArrayList<Integer> getBought() {
        return bought;
    }

    public ArrayList<String> getDates() {
        return dates;
    }*/

    public void incrementSold(){
        bigliettiVenduti = bigliettiVenduti + 1;
    }

    public void incrementMobile(){
        bigliettiMobile = bigliettiMobile + 1;
    }

    public void incrementRidotti(){
        ridotti = ridotti + 1;
    }
}
