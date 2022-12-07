package io.github.thewebcode.tsystem;


public class TestClass {
    public String getText() {
        TBungeeSystem.getInstance().getLogger().info("Worked finally!");
        return "Hallo, es hat funktioniert!";
    }
}
