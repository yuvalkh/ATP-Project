package algorithms.search;

import org.omg.CORBA.WrongTransactionHelper;

import java.util.Comparator;

public abstract class AState {
    private AState cameFrom;
    private String stateName;
    private double cost;

    public AState(String state,AState from,double cost){
        stateName = state;
        this.cost = cost;
        cameFrom = from;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public String getStateName() {
        return stateName;
    }

    public abstract boolean equals(Object s);

    public abstract double getDistanceFromGoal(AState Goal);
}
