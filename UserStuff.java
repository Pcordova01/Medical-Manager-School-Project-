//Information for both the staff and patients
public class UserStuff {
	private String id; 
	private String username;
	private String password; 
	private String name;
	private String email;
	
	
	public UserStuff(String id, String username, String password, String name, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email; 
		
	}
	
    // Getters for attributes
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password; 
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Setters for attributes
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }	
    public void setPassword(String password) {
        this.password = password;
    }

}
