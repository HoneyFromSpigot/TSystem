package io.github.thewebcode.tsystem;

import io.github.thewebcode.tsystem.api.TAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class TPaperSystem extends JavaPlugin {
    private static TPaperSystem instance;
    private TAPI api;

    @Override
    public void onLoad() {
        instance = this;
        this.api = new TAPI();
        System.out.println("Loaded!");
    }

    public TAPI getApi() {
        return api;
    }

    public static TPaperSystem getInstance() {
        return instance;
    }
}
