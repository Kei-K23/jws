package com.keik23;

import com.keik23.http.HttpServer;

public class Main {
    public static void main(String[] args) {
        // Start the server on port 9999
        HttpServer httpServer = new HttpServer(9999);
        httpServer.start();
    }
}