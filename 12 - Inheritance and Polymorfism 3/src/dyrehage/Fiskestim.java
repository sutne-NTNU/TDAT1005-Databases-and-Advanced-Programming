package dyrehage;

import java.time.LocalDate;

public class Fiskestim extends Dyregruppe{
	private boolean kanDeleAkvarium;
	private double gjennomsnittligLengde;
	
	public Fiskestim(double lengde, boolean kanDeleAkvarium, String gruppenavn, int antall, String norskNavn, String latNavn, String latFamilie, LocalDate ankommetDato, String adresse){
		super(gruppenavn, antall, norskNavn, latNavn, latFamilie, ankommetDato, adresse);
		this.kanDeleAkvarium = kanDeleAkvarium;
		this.gjennomsnittligLengde = lengde;
	}
	public double getGjennomsnittligLengde() {
		return this.gjennomsnittligLengde;
	}
	public boolean getKanDeleAkvarium() {
		return kanDeleAkvarium;
	}
	
	
	public String toString() {
	    return super.toString() + "\nGjennomsnittlig Lengde: " + getGjennomsnittligLengde() + "\nKan dele Akvarium: " + getKanDeleAkvarium();
	}
	
}
