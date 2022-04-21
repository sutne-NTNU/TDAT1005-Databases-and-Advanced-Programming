public class Bord {
	private String[] bordliste;
	
	public Bord(int antallBord) {
		this.bordliste = new String[antallBord];
	}
	
	public int getAntallBord() {
		return bordliste.length;
	}
	
	public int antallBordOpptatt() {
		int opptatt = 0;
		for( int i = 0; i < bordliste.length; i++) {
			if(bordliste[i] != null) {
				opptatt++;
			}
		}
		return opptatt;
	}
	
	public int antallBordLedig() {
		int ledig = 0;
		for( int i = 0; i < bordliste.length; i++) {
			if(bordliste[i] == null) {
				ledig++;
			}
		}
		return ledig;
	}
	
	public void frigiBord(int bordNummer) {
		bordliste[bordNummer] = null;
	}
	
	public boolean reserverBord(int antallBord, String gjestbordliste) {
		if(antallBordLedig() >= antallBord) {
			int teller = 0;
			for(int i = 0; i < bordliste.length; i++) {
				if(bordliste[i] == null && teller < antallBord) {
					bordliste[i] = gjestbordliste;
					teller++;
				}
			}
			return true;
		}
		return false;
	}
	
	public int[] reservertAv(String gjestbordliste) {
		int bordReservert = 0;
		for(int i = 0; i < bordliste.length; i++) {
			if(gjestbordliste.equals(bordliste[i])){
				bordReservert++;
			}
		}
		int[] reservertAvPerson = null; //dersom ingen bord er reservert på bordlisteet vil null bli returnert
		if(bordReservert != 0) {
			reservertAvPerson = new int[bordReservert];
			int teller = 0;
			for(int i = 0; i < bordliste.length; i++) {
				if(gjestbordliste.equals(bordliste[i])){
					reservertAvPerson[teller] = i;
					teller++;
				}
			}
		}
		return reservertAvPerson;
	}
	
	public String toString() {
		String streng = "";
		for(int i = 0; i < bordliste.length; i++) {
			streng += bordliste[i] + ", ";
		}
		return streng;
	}
}
