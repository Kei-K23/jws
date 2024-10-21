package com.keik23.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

import com.keik23.handler.ClientHandler;
import com.keik23.router.Router;

public class Server {
    private int port;
    private Router router = new Router();

    public Server(int port) {
        this.port = port;
    }

    public void addRoute(String path, Consumer<Request> handler) {
        router.addRoutes(path, handler);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);

            // Listen the user incoming request
            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Handle client socket
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
