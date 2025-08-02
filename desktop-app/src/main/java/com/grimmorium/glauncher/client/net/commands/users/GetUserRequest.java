package com.grimmorium.glauncher.client.net.commands.users;

import com.grimmorium.glauncher.contracts.interfaces.IHttpRequest;

public class GetUserRequest implements IHttpRequest<UserResponse> {
    public String token;
}
