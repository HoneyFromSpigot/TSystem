package io.github.thewebcode.testbungeemodule;

import io.github.thewebcode.tsystem.module.AbstractModule;

public class Module extends AbstractModule {
    public Module() {
        super("testbungeemodule", "1.0", "TestBungeeModule", "TheWebCode");
    }

    @Override
    public void onEnable() {
        instance = this;
        super.onEnable();
    }
}
