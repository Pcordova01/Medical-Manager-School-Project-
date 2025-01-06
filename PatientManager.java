import java.util.ArrayList;
import java.io.FileWriter; 
import java.util.Scanner; 


public class PatientManager {
	
	//Object for user
	private UserStuff user;
	
	//Object for viewed user (editing)
	private Patient viewedUser;
	
	//ArrayList to track the patients
	private ArrayList<Patient> patients = new ArrayList<>();
	
	//Object for report generating
    private ReportGenerator ReportGenerator;

    //Constructor for manager
	public PatientManager(UserStuff user, ArrayList<Patient> patients) {
		this.user = user;
		this.patients = patients;
		this.ReportGenerator = new ReportGenerator();
		
		if (user instanceof Patient) {
			this.viewedUser = (Patient)user;
			
		}
		
        bubbleSort();
	}
	
	//Getter for user
    public UserStuff getUser() {
        return user; 
    }
    
	//Looking at profile of user who's logged in
    public void viewProfile() {
        System.out.println("---Profile Information---\nName: " + user.getName() + "\nID: " + user.getId() + "\nEmail: " + user.getEmail());
        
        //Patient Specific Information 
        if (user instanceof Patient) {
            System.out.println("Treatment Notes: " + ((Patient) user).getTreatmentNotes());
        } 
        //Medical Staff Specific Information 
        else if (user instanceof Medical_Staff) {
            System.out.println("Department: " + ((Medical_Staff) user).getDepartment());
        }
    }
	    
    
    //Searching for a patient using id (with binary search)
	public Patient searchPatient(String id) {
	
		//A patient is thrown an error, as they should only be able to look up their own information
	    if (!(user instanceof Medical_Staff)) {
	        System.out.println("Woah!! A patient can only look at their own information!");
	        return null;
	    }
	
	   //binary search 
        int search = binarySearch(patients, id, 0, patients.size() - 1);

        //If the search found a valid patient
        if (search != -1) {
            viewedUser = patients.get(search); 
            System.out.println("---Patient---\nName: " + viewedUser.getName() + "\nID: " + viewedUser.getId()+ "\nEmail: " + viewedUser.getEmail()+ "\nTreatment Notes: " + viewedUser.getTreatmentNotes());
            return viewedUser;
        } 
        //Invalid id
        else {
            System.out.println(id + " is an invalid id!!!");
            return null;
        }
	}
	
	//Binary search requires information to be sorted, as such bubble sort will ensure it is all sorted
    private void bubbleSort() {
        int n = patients.size();
        Patient temp;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            
            for (int j = 0; j < n - 1 - i; j++) {
                if (patients.get(j).getId().compareTo(patients.get(j + 1).getId()) > 0) {
                    
                    temp = patients.get(j);
                    patients.set(j, patients.get(j + 1));
                    patients.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
    
    //Binary Search (recursive)
    private int binarySearch(ArrayList<Patient> patients, String id, int low, int high) {
        if (high >= low) {
            int mid = low + (high - low) / 2;

            int comparison = patients.get(mid).getId().compareTo(id);

            if (comparison == 0) {
                return mid; 
            } else if (comparison > 0) {
                return binarySearch(patients, id, low, mid - 1); 
            } else {
                return binarySearch(patients, id, mid + 1, high); 
            }
        }
        return -1; 
    }

    //Function to edit patient information
	public void editPatientInfo() {
	    Scanner scanner = new Scanner(System.in);

	    //User editing information is a patient
	    if (user instanceof Patient) {
	    	
	        //Patient editing menu
	        System.out.println("\n--- Edit Your Information ---\nSelect the attribute that you want to edit:\n1. Name\n2. Email\n3. Treatment Notes\n4. Password");

	        //Take user input
	        int choice = scanner.nextInt();
	        scanner.nextLine();  
	        	
	        //Editing name
	        if (choice == 1) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                viewedUser.setName(newName);
	        }
	        //Editing email
	        else if (choice == 2) {
                System.out.print("Enter new email: ");
                String newEmail = scanner.nextLine();
                viewedUser.setEmail(newEmail);
	        }
	        //Editing treatment notes
	        else if (choice == 3) {
                System.out.print("Enter new treatment notes: ");
                String newNotes = scanner.nextLine();
                viewedUser.setTreatmentNotes(newNotes);
	        }
	        //Editing password
	        else if (choice == 4) {
	        	System.out.print("Enter your new password: ");
	        	String newPassword = scanner.nextLine();
	        	viewedUser.setPassword(newPassword);
	        }
	        //Invalid option
	        else {
	        	System.out.println("\"" + choice + "\"" + " is not a choice!!");
	        }
	        
	        //Tell the user the changes have been implemented
	        System.out.println("Your changes have been implemented...");
	    }
	    else {
	  
	    }

	    //Save patient information changes
	    savePatientInfo();
	}    
	
	//Function to save the new, edited patient info
    private void savePatientInfo() {
    	
    	//Writing into patient.csv document
        try (FileWriter writer = new FileWriter("patient.csv")) {
        	
        	//Sift through patient information and overwrite document with new changes
            for (Patient patient : patients) {
                writer.write(patient.getId() + "," +patient.getUsername() + ","+ patient.getPassword() + "," +patient.getName() + "," +patient.getEmail() + "," + patient.getTreatmentNotes() + "\n");
            }
        } 
        catch (Exception e) {
        
        }
    }
    
    //function to make reports per user's request
    public void generateReport() {
    	
    	//scanner declaration
        Scanner scanner = new Scanner(System.in);
        
        //Prompt user to give the filename for the report
        System.out.print("Name your report: ");
        String filename = scanner.nextLine();

        // Ask the user to choose a report type
        System.out.println("Choose the type of report:\n1 - List of Patients sorted ascending by ID\n2 - List of Patients sorted ascending by Name\n3 - List of all emails sorted alphabetically\n4 - User Information");

        //track user's choice
        int choice = scanner.nextInt();
        
        //Only Medical Staff can make patient reports
        if (user instanceof Medical_Staff || choice == 4) {
            ReportGenerator reportGenerator = new ReportGenerator();
            reportGenerator.generateReport(choice, filename, user, patients);
        } 
        else {
            //Patients can't make patient reports
            System.out.println("Hey patient!!! Get out of here!!!!!");
        }
    }
}
