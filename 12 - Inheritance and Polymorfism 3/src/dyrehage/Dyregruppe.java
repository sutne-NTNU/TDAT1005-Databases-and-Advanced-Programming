package dyrehage;

import java.time.LocalDate;

public class Dyregruppe extends Dyr{
	private String gruppenavn;
	private int antIndivider;
	
	public Dyregruppe(String gruppenavn, int antall, String norskNavn, String latNavn, String latFamilie, LocalDate ankommetDato, String adresse) {
		super(norskNavn, latNavn, latFamilie, ankommetDato, adresse);
		this.gruppenavn = gruppenavn;
		this.antIndivider = antall;
	}
	public int getAntIndivider() {
		return antIndivider;
	}
	public void setAntIndivider(int antIndivider) {
		this.antIndivider = antIndivider;
	}
	public String getGruppenavn() {
		return gruppenavn;
	}

	
	@Override
	public final String getNorskNavn(){
	    return "gruppe av " + super.getNorskNavn();
	}
	public String toString() {
		return super.toString() + "\nGruppenavn: " + getGruppenavn() + "\nAntall Individer: " + getAntIndivider();
	}
}
