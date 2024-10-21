package com.keik23.router;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.keik23.http.Request;

public class Router {
    private Map<String, Consumer<Request>> routes = new HashMap<>();

    public void addRoutes(String path, Consumer<Request> handler) {
        routes.put(path, handler);
    }

    public void requestHandler(Request request, OutputStream output) throws IOException {
        var handler = routes.get(request.getPath());
        if (handler != null) {
            handler.accept(request);

            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/plain\r\n" +
                    "Content-Length: 13\r\n" +
                    "\r\n" +
                    "Hello, world!";
            output.write(httpResponse.getBytes());
        } else {
            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/plain\r\n" +
                    "Content-Length: 13\r\n" +
                    "\r\n" +
                    "Not found!";
            output.write(httpResponse.getBytes());
        }
    }
}
