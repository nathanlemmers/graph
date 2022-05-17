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
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
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
        boolean result = true;
        for (int i = 0; i < 5; i++) {
            Node node1 = graph.get((int) (Math.random() * 150 + 50));
            Node node2 = graph.get((int) (Math.random() * 150 + 50));
            ShortestPathData data = new ShortestPathData(graph, node1, node2,
                    ArcInspectorFactory.getAllFilters().get(4));
            DijkstraAlgorithm algo = new DijkstraAlgorithm(data);
            BellmanFordAlgorithm algoB = new BellmanFordAlgorithm(data);
            AStarAlgorithm algoC = new AStarAlgorithm(data);
            ShortestPathSolution dij;
            ShortestPathSolution Bel;
            ShortestPathSolution AStar;
            try {
                dij = algo.run();
                Bel = algoB.run();
                AStar = algoC.run();
                if (AStar.getStatus() == Status.INFEASIBLE && Bel.getStatus() != Status.INFEASIBLE) {
                    result = false;
                    break;
                } else if (dij.getPath().getLength() != dij.getPath().getLength()) {
                    System.out.println(AStar.getPath().getLength() + " et " + Bel.getPath().getLength() + "\n");
                    result = false;
                    break;
                }
            } catch (NullPointerException e) {
                continue;
            }

        }
        if (result) {
            System.out.println("Fonctionnel\n ");
        } else {
            System.out.println("Erreur\n ");
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
