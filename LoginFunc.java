import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginFunc {
	
	//ArrayList to track the patients
	private ArrayList<Patient> patients = new ArrayList<>();
	
	//ArrayList to track Medical Staff
	private ArrayList<Medical_Staff> MedicalStaff = new ArrayList<>();
	
	//Scanner declaration
	private Scanner scanner = new Scanner(System.in);
	
	public LoginFunc(String patientInfo, String staffInfo) {
		parsePatients(patientInfo);
		parseStaff(staffInfo);
	}
	
	//main login function
    public PatientManager login() {
        
    	//ongoing loop
    	while (true) {	
        	//prompt user for user name and password
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            //Check if user name and password match any in the database
            UserStuff user = checkUsername(username, password);
            
            //If there is a match, then we sign in
            if (user != null) {
                return new PatientManager(user, patients);
            } 
            else { //If there isn't a match, let the user know 
                System.out.println("Not a real username or password!!!! Please try it again...");
            }
        }
    }

    //Function to search for a match (with user name and password)
    private UserStuff checkUsername(String username, String password) {
    	
    	//Sift through our patients
        for (Patient patient : patients) {
        	
        	//If there's a valid user name and password, return the user 
            if (patient.getUsername().equals(username) && patient.getPassword().equals(password)) {
                return patient;
            }
        }
        
        //Sift though our medical staff
        for (Medical_Staff staff : MedicalStaff) {
        	//If there's a valid user name and password, return the user 
            if (staff.getUsername().equals(username) && staff.getPassword().equals(password)) {
                return staff;
            }
        }
        return null;
    }
    
    //Function to read through patient document
    private void parsePatients(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        	
            String line;
            while ((line = br.readLine()) != null) { //Read through document and save each element separated by a comma
            	
            	//Split with comma
                String[] info = line.split(","); 
                patients.add(new Patient(info[0], info[1], info[2], info[3], info[4], info[5]));
            }
        } 
        //User not found
        catch (Exception e) {
            System.out.println("ERROR! ERROR! User not found, please re-enter your username and password");
        }
    }

    //Function to read through medical staff document
    private void parseStaff(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        	
            String line;
            while ((line = br.readLine()) != null) { //Read through document and save each element separated by a comma
            	
            	//separated with comma
                String[] info = line.split(",");
                MedicalStaff.add(new Medical_Staff(info[0], info[1], info[2], info[3], info[4], info[5]));
            }
        } 
        //User not found
        catch (Exception e) {
            System.out.println("ERROR! ERROR! User not found, please re-enter your username and password");
        }
    }
	

}
