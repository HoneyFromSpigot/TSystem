package io.github.thewebcode.tsystem.server.reflections;

import io.github.thewebcode.tsystem.api.TAPI;

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

    public void run(String serviceID) {
        if(!methodServices.containsKey(serviceID)) {
            System.out.println("Service not found: " + serviceID);
            return;
        }

        try {
            System.out.println("Running service: " + serviceID);
            Method method = methodServices.get(serviceID);
            Class<?> declaringClass = method.getDeclaringClass();

            Object obj = TAPI.get().getSourceObject(declaringClass);

            if(obj == null) obj = declaringClass.newInstance();

            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean containsService(String serviceID) {
        return methodServices.containsKey(serviceID);
    }
}
