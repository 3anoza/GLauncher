package com.grimmorium.glauncher.client.net.services;

import com.grimmorium.glauncher.client.entities.HttpHeaderTuple;
import com.grimmorium.glauncher.client.entities.SerializedHttpRequest;
import com.grimmorium.glauncher.client.enums.HttpMethod;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

public class HttpRequestBuilder {
    public static HttpRequest requestFrom(SerializedHttpRequest request) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(request.uri));
        builder = setHeaders(builder, request.headers);
        builder = setHttpMethod(builder, request.method, request.body);

        return builder.build();
    }

    private static HttpRequest.Builder setHeaders(HttpRequest.Builder builder, List<HttpHeaderTuple> headers) {
        for (HttpHeaderTuple header : headers) {
            builder = builder.header(header.name, header.value);
        }

        return builder;
    }

    private static HttpRequest.Builder setHttpMethod(HttpRequest.Builder builder, HttpMethod method, String requestBody) {
        return switch (method) {
            case GET ->  builder.GET();
            case PUT ->  builder.PUT(HttpRequest.BodyPublishers.ofString(requestBody));
            case POST ->  builder.POST(HttpRequest.BodyPublishers.ofString(requestBody));
            case DELETE -> builder.DELETE();
        };
    }
}
