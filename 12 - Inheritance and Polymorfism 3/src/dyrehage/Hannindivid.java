package dyrehage;

import java.time.LocalDate;

public class Hannindivid extends Individ{
	public Hannindivid(String navn, LocalDate fDato, boolean farlig, String norskNavn, String latNavn, String latFamilie, LocalDate ankommetDato, String adresse) {
		super(navn, fDato, true, farlig, norskNavn, latNavn, latFamilie, ankommetDato, adresse);
	}
	
	public int getAntKull() {
		return 0;
	}
	public void leggTilNyttKull(){
	}
	public void leggTilKull(int antKull){
	}
}
