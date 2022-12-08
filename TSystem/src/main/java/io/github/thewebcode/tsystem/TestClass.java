package io.github.thewebcode.tsystem;


import io.github.thewebcode.tsystem.server.reflections.ServiceClass;
import io.github.thewebcode.tsystem.server.reflections.ServiceMethod;

@ServiceClass
public class TestClass {
    @ServiceMethod(serviceID = "service-test")
    public String getText() {
        TBungeeSystem.getInstance().getLogger().info("Worked finally!");
        return "Hallo, es hat funktioniert!";
    }
}
