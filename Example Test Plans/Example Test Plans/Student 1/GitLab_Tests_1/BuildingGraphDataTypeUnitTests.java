import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.util.ArrayList;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;

/**
 * BlackBox Tests to conduct on the high priority requirements 'Building a Graph
 * Data Type' for the ESGP software
 * Based on the given SRS and Software Design Specification proposed by student
 * 5
 * Blackbox tests further outlined in my Software Testing Plan
 * 
 * @author Student 1
 * @version 1.0
 */
public class BuildingGraphDataTypeUnitTests {

    Graph graph;

    /**
     * Sets up a new Graph for each test
     */
    @BeforeEach
    public void setUpGraph() {
        Graph graph = new SingleGraph("Test graph");
    }

    /**
     * Test that the devices are added as node to the graph using the GraphData
     * addNode Method
     */
    @Test
    @DisplayName("Test that device added as node to graph using addNode")
    void deviceGraphDataTypeAddNodeTest() {

        Device device = new Device("EWR-1234,01/04/22,Encost Router 360,Router,WKO-1234,-,Yes,Yes");

        // Setup actual
        GraphDataType graphData = new GraphDataType();
        graphData.addNode(device);

        // Setup expected
        graph.addNode(device);

        assertEquals(graph.nodes(), graphData.getGraph.nodes());
    }

    /**
     * Test that the devices are added as nodes to the graph
     */
    @Test
    @DisplayName("Test that devices added as nodes to graph")
    void deviceGraphAddNodesTest() {

        ArrayList<Device> devices = new ArrayList<>();
        devices.add(new Device("EWR-1234,01/04/22,Encost Router 360,Router,WKO-1234,-,Yes,Yes"));
        devices.add(new Device(
                "ELB-4567,01/04/22,Encost Smart Bulb B22 (multi colour),Light bulb,WKO-1234,EWR-1234,No,Yes"));
        devices.add(new Device("EK-9876,07/05/22,Encost Smart Jug,Kettle,WKO-1234,EWR-1234,No,Yes"));
        devices.add(new Device("EHC-2468,01/04/22,Encost Smart Hub 2.0,Hub/ Controller,WKO-1234,EWR-1234,Yes,Yes"));

        // Setup actual
        GraphDataType graphData = new GraphDataType();
        graphData.setDevices(devices);

        // Setup expected
        for (Device device : devices) {
            graph.addNode(device);
        }

        assertEquals(graph.nodes(), graphData.getGraph.nodes());
    }

    /**
     * Test that the devices have the correct edges added to the graph
     */
    @Test
    @DisplayName("Test that devices have edges added to graph")
    void deviceGraphAddEdgesTest() {

        ArrayList<Device> devices = new ArrayList<>();
        devices.add(new Device("EWR-1234,01/04/22,Encost Router 360,Router,WKO-1234,-,Yes,Yes"));
        devices.add(new Device(
                "ELB-4567,01/04/22,Encost Smart Bulb B22 (multi colour),Light bulb,WKO-1234,EWR-1234,No,Yes"));
        devices.add(new Device("EK-9876,07/05/22,Encost Smart Jug,Kettle,WKO-1234,EWR-1234,No,Yes"));
        devices.add(new Device("EHC-2468,01/04/22,Encost Smart Hub 2.0,Hub/ Controller,WKO-1234,EWR-1234,Yes,Yes"));

        // Setup actual
        GraphDataType graphData = new GraphDataType();
        graphData.setDevices(devices);

        // Setup expected
        for (Device device : devices) {
            graph.addNode(device);
        }
        graph.addEdge(devices[0].getRouterConnection(), devices[0], devices[1]);
        graph.addEdge(devices[0].getRouterConnection(), devices[0], devices[2]);
        graph.addEdge(devices[0].getRouterConnection(), devices[0], devices[3]);

        assertEquals(graph.edges(), graphData.getGraph.edges());
    }
}
