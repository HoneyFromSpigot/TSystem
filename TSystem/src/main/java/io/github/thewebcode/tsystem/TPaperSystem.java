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
        this.api = new TAPI();
    }

    @Override
    public void onEnable() {
        System.out.println("Registering channel");
    }

    public TAPI getApi() {
        return api;
    }

    public static TPaperSystem getInstance() {
        return instance;
    }
}
