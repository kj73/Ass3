import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;

// Student 3


public class ESPGTestSuite {
    
    /** <=============================================>
     *  This section is for Categorizing Users tests
     *  <=============================================>
     **/
    @Test
    @DisplayName("Test communityUser - Tests if user is community user")
    public void communityUser(){
        // Arrange
        User user = new User("1");

        // Assert
        Assertions.assertEquals("community", user.getType());
    }

    @Test
    @DisplayName("Test encostUser - Tests if user is encost user")
    public void encostUser(){
        // Arrange and Act
        User user = new User("2");

        // Assert
        Assertions.assertEquals("encost-unverified", user.getType());
    }

    @Test
    @DisplayName("Test outOfRangeUser - Tests when user inputs a number out of range of the valid inputs")
    public void characterUser(){
        // Arrange and Act
        User user = new User("9");

        // Assert
        Assertions.assertEquals("invalid", user.getType());
    }

    @Test
    @DisplayName("Test emptyUser - Tests when user inputs an empty string")
    public void emptyUser(){
        // Arrange and Act
        User user = new User("");

        // Assert
        Assertions.assertEquals("invalid", user.getType());
    }
    
    @Test
    @DisplayName("Test characterUser - Tests when user inputs a character")
    public void characterUser(){
        // Arrange and Act
        User user = new User("a");

        // Assert
        Assertions.assertEquals("invalid", user.getType());
    }
    
    /** <=============================================>
     *  This section is for ESGP Account Login tests
     *  <=============================================>
     **/
    @Test
    @DisplayName("Test badLogin - Tests when user inputs an invalid username and password in login")
    public void badLogin(){
        // Arrange
        EncostSmartGraphProject esgp = new EncostSmartGraphProject();

        // Act
        espg.authenticateLogin("invaild", "invalid");

        // Assert
        Assertions.assertEquals("encost-unverified", user.getType());
    }

    @Test
    @DisplayName("Test badUsernameLogin - Tests when user inputs an invalid username and valid password in login")
    public void badUsernameLogin(){
        // Arrange
        EncostSmartGraphProject esgp = new EncostSmartGraphProject();

        // Act
        espg.authenticateLogin("invaild", "valid");

        // Assert
        Assertions.assertEquals("encost-unverified", user.getType());
    }

    @Test
    @DisplayName("Test badPasswordLogin - Tests when user inputs a valid username and invalid password in login")
    public void badPasswordLogin(){
        // Arrange
        EncostSmartGraphProject esgp = new EncostSmartGraphProject();

        // Act
        espg.authenticateLogin("vaild", "invalid");

        // Assert
        Assertions.assertEquals("encost-unverified", user.getType());
    }

    @Test
    @DisplayName("Test goodLogin - Tests when user inputs a valid username and valid password in login")
    public void goodLogin(){
        // Arrange
        EncostSmartGraphProject esgp = new EncostSmartGraphProject();

        // Act
        espg.authenticateLogin("vaild", "valid");

        // Assert
        Assertions.assertEquals("encost-verified", user.getType());
    }

    /** <=============================================>
     *  This section is for ESGP Feature Options tests
     *  <=============================================>
     **/

    //commmunity version
    @Test
    @DisplayName("Test communityFirstFeature - Tests when user inputs the first option in community esgp feature options")
    public void communityFirstFeature(){
        // Arrange
        EncostSmartGraphProject esgp = new EncostSmartGraphProject();

        // Assert
        Assertions.assertEquals("visualiseGraph", espg.featureOptions("1"));
    }

    @Test
    @DisplayName("Test communityExitFeature - Tests when user inputs the exit option in community esgp feature options")
    public void communityExitFeature(){
        // Arrange
        EncostSmartGraphProject esgp = new EncostSmartGraphProject();

        // Assert
        Assertions.assertEquals("exit", espg.featureOptions("3"));
    }

    @Test
    @DisplayName("Test communityEmptyFeature - Tests when user inputs an empty string as an option in community esgp feature options")
    public void communityEmptyFeature(){
        // Arrange
        EncostSmartGraphProject esgp = new EncostSmartGraphProject();

        // Assert
        Assertions.assertEquals("invalid", espg.featureOptions(""));
    }

    @Test
    @DisplayName("Test communityOutOfRangeFeature - Tests when user inputs an out of range number as an option in community esgp feature options")
    public void communityOutOfRangeFeature(){
        // Arrange
        EncostSmartGraphProject esgp = new EncostSmartGraphProject();

        // Assert
        Assertions.assertEquals("invalid", espg.featureOptions("4"));
    }

    @Test
    @DisplayName("Test communityCharacterFeature - Tests when user inputs a character as an option in community esgp feature options")
    public void communityCharacterFeature(){
        // Arrange
        EncostSmartGraphProject esgp = new EncostSmartGraphProject();

        // Assert
        Assertions.assertEquals("invalid", espg.featureOptions("a"));
    }

    //encost user version
    @Test
    @DisplayName("Test encostFirstFeature - Tests when user inputs the first option in encost esgp feature options")
    public void encostFirstFeature(){
        // Arrange
        EncostSmartGraphProject esgp = new EncostSmartGraphProject();

        // Assert
        Assertions.assertEquals("loadDataset", espg.featureOptions("1"));
    }

    @Test
    @DisplayName("Test encostExitFeature - Tests when user inputs the exit option in encost esgp feature options")
    public void encostExitFeature(){
        // Arrange
        EncostSmartGraphProject esgp = new EncostSmartGraphProject();

        // Assert
        Assertions.assertEquals("exit", espg.featureOptions("5"));
    }

    @Test
    @DisplayName("Test encostEmptyFeature - Tests when user inputs an empty string as an option in encost esgp feature options")
    public void encostEmptyFeature(){
        // Arrange
        EncostSmartGraphProject esgp = new EncostSmartGraphProject();

        // Assert
        Assertions.assertEquals("invalid", espg.featureOptions(""));
    }

    @Test
    @DisplayName("Test encostOutOfRangeFeature - Tests when user inputs an out of range number as an option in encost esgp feature options")
    public void communityOutOfRangeFeature(){
        // Arrange
        EncostSmartGraphProject esgp = new EncostSmartGraphProject();

        // Assert
        Assertions.assertEquals("invalid", espg.featureOptions("6"));
    }

    @Test
    @DisplayName("Test encostCharacterFeature - Tests when user inputs a character as an option in encost esgp feature options")
    public void encostCharacterFeature(){
        // Arrange
        EncostSmartGraphProject esgp = new EncostSmartGraphProject();

        // Assert
        Assertions.assertEquals("invalid", espg.featureOptions("a"));
    }

    /** <=============================================>
     *  This section is for Loading the Encost Smart Homes Dataset tests
     *  <=============================================>
     **/

    @Test
    @DisplayName("Test badLoadDataset - Tests when the system loads a dataset that has incorrect data length and a data type")
    public void badLoadDataset(){
        // Arrange
        EncostData encostData = new EncostData();

        // Assert
        Assertions.assertEquals("invalid", encostData.transformData(",noDate,,invalid,"));
    }

    @Test
    @DisplayName("Test badDataLengthDataset - Tests when the system loads a dataset that has incorrect data length but correct data type")
    public void badDataLengthDataset(){
        // Arrange
        EncostData encostData = new EncostData();

        // Assert
        Assertions.assertEquals("invalid", encostData.transformData(",11/22/12,,Router,"));
    }

    @Test
    @DisplayName("Test badDataTypeDataset - Tests when the system loads a dataset that has correct data length but incorrect data type")
    public void badDataTypeDataset(){
        // Arrange
        EncostData encostData = new EncostData();

        // Assert
        Assertions.assertEquals("invalid", encostData.transformData(",noData,,invalid,,,,"));
    }

    @Test
    @DisplayName("Test goodDataset - Tests when the system loads a dataset that has correct data length and correct data type")
    public void goodDataset(){
        // Arrange
        EncostData encostData = new EncostData();

        // Assert
        Assertions.assertEquals("valid", encostData.transformData(",11/22/12,,Router,,,,"));
    }

    /** <=============================================>
     *  This section is for Loading the Categorizing Smart Home Devices tests
     *  <=============================================>
     **/

    @Test
    @DisplayName("Test badDevice - Tests when the system loads an invalid array into a device")
    public void badDevice(){
        // Arrange
        EncostData encostData = new EncostData();

        //act
        String[] array = new String[6];

        // Assert
        Assertions.assertEquals("Error: invalid data", encostData.createDevice(array));
    }

    @Test
    @DisplayName("Test emptyDevice - Tests when the system loads an empty deviceType in array into a device")
    public void emptyDevice(){
        // Arrange
        EncostData encostData = new EncostData();

        //act
        String[] array = new String[8];
        array[1] = "12/12/12";

        // Assert
        Assertions.assertEquals("Error: invalid data", encostData.createDevice(array));
    }

    @Test
    @DisplayName("Test badDeviceTypeDevice - Tests when the system loads an bad deviceType in array into a device")
    public void badDeviceTypeDevice(){
        // Arrange
        EncostData encostData = new EncostData();

        //act
        String[] array = new String[8];
        array[1] = "12/12/12";
        array[3] = "a";

        // Assert
        Assertions.assertEquals("Error: invalid data", encostData.createDevice(array));
    }

    @Test
    @DisplayName("Test goodDevice - Tests when the system loads a valid deviceType in array into a device")
    public void goodDevice(){
        // Arrange
        EncostData encostData = new EncostData();

        //act
        String[] array = new String[8];
        array[1] = "12/12/12";
        array[3] = "Router";

        // Assert
        Assertions.assertEquals("Encost Wifi Routers", encostData.createDevice(array));
    }

    @Test
    @DisplayName("Test goodLowerCaseDevice - Tests when the system loads a valid deviceType but lowercase in array into a device")
    public void goodLowerCaseDevice(){
        // Arrange
        EncostData encostData = new EncostData();

        //act
        String[] array = new String[8];
        array[1] = "12/12/12";
        array[3] = "router";

        // Assert
        Assertions.assertEquals("Encost Wifi Routers", encostData.createDevice(array));
    }

    @Test
    @DisplayName("Test goodAltDevice - Tests when the system loads a different valid deviceType in array into a device")
    public void goodAltDevice(){
        // Arrange
        EncostData encostData = new EncostData();

        //act
        String[] array = new String[8];
        array[1] = "12/12/12";
        array[3] = "Light bulb";

        // Assert
        Assertions.assertEquals("Encost Smart Lighting", encostData.createDevice(array));
    }

    /** <=============================================>
     *  This section is for Building a Graph Data Type tests
     *  <=============================================>
     **/

    @Test
    @DisplayName("Test badGraphData - Tests when the system trys to load a bad device into EncostData dataset")
    public void badGraphData(){
        // Arrange
        EncostData encostData = new EncostData();
        String[] array = new String[8];
        array[1] = "12/12/12";
        array[3] = "a";

        //Act
        encostData.createDevice(array);

        // Assert 
        //encostData.checkDataset(1) checks data at position 1
        Assertions.assertEquals("Error device not in dataset", encostData.checkDataset(1));
    }

    @Test
    @DisplayName("Test goodNoConnectionGraphData - Tests when the system loads a valid device with no connection into EncostData dataset")
    public void goodNoConnectionGraphData(){
        // Arrange
        EncostData encostData = new EncostData();
        String[] array = new String[8];
        array[1] = "12/12/12";
        array[3] = "Light bulb";

        //Act
        encostData.createDevice(array);

        // Assert 
        //encostData.checkDataset(1) checks data at position 1
        Assertions.assertEquals("device in dataset, no connection", encostData.checkDataset(1));
    }

    @Test
    @DisplayName("Test goodConnectionGraphData - Tests when the system loads a valid device with a connection into EncostData dataset")
    public void goodConnectionGraphData(){
        // Arrange
        EncostData encostData = new EncostData();
        String[] array = new String[8];
        array[1] = "12/12/12";
        array[3] = "Light bulb";
        array[5] = "EWR1234";

        //Act
        encostData.createDevice(array);

        // Assert 
        //encostData.checkDataset(1) checks data at position 1
        Assertions.assertEquals("device in dataset, connected to EWR1234", encostData.checkDataset(1));
    }

    /** <=============================================>
     *  This section is for Graph Visualisation tests
     *  <=============================================>
     **/

    @Test
    @DisplayName("Test badGraphVisual - Tests when the system loads two invalid nodes into graphStream")
    public void badGraphVisual(){
        // Arrange
        GraphStream graph = new GraphStream();
        //Act
        Node node1 = new node("invalid");
        Node node2 = new node("invalid");
        graph.addNode(node1);
        graph.addNode(node2);

        // Assert 
        Assertions.assertEquals("Error node not in GraphStream", graph.nodeInGraph(node1));
    }

    @Test
    @DisplayName("Test badNode2GraphVisual - Tests when the system loads a node and the node you want to connect is invalid into graphStream")
    public void badNode2GraphVisual(){
        // Arrange
        GraphStream graph = new GraphStream();
        //Act
        Node node1 = new node("valid");
        Node node2 = new node("invalid");
        node1.addConnection(node2);
        graph.addNode(node1);
        graph.addNode(node2);

        // Assert 
        Assertions.assertEquals("Error: can't connect to node 2", graph.nodeInGraph(node1));
    }

    @Test
    @DisplayName("Test badNode1GraphVisual - Tests when the system loads a invalid node and the node wanting to connect is valid into graphStream")
    public void badNode1GraphVisual(){
        // Arrange
        GraphStream graph = new GraphStream();
        //Act
        Node node1 = new node("invalid");
        Node node2 = new node("valid");
        node2.addConnection(node1);
        graph.addNode(node1);
        graph.addNode(node2);

        // Assert 
        Assertions.assertEquals("Error node not in GraphStream", graph.nodeInGraph(node1));
    }

    @Test
    @DisplayName("Test goodGraphVisual - Tests when the system loads two valid nodes connected to each other into graphStream")
    public void goodGraphVisual(){
        // Arrange
        GraphStream graph = new GraphStream();
        //Act
        Node node1 = new node("valid");
        Node node2 = new node("valid");
        node1.addConnection(node2);
        node2.addConnection(node1);
        graph.addNode(node1);
        graph.addNode(node2);

        // Assert 
        Assertions.assertEquals("node in GraphStream, connected to node2", graph.nodeInGraph(node1));
    }
}