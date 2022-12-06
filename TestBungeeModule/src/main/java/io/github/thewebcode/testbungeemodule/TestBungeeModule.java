package io.github.thewebcode.testbungeemodule;

import io.github.thewebcode.tsystem.api.TAPI;
import net.md_5.bungee.api.plugin.Plugin;

public final class TestBungeeModule extends Plugin {
    private static TestBungeeModule instance;

    @Override
    public void onEnable() {
        instance = this;
        TAPI.register(new Module());
    }
}
