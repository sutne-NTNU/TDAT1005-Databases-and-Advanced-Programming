import java.util.ArrayList;

public class Rom {
	private ArrayList<Reservasjon> reservations;
	private final int roomNr;
	private final int size;
	
	public Rom(int nr, int size) {
		this.roomNr = nr;
		this.size = size;
		this.reservations = new ArrayList<Reservasjon>();
	}
	
	public ArrayList<Reservasjon> getReservations() {
		return reservations;
	}
	public int getRoomNr() {
		return roomNr;
	}
	public int getSize() {
		return size;
	}

	public boolean nyReservasjon(Reservasjon reserv) {
		for(Reservasjon res : reservations) {
			if(res.overlapp(reserv.getFraTid(), reserv.getTilTid())) { 
				return  false;
			}
		}
		reservations.add(reserv);
		return true;
	}
	public String toString() {
		return "nr: " + getRoomNr() + " Plass til: " + getSize() + " personer\n    Reservasjoner:\n    " + reservations;
	}
	
	
	
	
	
	public static void main(String[] args) {
	    Kunde k = new Kunde("Anne Hansen", "12345678");
	    Rom test = new Rom(1, 10);
	    System.out.println("Totalt antall tester: 2");
	    Reservasjon r1 = new Reservasjon(new Tidspunkt(200302010500L), new Tidspunkt(200302010800L), k);
	    Reservasjon r2 = new Reservasjon(new Tidspunkt(200302010800L), new Tidspunkt(200302011100L), k);
	    Reservasjon r3 = new Reservasjon(new Tidspunkt(200302011130L), new Tidspunkt(200302011300L), k);
	    Reservasjon r4 = new Reservasjon(new Tidspunkt(200302011400L), new Tidspunkt(200302011700L), k);
	    
	    Reservasjon r5 = new Reservasjon(new Tidspunkt(200302011600L), new Tidspunkt(200302011900L), k);
	    if(		test.nyReservasjon(r1) &&
	    		test.nyReservasjon(r2) &&
	    		test.nyReservasjon(r3) &&
	    		test.nyReservasjon(r4)) {
	    	System.out.println("Rom Test 1: Vellykket");
	    }
	    if (!test.nyReservasjon(r5)) {
	    	System.out.println("Rom Test 2: Vellykket");
	    }
	}
}
