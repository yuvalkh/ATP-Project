package Server;

//import Server.Strategies.IServerStrategy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private int port;
    private int listeningInterval;
    ExecutorService ThreadPool;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    public static final Logger LOG = LogManager.getLogger(); //Log4j2

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
        ThreadPool = Executors.newFixedThreadPool(Configurations.getMaxNumberOfThreadsOnServer());
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningInterval);
            //System.out.println(String.format("Server starter at %s!", serverSocket));
            LOG.info(String.format("Server starter at %s!", serverSocket));
            //System.out.println(String.format("Server's Strategy: %s", serverStrategy.getClass().getSimpleName()));
            LOG.info(String.format("Server's Strategy: %s", serverStrategy.getClass().getSimpleName()));
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept(); // blocking call
                    //System.out.println(String.format("Client excepted: %s", clientSocket));
                    LOG.info(String.format("Client excepted: %s", clientSocket));
                    ThreadPool.execute(
                    new Thread(() -> {
                        handleClient(clientSocket);
                        LOG.info(String.format("Finished handle client: %s", clientSocket));
                        //System.out.println(String.format("Finished handle client: %s", clientSocket));
                    })
                    );
                } catch (SocketTimeoutException e) {
                    //System.out.println("Socket Timeout - No clients pending!");
                }
            }
            serverSocket.close();
            ThreadPool.shutdown();
        } catch (IOException e) {
            LOG.error("IOException");
            //System.out.println("IOException");
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            LOG.info(String.format("Handling client with socket: %s", clientSocket.toString()));
            //System.out.println(String.format("Handling client with socket: %s", clientSocket.toString()));
            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
            LOG.error("IOException");
            //System.out.println("IOException");
        }
    }

    public void stop() {
        LOG.info("Stopping server...");
        //System.out.println("Stopping server...");
        stop = true;
    }
}
