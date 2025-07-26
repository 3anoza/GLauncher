package com.grimmorium.glauncher.client.entities;

public class HttpHeaderTuple {
    public final String name;
    public final String value;

    public HttpHeaderTuple(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static HttpHeaderTuple contentType =  new HttpHeaderTuple("Content-Type", "application/json; charset=utf-8");
}
