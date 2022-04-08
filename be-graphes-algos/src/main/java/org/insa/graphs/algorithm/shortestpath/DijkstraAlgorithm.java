package org.insa.graphs.algorithm.shortestpath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.Label;


public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        Graph graph = data.getGraph();
        final int nbNodes = graph.size();
        List<Node> tabNode = graph.getNodes();
        // TODO:
        List<Label> tabLabel = new ArrayList<Label>() ;
        for (int i=0; i<nbNodes; i++){
            tabLabel.add(new Label(i, false, Double.POSITIVE_INFINITY, null));
        }
        boolean found=false ;
        Node depart = data.getOrigin() ;
        while (!found){
            for (int i=0; i<nbNodes; i++){
                for (int j=0; j<depart.getSuccessors().size();j++){
                    if (depart.getSuccessors().get(j).getDestination().compareTo(tabNode.get(i))==1) {
                        if (!tabLabel.get(i).getMarque()){
                            if (tabLabel.get(i).getCost()>tabLabel.get(depart.getId()).getCost() + depart.getSuccessors().get(j).getMinimumTravelTime()){
                                tabLabel.get(i).setCost(tabLabel.get(depart.getId()).getCost() + depart.getSuccessors().get(j).getMinimumTravelTime()) ;
                                tabLabel.get(i).setPere(depart.getSuccessors().get(j)) ;
                            }
                        }
                    }
                }

//La prochaine fois : Là on a changé les valeurs en partant de départ pour tous les labels si on avait une distance plus petite. Maintenant, tu dois comparer tous les labels, et le plus petit doit devenir depart et sa marque doit devenir vraie.



            }





        }










        return solution;
    }

}
