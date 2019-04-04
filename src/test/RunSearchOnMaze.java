/*package test;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;
import java.util.ArrayList;
public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(30, 30);
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
*/






package test;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;
import java.util.ArrayList;
public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(70, 70);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        //searchableMaze.print();
        solveProblem(searchableMaze, new BreadthFirstSearch(),10);
        //searchableMaze.print();
        solveProblem(searchableMaze, new DepthFirstSearch(),11);
        //searchableMaze.print();
        solveProblem(searchableMaze, new BestFirstSearch(),12);
        //searchableMaze.print();
        System.out.println("\n");

    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm
            searcher, int num) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated:%s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
                //Printing Solution Path
                System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        /////////////////////////
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
