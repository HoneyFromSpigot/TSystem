package io.github.thewebcode.testpapermodule;

import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.server.ServerRequest;
import io.github.thewebcode.tsystem.server.ServerResponse;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;


public final class TestPaperModule extends JavaPlugin implements CommandExecutor {
    private Module module;

    @Override
    public void onEnable() {
        this.module = new Module();
        TAPI.get().registerModule(module);
        getCommand("test").setExecutor(this);
    }

    @Override
    public void onDisable() {
        TAPI.get().unregisterModule(module);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ServerRequest request = new ServerRequest("service-123");
        request.setReturningPort(2222);
        TAPI.get().sendRequest(request, 2222);

        new BukkitRunnable(){
            @Override
            public void run() {
                ServerResponse response = module.getLocalServer().getResponse();
                if(response != null){
                    String text = (String) response.getObject();
                    System.out.println("Text from TestClass is:" + text);
                }
            }
        }.runTaskLater(this, 1);
        return true;
    }
}
