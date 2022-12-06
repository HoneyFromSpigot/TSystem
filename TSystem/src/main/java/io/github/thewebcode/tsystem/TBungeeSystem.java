package io.github.thewebcode.tsystem;

import io.github.thewebcode.tsystem.api.TAPI;
import net.md_5.bungee.api.plugin.Plugin;

public final class TBungeeSystem extends Plugin {
    private static TBungeeSystem instance;
    private TAPI api;

    @Override
    public void onLoad() {
        instance = this;
        this.api = new TAPI();
    }

    @Override
    public void onEnable() {
    }

    public TAPI getApi() {
        return api;
    }

    public static TBungeeSystem getInstance() {
        return instance;
    }
}
