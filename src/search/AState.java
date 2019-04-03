package search;

public class AState {
    AState cameFrom;
    String stateName;
    double cost;

    public AState(String state,AState from,double cost){
        stateName = state;
        this.cost = cost;
        cameFrom = from;
    }
}
