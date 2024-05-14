import java.util.Scanner;

public class ESGP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Encost Smart Graph Project!");
        displayWelcomeMessage();
        int userTypeChoice = scanner.nextInt();
        scanner.nextLine();

        if (userTypeChoice == 1) {
            // Encost User screen
            displayEncostUserScreen();
            int encostUserChoice = scanner.nextInt();
            scanner.nextLine(); 

            if (encostUserChoice == 1) {
                // Encost user log in screen
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();

                // Simulated authentication
                if (authenticateUser(username, password)) {
                    System.out.println("Login successful! Welcome, " + username + "!");
                    //Encost User features
                    displayEncostUserFeatures();
                    int encostFeatureChoice = scanner.nextInt();
                    scanner.nextLine();
                    if(encostFeatureChoice == 1)
                    {
                        //load custom dataset
                        System.out.println("Load in the custom dataset");
                    }
                    else if (encostFeatureChoice == 2) {
                        //Visualise graph
                        System.out.println("Display graph");
                    }
                    else if (encostFeatureChoice == 3) {
                        //View summary statistics
                        System.out.println("Display Summary Statistics");
                    }
                    else if (encostFeatureChoice == 4) {
                        // Go back to previous menu
                    }
                    else if (encostFeatureChoice == 5) {
                        //exit
                        System.out.println("Exiting ESGP.");
                        System.exit(0);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                } else {
                    System.out.println("Incorrect username or password. Please try again");
                }
            } else if (encostUserChoice == 2) {
                // Go back to previous menu
            } else if (encostUserChoice == 3) {
                System.out.println("Exiting ESGP.");
                System.exit(0);
            } else {
                System.out.println("Invalid choice.");
            }
        } else if (userTypeChoice == 2) {
            // Community User screen
            displayCommunityUserScreen();
            int communityUserChoice = scanner.nextInt();
            scanner.nextLine();
            // Handle Community User options
            if(communityUserChoice == 1)
            {

            }
            else if (communityUserChoice == 2) {
                // Go back to previous menu
            }
            else if (communityUserChoice == 3) {
                System.out.println("Exiting ESGP.");
                System.exit(0);
            } else {
                System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("Invalid choice.");
        }
        scanner.close();
    }

    // Simulated user authentication
    private static boolean authenticateUser(String username, String password) {
        return true;
    }
    private static void displayWelcomeMessage() {
        //show  the opening screen
        System.out.println("Choose user type:");
        System.out.println("1. Encost User");
        System.out.println("2. Community User");
    }
    private static void displayCommunityUserScreen(){
        // Community User screen
        System.out.println("Community User options:");
        System.out.println("1. Visualize Graph");
        System.out.println("2. Go Back");
        System.out.println("3. Exit");
    }
    private static void displayEncostUserFeatures(){
        //Encost User features
        System.out.println("1. Load custom dataset");
        System.out.println("2. Visualise Graph");
        System.out.println("3. View Summary Statistics");
        System.out.println("4. Go Back");
        System.out.println("5. Exit");
    }
    private static void displayEncostUserScreen(){
        System.out.println("1. Log in");
        System.out.println("2. Go back");
        System.out.println("3. Exit");
    }
}
