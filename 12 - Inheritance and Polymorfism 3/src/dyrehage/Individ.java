package dyrehage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Individ extends Dyr implements SkandinaviskeRovdyr{
	String navn;
	private LocalDate fDato;
	boolean hanndyr;
	boolean farlig;
	
	public Individ(String navn, LocalDate fDato, boolean hanndyr, boolean farlig, String norskNavn, String latNavn, String latFamilie, LocalDate ankommetDato, String adresse) {
		super(norskNavn, latNavn, latFamilie, ankommetDato, adresse);
		this.navn = navn;
		this.fDato = fDato;
		this.hanndyr = hanndyr;
		this.farlig = farlig;
	}
	public String getNavn() {
		return navn;
	}
	public LocalDate getFdato() {
		return fDato;
	}
	public int getAlder() {
		long alder = ChronoUnit.YEARS.between(fDato, LocalDate.now());
		return (int)alder;
	}
	public boolean getHanndyr() {
		return hanndyr;
	}
	public boolean getFarlig() {
		return farlig;
	}
	public void flytt(String nyAdresse) {
		super.setAdresse(nyAdresse);
	}
	public String skrivUtInfo() {
		return this.toString();
	}
	
	public String toString() {
	    return super.toString() + "\nIndivid Navn: " + getNavn() + "\nFødselsdato: " + getFdato() + "\nHanndyr? " + getHanndyr() + "\nfarlig? " + getFarlig();
	}
}
