package com.keik23.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.BiConsumer;

import com.keik23.handler.ClientHandler;
import com.keik23.router.Router;

public class Server {
    private int port;
    private Router router = new Router();

    public Server(int port) {
        this.port = port;
    }

    public void get(String path, BiConsumer<Request, OutputStream> handler) {
        router.addRoute("GET", path, handler);
    }

    public void post(String path, BiConsumer<Request, OutputStream> handler) {
        router.addRoute("POST", path, handler);
    }

    public void put(String path, BiConsumer<Request, OutputStream> handler) {
        router.addRoute("PUT", path, handler);
    }

    public void delete(String path, BiConsumer<Request, OutputStream> handler) {
        router.addRoute("DELETE", path, handler);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);

            // Listen the user incoming request
            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Handle client socket
                new Thread(new ClientHandler(clientSocket, router)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
