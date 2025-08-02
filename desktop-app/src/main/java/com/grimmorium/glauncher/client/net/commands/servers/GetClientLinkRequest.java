package com.grimmorium.glauncher.client.net.commands.servers;

import com.grimmorium.glauncher.contracts.interfaces.IHttpRequest;

public class GetClientLinkRequest implements IHttpRequest<ClientLinkResponse> {
    public String name;
}
