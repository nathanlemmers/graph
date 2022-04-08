package org.insa.graphs.model;


public class Label {

private int id ;
private boolean ok ;
private double cost ;
private Arc arc ;


    public Label(int som, boolean marque, double cout, Arc pere) {
        this.id=som ;
        this.ok=marque ;
        this.cost=cout;
        this.arc=pere ;
    }

    public double getCost(){
        return this.cost ;
    }

    public boolean getMarque(){
        return this.ok ;
    }

    public int associate(){
        return this.id ;
    }

    public void setCost(double cout){
        this.cost=cout ;
    }

    public void setPere(Arc pere){
        this.arc=pere ;
    }

}