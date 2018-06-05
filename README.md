 # EventManager

![UniNa](http://www.organizzazione.unina.it/immagini/logo-Federico-II.jpg)

Il progetto qui riportato è sviluppato per l'esame di Ingegneria del Software del CdL triennale in Informatica tenutosi a cavallo tra il 2017 e il 2018 presso l'università degli studi di Napoli Federico II.

Il progetto richiedeva lo sviluppo di um software per la gestione di eventi (quali ad esempio concerti, conferenze, mostre ecc...) tramite i seguenti passaggi:

- Analisi e Specifica dei requisiti mediante notazione UML/Cockburn/OCL e Mock-up 
- Definizione dell’architettura e progettazione del sistema, mediante notazione UML 
- Implementazione della proposta progettuale in un linguaggio Object-Oriented 
- Definizione di un piano di testing e di Test automatici con xUnit

## Analisi e specifica dei requisiti

Durante il seguente passaggio si sono raccolte le richieste del committente, tramite interversite, che sono state successivamente analizzate ed utilizzate per la generazione di un diagramma dei casi d'uso accompaganti ognuno dal proprio modello di Cockburn, riportando anche eventuali variazioni e casi d'errore, e dal proprio Mockup.

Le funzionalità del software da analizzare e impletentare erano:

- Gestione degli eventi da parte dei dipendenti
  - Aggiunta di un evento
  - Modifica di un evento
  - Eliminazione di un evento
- Generazione di statistiche relative ad uno o più eventi da parte dei dipendenti
- Validazione dell'ingresso (tramite scansionamento del codice QR del biglietto) da parte di un addetto alla security

Ogni caso d'uso è stato poi utilizzato per l'individuazione degli oggetti utilizzati per quella interazione e usati per costruire il **class diagram** relativo secondo il three-object-type, distinguendo gli oggetti in *boundary*, *control* ed *entity*. Contemporaneamente alla generazione del class diagram è stata affiancata la stesura del relativo **sequence diagram** raffigurante il caso di successo dell'operazione.

## Design del Sistema

Partendo dai class e sequence diagram della fase precedenti, usati come "contratto" col committente del progetto e quindi scritti in un linguaggio non tecnico e con un certo livello di astrazione, si sono sviluppati i class e sequence diagram di design cercanco di far collimare più cose possibili con funzionalità già messe a disposizione dal linguaggio (JAVA).
Per Android, molte delle funzionalità pensate, sono state accorpate in primitive quali *onCreate()* e *onStart* per le activity e *doInBackground* e *onPostExecute* per i task che richiedevano connessione ad internet e quindi risultavano essere bloccanti per la UI.
Per Java invece si è sviluppato tutto il lato di interfaccie tramite JavaFX e tutte le azione sule interfacce sono state svolte tramite primite sui loro riferimenti.


## Implementazione

Una volta finito il Design del sistema si è passati all'implementazione del software seguendo tutti i diagrammi generati nella fase precedente.

In questa fase ci siamo accorti di alcuni errori o dimenticanze dovuti ad una fase di progettazione e design non perfetta e ci siamo resi conto di quanto aggiustare una cosa mentre si sta già implementando il software possa essere dispendioso.

## Testing

L'ultima cosa rimanente da compiere era il testing dell'applicativo. Il committente ha specificato che dovevamo stilare un piano di testing di una sola classe e, di questa, testare effettivamemte solo due metodi.
