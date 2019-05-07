package Server;

//import Server.Strategies.IServerStrategy;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by Aviadjo on 3/2/2017.
 */
public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    //private static final Logger LOG = LogManager.getLogger(); //Log4j2

    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
    }

    public void start() {
        new Thread(() -> {
            runServer();
        }).start();
    }

    private void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            //serverSocket.setSoTimeout(listeningInterval);
            System.out.println(String.format("Server starter at %s!", serverSocket));
            System.out.println(String.format("Server's Strategy: %s", serverStrategy.getClass().getSimpleName()));
            System.out.println("Server is waiting for clients...");
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept(); // blocking call
                    System.out.println(String.format("Client excepted: %s", clientSocket));
                    new Thread(() -> {
                        handleClient(clientSocket);
                        //System.out.println(String.format("Finished handle client: %s", clientSocket));
                    }).start();
                } catch (SocketTimeoutException e) {
                    System.out.println("Socket Timeout - No clients pending!");
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            System.out.println(String.format("Handling client with socket: %s", clientSocket.toString()));
            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public void stop() {
       System.out.println("Stopping server...");
        stop = true;
    }
}
