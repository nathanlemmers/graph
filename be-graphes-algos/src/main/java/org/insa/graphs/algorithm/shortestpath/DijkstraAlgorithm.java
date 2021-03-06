package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.Label;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    protected final ShortestPathData data = getInputData();
    protected BinaryHeap<Label> tas = new BinaryHeap<Label>();
    protected List<Arc> chemin = new ArrayList<Arc>();
    protected ShortestPathSolution solution = null;
    protected Graph graph = data.getGraph();
    protected final int nbNodes = graph.size();
    protected List<Node> tabNode = graph.getNodes();
    protected Node fin = data.getDestination();
    protected List<Label> tabLabel = new ArrayList<Label>();
    protected Node depart = data.getOrigin();

    protected void init() {
        for (int i = 0; i < nbNodes; i++) {
            if (tabNode.get(i).compareTo(depart) == 0) {
                tabLabel.add(new Label(i, true, 0, null));
                tas.insert(tabLabel.get(i));
            } else {
                tabLabel.add(new Label(i, false, Float.POSITIVE_INFINITY, null));
            }
        }
    }

    @Override
    protected ShortestPathSolution doRun() {
        init();
        notifyOriginProcessed(depart);
        Label Labeldest = tabLabel.get(tabNode.indexOf(fin));
        Label min;
        int index;
        Label successeur;
        Arc a;
        while (!Labeldest.getMarque() && !tas.isEmpty()) {
            min = tas.findMin();
            index = min.getId();
            tabLabel.get(index).setMarque(true);
            notifyNodeMarked(tabNode.get(index));
            for (int i = 0; i < tabNode.get(index).getSuccessors().size(); i++) {
                successeur = tabLabel.get(tabNode.indexOf(tabNode.get(index).getSuccessors().get(i).getDestination()));
                a = tabNode.get(index).getSuccessors().get(i);
                if (!successeur.getMarque() && data.isAllowed(a)) {
                    if (successeur.getCost() > data.getCost(a) + min.getCost()) {
                        if (!Float.isInfinite(successeur.getCost())) {
                            tas.remove(successeur);
                        } else {
                            notifyNodeReached(a.getDestination());
                        }
                        successeur.setCost((float) data.getCost(a) + min.getCost());
                        successeur.setPere(a);
                        tas.insert(successeur);
                    }
                }
            }
            tas.remove(min);
        }
        notifyDestinationReached(fin);
        while (Labeldest.getPere() != null) {
            chemin.add(Labeldest.getPere());
            Labeldest = tabLabel.get(tabNode.indexOf(Labeldest.getPere().getOrigin()));
        }
        Collections.reverse(chemin);
        if (tabLabel.get(fin.getId()).getMarque()) {
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, chemin));
        } else {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        return solution;
    }

    public ShortestPathSolution getRun() {
        return this.doRun();
    }

}
