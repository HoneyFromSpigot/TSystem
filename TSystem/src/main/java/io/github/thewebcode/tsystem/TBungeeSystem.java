package io.github.thewebcode.tsystem;

import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.server.LocalServerManager;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public final class TBungeeSystem extends Plugin {
    private static TBungeeSystem instance;
    private TAPI api;
    private LocalServerManager localServerManager;

    @Override
    public void onLoad() {
        instance = this;
        this.api = new TAPI();
        this.localServerManager = new LocalServerManager();
    }

    @Override
    public void onEnable() {
        this.getProxy().getScheduler().schedule(this, () -> {
            localServerManager.receive();
        }, 5, 1, TimeUnit.SECONDS);
    }

    public TAPI getApi() {
        return api;
    }

    public static TBungeeSystem getInstance() {
        return instance;
    }
}
