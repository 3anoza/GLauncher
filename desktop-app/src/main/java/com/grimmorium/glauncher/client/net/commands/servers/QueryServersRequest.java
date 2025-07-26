package com.grimmorium.glauncher.client.net.commands.servers;

import com.grimmorium.glauncher.client.interfaces.IHttpRequest;
import com.grimmorium.glauncher.client.interfaces.IPaginatedList;
import com.grimmorium.glauncher.client.net.commands.common.QueryRequest;

public class QueryServersRequest extends QueryRequest implements IHttpRequest<IPaginatedList<ServerResponse>> {

    /**
     *     Property, for specifying a filter by protocol version
     */
    public int protocolVersion;
}
