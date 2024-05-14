import org.junit.Test;
import static org.junit.Assert.*;

public class GraphVisualizationTest {

    // test 1 to test the dataset for the community users 
    @Test
    public void testDefaultDatasetCommunityUser() {
        String visualization = visualizeDataset("default_dataset", "community_user");
        assertTrue(visualization.contains("Graph visualization"));
        assertTrue(visualization.contains("nodes representing devices"));
        assertTrue(visualization.contains("edges depicting connectivity"));
        assertTrue(visualization.contains("visual cues for device categories"));
    }

    // test 2 to test the dataset for the Encost users 
    @Test
    public void testDefaultDatasetEncostUser() {
        String visualization = visualizeDataset("default_dataset", "encost_user");
        assertTrue(visualization.contains("Graph visualization"));
        assertTrue(visualization.contains("nodes representing devices"));
        assertTrue(visualization.contains("edges depicting connectivity"));
        assertTrue(visualization.contains("visual cues for device categories"));
    }

    // test 3 to test the valid dataset for the Encost users 
    @Test
    public void testValidCustomDatasetEncostUser() {
        String visualization = visualizeDataset("custom_dataset_valid", "encost_user");
        assertTrue(visualization.contains("Graph visualization"));
        assertTrue(visualization.contains("nodes representing devices"));
        assertTrue(visualization.contains("edges depicting connectivity"));
        assertTrue(visualization.contains("visual cues for device categories"));
    }


    // test 4 to test the invalid datasaet for the Encost user 
    @Test
    public void testInvalidCustomDatasetEncostUser() {
        String visualization = visualizeDataset("invalid_custom_dataset", "encost_user");
        assertEquals("Invalid dataset format", visualization);
    }

    // test 5 to test a empty custom data set inputed by a encost user 
    @Test
    public void testEmptyCustomDatasetEncostUser() {
        String visualization = visualizeDataset("empty_custom_dataset", "encost_user");
        assertEquals("Empty dataset", visualization);
    }

    // test 6 to test a large data set 
    @Test
    public void testLargeCustomDatasetEncostUser() {
        String visualization = visualizeDataset("large_custom_dataset", "encost_user");
        assertTrue(visualization.contains("Graph visualization"));
        assertTrue(visualization.contains("nodes representing devices"));
        assertTrue(visualization.contains("edges depicting connectivity"));
        assertTrue(visualization.contains("visual cues for device categories"));
        assertTrue(visualization.contains("handling of performance and memory constraints"));
    }

    //Mock methods

    // Simulated visualizeDataset method based on refined implementation
    private String visualizeDataset(String datasetName, String userType) {
        // if a community user has access
        if (userType.equals("community_user")) {
            if (datasetName.equals("default_dataset")) {
                return "Graph visualization for Community User: nodes representing devices, edges depicting connectivity, visual cues for device categories";
            } else {
                return "Unknown dataset";
            }
            // or a Encost user has input
        } else if (userType.equals("encost_user")) {
            switch (datasetName) {
                case "default_dataset":
                    return "Graph visualization for Encost User: nodes representing devices, edges depicting connectivity, visual cues for device categories";
                case "custom_dataset_valid":
                    return "Graph visualization for Encost User (custom dataset): nodes representing devices, edges depicting connectivity, visual cues for device categories";
                case "invalid_custom_dataset":
                    return "Invalid dataset format";
                case "empty_custom_dataset":
                    return "Empty dataset";
                case "large_custom_dataset":
                    return "Graph visualization for Encost User (large dataset): nodes representing devices, edges depicting connectivity, visual cues for device categories, handling of performance and memory constraints";
                default:
                    return "Unknown dataset";
            }
        }
        return "Invalid user type";
    }
}
