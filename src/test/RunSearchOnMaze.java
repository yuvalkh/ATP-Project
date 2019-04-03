package test;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;
import java.util.ArrayList;
public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(50, 50);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        //searchableMaze.print();
        solveProblem(searchableMaze, new BreadthFirstSearch(),6);
        //searchableMaze.print();
        solveProblem(searchableMaze, new DepthFirstSearch(),7);
        //searchableMaze.print();
        solveProblem(searchableMaze, new BestFirstSearch(),8);
        //searchableMaze.print();

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
            ((SearchableMaze)domain).MazeInfo[((MazeState)solutionPath.get(i)).getCurrentPosition().getRowIndex()][((MazeState)solutionPath.get(i)).getCurrentPosition().getColumnIndex()] = num;
        }
        ((SearchableMaze)domain).print();
        for (int i = 0; i < solutionPath.size(); i++) {
            ((SearchableMaze)domain).MazeInfo[((MazeState)solutionPath.get(i)).getCurrentPosition().getRowIndex()][((MazeState)solutionPath.get(i)).getCurrentPosition().getColumnIndex()] = 0;
        }
        //////////////////////////
        for (int i = 0; i < solutionPath.size(); i++) {
         //   System.out.println(String.format("%s.  %s",i,solutionPath.get(i)));
        }
    }
}
