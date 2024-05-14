import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.Assert.*;

public class EncostSmartGraphProjectTest {

    /*
     * Verify that the system displays a prompt when the software is launched 
     * allowing the user to indicate their user-type as a member of the community 
     * or Encost.
     */
    @Test
    public void testDisplayUserTypeChoice() {
        EncostSmartGraphProject app = new EncostSmartGraphProject();

        // Create variable to store console output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        
        // Simulate software launch
        app.main(null);

        // Set the expected output placeholder value
        String expectedOutput = "Welcome to the Encost Smart Graph Project!";

        // Verify console output is equal to the expected output
        assertEquals(expectedOutput, output.toString());
    }

    /*
     * Verify that when a user inputs their user-type to the defineUserType method
     * the user type is correctly determined by the input and stored in a global class
     * variable. User input is hardcoded for test purposes.
     */
    @Test
    public void testDefineUserType() {
        EncostSmartGraphProject app = new EncostSmartGraphProject();
        
        // Call method to define user type with user-input as parameter
        app.defineUserType("1");
        // Determine whether the correct user-type was assigned
        assertEquals("community", app.userType.getType());

        app.defineUserType("2");
        assertEquals("encost-unverified", app.userType.getType());

        app.defineUserType("");
        assertEquals("invalid", app.userType.getType());

        app.defineUserType("9");
        assertEquals("invalid", app.userType.getType());

        app.defineUserType("a");
        assertEquals("invalid", app.userType.getType());
    }

    /*
     * Verify that after the user indicates their user-type, the system 
     * present the appropriate prompt for the next action.
     */
    @Test
    public void testDisplayNextPrompt() {
        EncostSmartGraphProject app = new EncostSmartGraphProject();

        // Call method to define user type with user-input as parameter
        app.defineUserType("1");
        // Call method to get the next prompt to display
        String output = app.displayNextPrompt();
        // Check whether the right prompt is displayed
        assertEquals("Welcome, Community User!", output[0]);
        assertEquals("", output[1]);
        assertEquals("Options:", output[2]);
        assertEquals("1. Log In", output[3]);
        assertEquals("2. Go Back", output[4]);
        assertEquals("3. Exit", output[5]);
        assertEquals("", output[6]);
        assertEquals("Enter the number corresponding to your choice:", output[7]);
        
        app.defineUserType("2");
        output = app.displayNextPrompt();
        assertEquals("Welcome, Encost User!", output);
        
        app.defineUserType("3");
        output = app.displayNextPrompt();
        assertEquals("Not valid user", output);
    }

    /*
     * Verify that the ESGP Account Login correctly prompts the user for their 
     * username firstand then their password, as specified in the requirements.
     * 
     * Verify that once the username and password have been entered, the system 
     * checks that the inputs are valid.
     * 
     * Verify that if the username and/or password are invalid, the system informs
     * the user and prompts them to enter their credentials again.
     * 
     * Verify that if the username and password are valid, the system updates the 
     * user-type to be “encost-verified” and provides the user with the ESGP Feature 
     * Options.
     */
    @Test
    public void testESGPAccountLogin() {
        EncostSmartGraphProject app = new EncostSmartGraphProject();

        // Create variable to store console output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Call method so there is valid user in the system
        app.defineUserType("2");

        // Call method to ask for login details
        app.askForLoginDetails();
        // Test that the first line of the console output is asking for the username
        assertEquals("Username:", output[0]);
        // Test that the second line of the console output is asking for the password
        assertEquals("Password:", output[1]);

        // Reset console output variable
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Valid username and password to parse to login authentication with
        String username = "thisisthefirstlogin"; 
        String password = "thisisthepassword";

        // Call login authentication method with username and password
        boolean success = app.authenticateLogin(username, password);
        // Check that login was successful
        assertTrue(success);
        // Validate that user-type has been appropriately changed
        assertEquals("encost-verified", app.userType.getType());
        // Validate that ESGP Feature Options is displayed once login is successful
        assertEquals("Welcome, thisisthefirstlogin!", output[0]);

        // Reset console output variable
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Change login strings to wrong values
        username = "wrongusername";
        password = "wrongpassword";

        // Call login authentication method with username and password
        boolean success = app.authenticateLogin(username, password);
        // Verify that login was unsuccessful
        assertFalse(success);
        // Verify that user is informed of incorrect login details
        assertEquals("Incorrect Username or Password please try again! (10 tries remaining)", output[0]);
        // output[1] left for new line
        
        // Validate that username and password are asked for again
        assertEquals("Username:", output[2]);
        assertEquals("Password:", output[3]);
    }

    /*
     * Verify that the ESGP Feature Options prompt provides the user with 
     * the correct selection of features based on their user-type.
     */
    @Test
    public void testDisplayFeatureOptions() {
        EncostSmartGraphProject app = new EncostSmartGraphProject();

        // Create variable to store console output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Set current user to community user
        app.defineUserType("1");
        // Call method to display feature options
        app.displayFeatureOptions();
        // Verify that community user features are displayed according to SDS
        assertEquals("Welcome, Community User!", output[0]);
        assertEquals("", output[1]);
        assertEquals("Options:", output[2]);
        assertEquals("1. Visualise Graph", output[3]);
        assertEquals("2. Go Back", output[4]);
        assertEquals("3. Exit", output[5]);
        assertEquals("", output[6]);
        assertEquals("Enter the number corresponding to your choice:", output[7]);
        
        // Reset console output variable
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        
        // Set current user to encost user
        app.defineUserType("2");
        // Valid username and password to parse to login authentication with
        String username = "thisisthefirstlogin"; 
        String password = "thisisthepassword";
        // Call login authentication method with username and password
        boolean success = app.authenticateLogin(username, password);
        // Call method to display feature options
        app.displayFeatureOptions();
        // Verify that encost user features are displayed according to SDS
        assertEquals("Welcome, thisisthefirstlogin!", output[0]);
        assertEquals("", output[1]);
        assertEquals("Features:", output[2]);
        assertEquals("1. Load a custom dataset", output[3]);
        assertEquals("2. Visualise Graph", output[4]);
        assertEquals("3. View Summary Statistics", output[5]);
        assertEquals("4. Go Back", output[6]);
        assertEquals("5. Exit", output[7]);
        assertEquals("", output[8]);
        assertEquals("Enter the corresponding to your choice:", output[9]);
    }

    /*
     * Verify that once the user has selected a feature from the ESGP Feature Options,
     * the system displays the corresponding prompt or information for that feature.
     */
    @Test
    public void testDisplayFeatureChoiceOption() {
        EncostSmartGraphProject app = new EncostSmartGraphProject();

        // Create variable to store console output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Verify that when the user selects the first feature option, the graph representation is displayed
        app.displayFeatureChoice("1");
        assertEquals("Graph Displayed!", output[0]);

        // Verify that when the user selects the second feature option, the file path prompt is displayed
        app.displayFeatureChoice("2");
        assertEquals("Full file Path:", output[0]);

        // Verify that when the user selects the third feature option, the summary statistics are shown
        app.displayFeatureChoice("3");
        assertEquals("Summary Statistics:", output[0]);
    }

    /*
     * Verify that the system can read and process the Encost Smart Homes Dataset.
     */
    @Test
    public void testReadEncostSmartHomesDataset() {
        EncostSmartGraphProject app = new EncostSmartGraphProject();

        // Verify that when the application starts, the encostData field is initialised
        assertNotNull(app.encostData);

        // Verify that Encost dataset can be read and process successfully
        boolean success = encostData.processData(encostData.path);
        assertTrue(success);
    }

    /*
     * Verify that the system determines the device category based for each device based on
     * the device type.
     */
    @Test
    public void testCategorisingDevices() {
        EncostSmartGraphProject app = new EncostSmartGraphProject();
        
        // Call method with certain device type value
        String prompt = app.categoriseDevice("Router");
        // Verify that output is correct category based on device type
        assertEquals("Encost Wifi Router", prompt);

        prompt = app.categoriseDevice("Extender");
        assertEquals("Encost Wifi Router", prompt);
        prompt = app.categoriseDevice("Hub");
        assertEquals("Encost Hub/Controller", prompt);
        prompt = app.categoriseDevice("Contoller");
        assertEquals("Encost Hub/Controller", prompt);
        prompt = app.categoriseDevice("Light Bulb");
        assertEquals("Encost Smart Lighting", prompt);
        prompt = app.categoriseDevice("Strip Lighting");
        assertEquals("Encost Smart Lighting", prompt);
        prompt = app.categoriseDevice("Other Lighting");
        assertEquals("Encost Smart Lighting", prompt);
        prompt = app.categoriseDevice("Kettle");
        assertEquals("Encost Smart Appliance", prompt);
        prompt = app.categoriseDevice("Toaster");
        assertEquals("Encost Smart Appliance", prompt);
        prompt = app.categoriseDevice("Coffee Maker");
        assertEquals("Encost Smart Appliance", prompt);
        prompt = app.categoriseDevice("Washing Machine");
        assertEquals("Encost Smart Whiteware", prompt);
        prompt = app.categoriseDevice("Dryer");
        assertEquals("Encost Smart Whiteware", prompt);
        prompt = app.categoriseDevice("Refrigerator");
        assertEquals("Encost Smart Whiteware", prompt);
        prompt = app.categoriseDevice("Freezer");
        assertEquals("Encost Smart Whiteware", prompt);
        prompt = app.categoriseDevice("Dishwasher");
        assertEquals("Encost Smart Whiteware", prompt);
    }

    /*
     * Verify that the system creates an object for each device, containing all
     * the information relevant to that device.
     */
    @Test
    public void testObjectCreation() {
        EncostSmartGraphProject app = new EncostSmartGraphProject();

        // Verify that when device object is initialised with valid information, object creation is successful
        Device device = new Device("1", "06/05/2024", "test", "Coffee Maker", "1", null, true, true);
        assertNotNull(device);

        // Verify that when device object is initialised with incomplete information, object creation is unsuccessful
        device = new Device("1", "06/05/2024", null, "Coffee Maker", "1", null, true, true);
        assertNull(device);

        // Verify that when device object is initialised with empty information, object creation is unsuccessful
        device = new Device(null, null, null, null, null, null, null, null);
        assertNull(device);

        // Verify that when device object is initialised with incorrect data format, object creation is unsuccessful
        device = new Device("1", null, "Coffee Maker", "1", null, true, true, "06/05/2024");
        assertNull(device);
    }

    /*
     * Verify that each Encost Smart Device Object is stored in the graph data structure.
     */
    @Test
    public void testStoringObjects() {
        // Create array of new devices
        Device[] devices = new Device[] {
            new Device("", "", "", DeviceType.Router, "", null, true, true)
        };

        // Initialise graph data stucture with device array
        DeviceGraph graph = new DeviceGraph(devices);
        // Get array of devices from the graph data structure
        Device[] returnedDevices = graph.getDevices();

        // Verify that the two arrays contain the same objects
        assertArrayEquals(devices, returnedDevices);
    }
}