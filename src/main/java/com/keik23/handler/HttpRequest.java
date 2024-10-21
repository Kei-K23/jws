package com.keik23.handler;

public class HttpRequest {
    private String method;
    private String path;
    private String httpVersion;

    public HttpRequest(String requestLine) {
        String[] parts = requestLine.split(" ");
        method = parts[0];
        path = parts[1];
        httpVersion = parts[2];
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHttpVersion() {
        return httpVersion;
    }
}
