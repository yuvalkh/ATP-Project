package JUnit;

import algorithms.search.*;
import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;
import sun.font.CreatedFontTracker;

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
        ISearchable maze;
        for (int i = 10; i <= 100; i += 10) {
            maze = new SearchableMaze(i, i);
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

    @Test
    public void TestBestFSonMyMaze(){
        ISearchingAlgorithm bfs = new BestFirstSearch();
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze;
        SearchableMaze searchableMaze;
        ArrayList<AState> solutionPath;
        for (int i = 1; i <= 10; i += 1) {
            for (int j = 1; j < 10; j++) {
                maze = mg.generate(i, j);
                searchableMaze = new SearchableMaze(maze);
                Solution solution = bfs.solve(searchableMaze);
                searchableMaze.print();
                System.out.println();
                solutionPath = solution.getSolutionPath();
                for (int k = 0; k < solutionPath.size(); k++) {
                    searchableMaze.setMazeInfo(((MazeState) solutionPath.get(k)).getCurrentPosition().getRowIndex(), ((MazeState) solutionPath.get(k)).getCurrentPosition().getColumnIndex(), 4);
                }
                searchableMaze.print();
                for (int k = 0; k < solutionPath.size(); k++) {
                    System.out.println(String.format("%s.  %s", k, solutionPath.get(k)));
                }
                System.out.println();
            }
        }
    }

    @Test
    public void testBestFS(){
        ArrayList<AState> solutionPath;
        IMazeGenerator mg = new EmptyMazeGenerator();
        ISearchingAlgorithm bfs = new BestFirstSearch();
        Maze maze = mg.generate(10,10);
        maze.setStartPosition(new Position(0,0));
        maze.setGoalPosition(new Position(9,9));
        for (int i = 1; i < 10; i++) {
            maze.setMazeInfo(i,0,1);
            maze.setMazeInfo(i,2,1);
        }
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        bfs.solve(searchableMaze);
        Solution solution = bfs.solve(searchableMaze);
        solutionPath = solution.getSolutionPath();
        for (int k = 0; k < solutionPath.size(); k++) {
            searchableMaze.setMazeInfo(((MazeState) solutionPath.get(k)).getCurrentPosition().getRowIndex(), ((MazeState) solutionPath.get(k)).getCurrentPosition().getColumnIndex(), 12);
        }
        searchableMaze.print();
        for (int k = 0; k < solutionPath.size(); k++) {
            System.out.println(String.format("%s.  %s", k, solutionPath.get(k)));
        }
    }
}
