package test;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;
import java.util.ArrayList;
public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1000, 1000);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm
            searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
                //Printing Solution Path
                System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
    }
}




/*
package test;
import JUnit.JUnitTestingBestFirstSearch;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;
import java.util.ArrayList;
public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();

        long sTime=System.currentTimeMillis();
        Maze maze = mg.generate(70, 70);
        System.out.println(System.currentTimeMillis()-sTime);
        System.out.println();

        SearchableMaze searchableMaze = new SearchableMaze(maze);
        searchableMaze.print();
        solveProblem(searchableMaze, new BreadthFirstSearch(),10);
        solveProblem(searchableMaze, new DepthFirstSearch(),11);
        solveProblem(searchableMaze, new BestFirstSearch(),12);
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm
            searcher, int num) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated:%s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
                //Printing Solution Path
                System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        ////////////////////////
        for (int i = 0; i < solutionPath.size(); i++) {
            ((SearchableMaze) domain).setMazeInfo(((MazeState) solutionPath.get(i)).getCurrentPosition().getRowIndex(), ((MazeState) solutionPath.get(i)).getCurrentPosition().getColumnIndex(), num);
        }
        ((SearchableMaze)domain).print();
        for (int i = 0; i < solutionPath.size(); i++) {
            ((SearchableMaze) domain).setMazeInfo(((MazeState) solutionPath.get(i)).getCurrentPosition().getRowIndex(), ((MazeState) solutionPath.get(i)).getCurrentPosition().getColumnIndex(), 0);
        }
        //////////////////////////
        for (int i = 0; i < solutionPath.size(); i++) {
         //   System.out.println(String.format("%s.  %s",i,solutionPath.get(i)));
        }

    }
}
*/