//alle er basic medlem når de registreres
import java.time.LocalDate;

public class BasicMedlem extends BonusMedlem{
	BasicMedlem(int tall, Personalia pers, LocalDate inmeldt){
		super(tall, pers, inmeldt, 0);
	}
	@Override
	public void registrerPoeng(int antPoeng) {
		super.registrerPoeng(antPoeng);
	}
	public String getMedlemskap() {
		return "Basic";
	}
}
