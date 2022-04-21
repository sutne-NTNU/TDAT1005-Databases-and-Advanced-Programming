public class VIP extends Sitte{
	private String[][] tilskuer; // tabellstørrelse: antall rader * antall plasser pr rad
	private final int plasser_per_rad;
	
	public VIP(String tribunenavn, int kapasitet, int rader, int pris) {
		super(tribunenavn, kapasitet, rader, pris);
		this.plasser_per_rad = kapasitet/rader;
		this.tilskuer = new String[rader][plasser_per_rad];
	}
	
	public String[][] getTilskuer() {
		return tilskuer;
	}
	
	@Override
	public int finnAntallSolgteBilletter() {
		 int solgteBilletter = 0;
		 String[][] vipPlasser = ((VIP)this).getTilskuer();
		 for(int i = 0; i < vipPlasser.length; i++) {
			 for( int j = 0; j < vipPlasser[i].length; j++) {
				 if(vipPlasser[i][j] != null) {
					 solgteBilletter++;
				 }
			 }
		 }
		 return solgteBilletter;
	 }
	
	@Override
	public Billett[] kjøpBilletter(int antBilletter){ 
		 return null;
	 }
	
	@Override
	public Billett[] kjøpBilletter(String[] navnKjøpere){
		if(getAntallLedig() < navnKjøpere.length) {
			return null;
	 	}
	 	Billett[] vipBilletter = new SitteplassBillett[navnKjøpere.length];
	 	int teller = 0;
	 	for(int i = 0; i < tilskuer.length; i++) {
		 	for(int j = 0; j < tilskuer[i].length; j++) {
			 	if(tilskuer[i][j] == null && teller < navnKjøpere.length) {
				 	vipBilletter[teller] = new SitteplassBillett(getTribunenavn(), getPris(), i, j);
				 	reserver(navnKjøpere[teller], i, j);
				 	teller++;
			 	}
		 	}
	 	}
	 	return vipBilletter;
	}
	
	@Override
	public String getOversikt() {
		String oversikt = "";
		for(int i = 0; i < tilskuer.length; i++) {
			for(int j = 0; j < tilskuer[i].length; j++) {
				oversikt += "Rad " + i + " Plass " + j + ": " + tilskuer[i][j] + "\n";
			}
		}
		return oversikt;
	}
 
	private int getAntallLedig() {
		int antLedige = 0;
		for(int i = 0; i < tilskuer.length; i++) {
			for(int j = 0; j < tilskuer[i].length; j++){
				if(tilskuer[i][j] == null) {
					antLedige ++;
				}
			}
		}
		return antLedige; 
	}
	
	private void reserver(String navn, int rad, int plass) {
		this.tilskuer[rad][plass] = navn;
	}
}
