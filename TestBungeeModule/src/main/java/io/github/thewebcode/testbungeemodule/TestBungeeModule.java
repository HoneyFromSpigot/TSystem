package io.github.thewebcode.testbungeemodule;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.server.pluginmessaging.BungeeMessageListener;
import net.md_5.bungee.api.plugin.Plugin;

public final class TestBungeeModule extends Plugin {
    private static TestBungeeModule instance;

    @Override
    public void onEnable() {
        instance = this;
        TAPI.register(new Module());
    }
}
