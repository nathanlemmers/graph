package org.insa.graphs.gui.simple;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.gui.drawing.Drawing;
import org.insa.graphs.gui.drawing.components.BasicDrawing;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.Point;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.PathReader;

public class Launch {

    /**
     * Create a new Drawing inside a JFrame an return it.
     * 
     * @return The created drawing.
     * 
     * @throws Exception if something wrong happens when creating the graph.
     */
    public static Drawing createDrawing() throws Exception {
        BasicDrawing basicDrawing = new BasicDrawing();
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("BE Graphes - Launch");
                frame.setLayout(new BorderLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setSize(new Dimension(800, 600));
                frame.setContentPane(basicDrawing);
                frame.validate();
            }
        });
        return basicDrawing;
    }

    public static void main(String[] args) throws Exception {

        // Visit these directory to see the list of available files on Commetud.
        final String mapName = "/home/lemmers/Bureau/3MIC/BE-Graph/Maps/insa.mapgr";
        final String pathName = "/home/lemmers/Bureau/3MIC/BE-Graph/Paths/path_fr31insa_rangueil_r2.path";

        // Create a graph reader.
        final GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

        // TODO: Read the graph.
        final Graph graph = reader.read();

        // test1 ;
        Node node1 = graph.get(283);
        Node node2 = graph.get(127);
        ShortestPathData data = new ShortestPathData(graph, node1, node2, ArcInspectorFactory.getAllFilters().get(4));
        DijkstraAlgorithm algo = new DijkstraAlgorithm(data);
        BellmanFordAlgorithm algoB = new BellmanFordAlgorithm(data);
        ShortestPathSolution dij = algo.run();
        ShortestPathSolution Bel = algoB.run();
        if (dij.getStatus() == Status.INFEASIBLE && Bel.getStatus() == Status.INFEASIBLE) {
            System.out.println("Super ! Les deux sont infaisable");
        } else if (dij.getPath().getMinimumTravelTime() == Bel.getPath().getMinimumTravelTime()) {
            System.out.println("Bien joué ! ça marche ! " + algo.getRun().getPath().getMinimumTravelTime()
                    + "\n" + algoB.getRun().getPath().getMinimumTravelTime() + "\n");
        } else {
            System.out.println("Non... ! " + algo.getRun().getPath().getMinimumTravelTime() + "\n"
                    + algoB.getRun().getPath().getMinimumTravelTime());
        }

        // Create the drawing:
        final Drawing drawing = createDrawing();

        // TODO: Draw the graph on the drawing.

        // TODO: Create a PathReader.
        final PathReader pathReader = null;

        // TODO: Read the path.
        final Path path = null;

        // TODO: Draw the path.
    }

}
