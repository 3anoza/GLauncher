package com.grimmorium.glauncher.client.net.commands.users;

import com.grimmorium.glauncher.client.interfaces.IHttpRequest;
import net.bytebuddy.utility.nullability.MaybeNull;

public class UpdateUserRequest implements IHttpRequest<UserResponse> {
    public String name;

    @MaybeNull
    public byte[] icon;

    @MaybeNull
    public byte[] skin;
}
