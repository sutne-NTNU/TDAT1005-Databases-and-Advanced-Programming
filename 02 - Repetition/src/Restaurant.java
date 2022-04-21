import java.util.Calendar;
import java.util.GregorianCalendar;
public class Restaurant {
	private String navn;
	private int etableringsår;
	private Bord bord;
	
	public Restaurant(String navn, int år, int antBord) {
		this.navn = navn;
		this.etableringsår = år;
		this.bord = new Bord(antBord);
	}
	public String getNavn() {
		return navn;
	}
	public void setNavn(String nyttNavn) {
		this.navn = nyttNavn;
	}
	public int getEtableringsår() {
		return etableringsår;
	}
	public Bord getBord() {
		return bord;
	}
	public int getAntallBord() {
		return bord.getAntallBord();
	}
	
	public int getAarIDrift() {
		GregorianCalendar kalender = new GregorianCalendar();
		int aar = kalender.get(Calendar.YEAR);
		int år = aar - etableringsår;
		return år;
	}
	
	public int antallLedige() {
		return bord.antallBordLedig();
	}
	
	public int antallOpptatt() {
		return bord.antallBordOpptatt();
	}
	
	public boolean reserverBord(int antallBord, String gjestNavn) {
		String gjest = gjestNavn.trim();
		return bord.reserverBord(antallBord, gjest);
	}
	
	public int[] reservertAv(String gjestNavn) {
		return bord.reservertAv(gjestNavn);
	}
	
	public void frigiBord(int[] bordNummerInput) {
		for(int bordNummer : bordNummerInput) {
			bord.frigiBord(bordNummer);
		}
	}
	
	public String toString() {
		String streng = "Navn: 		" + getNavn();
		streng += "\nEtableringsår: 	" + getEtableringsår();
		streng += "\nÅr i drift: 	" + getAarIDrift();
		streng += "\nAntall bord:	" + getAntallBord();
		streng += "\nBordoversikt: 	" + getBord();
		return streng;
	}
	/*
	 * Testprogram
	 */
	public static void main(String[] args) { 
		Restaurant restaurant = new Restaurant("KantinA", 1997, 10);
		System.out.println("Info om resturanten:\n" + restaurant);
		
		
		System.out.println("\nReserverer 5 bord til Ole");
		if(!restaurant.reserverBord(5, "Ole")){System.out.println("BORD IKKE RESERVERT, noe gikk galt/ikke nok plass");}
		System.out.println("bordoversikt: " + restaurant.getBord());
		
		
		System.out.println("\nFrigjør bord nr 1 og 3");
		int[] frigi = {1, 3};
		restaurant.frigiBord(frigi);
		System.out.println("bordoversikt: " + restaurant.getBord());
		
		
		System.out.println("\nReserverer 4 bord til Kari");
		if(!restaurant.reserverBord(4, "Kari")){System.out.println("BORD IKKE RESERVERT, noe gikk galt/ikke nok plass");}
		System.out.println("bordoversikt: " + restaurant.getBord());
		
		
		System.out.println("\nSjekker Om bordtellingen fungerer (skal returnere 3 og 7)");
		System.out.println("ledige: " + restaurant.antallLedige());			//3
		System.out.println("opptatt: " + restaurant.antallOpptatt());		//7
		
		
		System.out.println("\nprøver å Reservere 7 bord til Bjørn, skal være fullt:");
		if(!restaurant.reserverBord(7, "Bjørn")){System.out.println("BORD IKKE RESERVERT, noe gikk galt/ikke nok plass");}
		System.out.println("bordoversikt: " + restaurant.getBord());
				
		
		System.out.print("\nBord som er reservert av Ole: ");
		int[] ole = restaurant.reservertAv("Ole");
		for(int bord : ole) {
			System.out.print(bord + ", ");
		}
		System.out.println("\n\n\nTester å endre navn FØR:\n" + restaurant.getNavn());
		restaurant.setNavn("KantineB");
		System.out.println("\nTester å endre navn ETTER:\n" + restaurant.getNavn());
	}
}
