//dersom personen har fått 75000 poeng første året oppgraderes h*n til gull
// gir 50% poengbonus
import java.time.LocalDate;
public class GullMedlem extends BonusMedlem{
	GullMedlem(int tall, Personalia pers, LocalDate inmeldt, int poeng){
		super(tall, pers, inmeldt, poeng);
	}
	
	@Override
	public void registrerPoeng(int antPoeng) {
		super.registrerPoeng((int)(antPoeng * 1.5));
	}
	@Override
	public String getMedlemskap() {
		return "Gull";
	}
}
