public class Sitte extends Tribune{
		private int [] antOpptatt;  // tabellstørrelse: antall rader
		private final int kapasitet_rad;
		
		public Sitte(String tribunenavn, int kapasitet, int rader, int pris) {
			super(tribunenavn, kapasitet, pris);
			this.kapasitet_rad = kapasitet/rader; 
			this.antOpptatt = new int[rader];
		}
		@Override
		public int finnAntallSolgteBilletter() {
			int solgteBilletter = 0;
			for(int i = 0; i < antOpptatt.length; i++) {
				solgteBilletter += antOpptatt[i];
			}
			return solgteBilletter; 
		}
		
		@Override
		public Billett[] kjøpBilletter(String[] navnKjøpere){
			 return kjøpBilletter(navnKjøpere.length); 
		}
		
		@Override
		public Billett[] kjøpBilletter(int antBilletter){
			 if(antBilletter > getMaksLedige() || antBilletter == 0) {
				 return null;
			 }
			 Billett[] sitteBilletter = new SitteplassBillett[antBilletter];
			 int ledigRad = finnLedigRad(antBilletter);
			 for(int i = 0; i < antBilletter; i++) {
				 sitteBilletter[i] = new SitteplassBillett(getTribunenavn(), getPris(), ledigRad, 0);
				 reserver(ledigRad);
			 }
			 return sitteBilletter;
		 }
		
		@Override
		public String getOversikt() {
			String oversikt = "";
			for(int i = 0; i < antOpptatt.length; i++) {
				oversikt += "Rad " + i + " -		Solgt: " + antOpptatt[i] + "	Ledig: " + (kapasitet_rad - antOpptatt[i])+ "\n";
			}
			return oversikt;
		}
		
		public int finnLedigRad(int antallPersoner) { //returnere indeks til en rad med plass til alle som skal bestille
			for(int i = 0; i < antOpptatt.length; i++) {
				if(kapasitet_rad - antOpptatt[i] >= antallPersoner) {
					return i;
				}
			}
			return -1;
		}
		public int getMaksLedige() { //returnerer høyeste antall billetter mulig å bestille samtidig
			int maks_ledige = -1;
			for(int i = 0; i < antOpptatt.length; i++) {
				if(kapasitet_rad - antOpptatt[i] > maks_ledige) {
					maks_ledige = kapasitet_rad - antOpptatt[i];
				}
			}
			return maks_ledige;
		}
		
		private void reserver(int rad) {
			this.antOpptatt[rad]++;
		}
	}
//tisselur<3