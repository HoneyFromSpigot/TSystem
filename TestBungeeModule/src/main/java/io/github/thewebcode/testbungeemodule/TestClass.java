package io.github.thewebcode.testbungeemodule;

import io.github.thewebcode.tsystem.server.ServiceMethod;

public class TestClass {
    @ServiceMethod(serviceID = "service-1234")
    public String foo() {
        System.out.println("It works!");
        return "Test is working!";
    }
}
