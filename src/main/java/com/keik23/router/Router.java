package com.keik23.router;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.keik23.http.Request;

public class Router {
    private Map<String, Map<String, BiConsumer<Request, OutputStream>>> routes = new HashMap<>();

    public void addRoute(String method, String path, BiConsumer<Request, OutputStream> handler) {
        routes.computeIfAbsent(method, k -> new HashMap<>()).put(path, handler);
    }

    public void requestHandler(Request request, OutputStream output) throws IOException {
        var methodRoutes = routes.get(request.getMethod());

        if (methodRoutes != null) {
            var handler = methodRoutes.get(request.getPath());
            if (handler != null) {
                handler.accept(request, output);
                return;
            }
        }
        String httpResponse = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/plain\r\n" +
                "Content-Length: 13\r\n" +
                "\r\n" +
                "Not found!";
        output.write(httpResponse.getBytes());
    }
}
