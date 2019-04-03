package algorithms.search;

public abstract class AState {
    private AState cameFrom;
    private String stateName;
    private double cost;

    public AState(String state,AState from,double cost){
        stateName = state;
        this.cost = cost;
        cameFrom = from;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public String getStateName() {
        return stateName;
    }

    public abstract boolean equal(AState state);
}
