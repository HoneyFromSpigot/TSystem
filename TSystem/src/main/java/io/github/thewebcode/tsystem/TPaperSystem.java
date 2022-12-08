package io.github.thewebcode.tsystem;

import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.server.pluginmessaging.MessageListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

public class TPaperSystem extends JavaPlugin {
    private static TPaperSystem instance;
    private TAPI api;

    @Override
    public void onLoad() {
        instance = this;
        this.api = new TAPI(true);
    }

    @Override
    public void onEnable() {
        getServer().getMessenger().registerIncomingPluginChannel(this, "tsystem:main", new MessageListener());
    }

    public TAPI getApi() {
        return api;
    }

    public static TPaperSystem getInstance() {
        return instance;
    }
}
