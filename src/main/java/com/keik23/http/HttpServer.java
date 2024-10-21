package com.keik23.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.keik23.handler.ClientHandler;

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
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
