import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BonusMedlem {
	private final int medlNr;
	private final Personalia pers;
	private final LocalDate innmeldtDato; 
	private int poeng = 0;
	
	public BonusMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, int poeng) {
		this.medlNr = medlNr;
		this.pers = pers;
		this.innmeldtDato = innmeldtDato;
		this.poeng = poeng;
	}
	public int getMedlnr() {
		return medlNr;
	}
	public Personalia getPers() {
		return pers;
	}
	public LocalDate getInnmeldtDato() {
		return innmeldtDato;
	}
	public int getPoeng() {
		return poeng;
	}
	public int finnKvalPoeng(LocalDate testdato) {
		/* 
		 * skal returnere antall poeng som kan kvalifisere til oppgradering av medlemskapet 
		 * til sølv eller gull. Dersom innmeldingsdatoen  ligger mindre enn 365 dager bak i 
		 * tid i forhold til datoen som sendes inn som argument, returneres antall poeng. 
		 * Hvis det er mer enn ett år siden kunden meldte seg inn, returneres 0 poeng.
		 */
		//int dagerMellom = Period.between(innmeldtDato , testdato).getDays();
		long dagerMellom = ChronoUnit.DAYS.between(innmeldtDato, testdato); 
		if(dagerMellom <= 365) {
			return getPoeng();
		}
		return 0;
	}
	public boolean okPassord(String passord) {
		/*
		 * tar et passord som argument, og returnerer true dersom det er ok.
		 */
		return pers.okPassord(passord);
		
	}
	public void registrerPoeng(int antPoeng) {
		this.poeng += antPoeng;
		/*
		 * skal ta antall poeng som argument og registrere disse i henhold til reglene foran. 
		 * (Du skal ikke tenke på oppgradering til gull- og sølvmedlemskap her.)
		 * */
	}
	
	public String getMedlemskap() {
		return "";
	}
	
	public String toString() {
		return pers + "\nInmeldt: 	" + getInnmeldtDato() + "\nmedlem_id:	" + getMedlnr() + "\nPoeng:		" + getPoeng() + "\nKval_poeng:	" + finnKvalPoeng(LocalDate.now()) + "\nMedlem_Status:	" + getMedlemskap() + "\n";
	}
	
	public static void main(String[] args)  {
	    Personalia ole = new Personalia("Olsen", "Ole", "ole.olsen@dot.com", "ole");
	    Personalia tove = new Personalia("Hansen", "Tove", "tove.hansen@dot.com", "tove");
	    LocalDate testdato = LocalDate.of(2008, 2, 10);
	    System.out.println("Totalt antall tester: 8");

	    BasicMedlem b1 = new BasicMedlem(100, ole, LocalDate.of(2006, 2, 15));
	    b1.registrerPoeng(30000);
	    if (b1.finnKvalPoeng(testdato) == 0 && b1.getPoeng() == 30000) {
	      System.out.println("Test 1 ok");
	    }
	    b1.registrerPoeng(15000);
	    if (b1.finnKvalPoeng(testdato) == 0 && b1.getPoeng() == 45000) {
	      System.out.println("Test 2 ok");
	    }

	    BasicMedlem b2 = new BasicMedlem(110, tove, LocalDate.of(2007, 3, 5));
	    b2.registrerPoeng(30000);
	    if (b2.finnKvalPoeng(testdato) == 30000 && b2.getPoeng() == 30000) {
	      System.out.println("Test 3 ok");
	    }

	    SoelvMedlem b3 = new SoelvMedlem(b2.getMedlnr(), b2.getPers(), b2.getInnmeldtDato(), b2.getPoeng());
	    b3.registrerPoeng(50000);
	    if (b3.finnKvalPoeng(testdato) == 90000 && b3.getPoeng() == 90000) {
	      System.out.println("Test 4 ok");
	    }

	    GullMedlem b4 = new GullMedlem(b3.getMedlnr(), b3.getPers(), b3.getInnmeldtDato(), b3.getPoeng());
	    b4.registrerPoeng(30000);
	    if (b4.finnKvalPoeng(testdato) == 135000 && b4.getPoeng() == 135000) {
	      System.out.println("Test 5 ok");
	    }

	    testdato = LocalDate.of(2008, 12, 10);
	    if (b4.finnKvalPoeng(testdato) == 0 && b4.getPoeng() == 135000) {
	      System.out.println("Test 6 ok");
	    }

	    if (!ole.okPassord("OOO")) {
	      System.out.println("Test 7 ok");
	    }
	    if (b2.okPassord("tove")) {
	      System.out.println("Test 8 ok");
	    }
	}
}
