package com.design.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ObjectRegistry {
    private static Map<String, Object> objectsregistry = new HashMap<>();

    public static Object getObjectsregistry(String name) {
        return objectsregistry.get(name);
    }

    public static void setObjectsregistry(String name, Object object) {
        objectsregistry.put(name, object);
    }
}
