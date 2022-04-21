public class Staa extends Tribune{
	private int antSolgteBilletter;
	
	public Staa(String tribunenavn, int kapasitet, int pris) {
		super(tribunenavn, kapasitet, pris);
	}
	@Override
	public int finnAntallSolgteBilletter() {
		 return antSolgteBilletter;
	}
	@Override
	public Billett[] kjøpBilletter(String[] navnKjøpere){
		 return kjøpBilletter(navnKjøpere.length);
	}
	@Override
	public Billett[] kjøpBilletter(int antBilletter){
		 if(antBilletter > ledig()) {
			 return null;
		 }
		 Billett[] staaBilletter = new StaaplassBillett[antBilletter];
		 for(int i = 0; i < staaBilletter.length; i++) {
			 staaBilletter[i] = new StaaplassBillett(getTribunenavn(), getPris());
			 reserver();
		 }
		 return staaBilletter; 
		 
	}
	
	private void reserver() {
		this.antSolgteBilletter++;
	}
	
	private int ledig() {
		return getKapasitet() - antSolgteBilletter;
	}
	
	@Override
	public String getOversikt() {
		return "Solgt: " + antSolgteBilletter + "\nLedig: " + ledig();
	}
}
