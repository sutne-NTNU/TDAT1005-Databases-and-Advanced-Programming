import java.util.ArrayList;

public class Konferansesenter {
	private ArrayList<Rom> romListe;
	
	public Konferansesenter() {
		this.romListe = new ArrayList<Rom>();
		
	}
//	public static Comparator<Rom> RoomSizeComparator = new Comparator<Rom>() {
//
//		public int compare(Rom r1, Rom r2) {
//
//		   int rno1 = r1.getSize();
//		   int rno2 = r2.getSize();
//
//		   /*For ascending order*/
//		   return rno1-rno2;
//
//		   /*For descending order*/
//		   //return rno2-rno1;
//	   }
//	};
//	
//	public static Comparator<Rom> RoomNumberComparator = new Comparator<Rom>() {
//
//		public int compare(Rom r1, Rom r2) {
//
//		   int rno1 = r1.getRoomNr();
//		   int rno2 = r2.getRoomNr();
//
//		   /*For ascending order*/
//		   return rno1-rno2;
//
//		   /*For descending order*/
//		   //return rno2-rno1;
//	   }
//	};
	 
	
	
	/*Reserver rom, gitt tidspunkt fra og til, antall personer 
	 * samt navn og telefonnummer til kunden. (Her skal romnummer 
	 * ikke være parameter, metoden skal selv finne et rom som er 
	 * ledig og med plass til det oppgitte antallet personer.)
	 */
	public boolean reserverRom(long tidFra, long tidTil, int antallPers, String kundeNavn, String tlf) {
		//Collections.sort(romListe, Rom.RoomSizeComparator);
		Tidspunkt fra = new Tidspunkt(tidFra);
		Tidspunkt til = new Tidspunkt(tidTil);
		for(Rom room : romListe) {
			if(antallPers <= room.getSize()){ //mulig å f.eks. reservere et 10 persons rom for 2 personer, ikke veldig praktisk
				ArrayList<Reservasjon> reservasjoner = room.getReservations();
				if(reservasjoner.size() == 0) {
					room.nyReservasjon(new Reservasjon(fra, til, new Kunde(kundeNavn, tlf)));
					return true;
				}else{
					for(Reservasjon reservert : reservasjoner) {
						if(reservert.overlapp(fra,til)) {
							return false;
						}
					}
					room.nyReservasjon(new Reservasjon(fra, til, new Kunde(kundeNavn, tlf)));
					return true;
				}
			}
		}
		return false;
	}
	//Registrer et nytt rom med romnummer og størrelse (ikke tillatt dersom rom med dette nummer fins fra før)
	public boolean regNyttRom(int romnummer, int størrelse) {
		for(Rom room : romListe) {
			if(room.getRoomNr() == romnummer) {
				return false;
			}
		}
		romListe.add(new Rom(romnummer, størrelse));
		return true;
	}
	//finn antall rom
	public int getAntallRom() {
		return romListe.size();
	}
	//finn et bestemt rom (indeks)
	public Rom getRomNr(int indeks) {
		if(indeks <= romListe.size()) {
			return romListe.get(indeks);
		}
		return null;
	}
	//finn et bestemt rom (romnr)
	public Rom getRom(int romNr) {
		for(Rom room : romListe) {
			if(room.getRoomNr() == romNr) {
				return room;
			}
		}
		return null;
	}
	
	public String toString() {
		//Collections.sort(romListe, Rom.RoomNumberComparator);
		String streng = "Antall rom: " + getAntallRom() + "\n\n";
		for(Rom room : romListe) {
			streng += room + "\n\n";
		}
		return streng;
	}
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println("Totalt antall tester: 8");
	    Konferansesenter konf = new Konferansesenter();
	    
	    if(	konf.regNyttRom(101, 10)) {
	    	System.out.println("1: rom lagt til");
	    }
	    if(!konf.regNyttRom(101, 7)){
	    	System.out.println("2: rom ikke lagt til pga likt romnummer");
	    }
	    
	    if(	konf.reserverRom(200101010800L, 200101010900L, 3, "Ole", "42013371") &&
	    	konf.reserverRom(200101010900L, 200101011000L, 4, "Kari", "42013371") &&
	    	konf.reserverRom(200101011100L, 200101011500L, 6, "Bjørn", "42013371") &&
	    	konf.reserverRom(200101011600L, 200101011700L, 7, "Jostein", "42013371")) {
	    	System.out.println("3: 4 reservasjoner ble gjort");
	    }
	    
	    if(!konf.reserverRom(200101011100L, 200101011500L, 6, "Fredrik", "42013371")){
	    	System.out.println("4: Rom ikke reservert, overlappende tid");
	    }
	    
	    if(!konf.reserverRom(200201011800L, 200201012000L, 11, "Thomas", "42013371")){
	    	System.out.println("5: Rom ikke reservert, ikke rom med nok plass");
	    }
	    
	    System.out.println("6: antall rom = " + konf.getAntallRom());
	    System.out.println("7: rom med indeks 0 = " + konf.getRomNr(0));
	    System.out.println("8: romNr 102 = " + konf.getRom(102));
	    
	    //System.out.println("\n\n\n\n\n\n"+konf);
	}
}
