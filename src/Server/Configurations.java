package Server;

import java.io.*;
import java.util.Properties;

/**
 * this class need to return
 * which generateMaze algorithm to use
 * which search algorithm to use
 * how many threads can the server handle at a time
 */
public class Configurations {

    static Properties prop = new Properties();
    private Configurations(){//private constructor in order that this class will be static

    }

    public static String getGenerateMazeAlgorithm(){
        loadPropertiesFile();
        return prop.getProperty("GenerateMaze.Algorithm");
    }

    public static String getSearchAlgorithm(){
        loadPropertiesFile();
        return prop.getProperty("Search.Algorithm");
    }

    public static int getMaxNumberOfThreadsOnServer(){
        loadPropertiesFile();
        return Integer.parseInt(prop.getProperty("Max.Number.Of.Threads"));
    }

    private static void loadPropertiesFile(){
        File file = new File("resources/config.properties");
        InputStream inputStream = null;
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
