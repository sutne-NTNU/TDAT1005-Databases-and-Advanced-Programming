import java.util.ArrayList;

public class ValutaTabell {
	private ArrayList<Valuta> valutaliste;
	
	ValutaTabell(){
		valutaliste = new ArrayList<Valuta>(); 
		valutaliste.add(new Valuta("Euro", 8.10, 1));
		valutaliste.add(new Valuta("US Dollar", 6.23, 1)); 
		valutaliste.add(new Valuta("Britiske pund", 12.27, 1));
		valutaliste.add(new Valuta("Svenske kroner", 88.96, 100));
		valutaliste.add(new Valuta("Danske kroner", 108.75, 100));
		valutaliste.add(new Valuta("Yen", 5.14, 100));
		valutaliste.add(new Valuta("Islandske kroner", 9.16, 100));
		valutaliste.add(new Valuta("Norske kroner", 100, 100));
	}
	public void add(Valuta valuta) {
		valutaliste.add(valuta);
	}

	public double beregn(String fra, String til, double mengde) {
		double fra_kurs = 0;
		int fra_enhet = 0;
		double til_kurs = 0;
		int til_enhet = 0;
		
		for(Valuta valuta : valutaliste) {
			if(fra.equals(valuta.getValuta())) {
				fra_kurs = valuta.getKurs();
				fra_enhet = valuta.getEnhet();
			}
			if(til.equals(valuta.getValuta())) {
				til_kurs = valuta.getKurs();
				til_enhet = valuta.getEnhet();
			}
		}
		double iNorskeKr = (fra_kurs * mengde)/fra_enhet;
		double svar = (iNorskeKr/til_kurs) * til_enhet;
		return svar;
	}
	
	public ArrayList<Valuta> getAllValuta() {
		return valutaliste;
	}
}
