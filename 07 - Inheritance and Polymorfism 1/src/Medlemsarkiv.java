//Oppgave 2
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Medlemsarkiv { 
	private ArrayList<BonusMedlem> medlemmer;
	
	Medlemsarkiv(){
		this.medlemmer = new ArrayList<BonusMedlem>();
	}
	
	public ArrayList<BonusMedlem> getMedlemmer(){
		return medlemmer;
	}
	
	public int nyMedlem(Personalia pers, LocalDate inmeldt) {
		/*
			public int nyMedlem(Personalia pers, LocalDate innmeldt)
			Metoden skal opprette et objekt av klassen BasicMedlem og legge dette inn i arkivet. 
			(Alle medlemmer begynner som basic-medlemmer.) Metoden skal returnere medlemsnummeret.
			Metoden skal bestemme medlemsnummeret ved å kalle følgende private metode: finnLedigNr()
		*/
		int medlemsnummer = finnLedigNr();
		if(medlemsnummer == -1) {
			return -1;
		}
		medlemmer.add(new BasicMedlem(medlemsnummer, pers, inmeldt));
		return medlemsnummer;
	}
	
	private int finnLedigNr(){
		/*
		 	Denne metoden skal hente ut et tilfeldig heltall (bruk klassen Random) som ikke allerede 
			er i bruk som medlemsnr.
		 */
		final int MAKS_MEDLEMMER = 4;
		if(medlemmer.size() >= MAKS_MEDLEMMER) {
			return -1;
		}
		Random random = new Random();
		int ledigNummer = 0;
		boolean nummerOpptatt = false;
		do{
			ledigNummer = random.nextInt(MAKS_MEDLEMMER);
			nummerOpptatt = false;
			for(BonusMedlem medlem : medlemmer) {
				if(medlem.getMedlnr() == ledigNummer) {
					nummerOpptatt = true;
				}
			}
			
		}while(nummerOpptatt);
		return ledigNummer;
	}
	public int finnPoeng(int medlemNr, String passord) {
		/*
		 	skal ta medlemsnummer og passord som argument og returnere antall 
			poeng denne kunden har spart opp. Returner en negativ verdi hvis medlem med dette
 			nr ikke fins, eller passord er ugyldig.
 		*/
		for(BonusMedlem medlem : medlemmer) {
			if(medlem.getMedlnr() == medlemNr) {
				if(medlem.getPers().okPassord(passord)) {
					return medlem.getPoeng();
				}
			}
		}
		return -1; //medlem-nummer og/eller passord er feil
	}
	public boolean registrerPoeng(int medlemNr, int poeng) {
		/*
		 	skal ta medlemsnummer og antall poeng som argument og sørge for 
			at riktig antall poeng blir registrert for dette medlemmet. Returner false dersom 
			medlem med dette nr ikke fins.
		 */
		for(BonusMedlem medlem : medlemmer) {
			if(medlem.getMedlnr() == medlemNr) {
				medlem.registrerPoeng(poeng);
				return true;
			}
		}
		return false;
	}
	public void sjekkMedlemmer() {
		/*
		 	skal gå gjennom alle medlemmene og foreta oppgradering av medlemmer 
			som er kvalifisert for det. Basic-medlemmer kan kvalifisere seg for sølv eller gull, 
			mens sølvmedlemmer kan kvalifisere seg for gull. Tips: Du trenger å finne ut hvilken 
			klasse et objekt tilhører. Bruk operatoren instanceof. Det er ikke mulig å omforme 
			klassetilhørigheten til et objekt. Du må i stedet lage et nytt objekt med data fra 
			det gamle. Det nye objektet må legges inn i ArrayListen på den plassen der det gamle 
			lå (bruk metoden set()).
		 */
		LocalDate dagsDato = LocalDate.now();
		for(int i = 0; i < medlemmer.size(); i++) {
			int kvalPoeng = medlemmer.get(i).finnKvalPoeng(dagsDato);
			if(medlemmer.get(i) instanceof BasicMedlem) {
				if(kvalPoeng >= 75000) {
					medlemmer.set(i, new GullMedlem(medlemmer.get(i).getMedlnr(), medlemmer.get(i).getPers(), medlemmer.get(i).getInnmeldtDato(), medlemmer.get(i).getPoeng()));
				}else if(kvalPoeng >= 25000) {
					medlemmer.set(i, new SoelvMedlem(medlemmer.get(i).getMedlnr(), medlemmer.get(i).getPers(), medlemmer.get(i).getInnmeldtDato(), medlemmer.get(i).getPoeng()));
				}
			}else if(medlemmer.get(i) instanceof SoelvMedlem) {
				if(kvalPoeng >= 75000) {
					medlemmer.set(i, new GullMedlem(medlemmer.get(i).getMedlnr(), medlemmer.get(i).getPers(), medlemmer.get(i).getInnmeldtDato(), medlemmer.get(i).getPoeng()));
				}
			}
		}
	}
	public String getMedlemskap(int medlNr) {
		for(BonusMedlem medlem : medlemmer) {
			if(medlem.getMedlnr() == medlNr) {
				return medlem.getMedlemskap();
			}
		}
		return "Medlem eksisterer ikke";
	}
	public String toString() {
		String streng = "";
		for(BonusMedlem medlem : medlemmer) {
			streng += medlem; 
		}
		return streng;
	}
	/*
	Lag en enkel testklient der spesielt metoden sjekkMedlemmer() blir prøvd ut. Du kan 
	finne det hensiktsmessig å lage flere metoder i klassen Medlemsarkiv for å få testet 
	tilstrekkelig. Hvis du trenger å skrive ut hvilken klasse et objekt tilhører, kan du 
	bruke metoden getClass() i klassen Object.
	*/

	public static void main(String[] args)  {
		Medlemsarkiv medlemTest = new Medlemsarkiv();
		
		Personalia ole = new Personalia("Olsen", "Ole", "ole.olsen@dot.com", "ole");
		int oleNr = medlemTest.nyMedlem(ole, LocalDate.of(2018, 1, 29));
	    Personalia tove = new Personalia("Hansen", "Tove", "tove.hansen@dot.com", "tove");
	    int toveNr = medlemTest.nyMedlem(tove, LocalDate.of(2018, 3, 4));
	    Personalia kari = new Personalia("Nordmann", "Kari", "kari.nordmann@dot.com", "kari");
	    int kariNr = medlemTest.nyMedlem(kari, LocalDate.of(2018, 8, 24));
	    Personalia bjorn = new Personalia("Bjornsen", "Bjorn", "bjorn.bjornson@dot.com", "Bjorn");
	    int bjornNr = medlemTest.nyMedlem(bjorn, LocalDate.of(2019, 1, 15));
	    
	    System.out.println("Totalt antall tester: 6");
	    
	    if(0 == medlemTest.finnPoeng(oleNr, "ole") && -1 == medlemTest.finnPoeng(toveNr, "123")) {
	    	System.out.println("Test 1: OK");
	    }
	    
	    //sjekker registrering av poeng
	    if(	medlemTest.registrerPoeng(oleNr, 80000) &&
	    	medlemTest.registrerPoeng(toveNr, 75000) &&
	    	medlemTest.registrerPoeng(kariNr, 54000) &&
	    	medlemTest.registrerPoeng(bjornNr, 20000) &&
	    	!medlemTest.registrerPoeng(420, 24000)) {
	    	System.out.println("Test 2: OK"); 
	    }
	    
	    medlemTest.sjekkMedlemmer();
	    
	    //sjekker at medlemmer blir oppgradert til rikitg medlemskap
	   if(	medlemTest.getMedlemskap(oleNr).contentEquals("Basic") &&
			medlemTest.getMedlemskap(toveNr).contentEquals("Gull") &&
			medlemTest.getMedlemskap(kariNr).contentEquals("Sølv") &&
			medlemTest.getMedlemskap(bjornNr).contentEquals("Basic") &&
			medlemTest.getMedlemskap(1337).contentEquals("Medlem eksisterer ikke")) {
		   System.out.println("Test 3: OK");
	   }
	    medlemTest.registrerPoeng(oleNr, 10000);
	    medlemTest.registrerPoeng(toveNr, 10000);
	    medlemTest.registrerPoeng(kariNr, 30000);
	    medlemTest.registrerPoeng(bjornNr, 10000);
	    
	    //sjekker at registrering av poeng mtp forskjellige medlemskap gir riktige verdier.
	    if(	90000 == medlemTest.finnPoeng(oleNr, "ole") &&
	    	90000 == medlemTest.finnPoeng(toveNr, "tove") &&
	    	90000 == medlemTest.finnPoeng(kariNr, "kari") &&
	    	30000 == medlemTest.finnPoeng(bjornNr, "Bjorn")) {
	    	System.out.println("Test 4: OK");
	    }
	    medlemTest.sjekkMedlemmer();
	    
	    if(medlemTest.getMedlemskap(kariNr).contentEquals("Gull")) {
	    	System.out.println("Test 5: OK");
	    }
	    
	    //tester noen metoder fra personalia
	    if(	medlemTest.getMedlemmer().get(0).getPers().endrePassord("ole", "oleHansen") &&
	    	medlemTest.getMedlemmer().get(0).okPassord("oleHansen") &&
	    	!medlemTest.getMedlemmer().get(1).getPers().endrePassord("feilPassord", "irrelevant")) {
	    	System.out.println("Test 6: OK");
	    }
	    
	    //printer ut objektene i meldemstest
	    System.out.println("\n\n" + medlemTest);
	}
}
