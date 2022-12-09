package io.github.thewebcode.testpapermodule;

import io.github.thewebcode.tsystem.api.utils.PaperFileManager;
import io.github.thewebcode.tsystem.module.AbstractModule;
import io.github.thewebcode.tsystem.server.LocalServer;

public class Module extends AbstractModule {
    public Module() {
        super("testpapermodule", "1.0.0", "TestPaperModule", "TheWebcode");

        PaperFileManager fileManager = new PaperFileManager(this);
        int port = fileManager.getConfiguration().getInt("localserver.port");
        LocalServer localServer = new LocalServer(port);
        localServer.start();
        localServer.register("io.github.thewebcode.testpapermodule.TestClass");
    }


}
