import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.io.*;

public abstract class Tribune implements Serializable, Comparable<Tribune>{
	private final String tribunenavn;
	private final int kapasitet;
	private final int pris;
	
	public Tribune(String navn, int kapasitet, int pris) {
		this.tribunenavn = navn;
		this.kapasitet = kapasitet;
		this.pris = pris;
	}
	 public String getTribunenavn() {
		return tribunenavn;
	}

	public int getKapasitet() { 
		return kapasitet;
	}

	public int getPris() {
		return pris;
	}

	public int finnAntallSolgteBilletter() {
		return 0;
	 }
	 public int finnInntekt(){
		 return pris * finnAntallSolgteBilletter();
	 }
	 
	 public abstract Billett[] kjøpBilletter(int antBilletter);
	 
	 public abstract Billett[] kjøpBilletter(String[] navnKjøpere);
	 
	 public String getOversikt() {
		 return null;
	 }
	 
	 public String toString() {
		 return "\nTribunenavn:	" + getTribunenavn() + 
				 "\nKapasitet:	" + getKapasitet() + 
				 "\nSolgt:		" + finnAntallSolgteBilletter() + 
				 "\nInntekt:	" + finnInntekt() + "kr";
	 }
	 @Override
	 public int compareTo(Tribune tribune) {
		 return this.finnInntekt() - tribune.finnInntekt();
	 }
	 
	 /*
	  * Lag et lite testprogram der du oppretter to ståtribuner, en vanlig sittetribune og en VIP-tribune. 
	  * Samle tribunene i en tabell av typen Tribune[].
	  *
	  * Kjøp billetter på alle tribunene, og skriv ut billettene.
	  * 
	  * For hver tribune skal du også skrive ut tribunenavn, kapasitet, antall solgte billetter og inntekten. 
	  * (Du kan gjerne la en toString()-metode i klassen Tribune gjøre jobben.)
	  */
	 
	 public static void main(String[] args) {
		 Staa staa_1 = new Staa("Stå_1", 14, 100);
		 Staa staa_2 = new Staa("Stå_2", 8, 200); 
		 Sitte sitte = new Sitte("Sitte" , 6 , 2 , 300);
		 VIP vip = new VIP("VIP", 6, 2, 499);
		 
		 Tribune[] tribuner = {staa_1, staa_2, sitte, vip};
		 
		 System.out.println("\nStå-Tribune 1:");
		 Billett[] staa1 = staa_1.kjøpBilletter(11);
		 //for(Billett ticket : staa1) {System.out.println(ticket);}
		 System.out.println(staa_1.getOversikt());
		 
		 System.out.println("\nStå-Tribune 2:");
		 String[] staaGjester = {"Ole", "Kari", "Grethe"};
		 Billett[] staa2 = staa_2.kjøpBilletter(staaGjester);
		 //for(Billett ticket : staa2) {System.out.println(ticket);}
		 System.out.println(staa_2.getOversikt());
		 
		 System.out.println("\nSitte-Tribune:");
		 String[] sitteGjester = { "Bjørn", "Fredrik"};
		 Billett[] sitte1 = sitte.kjøpBilletter(sitteGjester);
		 //for(Billett ticket : sitte1) {System.out.println(ticket);}
		 Billett[] sitte2 = sitte.kjøpBilletter(3);
		 //for(Billett ticket : sitte2) {System.out.println(ticket);}
		 System.out.println(sitte.getOversikt());
		 
		 System.out.println("\nVIP-Tribune:");
		 if(vip.kjøpBilletter(1) == null) {System.out.println("Test ok");} //skal bli null;
		 
		 String[] vipGjester = {"Frida" , "Magnus" , "Johannes" , "Sebastian"};
		 Billett[] vip2 = vip.kjøpBilletter(vipGjester);
		 //for(Billett ticket : vip2) {System.out.println(ticket);}
		 System.out.println(vip.getOversikt());
		 
		 
		 Arrays.sort(tribuner); //vil sortere etter inntekt
		 
		 try {
	         ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("øving8.txt"));
	         for(Tribune tribune : tribuner) {
	        	 oos.writeObject(tribune);
	         }
	         oos.close();
	         
	         ObjectInputStream ois = new ObjectInputStream(new FileInputStream("øving8.txt"));
	         for(int i = 0; i < tribuner.length; i++){
	        	 System.out.println(ois.readObject());
	         }
	         ois.close();
	         
	         
		 }catch(Exception e) {
			 System.out.println("\nÆVV");
		 }
	 }
}
