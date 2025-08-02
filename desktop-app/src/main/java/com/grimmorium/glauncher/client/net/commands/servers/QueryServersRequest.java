package com.grimmorium.glauncher.client.net.commands.servers;

import com.grimmorium.glauncher.contracts.interfaces.IHttpRequest;
import com.grimmorium.glauncher.contracts.interfaces.IPaginatedList;
import com.grimmorium.glauncher.core.net.commands.common.QueryRequest;

public class QueryServersRequest extends QueryRequest implements IHttpRequest<IPaginatedList<ServerResponse>> {

    /**
     *     Property, for specifying a filter by protocol version
     */
    public int protocolVersion;
}
