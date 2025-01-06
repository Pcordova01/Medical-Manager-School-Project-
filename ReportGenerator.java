import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class ReportGenerator {

    public void generateReport(int report, String filename, UserStuff user, ArrayList<Patient> patients) {
    	
        try (FileWriter writer = new FileWriter(filename)) {

        	//List of patients ascending by id
        	if (report == 1){
                writePatientsById(writer, patients);
        	}
        	//List of patients ascending by name
        	else if (report == 2){
                writePatientsByName(writer, patients);
        	}
        	//List of all emails, sorted ascending alphabetically
        	else if (report == 3){
                writeAllEmails(writer, patients);
        	}
        	//user's information and attributes
        	else if (report == 4) {
        		writeUserDetails(writer, user);
        	}
        	//Invalid input
        	else {
        	 System.out.println("That isn't a valid number!!");	
        	}
            
            System.out.println("Report generated successfully: " + filename);
        } 
        catch (Exception e) {
          
        }
    }

    // Write a list of patients sorted by ID
    private void writePatientsById(FileWriter writer, ArrayList<Patient> patients) throws Exception {
    	
    	//Sort the id's
    	bubbleSortForIding(patients);
        
        //File title
        writer.write("--- List of patients, sorted ascending by id ---\n");
        
        //Sift through patients and write the information in ascending id
        for (Patient patient : patients) {
            writer.write(patient.getId() + ", " + patient.getName() + ", " + patient.getEmail() + "\n");
        }
    }

    // Write a list of patients sorted by Name
    private void writePatientsByName(FileWriter writer, ArrayList<Patient> patients) throws Exception {
    	
    	//Sort the names
    	bubbleSortForNameing(patients); 
        
        //File title
        writer.write("List of patients, sorted ascending by Name:\n");
        
        //Sift through patients and write information in ascending names
        for (Patient patient : patients) {
            writer.write(patient.getId() + ", " + patient.getName() + ", " + patient.getEmail() + "\n");
        }
    }

    // Write a list of all emails sorted alphabetically
    private void writeAllEmails(FileWriter writer, ArrayList<Patient> patients) throws Exception {
    	
    	//ArrayList to store emails
        ArrayList<String> emails = new ArrayList<>();
        
        //Sift through patients and record each email
        for (Patient patient : patients) {
            emails.add(patient.getEmail());
        }
        
        //Sorting emails
        Collections.sort(emails);
        
        //File title
        writer.write("Emails sorted alphabetically:\n");
        
        //Sift through emails and print them out
        for (String email : emails) {
            writer.write(email + "\n");
        }
    }

    //Write the details of logged-in user (Patient or Medical Staff)
    private void writeUserDetails(FileWriter writer, UserStuff user) throws Exception {
        
    	//If user is patient, write patient details
    	if (user instanceof Patient) {
            Patient patient = (Patient) user;
            writer.write("Patient: " + patient.getName() + "\nID: " + patient.getId() + "\nEmail: " + patient.getEmail() + "\nTreatment Notes: " + patient.getTreatmentNotes() + "\n");
        } 
    	//If user is medical staff, write medical staff details
        else if (user instanceof Medical_Staff) {
            Medical_Staff staff = (Medical_Staff) user;
            writer.write("Medical: " + staff.getName() + "\nID: " + staff.getId() + "\nEmail: " + staff.getEmail() + "\nDepartment: " + staff.getDepartment() + "\n");
        }
    }

    //Bubble sort method for sorting id's
    private void bubbleSortForIding(ArrayList<Patient> patients) {
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

   //Bubble sort for ascending name
    private void bubbleSortForNameing(ArrayList<Patient> patients) {
         int n = patients.size();
         for (int i = 0; i < n - 1; i++) {
             boolean swapped = false;
             for (int j = 0; j < n - 1 - i; j++) {
                 if (patients.get(j).getName().compareToIgnoreCase(patients.get(j + 1).getName()) > 0) {
                     
                     Patient temp = patients.get(j);
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
}
