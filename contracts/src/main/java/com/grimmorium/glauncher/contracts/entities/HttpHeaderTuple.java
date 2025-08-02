package com.grimmorium.glauncher.contracts.entities;

public class HttpHeaderTuple {
    public final String name;
    public final String value;

    public HttpHeaderTuple(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static HttpHeaderTuple contentType =  new HttpHeaderTuple("Content-Type", "application/json; charset=utf-8");

    public static HttpHeaderTuple getBearerAuth(String accessToken) {
        return new HttpHeaderTuple("Authorization", "Bearer " + accessToken);
    }
}
