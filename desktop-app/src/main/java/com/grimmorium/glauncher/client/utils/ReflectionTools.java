package com.grimmorium.glauncher.client.utils;

import com.grimmorium.glauncher.client.interfaces.IHttpRequest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionTools {
    public static <T> Class<T> getTResponseType(IHttpRequest<T> request)
    {
        Class<?> clazz = request.getClass();

        for(Type iface : clazz.getGenericInterfaces())
        {
            if (iface instanceof ParameterizedType parameterizedType)
            {
                if (parameterizedType.getRawType() instanceof Class && IHttpRequest.class.isAssignableFrom((Class<?>) parameterizedType.getRawType()))
                {
                    Type argument = parameterizedType.getActualTypeArguments()[0];
                    if (argument instanceof Class)
                    {
                        return (Class<T>) argument;
                    }
                }
            }
        }
        return null;
    }
}
