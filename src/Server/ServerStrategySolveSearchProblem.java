package Server;

import algorithms.mazeGenerators.*;
import algorithms.search.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    static int FileID = 1;
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            List<String> lines = new ArrayList<>();
            Maze maze = (Maze)fromClient.readObject();//we get from the user the maze he wants to solve
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");//this is the path of the file we want to save at
            for (int i = 1; i < FileID; i++) {
                FileInputStream fi = new FileInputStream(new File(tempDirectoryPath + "/maze"+ i +".txt"));
                ObjectInputStream oi = new ObjectInputStream(fi);
                //read the maze and the solutions
                byte[] bytedLoadedMaze = (byte[]) oi.readObject();
                Solution loadedSolution = (Solution) oi.readObject();
                if(Arrays.equals(bytedLoadedMaze,maze.toByteArray())){
                    toClient.writeObject(loadedSolution);//we send to the client the solution
                    toClient.flush();
                    oi.close();
                    fi.close();
                    return;
                }
                oi.close();
                fi.close();

            }
            SearchableMaze searchableMaze = new SearchableMaze(maze);//we make that a searchable maze
            ISearchingAlgorithm searcher;//we choose with what we want to search
            if(Configurations.getSearchAlgorithm().equals("Depth First Search")){
                searcher = new DepthFirstSearch();
            }
            else if(Configurations.getSearchAlgorithm().equals("Breadth First Search")){
                searcher = new BreadthFirstSearch();
            }
            else{//it equals to Best First Search
                searcher = new BestFirstSearch();
            }
            Server.LOG.info("Solving the maze by algorithm: " + Configurations.getSearchAlgorithm());
            Solution solution = searcher.solve(searchableMaze);//we solve the maze
            //now we save the solution
            FileOutputStream f = new FileOutputStream(new File(tempDirectoryPath + "/maze"+ FileID +".txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            //write the maze and the solutions
            o.writeObject(maze.toByteArray());
            o.writeObject(solution);
            o.close();
            f.close();
            FileID++;
            toClient.writeObject(solution);//we send to the client the solution
            toClient.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
