package org.insa.graphs.model;

public class Label implements Comparable<Label> {

    private int id;
    private boolean ok;
    private float cost;
    private Arc arc;

    public Label(int som, boolean marque, float cout, Arc pere) {
        this.id = som;
        this.ok = marque;
        this.cost = cout;
        this.arc = pere;
    }

    public int getId() {
        return this.id;
    }

    public float getCost() {
        return this.cost;
    }

    public boolean getMarque() {
        return this.ok;
    }

    public Arc getPere() {
        return this.arc;
    }

    public int associate() {
        return this.id;
    }

    public void setCost(float cout) {
        this.cost = cout;
    }

    public void setPere(Arc pere) {
        this.arc = pere;
    }

    public void setMarque(boolean b) {
        this.ok = b;
    }

    public int compareTo(Label autre) {
        float res = this.cost - autre.cost;
        if (res < 0) {
            return -1;
        } else if (res == 0) {
            return 0;
        } else {
            return 1;
        }
    }

}