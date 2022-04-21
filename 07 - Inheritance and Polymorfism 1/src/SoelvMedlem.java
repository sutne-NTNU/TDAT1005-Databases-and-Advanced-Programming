//dersom personen har fått 25000 poeng første året oppgraderes h*n til soelv
//gir 20% poengbonus
import java.time.LocalDate;
public class SoelvMedlem extends BonusMedlem{
	SoelvMedlem(int tall, Personalia pers, LocalDate inmeldt, int poeng){
		super(tall, pers, inmeldt, poeng);
	}
	
	@Override
	public void registrerPoeng(int antPoeng) {
		super.registrerPoeng((int)(antPoeng * 1.2));
	}
	@Override
	public String getMedlemskap() {
		return "Sølv";
	}
}
