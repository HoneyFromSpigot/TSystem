package io.github.thewebcode.testpapermodule;

import io.github.thewebcode.tsystem.api.utils.PaperFileManager;
import io.github.thewebcode.tsystem.module.AbstractModule;
import io.github.thewebcode.tsystem.server.LocalServer;

public class Module extends AbstractModule {
    private LocalServer localServer;
    public Module() {
        super("testpapermodule", "1.0.0", "TestPaperModule", "TheWebcode");

        PaperFileManager fileManager = new PaperFileManager(this);
        int port = fileManager.getConfiguration().getInt("localserver.port");
        this.localServer = new LocalServer(port);
        localServer.start();
        localServer.register("io.github.thewebcode.testpapermodule.TestClass");
    }

    @Override
    public void onDisable() {
        System.out.println("Closing Server Socket...");
        localServer.stopServer();
        super.onDisable();
    }

    public LocalServer getLocalServer() {
        return localServer;
    }
}
