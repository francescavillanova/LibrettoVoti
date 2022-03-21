package it.polito.tdp.librettovoti;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.librettovoti.model.Libretto;
import it.polito.tdp.librettovoti.model.Voto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label; //importo quello che c'è mostrato su SceneBuilder e non a caso perchè esistono più tipi di Label

public class FXMLController {
	
	private Libretto model; //perchè Controller possa lavorare con un oggetto model
	                        //senza che io debba scrivere =new Libretto(), cosa che
	                        //non posso fare qui, devo mettere nel main (EntryPoint)
	                        //il richiamo a setModel

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> cmbPunti;

    @FXML
    private TextField txtNome;
    
    @FXML
    private Label txtStatus;

    @FXML
    private TextArea txtVoti;

    @FXML
    void handleNuovoVoto(ActionEvent event) {
    	
    	// 1) acquisizione e controllo dati
    	String nome=txtNome.getText();
    	Integer punti=cmbPunti.getValue();  //getValue mi restituisce i valori della comboBox, se non si seleziona nulla restituisce null (per questo Integer)
    	
    	if(nome.equals("") || punti==null) {
    		//errore, non posso eseguire l'operazione
    		txtStatus.setText("ERRORE: occorre inserire nome e voto\n");
    		return;  //return esce dal metodo, se i dati non vanno bene si deve ricominciare
    	}
    	
    	// 2) esecuzione dell'operazione (chiedere al model di farla)
    	boolean ok=model.add(new Voto(nome, punti)); //dovrò importare la classe Voto
    	
      	// 3) visualizzazione/aggiornamento del risultato (la forma in cui visualizzare i dati è in mano al controller)
    	if(ok) {     //significa se ok=true
    	   List<Voto> voti=model.getVoti(); 
    	   txtVoti.clear();
    	   txtVoti.appendText("Hai superato "+voti.size()+" esami\n");
    	   for(Voto v: voti) {
    		  txtVoti.appendText(v.toString()+"\n");
    	   }
    	        //una volta registrato il voto correttamente pulisco le caselle di input
    	   txtNome.clear();
    	   cmbPunti.setValue(null); //la comboBox non ha il metodo clear, quindi faccio così
    	   txtStatus.setText(""); //non ha il metodo clear perchè è una label
      		
    	}else 
    		txtStatus.setText("ERRORE: esame già presente");
    	
    }	
    
    public void setModel(Libretto model) {
    	this.model=model;
    	
    	//questa parte mi permette di impostare fin dall'inizio i voti nella casella di testo
    	List<Voto> voti=model.getVoti(); 
 	   txtVoti.clear();
 	   txtVoti.appendText("Hai superato "+voti.size()+" esami\n");
 	   for(Voto v: voti) {
 		  txtVoti.appendText(v.toString()+"\n");
 	   }
    }

    @FXML
    void initialize() {  //posso fare tutte le operazioni iniziali prima che venga creato il modello 
        assert cmbPunti != null : "fx:id=\"cmbPunti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtVoti != null : "fx:id=\"txtVoti\" was not injected: check your FXML file 'Scene.fxml'.";
        
        cmbPunti.getItems().clear();
        for(int p=18; p<=30; p++) {
        	cmbPunti.getItems().add(p);
        }
        //qui non posso richiamare in modello

    }

}
