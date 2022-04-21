package dyrehage;

import java.time.LocalDate;

public class Rovdyrfabrikk {
	
	public Rovdyrfabrikk() {
	}
	
	public SkandinaviskeRovdyr nyBinne(String navn, LocalDate fDato, LocalDate ankommetDato, String adresse, int antKull) {
		return new Hunnindivid(navn, fDato, true, "Brunbjørn", "Ursus arctos", "Ursidae", ankommetDato, adresse, antKull);
	}
	public SkandinaviskeRovdyr nyHannbjørn(String navn, LocalDate fDato, LocalDate ankommetDato, String adresse) {
		return new Hannindivid(navn, fDato, true, "Brunbjørn", "Ursus arctos", "Ursidae", ankommetDato, adresse);
	}
	public SkandinaviskeRovdyr nyUlvetispe(String navn, LocalDate fDato, LocalDate ankommetDato, String adresse, int antKull) {
		return new Hunnindivid(navn, fDato, true, "Ulv", "Canis lupus", "Canidae", ankommetDato, adresse, antKull);
	}
	public SkandinaviskeRovdyr nyUlvehann(String navn, LocalDate fDato, LocalDate ankommetDato, String adresse) {
		return new Hannindivid(navn, fDato, true, "Ulv", "Canis lupus", "Canidae", ankommetDato, adresse);
	}
	/*
	 * Tips: Brunbjørnen har artsnavn "Ursus arctos" og familienavn "Ursidae", 
	 * mens ulven har artsnavn "Canis lupus" og familienavn "Canidae". 
	 * Vi antar at alle bjørner og ulver er farlige.
	 */
}
