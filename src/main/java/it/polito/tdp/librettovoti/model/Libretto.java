package it.polito.tdp.librettovoti.model;

import java.util.ArrayList;
import java.util.List; //lista generica

import it.polito.tdp.librettovotidb.LibrettoDAO;

public class Libretto {

	
	private List<Voto> voti;

	public Libretto() {
	}
	
	public boolean add(Voto v) { 
		LibrettoDAO dao=new LibrettoDAO();
		boolean result=dao.creaVoto(v);
		return result;		
	}
	
	public List<Voto> getVoti(){  
		LibrettoDAO dao=new LibrettoDAO();
		return dao.readAllVoto();
	}
	
	public String toString() {
		return this.voti.toString() ; //dato che riguarda la variabile voto, anche la classe Voto ha bisogn del toString
	}
	
	public Libretto filtraPunti(int punti) {   //creo un libretto contenente i voti 25
		Libretto result = new Libretto() ;
		for(Voto v: this.voti) {
			if(v.getPunti()==punti) {
				result.add(v);
			}
		}
		return result ;
	}
	
	public Integer puntiEsame(String nome) {
		for(Voto v: this.voti) {
			if( v.getNome().equals(nome) ) {
				return v.getPunti() ;
			}
		}
//		return -1;  devo ritornare un valore che non può essere confuso con un voto, ritornerei questo se il metodo ritornasse un int
		return null ;  //è meglio di -1 ma il metodo dovrà ritornare Integer al posto di int
//		throw new IllegalArgumentException("Corso non trovato") ;  oppure scateno un'eccezione se il valore è errato
	}

	/*
	  Restituisce il punteggio ottenuto all'esame di cui 
	  specifico il nome
	  return punteggio numerico, oppure null se l'esame non esiste
	 */
	
	public boolean isDuplicato(Voto v) {
		for(Voto v1: this.voti) {
			if(v1.equals(v))  //per poterlo fare la classe Voto deve avere equals49
				return true ;
		}
		return false ;
	}
	
	
	public boolean isConflitto(Voto v) {
		Integer punti = this.puntiEsame(v.getNome()) ;
		if (punti != null && punti != v.getPunti())
			return true;
		else
			return false;
	}
	
	
	
	
	public Libretto votiMigliorati() {
		Libretto nuovo=new Libretto();
		for(Voto v: this.voti) {
			int punti=v.getPunti();
			if(punti>=24)
				punti+=2;
			else             //sono tutti >=18 altrimenti non sarebbero nel libretto
				punti++;
			if(punti>30)
				punti=30;
			
			nuovo.add(new Voto(v.getNome(), punti, v.getData()));
			
			//al posto di add new Voto avrei potuto scrivere:
			// v.setPunti(punti);
			// nuovo.add(v);
			//ma non va bene perchè così ho modificato il voto, quindi anche quello che va nel libretto originale
		}
		return nuovo;
	}
	
	public void cancellaVotiMinori(int punti) {  //non metto direttamente minori di 24 ma faccio un metodo generale
		for(Voto v: this.voti) {
			if(v.getPunti()>punti)
				this.voti.remove(v);  //voto ha il metodo equals quindi va bene
		}
	}
}
