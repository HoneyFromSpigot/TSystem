package io.github.thewebcode.testbungeemodule;

import io.github.thewebcode.tsystem.api.utils.BungeeFileManager;
import io.github.thewebcode.tsystem.module.AbstractModule;
import io.github.thewebcode.tsystem.server.LocalServer;

public class Module extends AbstractModule {
    public Module() {
        super("testbungeemodule", "1.0", "TestBungeeModule", "TheWebCode");
    }

    @Override
    public void onEnable() {
        BungeeFileManager bungeeFileManager = new BungeeFileManager(this);
        int port = bungeeFileManager.getConfiguration().getInt("localserver.port");
        LocalServer localServer = new LocalServer(port);
        localServer.start();
        localServer.register("io.github.thewebcode.testbungeemodule.TestClass");
        super.onEnable();
    }
}
