public class Student {
	private final String navn;
	private int antOppg;
	
	public Student(String navn) {
		this.navn = navn;
		this.antOppg = 0;
	}
	
	public String getNavn() {
		return navn;
	}
	public int getAntOppg() {
		return antOppg;
	}
	public void setAntOppg(int antOppgaver) {
		if(antOppgaver < 0) {
			throw new IllegalArgumentException("FEIL, kun positive tall er gyldig");
		}
		this.antOppg = antOppgaver;
	}
	public String toString() {
		return "Navn: " + navn + ", antall oppgaver løst: " + antOppg;
	}
}
