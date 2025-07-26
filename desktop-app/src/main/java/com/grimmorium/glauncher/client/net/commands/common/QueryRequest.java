package com.grimmorium.glauncher.client.net.commands.common;

import net.bytebuddy.utility.nullability.MaybeNull;


/**
 *     Represents a base request for querying data.
 */
public class QueryRequest {

    /**
     *    Property to specify the order by field.
     */
    @MaybeNull
    public String orderBy;

    /**
     *    Property to indicate if the order should be reversed.
     */
    public boolean reverse;

    /**
     *    Property to specify the number of items to skip.
     */
    @MaybeNull
    public Integer skip;

    /**
     *    Property to specify the number of items to take.
     */
    @MaybeNull
    public Integer take;
}
