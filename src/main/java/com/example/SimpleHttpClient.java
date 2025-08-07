package com.example;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class SimpleHttpClient {

    private final HttpClient httpClient;

    public SimpleHttpClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public HttpResponse send(HttpRequest request) {
        try {
            var httpRequest = java.net.http.HttpRequest.newBuilder()
                    .uri(request.getUri())
                    .GET()
                    .build();

            var httpResponse = httpClient.send(httpRequest, BodyHandlers.ofString());

            return new HttpResponse() {
                @Override
                public int getStatusCode() {
                    return httpResponse.statusCode();
                }

                @Override
                public String getBody() {
                    return httpResponse.body();
                }
            };
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao enviar requisição: " + e.getMessage(), e);
        }
    }
}
