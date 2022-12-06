package io.github.thewebcode.tsystem;

import io.github.thewebcode.tsystem.TBungeeSystem;

import java.util.logging.Logger;

public class TestClass {
    public String getText() {
        TBungeeSystem.getInstance().getLogger().info("Worked finally!");
        return "Hallo, es hat funktioniert!";
    }
}
