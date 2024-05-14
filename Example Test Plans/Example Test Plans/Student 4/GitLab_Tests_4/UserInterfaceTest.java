import org.junit.Test;
import static org.junit.Assert.*;

public class UserInterfaceTest {

    // Test 1 to test the selected user either Community or Encost
    @Test
    public void testUserTypeSelection() {
        UserInterface ui = new UserInterface();
        String selectedUserType = ui.selectUserType("1");
        assertEquals("Community", selectedUserType);
    }
    // test 2 to test the user authetntication 
    @Test
    public void testEncostUserAuthentication() {
        UserInterface ui = new UserInterface();
        boolean isAuthenticated = ui.authenticateEncostUser("encost_user", "password");
        assertTrue(isAuthenticated);
    }

    // test 3 to test if the user has correctly inputed the dataset
    @Test
    public void testLoadCustomDataset() {
        UserInterface ui = new UserInterface();
        boolean isDatasetLoaded = ui.loadCustomDataset("encost_user", "dataset.csv");
        assertTrue(isDatasetLoaded);
    }

    // test 4 to test the graph visulation component 
    @Test
    public void testGraphVisualization() {
        UserInterface ui = new UserInterface();
        boolean isGraphDisplayed = ui.visualizeGraph("1");
        assertTrue(isGraphDisplayed);
    }

    // test 5 to test the summarary statistic component
    @Test
    public void testViewSummaryStatistics() {
        UserInterface ui = new UserInterface();
        String summaryStatistics = ui.viewSummaryStatistics("encost_user");
        assertNotNull(summaryStatistics);
        assertTrue(summaryStatistics.contains("Summary statistics"));
    }
}
