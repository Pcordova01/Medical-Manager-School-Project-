import java.util.Scanner;

public class Driver {
	
	//Main function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Initializing LoginFunc with two documents
        LoginFunc loginFunc = new LoginFunc("patient.csv", "medicalstaff.csv");

        //Main menu title
        System.out.println("---Patient Management Subsystem---");

        //ongoing while loop
        while (true) {
        	
            //Instantiating login execution
            PatientManager manager = loginFunc.login();

            //Retrieving the corresponding user
            UserStuff user = manager.getUser();

            //If user is a patient
            if (user instanceof Patient) {
            	
                //Main menu for user's who are patients
                System.out.println("\n--- Patient Menu ---\n1- View Patient Profile\n2- Edit Patient Information\n3- Log Out of Patient Profile\nEnter choice: ");
                
                //User's choice
                int choice = scanner.nextInt();
                scanner.nextLine();

                //view profile
                if(choice == 1) {
                  manager.viewProfile();
                  break;
                }
                //Editing Information
                else if(choice == 2) {
                	manager.editPatientInfo();
                }
                //log out 
                else if(choice == 3) {
                   System.out.println("Logging out...\n");
                   break;
                }
                //Invalid input
                else {
                    System.out.println("\"" + choice + "\"" + " is not a valid choice...try again!!");
                }                
            } 
            //If user is a medical staff
            else if (user instanceof Medical_Staff) {
            	
                //Main menu for users who are Medical Staff
                System.out.print("\n--- Staff Menu ---\n1- View Staff Profile\n2- Search Patients\n3- Generate a Patient Report\n4- Log Out of Staff Profile\nEnter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                //view profile
                if(choice == 1) {
                    manager.viewProfile();
                    break;
                }
                //Searching ID
                else if(choice == 2) {
                    System.out.print("Please enter patient ID: ");
                    String patientId = scanner.nextLine();
                    manager.searchPatient(patientId);
                    break;
                }
                //Making a patient report
                else if(choice == 3) {
                    manager.generateReport();
                }
                //log out
                else if (choice == 4) {
                	System.out.println("okay...logging out...");
                	break;
                }
                //invalid input
                else {
                	System.out.println("\"" + choice + "\"" + " is not a valid choice...try again!!");
                }
                
            }
        }
    }
}
