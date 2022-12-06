package io.github.thewebcode.tsystem;

import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.server.LocalPaperReceiver;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TPaperSystem extends JavaPlugin {
    private static TPaperSystem instance;
    private LocalPaperReceiver localPaperReceiver;
    private TAPI api;

    @Override
    public void onLoad() {
        instance = this;
        this.api = new TAPI();
    }

    @Override
    public void onEnable() {
        this.localPaperReceiver = new LocalPaperReceiver();
        new BukkitRunnable(){
            @Override
            public void run() {
                localPaperReceiver.receive();
            }
        }.runTaskTimer(this, 5 * 20, 20);

    }

    public TAPI getApi() {
        return api;
    }

    public static TPaperSystem getInstance() {
        return instance;
    }
}
