package test;
import java.time.LocalDate;

import dyrehage.*;

/*
 * Lag en enkel testklient som tester hver av metodene i interfacet SkandinaviskeRovdyr. Du skal altså lage dyreobjekter ved hjelp av et 
 * RovdyrFabrikk-objekt, og kun bruke metodene i SkandinaviskeRovdyr.
 
 	String getNavn();
	LocalDate getFdato();
 	int getAlder();
	String getAdresse();
	void flytt(String nyAdresse);
	String skrivUtInfo();
	
	int getAntKull();
	void leggTilKull(int antall);
	void leggTilNyttKull();
	
 */

public class Testing {
	public static void main(String[] args) {
		Rovdyrfabrikk testFabrikk = new Rovdyrfabrikk();
		SkandinaviskeRovdyr fB = testFabrikk.nyBinne("Fru bjørn", LocalDate.of(2007, 10, 10), LocalDate.of(2017, 10, 10), "Bamseveien 25", 1);
		SkandinaviskeRovdyr mB = testFabrikk.nyHannbjørn("Mister bjørn", LocalDate.of(2008, 10, 10), LocalDate.of(2018, 10, 10), "Bamseveien 15");
		SkandinaviskeRovdyr mU = testFabrikk.nyUlvehann("Herr ulv", LocalDate.of(2009, 10, 10), LocalDate.of(2018, 7, 10), "Ulvehiet 3");
		SkandinaviskeRovdyr fU = testFabrikk.nyUlvetispe("Frøken ulv", LocalDate.of(2010, 10, 10), LocalDate.of(2019, 1, 1), "Ulvehiet 4", 2);
		
		
		
		System.out.println("Antall Tester: 5");
		if(	fU.getNavn().equals("Frøken ulv") &&
			mU.getNavn().equals("Herr ulv") &&
			mB.getNavn().contentEquals("Mister bjørn") &&
			fB.getNavn().contentEquals("Fru bjørn")) {
			System.out.println("Test 1: OK");
		}
		if(	fU.getAlder() == 8 &&
			mU.getAlder() == 9) {
			System.out.println("Test 2: OK");
		}
		if(fB.getFdato().toString().equals("2007-10-10")) {
			System.out.println("Test 3: OK");
		}
		
		if(mU.getAdresse().contentEquals("Ulvehiet 3")) {
			System.out.println("Test 4: OK");
		}
		mU.flytt("Ulvesommer the sequal");
		if(mU.getAdresse().contentEquals("Ulvesommer the sequal")) {
			System.out.println("Test 5: OK");
		}
			
		
		System.out.println("\n\n" + fB.skrivUtInfo());
		System.out.println("\n" + mB.skrivUtInfo());
		System.out.println("\n" + fU.skrivUtInfo());
		System.out.println("\n" + mU.skrivUtInfo());
		
		fB.leggTilKull(2);
		fU.leggTilKull(4);
		fB.leggTilNyttKull();
		fU.leggTilNyttKull();
		
		if(	mB.getAntKull() == 0 &&
			mU.getAntKull() == 0 &&
			fB.getAntKull() == 4 &&
			fU.getAntKull() == 7) {
			System.out.println("\n\nTest Kull: OK");
		}
	}
}
