import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class CategorizingSmartHomeDevicesTest {

    // Mock EncostSmartDevice class representing smart devices
    static class EncostSmartDevice {
        private String deviceName;
        private String deviceType;
        private String category;

        public EncostSmartDevice(String deviceName, String deviceType) {
            this.deviceName = deviceName;
            this.deviceType = deviceType;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }

    // test 1 to test valid devices 
    @Test
    public void testCategorizeValidDevices() {
        List<EncostSmartDevice> devices = createSampleDevicesFromAllCategories();
        assertNotNull(devices);

        for (EncostSmartDevice device : devices) {
            categorizeDevice(device);
            assertNotNull(device.getCategory());
        }
    }

    // test 2 to document the category errors
    @Test
    public void testDeviceCategoryError() {
        List<EncostSmartDevice> devices = createInvalidDeviceDataset();
        assertNotNull(devices);
    
        for (EncostSmartDevice device : devices) {
            categorizeDevice(device);
            assertNotNull(device.getCategory());
            assertEquals("Error", device.getCategory());
        }
    }


    // test 3 to test the empty dataset input
    @Test
    public void testEmptyDataset() {
        List<EncostSmartDevice> devices = createEmptyDataset();
        assertTrue(devices.isEmpty());
    }

    // test 4 to test a singular device connection
    @Test
    public void testSingleDeviceDataset() {
        List<EncostSmartDevice> devices = createSingleDeviceDataset();
        assertEquals(1, devices.size());

        EncostSmartDevice singleDevice = devices.get(0);
        categorizeDevice(singleDevice);
        assertNotNull(singleDevice.getCategory());
    }

    // mock method to initilaise devices 
    private void categorizeDevice(EncostSmartDevice device) {
        String deviceType = device.getDeviceType().toLowerCase();
        if (deviceType.contains("router")) {
            device.setCategory("Encost WiFi Routers");
        } else if (deviceType.contains("hub") || deviceType.contains("controller")) {
            device.setCategory("Encost Hubs/Controllers");
        } else if (deviceType.contains("light")) {
            device.setCategory("Encost Smart Lighting");
        } else if (deviceType.contains("appliance")) {
            device.setCategory("Encost Smart Appliances");
        } else if (deviceType.contains("white ware")) {
            device.setCategory("Encost Smart White ware");
        } else {
            device.setCategory("Error");
        }
    }

    private List<EncostSmartDevice> createSampleDevicesFromAllCategories() {
        List<EncostSmartDevice> devices = new ArrayList<>();

        // Devices from all five categories
        devices.add(new EncostSmartDevice("WiFi Router 1", "Router"));
        devices.add(new EncostSmartDevice("Smart Hub", "Hub"));
        devices.add(new EncostSmartDevice("Smart Light Bulb", "Light"));
        devices.add(new EncostSmartDevice("Smart Oven", "Appliance"));
        devices.add(new EncostSmartDevice("Smart Dishwasher", "White Ware"));

        return devices;
    }

    // list to create a set of invalid devices 
    private List<EncostSmartDevice> createInvalidDeviceDataset() {
        List<EncostSmartDevice> devices = new ArrayList<>();

        // Devices with invalid names or types
        devices.add(new EncostSmartDevice("Invalid Device", "InvalidType"));

        return devices;
    }

    private List<EncostSmartDevice> createEmptyDataset() {
        return new ArrayList<>(); // Return an empty list
    }

    private List<EncostSmartDevice> createSingleDeviceDataset() {
        List<EncostSmartDevice> devices = new ArrayList<>();

        // Single device dataset
        devices.add(new EncostSmartDevice("Smart Thermostat", "Thermostat"));

        return devices;
    }
}
