package dyrehage;

import java.time.LocalDate;

public class Hunnindivid extends Individ{
	private int antKull;
	public Hunnindivid(String navn, LocalDate fDato, boolean farlig, String norskNavn, String latNavn, String latFamilie, LocalDate ankommetDato, String adresse, int antKull) {
		super(navn, fDato, false, farlig, norskNavn, latNavn, latFamilie, ankommetDato, adresse);
		this.antKull = antKull;
	}
	public int getAntKull() {
		return antKull;
	}
	public void leggTilNyttKull(){
		this.antKull++;
	}
	public void leggTilKull(int antKull){
		this.antKull += antKull;
	}
	public String toString() {
		return super.toString() + "\nAntall Kull " + getAntKull();
	}
}
