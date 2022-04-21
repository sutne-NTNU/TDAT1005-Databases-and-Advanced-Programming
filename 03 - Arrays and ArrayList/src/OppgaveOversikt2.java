import java.util.ArrayList;

public class OppgaveOversikt2 {
	private ArrayList<Student> studenter = new ArrayList<Student>();
    private int antStud = 0; 
    
    public OppgaveOversikt2() {
    }
    
    public boolean regNyStudent(String navn) {
    	for(Student tabStudent : studenter) {
    		if(tabStudent.getNavn().equals(navn)) {
    			return false;
    		}
    	}
    	Student nyStudent = new Student(navn);
    	studenter.add(nyStudent);
    	antStud++;
	    return true;
    }
    
    public int finnAntStud(){
    	return antStud;
    }
    
    public int finnAntOppgaver(String navn){
    	for(Student tabStudent : studenter) {
    		if(navn.equals(tabStudent.getNavn())) {
    			return tabStudent.getAntOppg();
    		}
    	}
    	return -1;
    }
    public boolean økAntOppg(String navn, int økning) {
    	for(Student tabStudent : studenter) {
			if(navn.equals(tabStudent.getNavn())) {
				tabStudent.setAntOppg(tabStudent.getAntOppg() + økning);
				return true;
			}
	    }
		return false;
    }
	public String[] finnAlleNavn(){ 
		String[] alleNavn = new String[antStud];
		int teller = 0;
		for(Student tabStudent : studenter) {
			alleNavn[teller] = tabStudent.getNavn();
			teller++;
		}
		return alleNavn;
	}
	
	public String toString() {
		String streng = "";
		for(Student tabStudent : studenter) {
			if(tabStudent != null) {
				streng += tabStudent + "\n";
			}
		}
		return streng;
	}
}