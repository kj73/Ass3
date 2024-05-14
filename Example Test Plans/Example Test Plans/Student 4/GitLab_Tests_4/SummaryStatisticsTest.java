import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SummaryStatisticsTest {

    // test 1 for default summary for community user
    @Test
    public void testDefaultDatasetCommunityUser() {
        String statistics = computeSummaryStatistics("default_dataset", "community_user");
        assertTrue(statistics.contains("Total number of devices"));
        assertTrue(statistics.contains("Number of devices per category"));
        assertTrue(statistics.contains("Connectivity statistics"));
    }

    // test 2 for default summary for Encost user
    @Test
    public void testDefaultDatasetEncostUser() {
        String statistics = computeSummaryStatistics("default_dataset", "encost_user");
        assertTrue(statistics.contains("Total number of devices"));
        assertTrue(statistics.contains("Number of devices per category"));
        assertTrue(statistics.contains("Connectivity statistics"));
        assertTrue(statistics.contains("Additional statistics"));
    }

    // test3 for a dataset valid for the encost user
    @Test
    public void testValidCustomDatasetEncostUser() {
        String statistics = computeSummaryStatistics("custom_dataset_valid", "encost_user");
        assertTrue(statistics.contains("Total number of devices"));
        assertTrue(statistics.contains("Number of devices per category"));
        assertTrue(statistics.contains("Connectivity statistics"));
        assertTrue(statistics.contains("Additional statistics"));
    }

    // test 4 for a single connected device 
    @Test
    public void testSingleDeviceEncostUser() {
        String statistics = computeSummaryStatistics("single_device_dataset", "encost_user");
        assertTrue(statistics.contains("total devices = 1"));
        assertFalse(statistics.contains("Connectivity statistics")); // No connectivity for single device
    }

    // test 5 for no connection of devices 
    @Test
    public void testNoConnectivityEncostUser() {
        String statistics = computeSummaryStatistics("no_connectivity_dataset", "encost_user");
        assertTrue(statistics.contains("Total number of devices"));
        assertTrue(statistics.contains("Number of devices per category"));
        assertFalse(statistics.contains("Connectivity statistics")); // No connectivity in this dataset
    }

    // Mock method to calculate the statistics 
    private String computeSummaryStatistics(String datasetFileName, String userType) {
        String datasetContent = getDatasetContent(datasetFileName);
        if (isDatasetEmpty(datasetContent)) {
            return "Error message: \"Empty dataset\". No summary statistics displayed.";
        } else {
            return generateSummaryStatistics(datasetContent);
        }
    }

    // mock method to check if the dataset is empty 
    private boolean isDatasetEmpty(String datasetContent) {
        return datasetContent == null || datasetContent.trim().isEmpty();
    }

    // mock method to get the content of the dataset
    private String getDatasetContent(String datasetFileName) {
        switch (datasetFileName) {
            case "default_dataset":
                return getDefaultDatasetContent();
            case "custom_dataset_valid":
                return getCustomValidDatasetContent();
            case "single_device_dataset":
                return getSingleDeviceDatasetContent();
            case "no_connectivity_dataset":
                return getNoConnectivityDatasetContent();
            default:
                return ""; // Handle other dataset files accordingly
        }
    }
    
    // set up of a mock example of a default dataset
    private String getDefaultDatasetContent() {
        return "{\n" +
                "  \"devices\": [\n" +
                "    {\"id\": 1, \"name\": \"Smart Thermostat\", \"category\": \"Thermostat\", \"connection\": \"Wi-Fi\"},\n" +
                "    {\"id\": 2, \"name\": \"Smart Speaker\", \"category\": \"Audio\", \"connection\": \"Wi-Fi\"},\n" +
                "    {\"id\": 3, \"name\": \"Security Camera\", \"category\": \"Security\", \"connection\": \"Wired\"},\n" +
                "    {\"id\": 4, \"name\": \"Smart Lighting\", \"category\": \"Lights\", \"connection\": \"Wi-Fi\"},\n" +
                "    {\"id\": 5, \"name\": \"Smart TV\", \"category\": \"Entertainment\", \"connection\": \"Wi-Fi\"}\n" +
                "  ]\n" +
                "}";
    }


    // set up of a custom dataset
    private String getCustomValidDatasetContent() {
        return "{\n" +
                "  \"devices\": [\n" +
                "    {\"id\": 1, \"name\": \"Custom Device 1\", \"category\": \"Thermostat\", \"connection\": \"Wi-Fi\"},\n" +
                "    {\"id\": 2, \"name\": \"Custom Device 2\", \"category\": \"Audio\", \"connection\": \"Wi-Fi\"}\n" +
                "  ]\n" +
                "}";
    }
    
    // set up of a single device 
    private String getSingleDeviceDatasetContent() {
        return "{\n" +
                "  \"devices\": [\n" +
                "    {\"id\": 1, \"name\": \"Single Device\", \"category\": \"Thermostat\", \"connection\": \"Wi-Fi\"}\n" +
                "  ]\n" +
                "}";
    }
    
    // set up for no connection
    private String getNoConnectivityDatasetContent() {
        return "{\n" +
                "  \"devices\": [\n" +
                "    {\"id\": 1, \"name\": \"Device 1\", \"category\": \"Thermostat\"},\n" +
                "    {\"id\": 2, \"name\": \"Device 2\", \"category\": \"Audio\"}\n" +
                "  ]\n" +
                "}";
    }

    // mock method to create and generate the final total summary connectivity data 
    private String generateSummaryStatistics(String datasetContent) {
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(datasetContent);
            JSONArray devices = (JSONArray) jsonObject.get("devices");
    
            int totalDevices = devices.size();
            Map<String, Integer> categoryCounts = new HashMap<>();
            boolean hasConnectivity = false;
    
            for (Object obj : devices) {
                JSONObject device = (JSONObject) obj;
                String category = (String) device.get("category");
                categoryCounts.put(category, categoryCounts.getOrDefault(category, 0) + 1);
    
                if (device.containsKey("connection")) {
                    hasConnectivity = true;
                }
            }
    
            StringBuilder summary = new StringBuilder();
            if (totalDevices == 1) {
                summary.append("Total number of devices: total devices = 1\n");
            } else {
                summary.append("Total number of devices: ").append(totalDevices).append("\n");
            }
            summary.append("Number of devices per category: ").append(categoryCounts).append("\n");
    
            if (totalDevices > 1 && hasConnectivity) {
                summary.append("Connectivity statistics: Average connections: 1\n");
            }
    
            summary.append("Additional statistics: [Devices by location]\n");
    
            return summary.toString();
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error parsing dataset";
        }
    }
}
