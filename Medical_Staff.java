//medical staff attributes
public class Medical_Staff extends UserStuff{
	
	private String department;
	
	public Medical_Staff(String id, String username, String password, String name, String email, String department) {	
    super(id, username, password, name, email);
	
    
    this.department = department; 
	}
	
	
	//Getter for department
	public String getDepartment() {
        return department;
    }

	//Setter for department
    public void setDepartment(String department) {
        this.department = department;
    }
    
	
}
