package EventManager.Entity;

public class SingletonEvento {
    private static SingletonEvento instance = new SingletonEvento(); // Quando la classe è caricata crea l'istanza
    private Evento e = new Evento();

    private SingletonEvento(){}

    public void inizializza(Evento e){
        if(e != null)
            this.e = e;
        else
            this.e = null;
    }

    public Evento ottieniEvento() {
        return e;
    }

    public static SingletonEvento getInstance(){
        return instance;  // Restituirà o l'istanza appena creata (se è la prima volta che lo chiami dopo aver caricato la classe)
    }                    // oppure l'istanza dopo aver inizializzato il singleton con l'evento quindi che ti sreve

}
