package dyrehage;

import java.time.LocalDate;

public class Fugleflokk extends Dyregruppe{
	private boolean svømmer;
	private double gjennomsnittligVekt;
	
	public Fugleflokk(double vekt, boolean svømmer, String gruppenavn, int antall, String norskNavn, String latNavn, String latFamilie, LocalDate ankommetDato, String adresse){
		super(gruppenavn, antall, norskNavn, latNavn, latFamilie, ankommetDato, adresse);
		this.svømmer = svømmer;
		this.gjennomsnittligVekt = vekt;
	}
	public boolean getSvømmer() {
		return svømmer;
	}
	public double getGjennomsnittligVekt() {
		return gjennomsnittligVekt;
	}
	
	
	public String toString() {
		return super.toString() + "\nGjennomsnittlig Vekt: " + getGjennomsnittligVekt() + "\nKan svømme: " + getSvømmer();
	}
	
}
