package Server;

import algorithms.mazeGenerators.*;
import  IO.MyCompressorOutputStream;
import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectOutputStream ToClient = new ObjectOutputStream(outToClient);
            ToClient.flush();
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            //place 0 is numOfRows,place 1 is numOfColumns
            int[] properties = (int[])fromClient.readObject();//we get from the user an array(suppose to be with length 2)
            IMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(properties[0], properties[1]);//now we make the maze
            MyCompressorOutputStream comp = new MyCompressorOutputStream(ToClient);
            comp.write(maze.toByteArray());//now we compress it and send it to the client
            ToClient.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
