public class Valuta {
	private final String valuta;
	private double kurs;
	private final int enhet;
	
	
	public Valuta(String valuta, double kurs, int enhet) {
		this.valuta = valuta;
		this.kurs = kurs;
		this.enhet = enhet;
	}
	public String getValuta() {
		return valuta;
	}
	public double getKurs() {
		return kurs;
	}
	public int getEnhet() {
		return enhet;
	}
	public void setKurs(double kurs) {
		this.kurs = kurs;
	}
	
}
