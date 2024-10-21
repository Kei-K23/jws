package com.keik23;

import com.keik23.http.Server;

public class Main {
    public static void main(String[] args) {
        // Start the server on port 9999
        Server httpServer = new Server(9999);
        httpServer.addRoute("/about", request -> System.out.println("Handling" + " " + request.getMethod()));
        httpServer.addRoute("/hello", request -> System.out.println("Handling" + " " + request.getPath()));
        httpServer.start();
    }
}