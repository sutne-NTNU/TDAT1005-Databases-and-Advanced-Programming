import static javax.swing.JOptionPane.*;

import javax.swing.JOptionPane;
public class Klientprogram {
/* Lag et klientprogram der brukeren kan legge inn data. Sørg for at alle metodene i klassen Konferansesenter blir prøvd ut. Bruk for eksempel følgende mal:
 * Begynn med å registrere alle rommene.
 * Les inn noen reservasjoner.
 * Skriv ut all registrert informasjon. Bruk metodene som finner antall rom og rom gitt indeks.
 * Prøv ut metoden som finnet er rom, gitt romnummer. La brukeren skrive inn romnummeret. All info om rommet, inkludert reservasjonene skal skrives ut.
 */
	public static void main(String[] args) {
		Konferansesenter konf = new Konferansesenter();
		konf.regNyttRom(101, 10);
		if(	konf.reserverRom(200101010800L, 200101010900L, 3, "Sebastian", "42013371") &&
		    konf.reserverRom(200101010900L, 200101011000L, 4, "Sivert", "42013371") &&
		    konf.reserverRom(200101011100L, 200101011500L, 6, "Fredrik", "42013371") &&
		    konf.reserverRom(200101011600L, 200101011700L, 7, "Sabine", "95422229" /* ring dette nummer for bang*/)) {
		    System.out.println("3: 4 reservasjoner ble gjort");
		}
		final String[] VALG = {"Avslutt" , "Registrer et nytt rom", "Skriv ut info om alle rom" , "Skriv ut et gitt rom", "Ny reservasjon"};
		final int REG_NYTT_ROM = 1;
		final int PRINT_ALL = 2;
		final int PRINT_ROM = 3;
		final int NY_RESERVASJON = 4; 
		final int AVSLUTT = 0;
		int valg = 0;
		do {
			valg = JOptionPane.showOptionDialog(null, "Hva ønsker du å gjøre?", "Horehus", DEFAULT_OPTION, 2, null, VALG, VALG[0]);
			switch (valg) {
				case REG_NYTT_ROM:
					String rom = showInputDialog("Romnummer:");
					int romnummer = Integer.parseInt(rom);
					String size = showInputDialog("Sitteplasser:");
					int størrelse = Integer.parseInt(size); 
					if(konf.regNyttRom(romnummer, størrelse)){
						showMessageDialog(null, "Nytt rom lagt til\n" + romnummer + " med plass til " + size);
					}else {
						showMessageDialog(null, "FEIL, dette rommnummeret er allerede i bruk");
					}
					break;
				case PRINT_ALL:
					System.out.println(konf);
					break;
				case PRINT_ROM:
					String romnr = showInputDialog("Romnummer:");
					int romNr = Integer.parseInt(romnr);
					showMessageDialog(null, konf.getRom(romNr));
					break;
				case NY_RESERVASJON:
					String fra = showInputDialog("Reserver fra (yyyymmddttmm)");
					long tidFra = Long.parseLong(fra);
					String til = showInputDialog("Reserver fra (yyyymmddttmm)");
					long tidTil = Long.parseLong(til);
					String pers = showInputDialog("Hvor mange er dere?");
					int antallPers = Integer.parseInt(pers);
					String kundeNavn = showInputDialog("Navnet det skal reserveres på");
					String tlf = showInputDialog("Telefonnummer for ansvarlig person:");
					if(konf.reserverRom(tidFra, tidTil, antallPers, kundeNavn, tlf)){
						showMessageDialog(null, "Reservasjonen har blitt registrert");
					}else {
						showMessageDialog(null, "FEIL, reservasjonen kunne ikke gjennomføres");
					}
					break;
				default:
					valg = 0;
			}
		}while(valg != AVSLUTT);
	}
}
