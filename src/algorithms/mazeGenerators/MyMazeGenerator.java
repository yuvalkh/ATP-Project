package algorithms.mazeGenerators;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {
    private LinkedList <Position> neighbors = new LinkedList<>();

    /**
     *
     * @param rows - number of rows in the maze
     * @param columns -number of columns in the maze
     * @return a generated Maze by Prim's Algorithm.
     */
    @Override
    public Maze generate(int rows, int columns) {
        //make a maze by Prim/Kruskal/DFS/Recursive algorithm
        Maze MyMaze = new Maze(rows,columns);
        MakeNet(MyMaze);
        MyMaze.GenerateStartAndEndPoints();

        //add all the neighbor cells of the GoalPoint
        addNeighbors(neighbors,MyMaze.getGoalPosition(),MyMaze);
        MyMaze.setMazeInfo(MyMaze.getGoalPosition().getRowIndex(),MyMaze.getGoalPosition().getColumnIndex(),8);
        /*_^_^_^_^_^_^_^_^_^_^_^_^ Works Until Here(CHECKED) ^_^_^_^_^_^_^_^_^_^_^_^_^_^_*/

        Random random = new Random();
        while(!neighbors.isEmpty())
        {
            int nextNeighbor = random.nextInt(neighbors.size());
            Position temp = neighbors.get(nextNeighbor);// choose neighbor randomly
            neighbors.remove(nextNeighbor); // remove chosen neighbor from the list
            if(MyMaze.getMazeInfo(temp.getRowIndex(),temp.getColumnIndex())!= 8)
            {
                MyMaze.setMazeInfo(temp.getRowIndex(),temp.getColumnIndex(),8);// visited
                breakWall(temp,MyMaze);//breakWalls
                addNeighbors(neighbors,temp,MyMaze);
            }


        }
        return MyMaze;
    }
    //private boolean DFSMaze()

    /**
     *
     * @param MyMaze - makes MyMaze's MazeInfo a Net which means every cell has 4 blocks surrounding it.
     *               i necessary condition in order to use Prim's Algorithm
     */
    private void MakeNet(Maze MyMaze) { //Make a Net
        Random random = new Random();
        int randomWallIndex1 = random.nextInt(2);
        int randomWallIndex2 = random.nextInt(2);

        for (int row = 0; row < MyMaze.getNumOfRows(); row++) {
            for(int column = 0; column < MyMaze.getNumOfColumns();column++) {
                if(row%2 == randomWallIndex1 || column%2 == randomWallIndex2){
                    MyMaze.setMazeInfo(row,column,1);

                }
                else{
                    MyMaze.setMazeInfo(row,column,0);
                }
            }
        }
    }
    //sign GoalPoint as part of the maze, info[i][j] = 8

    /**
     *
     * @param neighbors - list of Positions (Cells)
     * @param temp - a Cell. We will need to find it's neighbors
     * @param MyMaze - Maze.
     */
    private void addNeighbors(List<Position> neighbors, Position temp ,Maze MyMaze){
        if(temp.getColumnIndex() < MyMaze.getNumOfColumns() && temp.getRowIndex()<MyMaze.getNumOfRows())
        {
            int tempRow = temp.getRowIndex();
            int tempColumn = temp.getColumnIndex();
            if(tempRow + 2 < MyMaze.getNumOfRows())
            {
                if(MyMaze.getMazeInfo(tempRow+2,tempColumn) != 8) {
                    neighbors.add(new Position(tempRow + 2,tempColumn ));
                }
            }
            if(tempRow - 2 >=0) {
                if(MyMaze.getMazeInfo(tempRow-2,tempColumn) != 8){
                    neighbors.add(new Position(tempRow - 2, tempColumn));
                }
            }
            if(tempColumn + 2 < MyMaze.getNumOfColumns())
            {
                if(MyMaze.getMazeInfo(tempRow,tempColumn +2) != 8){
                    neighbors.add(new Position(tempRow,tempColumn + 2 ));
                }
            }
            if(tempColumn - 2 >= 0)
            {
                if(MyMaze.getMazeInfo(tempRow,tempColumn -2) != 8){
                    neighbors.add(new Position(tempRow,tempColumn - 2 ));
                }
            }
        }
    }
    private void breakWall(Position a , Maze MyMaze) //break random wall of neighbors
    {
        int ARow=a.getRowIndex();
        int ACol=a.getColumnIndex();
        boolean broke = false;
        //boolean Anotherbroke = false;
        Random randomBreake = new Random();
        int AnotherBreak = randomBreake.nextInt(30);
        while(!broke)
        {
            int random = randomBreake.nextInt(4);
            if(random == 0 || AnotherBreak == 0)
            {
                if(ACol -2 >=0)
                {
                   // if(!broke )
                    //{
                        if( MyMaze.getMazeInfo(ARow,ACol-2) == 8 )
                        {
                            MyMaze.setMazeInfo(ARow,ACol-1, 8);
                            broke = true;
                        }
                    //}
                }
            }
            if(random == 1 || AnotherBreak == 1)
            {
                //if(!broke)
               // {
                    if(ACol +2 < MyMaze.getNumOfColumns())
                    {
                        if( MyMaze.getMazeInfo(ARow,ACol+2) == 8 )
                        {
                            MyMaze.setMazeInfo(ARow,ACol+1, 8);
                            broke = true;
                        }
                    }
                //}
            }
            if(random == 2 || AnotherBreak == 2)
            {
               // if(!broke)
                //{
                    if(ARow - 2 >= 0)
                    {
                        if( MyMaze.getMazeInfo(ARow-2,ACol) == 8 )
                        {
                            MyMaze.setMazeInfo(ARow-1,ACol, 8);
                            broke = true;
                        }
                    }
                //}
            }
            if(random == 3 || AnotherBreak == 3)
            {
               // if(!broke)
                //{
                    if(ARow + 2 < MyMaze.getNumOfRows())
                    {
                        if( MyMaze.getMazeInfo(ARow+2,ACol) == 8 )
                        {
                            MyMaze.setMazeInfo(ARow+1,ACol, 8);
                            broke = true;
                        }
                    }
                //}
            }
        }

    }

   /* private boolean visited (Position temp , List <Position> Visited){
       for (int i =0 ; i<Visited.size();i++)
       {
           if(Visited.get(i).getColumnIndex()== temp.getColumnIndex() && Visited.get(i).getRowIndex()== temp.getRowIndex())
           {
               return true;
           }
       }
       return false;
    }*/
}
