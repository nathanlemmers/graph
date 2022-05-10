package org.insa.graphs.model;

public class LabelStar extends Label implements Comparable<Label> {

    private float vol;

    public LabelStar(int som, boolean marque, float cout, Arc pere, float vole) {
        super(som, marque, cout, pere);
        this.vol = vole;
    }

    public float getTotalCost() {
        return (this.getCost() + this.vol);
    }

}