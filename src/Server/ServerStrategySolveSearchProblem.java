package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;
import java.util.ArrayList;

public class ServerStrategySolveSearchProblem implements IServerStrategy {

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze)fromClient.readObject();//we get from the user the maze he wants to solve
            SearchableMaze searchableMaze = new SearchableMaze(maze);//we make that a searchable maze
            ISearchingAlgorithm searcher = new BestFirstSearch();//we choose with what we want to search
            Solution solution = searcher.solve(searchableMaze);//we solve the maze
            toClient.writeObject(solution);//we send to the client the solution
            toClient.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
