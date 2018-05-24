# Compiti da svolgere

## Analisi dei Requisiti

### Modello Funzionale
- [x] Disegnare il diagramma dei casi d'uso
- [x] ~~Far revisionare il diagramma dei casi d'uso~~
- [x] Disegnare i Mockup
- [x] Far revisionare i Mockup
- [x] Scrivere il Cockburn della visualizzazione statistiche
- [x] Scrivere il Cockburn dell'aggiunta di un evento
- [x] Scrivere il Cockburn della rimozione di un evento
- [x] Scrivere il Cockburn della modifica di un evento
- [x] Scrivere il Cockburn della validazione di un codice QR **killtheprocess23**
- [ ] Scrivere il diagramma di Gantt

### Modelli di Dominio
- [x] Identificare oggetti in visualizzazione statistiche
- [x] Disegnare il Sequence Diagram della visualizzazione statistiche
- [x] Identificare oggetti in Aggiungere Evento
- [x] Disegnare il Sequence Diagram dell'aggiunta di un evento
- [x] Identificare oggetti in Rimuovi Evento **killtheprocess23**
- [x] Disegnare il Sequence Diagram della rimozione di un evento **killtheprocess23**
- [x] Identificare oggetti in Modifica Evento **killtheprocess23**
- [x] Disegnare il Sequence Diagram della modifica di un evento **killtheprocess23**
- [x] Identificare oggetti in validazione codice QR
- [x] Disegnare il Sequence Diagram della validazione di un codice QR
- [ ] Disegnare Statechart della Validazione codice QR **killtheprocess23**
- [x] ~~Disegnare Activity Diagram di Validazione di un codice QR~~
- [ ] **Scrivere il Documento dei Requisiti**

## Design del Sistema

### Applicazione Desktop
- [ ] Realizzare il class Diagram delle Entity **killtheprocess23**
- [ ] Realizzare il class Diagram dei DAO **killtheprocess23**
- [x] Realizzare il class Diagram dei Controller 
- [ ] Realizzare il class Diagram dei Boundary **killtheprocess23**
      
      La boundary della creazione dell'evento deve avere un metodo che restituisce l'evento inserito (es. getNewEvent(): Evento)
      analogamente modify che restituisce l'evento modificato ma anche un metodo che accetta un evento (per popolare i campi)
      Entrambe le boundary devono avere un metodo che informa il chiamante se i campi sono tutti pieni (es. validateForms(): boolean)
      La boundary delle statistiche deve avere un metodo che accetta delle statistiche, per fillare il contenuto (es. fill(s: Statistiche).
      Tutte le boundary dovranno estendere JPanel (mi pare eh) così non devi specificare i metodi show(), setVisible() ecc...
      Le boundary NON devono implementare Observer ecc..., per quello che serve a noi è un po' limitante e quindi lo evitiam
- [ ] Realizzare il Sequence Diagram dell'aggiunta di un evento **illtheprocess23**
- [ ] Realizzare il Sequence Diagram della modifica di un evento **illtheprocess23**
- [ ] Realizzare il Sequence Diagram della rimozione di un evento **illtheprocess23**
- [ ] Realizzare il Sequence Diagram della generazione delle statistiche

### Applicazione Android
- [x] Realizzare il class Diagram delle Activity
- [ ] Realizzare il Sequence Diagram dello scan del QR code 

### Database
- [x] Realizzare il class Diagram del Database

- [ ] **Scrivere il documento di design**
