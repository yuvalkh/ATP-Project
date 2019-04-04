package algorithms.search;

public interface ISearchingAlgorithm {
    /**
     *
     * @param s an object that we can search on him
     * @return a solution of the problem
     */
    Solution solve(ISearchable s);

    /**
     *
     * @return the name of the Searching Algorithm
     */
    String getName();

    /**
     *
     * @return the number of nodes the algorithm has Evaluated during his solve
     */
    int getNumberOfNodesEvaluated();

}
