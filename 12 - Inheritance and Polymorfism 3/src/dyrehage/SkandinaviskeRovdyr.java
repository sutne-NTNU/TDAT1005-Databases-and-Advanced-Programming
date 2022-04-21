package dyrehage;

import java.time.LocalDate;

public interface SkandinaviskeRovdyr{
	  String getNavn();
	  LocalDate getFdato();
	  int getAlder();
	  String getAdresse();
	  void flytt(String nyAdresse);
	  String skrivUtInfo();
	  int getAntKull();
	  void leggTilKull(int antall);
	  void leggTilNyttKull();
	}
