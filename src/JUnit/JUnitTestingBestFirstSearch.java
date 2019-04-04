package JUnit;

import algorithms.search.*;
import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class JUnitTestingBestFirstSearch {

    @Test
    public void sendingNullToBestFS() {
        ISearchingAlgorithm bfs = new BestFirstSearch();
        bfs.solve(null);
    }

    @Test
    public void Test2() {//test the implement for several mazes
        ISearchingAlgorithm bfs = new BestFirstSearch();
        for (int i = 10; i <= 100; i += 10) {
            ISearchable maze = new SearchableMaze(i, i);
            Solution solution = bfs.solve(maze);
            ((SearchableMaze) maze).print();
            ArrayList<AState> solutionPath = solution.getSolutionPath();
            for (int j = 0; j < solutionPath.size(); j++) {
                ((SearchableMaze) maze).setMazeInfo(((MazeState) solutionPath.get(j)).getCurrentPosition().getRowIndex(), ((MazeState) solutionPath.get(j)).getCurrentPosition().getColumnIndex(), 4);
            }
            ((SearchableMaze) maze).print();
            for (int j = 0; j < solutionPath.size(); j++) {
                System.out.println(String.format("%s.  %s", j, solutionPath.get(j)));
            }
        }
    }

    @Test
    public boolean Test3() {
        ISearchingAlgorithm bfs = new BestFirstSearch();
        return bfs.getName().equals("Best First Search");
    }
}
