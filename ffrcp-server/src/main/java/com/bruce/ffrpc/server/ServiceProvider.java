package com.bruce.ffrpc.server;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author bruce.ge
 * @Date 2017/8/7
 * @Description
 */
public class ServiceProvider {


    private static Map<Class, Object> serviceObjects = new HashMap<>();

    /**
     * @param t
     * @param <T>
     * @return get the serviceObject for the service.
     */
    public static <T> T getService(Class<T> t) {
        return (T) serviceObjects.get(t);
    }

    public static void addService(Class a, Object b) {
        serviceObjects.put(a, b);
    }
}
