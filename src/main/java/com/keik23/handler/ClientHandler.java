package com.keik23.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (InputStream input = clientSocket.getInputStream();
                OutputStream output = clientSocket.getOutputStream()) {

            // Read http request
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            StringBuilder requestBuilder = new StringBuilder();
            String requestLine = reader.readLine();

            // Read http request
            while (!(line = reader.readLine()).isEmpty()) {
                requestBuilder.append(line).append("\r\n");
            }

            System.out.println(requestBuilder.toString());

            // Send HTTP response
            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/plain\r\n" +
                    "Content-Length: 13\r\n" +
                    "\r\n" +
                    "Hello, world!";

            output.write(httpResponse.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the socket connection
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
