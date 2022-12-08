package io.github.thewebcode.tsystem;

import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.server.pluginmessaging.BungeeMessageListener;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public final class TBungeeSystem extends Plugin implements Listener {
    private static TBungeeSystem instance;
    private TAPI api;

    @Override
    public void onLoad() {
        instance = this;
        this.api = new TAPI(false);
    }

    @Override
    public void onEnable() {
        getProxy().registerChannel("tsystem:main");
        getProxy().getPluginManager().registerListener(this, new BungeeMessageListener());
    }

    public TAPI getApi() {
        return api;
    }

    public static TBungeeSystem getInstance() {
        return instance;
    }
}
