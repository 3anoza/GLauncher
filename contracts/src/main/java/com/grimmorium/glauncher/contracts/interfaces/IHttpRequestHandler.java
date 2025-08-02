package com.grimmorium.glauncher.contracts.interfaces;

public interface IHttpRequestHandler<TRequest extends IHttpRequest<TResponse>, TResponse> {
    TResponse handleRequest(TRequest request);
}
