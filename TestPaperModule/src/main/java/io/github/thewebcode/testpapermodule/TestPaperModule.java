package io.github.thewebcode.testpapermodule;

import io.github.thewebcode.tsystem.api.TAPI;
import io.github.thewebcode.tsystem.server.LocalServer;
import io.github.thewebcode.tsystem.server.ServerRequest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class TestPaperModule extends JavaPlugin implements CommandExecutor {
    private static TestPaperModule instance;

    @Override
    public void onEnable() {
        instance = this;
        Module module = new Module();
        TAPI.get().registerModule(module);
        getCommand("test").setExecutor(this);

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ServerRequest request = new ServerRequest("service-123");
        TAPI.get().sendRequest(request, 2222);
        return true;
    }
}
