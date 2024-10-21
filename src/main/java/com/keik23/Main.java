package com.keik23;

import java.io.IOException;

import com.keik23.http.Server;

public class Main {
    public static void main(String[] args) {
        // Start the server on port 9999
        Server httpServer = new Server(9999);

        httpServer.get("/", (request, output) -> {
            try {
                String responseBody = "This is response from /";
                String httpResponse = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/plain\r\n" +
                        "Content-Length: " + responseBody.length() + "\r\n" +
                        "\r\n" +
                        responseBody;

                output.write(httpResponse.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        httpServer.get("/about", (request, output) -> {
            try {
                String responseBody = "This is response from about";
                String httpResponse = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/plain\r\n" +
                        "Content-Length: " + responseBody.length() + "\r\n" +
                        "\r\n" +
                        responseBody;

                output.write(httpResponse.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        httpServer.start();
    }
}