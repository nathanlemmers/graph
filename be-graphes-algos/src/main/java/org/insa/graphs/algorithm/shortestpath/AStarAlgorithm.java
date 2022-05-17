package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.LabelStar;
import java.lang.Math;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }

    public float getVol(Node d, Node f) {
        double a = d.getPoint().distanceTo(f.getPoint());
        return (float) a;
    }

    @Override
    protected void init() {

        for (int i = 0; i < nbNodes; i++) {
            if (tabNode.get(i).compareTo(depart) == 0) {
                tabLabel.add(new LabelStar(i, true, 0, null, this.getVol(tabNode.get(i), fin)));
                tas.insert(tabLabel.get(i));
            } else {
                tabLabel.add(new LabelStar(i, false, Float.POSITIVE_INFINITY, null, this.getVol(tabNode.get(i), fin)));

            }
        }
    }

}
