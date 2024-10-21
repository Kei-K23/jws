package com.keik23.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);

            // Listen the user incoming request
            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Handle client socket
                new Thread().start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
