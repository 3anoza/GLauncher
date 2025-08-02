package com.grimmorium.glauncher.core.net.services;

import com.grimmorium.glauncher.contracts.enums.HttpMethod;
import com.grimmorium.glauncher.contracts.interfaces.IHttpRequest;
import com.grimmorium.glauncher.contracts.entities.HttpHeaderTuple;
import com.grimmorium.glauncher.contracts.entities.SerializedHttpRequest;
import com.grimmorium.glauncher.core.utils.ReflectionTools;
import com.grimmorium.glauncher.core.utils.Serializer;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class HttpLauncherClient {
    private static HttpLauncherClient instance;

    private final HttpClient httpClient;

    HttpLauncherClient(){
        httpClient = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .executor(Executors.newFixedThreadPool(20))
                .build();
    }

    public <T> CompletableFuture<T> sendRequestAsync(String uri, HttpMethod method, IHttpRequest<T> request){
        Class<T> responseType = ReflectionTools.getTResponseType(request);
        SerializedHttpRequest serializedRequest = new SerializedHttpRequest(uri, method,
                Serializer.getSerializer().Serialize(request));


        return httpClient
                .sendAsync(HttpRequestBuilder.requestFrom(serializedRequest), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> (T) Serializer.getSerializer().Deserialize(body, responseType));
    }

    public <T> CompletableFuture<T> sendRequestAsync(String uri, HttpMethod method,
                                              List<HttpHeaderTuple> headers, IHttpRequest<T> request){
        Class<?> responseType = ReflectionTools.getTResponseType(request);
        SerializedHttpRequest serializedRequest = new SerializedHttpRequest(uri, headers, method,
                Serializer.getSerializer().Serialize(request));

        return httpClient
                .sendAsync(HttpRequestBuilder.requestFrom(serializedRequest), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> (T) Serializer.getSerializer().Deserialize(body, responseType));
    }

    public <T> CompletableFuture<T> sendGetAsync(String uri,IHttpRequest<T> request){
        Class<T> responseType = ReflectionTools.getTResponseType(request);
        SerializedHttpRequest serializedRequest = new SerializedHttpRequest(uri,
                Serializer.getSerializer().Serialize(request));

        return httpClient
                .sendAsync(HttpRequestBuilder.requestFrom(serializedRequest), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> (T) Serializer.getSerializer().Deserialize(body, responseType));
    }

    public static HttpLauncherClient getInstance(){
        if (instance == null){
            instance = new HttpLauncherClient();
        }

        return instance;
    }
}
