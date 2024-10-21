package com.keik23.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.keik23.http.Request;
import com.keik23.router.Router;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Router router;

    public ClientHandler(Socket clientSocket, Router router) {
        this.clientSocket = clientSocket;
        this.router = router;
    }

    @Override
    public void run() {
        try (InputStream input = clientSocket.getInputStream();
                OutputStream output = clientSocket.getOutputStream()) {

            Request request = new Request(input);

            router.requestHandler(request, output);

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
