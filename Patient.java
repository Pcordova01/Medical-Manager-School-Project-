//patient specific attributes
public class Patient extends UserStuff{

	private String treatmentNotes;
	
	public Patient(String id, String username, String password, String name, String email, String treatmentNotes) {	
    super(id, username, password, name, email);
	
    
    this.treatmentNotes = treatmentNotes; 
	}
	
	
	//Getter for treatment_notes
	public String getTreatmentNotes() {
        return treatmentNotes;
    }

	//Setter for treatment_notes
    public void setTreatmentNotes(String treatmentNotes) {
        this.treatmentNotes = treatmentNotes;
    }

   
}
