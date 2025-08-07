package com.example;

import java.net.URI;

public class HttpRequest {
    private final URI uri;

    public HttpRequest(String url) {
        this.uri = URI.create(url);
    }

    public URI getUri() {
        return uri;
    }
}
