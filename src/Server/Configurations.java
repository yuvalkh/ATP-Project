package Server;

import com.sun.org.apache.xerces.internal.parsers.IntegratedParserConfiguration;

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
        return prop.getProperty("GenerateMaze Algorithm");
    }

    public static String getSearchAlgorithm(){
        return prop.getProperty("Search Algorithm");
    }

    public static int getMaxNumberOfThreadsOnServer(){
        return Integer.parseInt(prop.getProperty("Max Number Of Threads"));
    }
}
