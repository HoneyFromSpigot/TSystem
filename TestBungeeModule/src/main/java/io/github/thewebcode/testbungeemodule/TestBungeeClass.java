package io.github.thewebcode.testbungeemodule;

import io.github.thewebcode.tsystem.server.reflections.ServiceClass;
import io.github.thewebcode.tsystem.server.reflections.ServiceMethod;

@ServiceClass
public class TestBungeeClass {
    @ServiceMethod(serviceID = "service-123")
    public void test() {
        System.out.println("Hello from Bungee Test Class!");
    }
}
