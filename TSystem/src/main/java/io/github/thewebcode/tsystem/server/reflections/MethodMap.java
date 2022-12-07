package io.github.thewebcode.tsystem.server.reflections;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;

public class MethodMap implements Serializable {
    private HashMap<String, Method> methodServices;

    public MethodMap() {
        this.methodServices = new HashMap<>();
    }

    public void add(Method m, String serviceID) {
        if (methodServices.containsKey(serviceID)) {
            throw new IllegalStateException("ServiceID already exists");
        }

        methodServices.put(serviceID, m);
    }

    public void add(MethodMap map2) {
        this.methodServices.putAll(map2.getMethodServices());
    }

    public HashMap<String, Method> getMethodServices() {
        return methodServices;
    }

    /*
    public void run(String serviceID, Object source) {
        if(!methodServices.containsKey(serviceID)) return;

        try {
            methodServices.get(serviceID).invoke(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     */

    public void run() {
        methodServices.values().forEach(value -> {
            System.out.println("Method Name: " + value.getName());
        });
    }
}
