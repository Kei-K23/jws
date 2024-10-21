package com.keik23.http;

public class Request {
    private String method;
    private String path;
    private String httpVersion;

    public Request(String requestLine) {
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
