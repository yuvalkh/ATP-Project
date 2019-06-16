package Server;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

/**
 * this class need to return
 * which generateMaze algorithm to use
 * which search algorithm to use
 * how many threads can the server handle at a time
 */
public class Configurations {

    private static Properties prop;
    private static String path;
    private Configurations(){//private constructor in order that this class will be static

    }

    public static String getGenerateMazeAlgorithm(){
        //loadPropertiesFile();
        return prop.getProperty("GenerateMaze.Algorithm");
    }

    public static String getSearchAlgorithm(){
        //loadPropertiesFile();
        return prop.getProperty("Search.Algorithm");
    }

    public static int getMaxNumberOfThreadsOnServer(){
        //loadPropertiesFile();
        return Integer.parseInt(prop.getProperty("Max.Number.Of.Threads"));
    }

    public static void setGenerateMazeAlgorithm(String GenerateMazeAlgorithm){
        //loadPropertiesFile();
        prop.setProperty("GenerateMaze.Algorithm",GenerateMazeAlgorithm);
    }

    public static void setSearchAlgorithm(String SearchAlgorithm){
        //loadPropertiesFile();
        prop.setProperty("Search.Algorithm",SearchAlgorithm);
    }

    public static void setMaxNumberOfThreadsOnServer(String MaxNumberOfThreadsOnServer){
        //loadPropertiesFile();
        prop.setProperty("Max.Number.Of.Threads",MaxNumberOfThreadsOnServer);
    }

    public static void loadFilePath(String Path){
        path = Path;
        loadPropertiesFile();
    }

    private static void loadPropertiesFile(){
        if(prop == null) {//we need to load it
            prop = new Properties();
            File file = new File(path);
            InputStream inputStream;
            try {
                inputStream = new FileInputStream(file);
                prop.load(inputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
