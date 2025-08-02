package com.grimmorium.glauncher.client.net.commands.users;

import com.grimmorium.glauncher.contracts.entities.AuthToken;
import com.grimmorium.glauncher.contracts.interfaces.IHttpRequest;
import net.bytebuddy.utility.nullability.MaybeNull;

public class UpdateUserRequest implements IHttpRequest<UserResponse> {
    public AuthToken authToken;

    @MaybeNull
    public byte[] avatar;

    @MaybeNull
    public byte[] skin;

    @MaybeNull
    public byte[] cape;
}
