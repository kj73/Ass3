import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class SmartDeviceGraphTest {

    // Define a simple representation of a smart device
    private static class SmartDevice {
        private String name;
        private String type;

        public SmartDevice(String name, String type) {
            this.name = name;
            this.type = type;
        }

      

        public String getType() {
            return type;
        }
    }

    // graph data structure to represent connections between devices
    private static class DeviceGraph {
        private Map<SmartDevice, Set<SmartDevice>> adjacencyList;

        public DeviceGraph() {
            this.adjacencyList = new HashMap<>();
        }

        public void addDevice(SmartDevice device) {
            adjacencyList.putIfAbsent(device, new HashSet<>());
        }

        public void addConnection(SmartDevice device1, SmartDevice device2) {
            adjacencyList.get(device1).add(device2);
            adjacencyList.get(device2).add(device1);
        }

        public int getNodeCount() {
            return adjacencyList.size();
        }
    }

    // Test 1 to check graph is being implemented 
    @Test
    public void testValidGraphCreation() {
        List<SmartDevice> devices = createSampleDevices();
        DeviceGraph graph = buildGraph(devices);
        
        assertNotNull(graph);
        assertEquals(devices.size(), graph.getNodeCount());
    }

    // test 2 to see if graph is empty
    @Test
    public void testEmptyGraphCreation() {
        List<SmartDevice> devices = Collections.emptyList();
        DeviceGraph graph = buildGraph(devices);
        
        assertNotNull(graph);
        assertEquals(0, graph.getNodeCount());
    }

    // test 3 to check if the graph has one connected device
    @Test
    public void testSingleDeviceGraphCreation() {
        SmartDevice device = createSingleDevice();
        DeviceGraph graph = buildGraph(Collections.singletonList(device));
        
        assertNotNull(graph);
        assertEquals(1, graph.getNodeCount());
        
    }

    // Helper methods to create sample devices for testing
    private List<SmartDevice> createSampleDevices() {
        List<SmartDevice> devices = new ArrayList<>();
        devices.add(new SmartDevice("Device A", "Type 1"));
        devices.add(new SmartDevice("Device B", "Type 2"));
        devices.add(new SmartDevice("Device C", "Type 1"));
        // Add more devices as needed
        return devices;
    }

    private SmartDevice createSingleDevice() {
        return new SmartDevice("Single Device", "Type X");
    }

    // Method to build a graph based on a list of devices
    private DeviceGraph buildGraph(List<SmartDevice> devices) {
        DeviceGraph graph = new DeviceGraph();
        for (SmartDevice device : devices) {
            graph.addDevice(device);
        }
        
        // Example: Connect devices based on shared type
        for (int i = 0; i < devices.size(); i++) {
            for (int j = i + 1; j < devices.size(); j++) {
                if (devices.get(i).getType().equals(devices.get(j).getType())) {
                    graph.addConnection(devices.get(i), devices.get(j));
                }
            }
        }
        return graph;
    }
}
