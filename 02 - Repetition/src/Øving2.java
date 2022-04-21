import static javax.swing.JOptionPane.*;

public class Øving2 {
	public static void main(String[] args) {
		/*spør brukeren om restaurantnavn, etableringsår, og antall bord.
		opprett et Restaurant-objekt*/
		String navn = showInputDialog("Skriv inn navnet på restauranten her:");
		
		String år = showInputDialog("Skriv inn året restauranten ble etablert:");
		int etbÅr = Integer.parseInt(år);
		
		String antallbord = showInputDialog("Hvor mange bord har restauranten?");
		int antBord = 1;
		if(!antallbord.contentEquals("")) antBord = Integer.parseInt(antallbord);
		
		Restaurant restaurant = new Restaurant(navn, etbÅr, antBord);
		
		final int reserverBord = 1;  
		final int finnBordNr = 2;
		final int frigiBord = 3;
		final int printAllInfo = 4;
		final int avslutt = 0;
		String[] OPTIONS = {"Avslutt" , "Reserver Bord" , "Finn Bord reservert av Gjest" , "Frigi Bord", "Print all info"};
		
		int valg = 0;
		do {     
			valg = showOptionDialog(null, "Hva ønsker du å gjøre?", restaurant.getNavn(), DEFAULT_OPTION , INFORMATION_MESSAGE , null, OPTIONS, OPTIONS[0] );
		    switch (valg) {
		        case reserverBord:  // reservere et antall bord på et bestemt navn
		        	if(restaurant.antallLedige() == 0) {
		    			showMessageDialog(null, "Resturanten er fullbooket!\nPrøv igjen senere");
		        	}else{
			        	String gjestNavn = showInputDialog("Skriv inn navn:");
			            String atlbord = showInputDialog("Hvor mange bord reserveres?");
			    		int antlBord = Integer.parseInt(atlbord);
			    		if(!restaurant.reserverBord(antlBord, gjestNavn)){showMessageDialog(null, "IKKE NOK BORD, prøv på nytt");}
		        	}
		    		break;
		    		
		        case finnBordNr:  // finne alle bordene som er reservert på et bestemt navn
		        	String gjestNavn2 = showInputDialog("Skriv inn navn:");
		        	int[] res = restaurant.reservertAv(gjestNavn2);
		    		if(res == null) {
		    			showMessageDialog(null, "Ingen bord registrert på dette navnet");
		    			break;
		    		}
		        	String svar = "";
		        	for(int i = 0; i < res.length; i++) {
		    			svar += res[i] + ", ";
		    		}
		        	showMessageDialog(null, svar + " Er reservert av " + gjestNavn2);
		        	break;
		        	
		        case frigiBord:  // frigi en rekke bord, bordnummer er gitt
		            String free = showInputDialog("Skriv inn alle bord som skal frigis, separtert med et komma (,)");
		            String[] frigiOss = free.split(",");
		            int[] frigi = new int[frigiOss.length];
		            
		            for(int i = 0; i < frigiOss.length; i++) {
		            	frigi[i] = Integer.parseInt(frigiOss[i]);
		            }
		            restaurant.frigiBord(frigi);
		            break;
		        case printAllInfo:
		        	System.out.println(restaurant);
		        	System.out.println("\nledige: " + restaurant.antallLedige());					
		    		System.out.println("opptatt: " + restaurant.antallOpptatt()+ "\n");				
		        	break;
		        default:
		        	break;
		    }
		} while (valg != avslutt);
		System.out.println(restaurant);
	}
}
