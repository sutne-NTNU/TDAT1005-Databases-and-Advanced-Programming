public class OppgaveOversikt {
	private Student[] studenter = new Student[5];   
    private int antStud = 0;
    
    public OppgaveOversikt() {
    }
    
    public boolean regNyStudent(String navn) {
    	for(int i = 0; i < studenter.length; i++) {
    		if(studenter[i] != null && studenter[i].getNavn().equals(navn)) {
    			return false;
    		}
    	}
    	if(studenter[antStud] != null){
    		Student[] nyTab = new Student[studenter.length + 5];
        	for(int i = 0; i < antStud; i++) {
        		nyTab[i] = studenter[i];
    		}
    		studenter = nyTab;
    	}
    	studenter[antStud] = new Student(navn);
    	antStud++;
	
	    return true;
    }
    
    public int finnAntStud(){
    	return antStud;
    }
    public int finnAntOppgaver(String navn){
    	for(int i = 0; i < studenter.length; i++) {
    		if(navn.equals(studenter[i].getNavn())) {
    			return studenter[i].getAntOppg();
    		}
    	}
    	return -1;
    }
    public boolean økAntOppg(String navn, int økning) {
	    for(int i = 0; i < studenter.length; i++) {
			if(navn.equals(studenter[i].getNavn())) {
				studenter[i].setAntOppg(studenter[i].getAntOppg() + økning);
				return true;
			}
	    }
		return false;
    }
	public String[] finnAlleNavn(){ 
		String[] alleNavn = new String[antStud];
		for(int i = 0; i < antStud; i++) {
			alleNavn[i] = studenter[i].getNavn();
		}
		return alleNavn;
	}
	
	public String toString() {
		String streng = "";
		for(int i = 0; i < studenter.length; i++) {
			if(studenter[i] != null) {
				streng += studenter[i] + "\n";
			}
		}
		return streng;
	}
}
