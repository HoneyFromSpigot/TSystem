package io.github.thewebcode.testpapermodule.test;

import io.github.thewebcode.tsystem.server.reflections.ServiceClass;
import io.github.thewebcode.tsystem.server.reflections.ServiceMethod;

@ServiceClass
public class TestClass {

    @ServiceMethod(serviceID = "hello")
    public void hello() {
        System.out.println("Test Method says hello!");
    }
}
