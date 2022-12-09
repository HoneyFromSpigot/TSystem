package io.github.thewebcode.testpapermodule;

import io.github.thewebcode.tsystem.server.ServiceMethod;

public class TestClass {
    @ServiceMethod(serviceID = "service-123")
    public void sayHello(){
        System.out.println("Hello World!");
    }
}
