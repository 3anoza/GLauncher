package com.grimmorium.glauncher.client.net.commands.authorize;

import com.grimmorium.glauncher.contracts.interfaces.IHttpRequest;

public class LoginRequest implements IHttpRequest<LoginResponse> {
    public String username;
    public String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
