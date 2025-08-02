package com.grimmorium.glauncher.contracts.entities;

import com.grimmorium.glauncher.contracts.enums.HttpMethod;

import java.util.ArrayList;
import java.util.List;

public class SerializedHttpRequest<TResponse> {
    public final String uri;
    public final List<HttpHeaderTuple> headers;
    public final HttpMethod method;
    public final String body;

    public SerializedHttpRequest(String uri, String requestBody) {
        this(uri, HttpHeaderTuple.contentType, HttpMethod.GET, requestBody);
    }

    public SerializedHttpRequest(String uri, HttpMethod method, String requestBody) {
        this(uri, HttpHeaderTuple.contentType, method, requestBody);
    }

    public SerializedHttpRequest(String uri, HttpHeaderTuple headers, HttpMethod method, String requestBody) {
        this.uri = uri;
        this.headers = new ArrayList<>();
        this.headers.add(headers);
        this.method = method;
        this.body = requestBody;
    }

    public SerializedHttpRequest(String uri, List<HttpHeaderTuple> headers, HttpMethod method, String requestBody) {
        this.uri = uri;
        this.headers = headers;
        this.method = method;
        this.body = requestBody;
    }
}
